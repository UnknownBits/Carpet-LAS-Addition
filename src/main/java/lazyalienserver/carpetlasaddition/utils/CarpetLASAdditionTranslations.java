package lazyalienserver.carpetlasaddition.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lazyalienserver.carpetlasaddition.CarpetLASServer;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;

public class CarpetLASAdditionTranslations {
    public static Map<String, String> getTranslationFromResourcePath(String lang)
    {
        /*String dataJSON;
        try
        {
            dataJSON = IOUtils.toString(Objects.requireNonNull(CarpetLASAdditionTranslations.class.getClassLoader().getResourceAsStream(String.format("assets/carpet-las-addition/lang/%s.json", lang))), StandardCharsets.UTF_8);
        }
        catch (IOException | NullPointerException e)
        {
            return null;
        }

        Gson gson = (new GsonBuilder()).enableComplexMapKeySerialization().create();
        return gson.fromJson(dataJSON, (new TypeToken<Map<String, String>>()
        {
        }).getType());*/
        InputStream langFile = CarpetLASAdditionTranslations.class.getClassLoader().getResourceAsStream("assets/carpet-las-addition/lang/%s.json".formatted(lang));
        if (langFile == null) {
            // we don't have that language
            return Collections.emptyMap();
        }
        String jsonData;
        try {
            jsonData = IOUtils.toString(langFile, StandardCharsets.UTF_8);
        } catch (IOException e) {
            return Collections.emptyMap();
        }
        Gson gson = new GsonBuilder().setLenient().create(); // lenient allows for comments
        return gson.fromJson(jsonData, new TypeToken<Map<String, String>>() {}.getType());
    }
}
