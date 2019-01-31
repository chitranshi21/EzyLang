package Configuration;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@State(name="ConfigDTO", storages = {@Storage("Config.xml")})
public class ConfigDTO implements PersistentStateComponent<ConfigDTO> {

    // Key = Lang Display Name, Value = Lang Code
    protected Map<String, String> _langCodesMap = populateMap();

    private String _key = "trnsl.1.1.20190128T131954Z.781802b839e7e60c.c08444661352ca018edd87b1e298280f0542a951";
    private String _sourceLang = "de";
    private String _targetLang = "en";
    private String _format = "plain";

    private String KEY_PROP = "Key";
    private String SOURCELANG_PROP = "SourceLang";
    private String TARGETLANG_PROP = "TargetLang";
    private String FORMAT_PROP = "Format";

    private ConfigDTO() {}

    public String getSourceLang() {
        return _sourceLang;
    }

    public void setSourceLang(String _sourceLang) {
        this._sourceLang = _sourceLang;
    }

    public String getTargetLang() {
        return _targetLang;
    }

    public void setTargetLang(String _targetLang) {
        this._targetLang = _targetLang;
    }

    public String getLangForRequest() {
        return _sourceLang + "-" + _targetLang;
    }

    public String getKey() {
        return _key;
    }

    public void setKey(String _key) {
        this._key = _key;
    }

    public String getFormat() {
        return _format;
    }

    public Map<String, String> populateMap() {
        Map<String, String> langCodesMap = new HashMap<>();
        langCodesMap.put("af", "Afrikaans");
        langCodesMap.put("am", "Amharic");
        langCodesMap.put("ar", "Arabic");
        langCodesMap.put("az", "Azerbaijani");
        langCodesMap.put("ba", "Bashkir");
        langCodesMap.put("be", "Belarusian");
        langCodesMap.put("bg", "Bulgarian");
        langCodesMap.put("bn", "Bengali");
        langCodesMap.put("bs", "Bosnian");
        langCodesMap.put("ca", "Catalan");
        langCodesMap.put("ceb", "Cebuano");
        langCodesMap.put("cs", "Czech");
        langCodesMap.put("cy", "Welsh");
        langCodesMap.put("da", "Danish");
        langCodesMap.put("de", "German");
        langCodesMap.put("el", "Greek");
        langCodesMap.put("en", "English");
        langCodesMap.put("eo", "Esperanto");
        langCodesMap.put("es", "Spanish");
        langCodesMap.put("et", "Estonian");
        langCodesMap.put("eu", "Basque");
        langCodesMap.put("fa", "Persian");
        langCodesMap.put("fi", "Finnish");
        langCodesMap.put("fr", "French");
        langCodesMap.put("ga", "Irish");
        langCodesMap.put("gd", "Scottish Gaelic");
        langCodesMap.put("gl", "Galician");
        langCodesMap.put("gu", "Gujarati");
        langCodesMap.put("he", "Hebrew");
        langCodesMap.put("hi", "Hindi");
        langCodesMap.put("hr", "Croatian");
        langCodesMap.put("ht", "Haitian");
        langCodesMap.put("hu", "Hungarian");
        langCodesMap.put("hy", "Armenian");
        langCodesMap.put("id", "Indonesian");
        langCodesMap.put("is", "Icelandic");
        langCodesMap.put("it", "Italian");
        langCodesMap.put("ja", "Japanese");
        langCodesMap.put("jv", "Javanese");
        langCodesMap.put("ka", "Georgian");
        langCodesMap.put("kk", "Kazakh");
        langCodesMap.put("km", "Khmer");
        langCodesMap.put("kn", "Kannada");
        langCodesMap.put("ko", "Korean");
        langCodesMap.put("ky", "Kyrgyz");
        langCodesMap.put("la", "Latin");
        langCodesMap.put("lb", "Luxembourgish");
        langCodesMap.put("lo", "Lao");
        langCodesMap.put("lt", "Lithuanian");
        langCodesMap.put("lv", "Latvian");
        langCodesMap.put("mg", "Malagasy");
        langCodesMap.put("mhr", "Mari");
        langCodesMap.put("mi", "Maori");
        langCodesMap.put("mk", "Macedonian");
        langCodesMap.put("ml", "Malayalam");
        langCodesMap.put("mn", "Mongolian");
        langCodesMap.put("mr", "Marathi");
        langCodesMap.put("mrj", "Hill Mari");
        langCodesMap.put("ms", "Malay");
        langCodesMap.put("mt", "Maltese");
        langCodesMap.put("my", "Burmese");
        langCodesMap.put("ne", "Nepali");
        langCodesMap.put("nl", "Dutch");
        langCodesMap.put("no", "Norwegian");
        langCodesMap.put("pa", "Punjabi");
        langCodesMap.put("pap", "Papiamento");
        langCodesMap.put("pl", "Polish");
        langCodesMap.put("pt", "Portuguese");
        langCodesMap.put("ro", "Romanian");
        langCodesMap.put("ru", "Russian");
        langCodesMap.put("si", "Sinhalese");
        langCodesMap.put("sk", "Slovak");
        langCodesMap.put("sl", "Slovenian");
        langCodesMap.put("sq", "Albanian");
        langCodesMap.put("sr", "Serbian");
        langCodesMap.put("su", "Sundanese");
        langCodesMap.put("sv", "Swedish");
        langCodesMap.put("sw", "Swahili");
        langCodesMap.put("ta", "Tamil");
        langCodesMap.put("te", "Telugu");
        langCodesMap.put("tg", "Tajik");
        langCodesMap.put("th", "Thai");
        langCodesMap.put("tl", "Tagalog");
        langCodesMap.put("tr", "Turkish");
        langCodesMap.put("tt", "Tatar");
        langCodesMap.put("udm", "Udmurt");
        langCodesMap.put("uk", "Ukrainian");
        langCodesMap.put("ur", "Urdu");
        langCodesMap.put("uz", "Uzbek");
        langCodesMap.put("vi", "Vietnamese");
        langCodesMap.put("xh", "Xhosa");
        langCodesMap.put("yi", "Yiddish");
        langCodesMap.put("zh", "Chinese");
        return langCodesMap;
    }

    @Nullable
    @Override
    public ConfigDTO getState() {
        return this;
    }

    @Override
    public void loadState(ConfigDTO configDTO) {
        XmlSerializerUtil.copyBean(configDTO, this);
    }

    @Nullable
    public static ConfigDTO getInstance() {
        return ServiceManager.getService(ConfigDTO.class);
    }

    /*
    public void readProperties() {
        Properties prop = new Properties();
        InputStream stream = null;
        try {
            stream = new FileInputStream("properties.properties");
            prop.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        _sourceLang = prop.getProperty(SOURCELANG_PROP);
        _targetLang = prop.getProperty(TARGETLANG_PROP);
        _key = prop.getProperty(KEY_PROP);
    }

    public void writeProperties() {
        Properties prop = new Properties();
        OutputStream output = null;
        try {
            output = new FileOutputStream("properties.properties");
            prop.setProperty(KEY_PROP, _key);
            prop.setProperty(SOURCELANG_PROP, _sourceLang);
            prop.setProperty(TARGETLANG_PROP, _targetLang);
            prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }*/
}
