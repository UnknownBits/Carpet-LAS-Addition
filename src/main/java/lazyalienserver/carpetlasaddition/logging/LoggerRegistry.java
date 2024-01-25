package lazyalienserver.carpetlasaddition.logging;

import carpet.logging.Logger;
import lazyalienserver.carpetlasaddition.CarpetLASServer;
import lazyalienserver.carpetlasaddition.logging.Loggers.RenderLogger;
import org.jetbrains.annotations.Nullable;

public class LoggerRegistry {
    public static boolean __hopperCoolTime =false;
    public static boolean __blockUpdate = false;
    public static void registerLoggers(){
        LoggerRegister(createRenderLogger("hopperCoolTime",null,null));
        //LoggerRegister(createRenderLogger("blockUpdate",null,null));
    }
    private static void LoggerRegister(Logger logger){
        carpet.logging.LoggerRegistry.registerLogger(logger.getLogName(),logger);
    }

    private static Logger createCarpetLogger(String name, @Nullable String def,@Nullable String[] opt) {
        try {
            return new Logger(LoggerRegistry.class.getField("__" + name),name, def, opt);
        } catch (NoSuchFieldException exception) {
            throw new RuntimeException(String.format("Field to get logger field \"%s @\"%s",name, CarpetLASServer.MOD_NAME));
        }
    }

    private static RenderLogger createRenderLogger(String name, @Nullable String def,@Nullable String[] opt) {
        try {
            return RenderLogger.addRenderLogger(new RenderLogger(LoggerRegistry.class.getField("__" + name),name, def, opt));
        } catch (NoSuchFieldException exception) {
            throw new RuntimeException(String.format("Field to get logger field \"%s @\"%s",name, CarpetLASServer.MOD_NAME));
        }
    }

}
