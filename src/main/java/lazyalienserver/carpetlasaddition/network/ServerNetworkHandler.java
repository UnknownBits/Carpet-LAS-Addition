package lazyalienserver.carpetlasaddition.network;

import lazyalienserver.carpetlasaddition.logging.Loggers.BlockUpdateLogger.BlockUpdateLogger;
import lazyalienserver.carpetlasaddition.logging.Loggers.HopperCoolTimeLogger.HopperCoolTimeLogger;
import java.util.ArrayList;

public class ServerNetworkHandler {
    public static final ArrayList<Runnable> ServerPacketSender = new ArrayList<>();

    public static void loadServer(){
        ServerNetworkHandlerRegister();
    }

    private static void ServerNetworkHandlerRegister(){

    }

    public static void sendPacket(){
        for (Runnable sendPacket: ServerPacketSender){
            sendPacket.run();
        }
    }

    public static void registerServerPacketSender(Runnable sender){
        ServerPacketSender.add(sender);
    }

    static {
        registerServerPacketSender(HopperCoolTimeLogger::sendHopperCoolTime);
        //registerServerPacketSender(BlockUpdateLogger::sendBlockUpdate);
    }
}
