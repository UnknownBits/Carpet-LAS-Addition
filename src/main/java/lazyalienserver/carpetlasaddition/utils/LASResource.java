package lazyalienserver.carpetlasaddition.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class LASResource {
    public static String lang="zh_cn";
    public static Map<String,String> LASTranslationsResource=new HashMap<>();

    public static Map<String,String> LASConfig=new HashMap<>();
    public static void loadLASResource(){
        LASLogUtils.log("loadLASResource");
        createLASConfig();
        getLASConfig();
        LASTranslationsResource=CarpetLASAdditionTranslations.getLASResource(lang);

    }

    public static void reloadLASLang(){
        LASTranslationsResource=CarpetLASAdditionTranslations.getLASResource(lang);
    }

    public static String getLASTranslationsResource(String key) {
        return LASTranslationsResource.get(key)!=null?LASTranslationsResource.get(key):"null";
    }

    public static String getLASConfig(String key) {
        return LASConfig.get(key)!=null?LASConfig.get(key):"null";
    }

    private static void getLASConfig(){
        Stream<String> stringStream=FileUtils.readFile(FileUtils.getMainPath().resolve("LAS.conf"));
        if (stringStream != null) {
            for (String s:stringStream.toList()){
                String[] strings=s.split(":");
                if (strings.length==2){
                    LASConfig.put(strings[0],strings[1]);
                    LASLogUtils.log(strings[0]+":"+strings[1]);
                }
            }
        }
    }
    public static void createLASConfig(){
        if (!FileUtils.getMainPath().resolve("LAS.conf").toFile().exists()){
            FileUtils.writeFile(FileUtils.getMainPath().resolve("LAS.conf"),FileUtils.getResourceFiles("assets/carpet-las-addition/LAS/LAS.conf"));
        }
    }
}
