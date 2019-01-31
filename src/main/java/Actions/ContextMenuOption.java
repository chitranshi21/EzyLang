package Actions;

import Configuration.ConfigDTO;
import Translator.Config;
import Translator.Translator;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.editor.ex.EditorEx;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.TextEditor;
import com.intellij.openapi.fileEditor.ex.FileEditorManagerEx;
import com.intellij.openapi.fileEditor.impl.EditorWindow;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.testFramework.LightVirtualFile;

import java.util.Arrays;
import java.util.List;

public class ContextMenuOption extends AnAction {

    public ContextMenuOption() {
        // Set the menu item name.
        super("Context _Menu _Option");
    }
    @Override
    public void actionPerformed(AnActionEvent event) {
        Project project = event.getProject();

        final EditorEx editor = (EditorEx) event.getData(CommonDataKeys.EDITOR);
        final Document document = editor.getDocument();
        final SelectionModel selectionModel = editor.getSelectionModel();

         Translator translator = new Translator();
        List<String> results;

        ConfigDTO configDTO = ConfigDTO.getInstance();
        if (editor.getSelectionModel().hasSelection()) {
            String selectedText = selectionModel.getSelectedText();

            results = translator.getTranslations(configDTO, Arrays.asList(selectedText));
        } else {
            results = translator.getTranslations(configDTO, Arrays.asList(document.getText()));
        }
        FileEditorManagerEx fileEditorManagerEx = FileEditorManagerEx.getInstanceEx(project);
        EditorWindow currentWindow = fileEditorManagerEx.getCurrentWindow();

        VirtualFile origFile = currentWindow.getSelectedFile();
        VirtualFile virtualFile = new LightVirtualFile(origFile.getName(), origFile.getFileType(), results.get(0));
        if (currentWindow.findSiblings().length == 0) {
            currentWindow.split(1, true, virtualFile, false);
        }
        WriteCommandAction.runWriteCommandAction(project, () -> {
            CodeStyleManager styleManager = CodeStyleManager.getInstance(project);
            PsiElement psiFile = PsiManager.getInstance(project).findFile(virtualFile);
            styleManager.reformat(psiFile);
        });
        virtualFile.refresh(false, false);

        EditorWindow newWindow = currentWindow.findSiblings()[0];
        FileEditor fileEditor = newWindow.getEditors()[0].getEditors()[0];
        final Editor ed = ((TextEditor)fileEditor).getEditor();
        ed.getDocument().setReadOnly(true);  // TODO - not working

    }
}
