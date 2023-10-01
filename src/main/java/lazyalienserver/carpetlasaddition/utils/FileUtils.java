package lazyalienserver.carpetlasaddition.utils;

import carpet.CarpetServer;
import net.minecraft.util.WorldSavePath;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

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
            BufferedWriter out= new BufferedWriter(new FileWriter(path.toFile(),true));
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

    private void check(){
        File file=new File(getPath().getParent().toUri());
    }
}
