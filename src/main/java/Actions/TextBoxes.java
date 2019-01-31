package Actions;

import Translator.Config;
import Translator.Translator;
import com.intellij.injected.editor.DocumentWindow;
import com.intellij.injected.editor.DocumentWindowImpl;
import com.intellij.injected.editor.EditorWindowImpl;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.editor.ex.EditorEx;
import com.intellij.openapi.editor.impl.EditorImpl;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.TextEditorWithPreview;
import com.intellij.openapi.fileEditor.ex.FileEditorManagerEx;
import com.intellij.openapi.fileEditor.impl.EditorWindow;
import com.intellij.openapi.fileEditor.impl.EditorsSplitters;
import com.intellij.openapi.fileEditor.impl.FileEditorManagerImpl;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.newvfs.impl.VirtualFileImpl;
import com.intellij.psi.PsiFile;
import com.intellij.testFramework.LightVirtualFile;
import com.intellij.uiDesigner.compiler.SplitPaneLayoutCodeGenerator;
import com.intellij.uiDesigner.lw.LwComponent;
import com.intellij.uiDesigner.lw.LwSplitPane;
import com.intellij.uiDesigner.lw.PropertiesProvider;
import javafx.scene.control.SplitPane;
import org.jdom.Element;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class TextBoxes extends AnAction implements DumbAware {

    ArrayList<String> myList = new ArrayList<>();


    // If you register the action from Java code, this constructor is used to set the menu item name
    // (optionally, you can specify the menu description and an icon to display next to the menu item).
    // You can omit this constructor when registering the action in the plugin.xml file.
    public TextBoxes() {
        // Set the menu item name.
        super("Text _Boxes");
        // Set the menu item name, description and icon.
        // super("Text _Boxes","Item description",IconLoader.getIcon("/Mypackage/icon.png"));
    }



    public void actionPerformed(AnActionEvent event) {
        Project project = event.getProject();

        //String txt= Messages.showInputDialog(project, "What is your name?", "Input your name", Messages.getQuestionIcon());

        final Editor editor =  event.getData(CommonDataKeys.EDITOR);
        final Document document = editor.getDocument();
        final SelectionModel selectionModel = editor.getSelectionModel();

        Translator translator = new Translator();
        List<String> results = null;

//        if (editor.getSelectionModel().hasSelection()) {
//            String selectedText = selectionModel.getSelectedText();
//            results = translator.getTranslations(new Config(), Arrays.asList(selectedText));
//
//        } else {
//            results = translator.getTranslations(new Config(), Arrays.asList(document.getText()));
//        }

        FileEditorManagerEx fileEditorManagerEx = FileEditorManagerEx.getInstanceEx(project);

        EditorWindow editorWindow = fileEditorManagerEx.getSplitters().getCurrentWindow();

        File file = new File("./temp.txt");

        if(!file.exists()) {
            try{
                file.createNewFile();

            }catch (IOException e) {
                e.printStackTrace();
                System.out.println("not able to create file");
            }
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(results.get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }

        VirtualFile file3 = LocalFileSystem.getInstance().refreshAndFindFileByIoFile(file);
        VirtualFile file2 = new LightVirtualFile("translations", "my content");


        // condition to check if there are already two splits use the second one
        if (fileEditorManagerEx.getSplitters().getWindows().length > 1) {
            EditorWindow ew = fileEditorManagerEx.getSplitters().getWindows()[1];

        }
        editorWindow.split(1, true, file3, false);

//        fileEditorManagerEx.createSplitter(1, fileEditorManagerEx.getCurrentWindow());

        //Messages.showMessageDialog(project, results.get(0), "Highlighted Result", Messages.getInformationIcon());
        EditorsSplitters editorsSplitters = fileEditorManagerEx.getSplitters();
        System.out.println(editorsSplitters);
        System.out.println(editorsSplitters.getWindows().length);
        EditorWindow  editWindow2 =  editorsSplitters.getWindows()[1];


//        editWindow2.setFilePinned(file2, true);


//        VirtualFile file = fileEditorManagerEx.getCurrentFile();
//        try{
//            file2.setBinaryContent(results.get(0).getBytes());
//            file.setBinaryContent(results.get(0).getBytes());
//            EditorWindow editorWindow = EditorWindowImpl.create(document)
//            fileEditorManagerEx.setCurrentWindow();
//        }catch (IOException exp) {
//            // do nothing
//        }

//        DocumentWindow documentWindow = new DocumentWindowImpl()


//        Editor editorWin = EditorWindowImpl.create(fileEditorManagerEx.get, (EditorImpl) editor, psiFile);
    }
}