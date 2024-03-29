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
    public static Map<String,String> getCarpetResource(String lang){
        return getTranslationFromResourcePath("assets/carpet-las-addition/carpet/lang/%s.json",lang);
    }
    public static Map<String,String> getLASResource(String lang){
        return getTranslationFromResourcePath("assets/carpet-las-addition/LAS/lang/%s.json",lang);
    }

    public static Map<String, String> getTranslationFromResourcePath(String path,String lang)
    {
        /*String dataJSON;
        try
        {
            dataJSON = IOUtils.toString(Objects.requireNonNull(CarpetLASAdditionTranslations.class.getClassLoader().getResourceAsStream(String.format(path, lang))), StandardCharsets.UTF_8);
        }
        catch (IOException | NullPointerException e)
        {
            LASLogUtils.error("[CLA]:"+"failed read lang_File   " + String.format(path,lang));
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
