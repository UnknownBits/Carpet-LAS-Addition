package lazyalienserver.carpetlasaddition.mixin;

import lazyalienserver.carpetlasaddition.records.RecordList;
import lazyalienserver.carpetlasaddition.utils.DateTimeUtils;
import lazyalienserver.carpetlasaddition.utils.LASResource;
import net.minecraft.network.packet.c2s.play.UpdateCommandBlockC2SPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayNetworkHandler.class)
public class ServerPlayNetworkHandlerMixin {
    @Shadow
    public ServerPlayerEntity player;

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerEntity;sendSystemMessage(Lnet/minecraft/text/Text;Ljava/util/UUID;)V"), method = "onUpdateCommandBlock")
    public void onUpdateCommandBlock(UpdateCommandBlockC2SPacket packet, CallbackInfo ci) {
        if (LASResource.LASConfig.get("CommandBlockRecord").equals("true")) {
            switch (packet.getType()) {
                case REDSTONE -> {
                    RecordList.commandBlockRecord.addRecord("[Time:" + DateTimeUtils.getNowTime() + "]: { Player:" + this.player.getName().asString() + " | " + packet.getBlockPos().toString() + " | Command:" + packet.getCommand() + " | Type: " + LASResource.getLASTranslationsResource("Record.CommandBlock.REDSTONE") + checkUpdateCommandBlock(packet) + "}");
                }
                case AUTO -> {
                    RecordList.commandBlockRecord.addRecord("[Time:" + DateTimeUtils.getNowTime() + "]: { Player:" + this.player.getName().asString() + " | " + packet.getBlockPos().toString() + " | Command:" + packet.getCommand() + " | Type: " + LASResource.getLASTranslationsResource("Record.CommandBlock.AUTO") + checkUpdateCommandBlock(packet) + "}");
                }
                case SEQUENCE -> {
                    RecordList.commandBlockRecord.addRecord("[Time:" + DateTimeUtils.getNowTime() + "]: { Player:" + this.player.getName().asString() + " | " + packet.getBlockPos().toString() + " | Command:" + packet.getCommand() + " | Type: " + LASResource.getLASTranslationsResource("Record.CommandBlock.SEQUENCE") + checkUpdateCommandBlock(packet) + "}");
                }
            }
        }
    }

    @Unique
    private static String checkUpdateCommandBlock(UpdateCommandBlockC2SPacket packet) {
        if (packet.isConditional()) {
            if (packet.isAlwaysActive()) {
                return " | " + LASResource.getLASTranslationsResource("Record.CommandBlock.isConditional") + " | " + LASResource.getLASTranslationsResource("Record.CommandBlock.isAlwaysActive");
            } else {
                return " | " + LASResource.getLASTranslationsResource("Record.CommandBlock.isConditional") + " | " + LASResource.getLASTranslationsResource("Record.CommandBlock.noAlwaysActive");
            }
        } else {
            if (packet.isAlwaysActive()) {
                return " | " + LASResource.getLASTranslationsResource("Record.CommandBlock.noConditional") + " | " + LASResource.getLASTranslationsResource("Record.CommandBlock.isAlwaysActive");
            } else {
                return " | " + LASResource.getLASTranslationsResource("Record.CommandBlock.noConditional") + " | " + LASResource.getLASTranslationsResource("Record.CommandBlock.noAlwaysActive");
            }
        }

    }
}
