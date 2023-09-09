package lazyalienserver.carpetlasaddition.network;

import lazyalienserver.carpetlasaddition.utils.LASLogUtils;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class ServerNetworkHandler {
    public static void send(ServerPlayerEntity player, Identifier identifier, PacketByteBuf buf){
        ServerPlayNetworking.send(player,identifier,buf);
    }
    public static void loadServer(){
        LASLogUtils.log("Lazy-Alien-Server's Server start run.");
    }
}
