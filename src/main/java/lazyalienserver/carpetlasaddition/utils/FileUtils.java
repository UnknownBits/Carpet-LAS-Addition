package lazyalienserver.carpetlasaddition.utils;

import carpet.CarpetServer;
import net.minecraft.util.WorldSavePath;
import org.apache.commons.io.IOUtils;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    public static Path getPath() {
        return CarpetServer.minecraft_server.getSavePath(WorldSavePath.ROOT);
    }
    public static Path getMainPath(){
        return FileUtils.getPath().resolve("Lazy-Alien-Server");
    }
    public static boolean createDir(Path path){
        File file=new File(path.toUri());
        return file.mkdirs();
    }

    public static void writeFile(Path path, ArrayList<String> line){
        try {
            //new OutputStreamWriter(new FileOutputStream(new File(path.toUri())), StandardCharsets.UTF_8)
            BufferedWriter out= new BufferedWriter(new FileWriter(path.toFile(), StandardCharsets.UTF_8, true));
            for (String l:line) {
                out.write(l);
                out.newLine();
            }
            out.close();
        }
        catch (IOException ioException){
            LASLogUtils.error("[CLA]:"+"failed write " + path.getFileName(), ioException);
        }

    }

    private static void createFile(Path path){

    }

    public static List<String> ResourceFilesList(String path) {

        try (InputStream inputStream=FileUtils.class.getClassLoader().getResourceAsStream(path)){
            List<String> flieList= IOUtils.readLines(inputStream,StandardCharsets.UTF_8);
            return flieList;
        }
        catch (IOException ioException){
            LASLogUtils.error("LASResource : getResourceFilesList"+ioException);
        }
        finally {
        }

        /*
        File file=new File(path.toUri());
        if (file.isDirectory()) {
            return file.listFiles();
        } else {
            LASLogUtils.warn("filesList:It's a File :"+path.toUri());
        }*/
        return null;
    }
}
