package lazyalienserver.carpetlasaddition.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;

public class CarpetLASAdditionTranslations {

    public static String lang="zh_cn";
    public static Map<String,String> getCarpetResource(String lang){
        return getTranslationFromResourcePath("assets/carpet-las-addition/carpet/lang/%s.json",lang);
    }
    public static Map<String,String> getLASResource(String lang){
        return getTranslationFromResourcePath("assets/carpet-las-addition/lang/%s.json",lang);
    }

    public static Map<String, String> getTranslationFromResourcePath(String path,String lang)
    {
        CarpetLASAdditionTranslations.lang=lang;
        String dataJSON;
        try
        {
            dataJSON = IOUtils.toString(Objects.requireNonNull(CarpetLASAdditionTranslations.class.getClassLoader().getResourceAsStream(String.format(path, lang))), StandardCharsets.UTF_8);
        }
        catch (IOException | NullPointerException e)
        {
            return null;
        }

        Gson gson = (new GsonBuilder()).enableComplexMapKeySerialization().create();
        return gson.fromJson(dataJSON, (new TypeToken<Map<String, String>>()
        {
        }).getType());
    }
}
