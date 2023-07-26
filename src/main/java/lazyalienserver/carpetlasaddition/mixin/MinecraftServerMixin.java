package lazyalienserver.carpetlasaddition.mixin;

import lazyalienserver.carpetlasaddition.helper.CheckLASCarpetSetting;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Iterator;
import java.util.function.BooleanSupplier;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin {
    @Inject(at=@At(value = "INVOKE",target = "Lnet/minecraft/server/PlayerManager;sendToDimension(Lnet/minecraft/network/Packet;Lnet/minecraft/util/registry/RegistryKey;)V"),method = "tickWorlds",locals = LocalCapture.CAPTURE_FAILHARD)
    public void tick(BooleanSupplier shouldKeepTicking, CallbackInfo ci, Iterator var2, ServerWorld serverWorld){
            CheckLASCarpetSetting.checkNoteChunkLoad(serverWorld);
    }
    @Inject(at=@At(value = "INVOKE",target = "Lnet/minecraft/server/world/ServerChunkManager;removePersistentTickets()V"),method = "shutdown",locals = LocalCapture.CAPTURE_FAILHARD)
    public void shutdown(CallbackInfo ci, Iterator var1, ServerWorld serverWorld){
            CheckLASCarpetSetting.checkNoteChunkLoad(serverWorld);
    }
}
