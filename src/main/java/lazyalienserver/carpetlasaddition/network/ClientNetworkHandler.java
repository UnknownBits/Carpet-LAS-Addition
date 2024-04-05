package lazyalienserver.carpetlasaddition.network;

import lazyalienserver.carpetlasaddition.logging.Loggers.HopperCoolTimeLogger.HopperCoolTimeRender;
import lazyalienserver.carpetlasaddition.logging.Loggers.RenderList;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;


public class ClientNetworkHandler {

    public static void loadServer(){
        ClientNetworkHandlerRegister();
    }

    private static void ClientNetworkHandlerRegister(){
        ClientPlayNetworking.registerGlobalReceiver(NetWorkPacket.HopperCoolTime, ((client, handler, buf, responseSender) -> HopperCoolTimeRender.getServerLogger(buf)));
        ClientPlayNetworking.registerGlobalReceiver(NetWorkPacket.ClearRender, ((client, handler, buf, responseSender) -> RenderList.clear(buf)));
    }


}
