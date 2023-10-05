package lazyalienserver.carpetlasaddition.utils;

import java.util.HashMap;
import java.util.Map;

public class LASResource {
    public static String lang="zh_cn";
    public static Map<String,String> LASTranslationsResource=new HashMap<>();
    public static void loadLASResource(){
        LASLogUtils.log("loadLASResource");
        LASTranslationsResource=CarpetLASAdditionTranslations.getLASResource(lang);

    }

    public static void reloadLASLang(){
        LASTranslationsResource=CarpetLASAdditionTranslations.getLASResource(lang);
    }

    public static String getLASTranslationsResource(String key) {
        return LASTranslationsResource.get(key);
    }
}
