package lazyalienserver.carpetlasaddition.logging;

import carpet.logging.Logger;
import lazyalienserver.carpetlasaddition.CarpetLASServer;
import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.fabricmc.loader.impl.util.log.LogLevel;
import org.jetbrains.annotations.Nullable;

public class LoggerRegistry {
    public static boolean __hoppercooltime;
    public static void registerLoggers(){
        LoggerRegister(createCarpetLogger("hoppercooltime",null,null));
    }
    private static void LoggerRegister(Logger logger){
        Log.log(LogLevel.DEBUG, LogCategory.LOG,"registry"+logger.getLogName());
        carpet.logging.LoggerRegistry.registerLogger(logger.getLogName(),logger);
    }

    private static Logger createCarpetLogger(String name, @Nullable String def,@Nullable String[] opt) {
        try {
            Log.log(LogLevel.DEBUG,LogCategory.LOG,LoggerRegistry.class.getField("__"+name).toString());
            return new Logger(LoggerRegistry.class.getField("__" + name),name, def, opt);
        } catch (NoSuchFieldException exception) {
            throw new RuntimeException(String.format("Field to get logger field \"%s @\"%s",name, CarpetLASServer.MOD_NAME));
        }
    }

}