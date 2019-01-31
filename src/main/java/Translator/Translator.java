package Translator;

import Configuration.ConfigDTO;
import com.google.gson.Gson;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.message.BasicNameValuePair;


public class Translator {

    public List<String> getTranslations(ConfigDTO config, List<String> texts) throws RuntimeException {
        List<String> translations = new LinkedList<>();
        if (texts.isEmpty()) {
            return translations;
        }

        HttpPost post = new HttpPost("https://translate.yandex.net/api/v1.5/tr.json/translate");
        post.addHeader("Content-Type", "application/x-www-form-urlencoded");
        post.addHeader("Content-Language", "en-US");
        post.setEntity(constructBody(config, texts));
        try (CloseableHttpClient client = HttpClients.createDefault();
             ClassicHttpResponse response = client.execute(post)) {
            if (response != null) {
                if (response.getCode() != HttpStatus.SC_OK) {
                    throw new RuntimeException("Encountered API response <" + response.getCode() + "> \'" + response.getReasonPhrase() + "\'");
                }
                try (BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()))) {
                    Gson gson = new Gson();
                    Translations result = gson.fromJson(in, Translations.class);
                    translations = result.text;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return translations;
    }

    private HttpEntity constructBody(ConfigDTO config, List<String> texts) {
        List<NameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("key", config.getKey()));
        nvps.add(new BasicNameValuePair("lang", config.getLangForRequest()));
        nvps.add(new BasicNameValuePair("format", config.getFormat()));
        texts.forEach(text -> nvps.add(new BasicNameValuePair("text", text)));
        return new UrlEncodedFormEntity(nvps, StandardCharsets.UTF_8);
    }
}




