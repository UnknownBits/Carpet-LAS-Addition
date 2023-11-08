package lazyalienserver.carpetlasaddition.logging.Loggers;

import carpet.logging.Logger;
import lazyalienserver.carpetlasaddition.network.NetWorkPacket;
import lazyalienserver.carpetlasaddition.utils.LASLogUtils;
import net.minecraft.server.network.ServerPlayerEntity;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class RenderLogger extends Logger {

    private String logName;

    public static HashMap<String,RenderLogger> renderLoggers=new HashMap<>();

    private Map<String,String> subscribedOnlinePlayers;


    public static HashMap<String,RenderLogger> getRenderLoggers(){
        return renderLoggers;
    }

    public static RenderLogger getRenderLogger(String logName){
        return renderLoggers.get(logName);
    }

    public static RenderLogger addRenderLogger(RenderLogger renderLogger){
        renderLoggers.put(renderLogger.getLogName(),renderLogger);
        return renderLogger;
    }

    public static void clearClientLoggers(ServerPlayerEntity player){
        NetWorkPacket.serverSend(player,NetWorkPacket.ClearRender, NetWorkPacket.createStringPacket("all"));
    }


    public RenderLogger(Field acceleratorField, String logName, String def, String[] options) {
        super(acceleratorField, logName, def, options);
        this.logName=logName;
        try{
            Field field= Logger.class.getDeclaredField("subscribedOnlinePlayers");
            field.setAccessible(true);
            subscribedOnlinePlayers=(Map<String, String>) field.get(this);
        }catch (Exception e){
            LASLogUtils.error("Error while getting subscribed online players",e);
        }
    }
    public Map<String,String> getSubscribedOnlinePlayers(){
        return subscribedOnlinePlayers;
    }


}
