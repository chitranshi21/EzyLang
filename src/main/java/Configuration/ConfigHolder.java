package Configuration;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class ConfigHolder implements SearchableConfigurable {
    private ConfigUI configUI;
    private ConfigDTO configDTO;

    public ConfigHolder() {
        configDTO = ConfigDTO.getInstance();
    }

    @NotNull
    @Override
    public String getId() {
        return "preference.EzLang";
    }

    @Nls
    @Override
    public String getDisplayName() {
        return "EzLang Plugin2";
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        configUI = new ConfigUI();
        configUI.createUIComponents(configDTO);
        return configUI.getRootPanel();
    }

    @Override
    public boolean isModified() {
        return !configDTO.getSourceLang().equals(configUI.getSourceLang())
                || !configDTO.getTargetLang().equals(configUI.getTargetLang())
                || !configDTO.getKey().equals(configUI.getAccessKey());
    }

    @Override
    public void apply() throws ConfigurationException {
        configDTO.setSourceLang(configUI.getSourceLang());
        configDTO.setTargetLang(configUI.getTargetLang());
        if (!(configUI.getAccessKey().isEmpty())) {
            configDTO.setKey(configUI.getAccessKey());
        }
        //configDTO.writeProperties();
    }
}
