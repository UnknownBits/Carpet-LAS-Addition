package lazyalienserver.carpetlasaddition.network;

import lazyalienserver.carpetlasaddition.helper.NetWorkHelper;
import lazyalienserver.carpetlasaddition.logging.Loggers.HopperCoolTimeLogger.HopperCoolTimeLogger;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.networking.v1.*;


public class ClientNetworkHandler {
    public static void ClientNetworkHandler(){
        /*ClientPlayNetworking.registerGlobalReceiver(NetWorkHelper.hoppercooltime,((client, handler, buf, responseSender) ->
                client.execute(()->{
                    //HopperCoolTimeLogger.getHopperBlockCoolTime().putAll();
                })));*/
    }
}
