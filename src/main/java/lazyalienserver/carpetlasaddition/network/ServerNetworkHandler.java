package lazyalienserver.carpetlasaddition.network;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class ServerNetworkHandler {
    public static void send(ServerPlayerEntity player, Identifier identifier, PacketByteBuf buf){
        ServerPlayNetworking.send(player,identifier,buf);
    }
}
