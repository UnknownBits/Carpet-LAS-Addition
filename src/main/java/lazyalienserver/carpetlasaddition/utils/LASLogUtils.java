package lazyalienserver.carpetlasaddition.utils;


import lazyalienserver.carpetlasaddition.commands.Server.LazyAlienServerCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LASLogUtils {
    private static final Logger LASLog=LoggerFactory.getLogger("Lazy-Alien-Server");
    //Log
    public static void log(String reason,Object... object){
        if(!LazyAlienServerCommand.CLALog)return;
        LASLog.info(reason,object);
    }

    public static void log(String reason,Throwable throwable){
        if(!LazyAlienServerCommand.CLALog)return;
        LASLog.info(reason,throwable);
    }
    public static void log(String reason){
        if(!LazyAlienServerCommand.CLALog)return;
        LASLog.info(reason);
    }

    //debug
    public static void debug(String reason,Object... object){
        if(!LazyAlienServerCommand.CLALog)return;
        LASLog.debug(reason,object);
    }
    public static void debug(String reason,Throwable throwable){
        if(!LazyAlienServerCommand.CLALog)return;
        LASLog.debug(reason,throwable);
    }
    public static void debug(String reason){
        if(!LazyAlienServerCommand.CLALog)return;
        LASLog.debug(reason);
    }

    //warn
    public static void warn(String reason,Object... object){
        if(!LazyAlienServerCommand.CLALog)return;
        LASLog.warn(reason,object);
    }
    public static void warn(String reason,Throwable throwable){
        if(!LazyAlienServerCommand.CLALog)return;
        LASLog.warn(reason,throwable);
    }
    public static void warn(String reason){
        if(!LazyAlienServerCommand.CLALog)return;
        LASLog.warn(reason);
    }
    //error
    public static void error(String reason,Object... object){
        if(!LazyAlienServerCommand.CLALog)return;
        LASLog.error(reason,object);
    }
    public static void error(String reason,Throwable throwable){
        if(!LazyAlienServerCommand.CLALog)return;
        LASLog.error(reason,throwable);
    }
    public static void error(String reason){
        if(!LazyAlienServerCommand.CLALog)return;
        LASLog.error(reason);
    }

    //trace
    public static void trace(String reason,Object... object){
        if(!LazyAlienServerCommand.CLALog)return;
        LASLog.trace(reason,object);
    }
    public static void trace(String reason,Throwable throwable){
        if(!LazyAlienServerCommand.CLALog)return;
        LASLog.trace(reason,throwable);
    }
    public static void trace(String reason){
        if(!LazyAlienServerCommand.CLALog)return;
        LASLog.trace(reason);
    }
}
