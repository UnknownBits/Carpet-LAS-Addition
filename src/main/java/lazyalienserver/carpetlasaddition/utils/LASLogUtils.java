package lazyalienserver.carpetlasaddition.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LASLogUtils {
    private static final Logger LASLog=LoggerFactory.getLogger("Lazy-Alien-Server");
    //Log
    public static void log(String reason,Object... object){
        LASLog.info(reason,object);
    }

    public static void log(String reason,Throwable throwable){
        LASLog.info(reason,throwable);
    }
    public static void log(String reason){
        LASLog.info(reason);
    }

    //debug
    public static void debug(String reason,Object... object){
        LASLog.debug(reason,object);
    }
    public static void debug(String reason,Throwable throwable){
        LASLog.debug(reason,throwable);
    }
    public static void debug(String reason){
        LASLog.debug(reason);
    }

    //warn
    public static void warn(String reason,Object... object){
        LASLog.warn(reason,object);
    }
    public static void warn(String reason,Throwable throwable){
        LASLog.warn(reason,throwable);
    }
    public static void warn(String reason){
        LASLog.warn(reason);
    }
    //error
    public static void error(String reason,Object... object){
        LASLog.error(reason,object);
    }
    public static void error(String reason,Throwable throwable){
        LASLog.error(reason,throwable);
    }
    public static void error(String reason){
        LASLog.error(reason);
    }

    //trace
    public static void trace(String reason,Object... object){
        LASLog.trace(reason,object);
    }
    public static void trace(String reason,Throwable throwable){
        LASLog.trace(reason,throwable);
    }
    public static void trace(String reason){
        LASLog.trace(reason);
    }
}
