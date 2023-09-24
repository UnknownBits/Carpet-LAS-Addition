package lazyalienserver.carpetlasaddition.network;

import lazyalienserver.carpetlasaddition.utils.LASLogUtils;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class ServerNetworkHandler {

    public static void send(ServerPlayerEntity player, Identifier identifier, PacketByteBuf buf){
        ServerPlayNetworking.send(player,identifier,buf);
    }
    public static void loadServer(){
        LASLogUtils.log("The server of Lazy-Alien-Server starts running.");
        registerGlobalReceiver();
    }

    public static void registerGlobalReceiver(){
        //ServerPlayNetworking.registerGlobalReceiver(NetWorkPacket.HopperCoolTime, ServerNetworkHandler::serverNetWork);
    }

    public static void serverNetWork(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buffer, PacketSender sender){

    }
}
