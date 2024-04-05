package lazyalienserver.carpetlasaddition.mixin;

import carpet.api.settings.SettingsManager;
import net.minecraft.server.command.ServerCommandSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import carpet.utils.Messenger;

import static lazyalienserver.carpetlasaddition.CarpetLASServer.MOD_NAME;
import static lazyalienserver.carpetlasaddition.CarpetLASServer.MOD_VERSION;

@Mixin(SettingsManager.class)
public class SettingsManagerMixin{
    //TODO 这里可能有点筱问题
    @Inject(method = "listAllSettings", slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=carpet.settings.command.version", ordinal = 0)), at = @At(value = "INVOKE", target = "Lcarpet/api/settings/SettingsManager;getCategories()Ljava/lang/Iterable;", ordinal = 0), remap = false)
    private void printAdditionVersion(ServerCommandSource source, CallbackInfoReturnable<Integer> cir) {
        Messenger.m(source, "g "+MOD_NAME+" version: " + MOD_VERSION);
    }
}
