package lazyalienserver.carpetlasaddition.mixin;

import lazyalienserver.carpetlasaddition.records.RecordList;
import lazyalienserver.carpetlasaddition.utils.DateTimeUtils;
import lazyalienserver.carpetlasaddition.utils.LASLogUtils;
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
    @Shadow public ServerPlayerEntity player;

    @Inject(at=@At(value = "INVOKE",target = "Lnet/minecraft/server/network/ServerPlayerEntity;sendSystemMessage(Lnet/minecraft/text/Text;Ljava/util/UUID;)V"),method = "onUpdateCommandBlock")
    public void onUpdateCommandBlock(UpdateCommandBlockC2SPacket packet, CallbackInfo ci){
        //LASLogUtils.log(packet.getType().name());

        switch (packet.getType()){
            case REDSTONE -> {
                RecordList.commandBlockRecord.addRecord("[Time:"+ DateTimeUtils.getNowTime() +"]: { Player:"+this.player.getName().asString()+" | Command:" +packet.getCommand()+"Type: 脉冲 "+checkUpdateCommandBlock(packet)+"}");
                LASLogUtils.log("[Time:"+ DateTimeUtils.getNowTime() +"]: { Player:"+this.player.getName().asString()+" | Command:" +packet.getCommand()+" | Type: 脉冲 "+checkUpdateCommandBlock(packet)+"}");
            }
            case AUTO -> {
                //checkUpdateCommandBlock("\\u5faa\\u73af",packet,this.player.getName().asString());
                //RecordList.commandBlockRecord.addRecord("[Time:\"+ DateTimeUtils.getNowTime() +\"]: { Player:"+this.player.getName()+" | Command:" +packet.getCommand()+"Type: \\u5faa\\u73af"+"}");
                RecordList.commandBlockRecord.addRecord("[Time:"+ DateTimeUtils.getNowTime() +"]: { Player:"+this.player.getName().asString()+" | Command:" +packet.getCommand()+"Type: 循环 "+checkUpdateCommandBlock(packet)+"}");
                LASLogUtils.log("[Time:"+ DateTimeUtils.getNowTime() +"]: { Player:"+this.player.getName().asString()+" | Command:" +packet.getCommand()+" | Type: 循环 "+checkUpdateCommandBlock(packet)+"}");

            }
            case SEQUENCE -> {
                //checkUpdateCommandBlock("\\u5faa\\u73af",packet,this.player.getName().asString());
                //RecordList.commandBlockRecord.addRecord("[Time:\"+ DateTimeUtils.getNowTime() +\"]: { Player:"+this.player.getName()+" | Command:" +packet.getCommand()+"Type: \\u8fde\\u9501"+"}");
                RecordList.commandBlockRecord.addRecord("[Time:"+ DateTimeUtils.getNowTime() +"]: { Player:"+this.player.getName().asString()+" | Command:" +packet.getCommand()+"Type: 连锁 "+checkUpdateCommandBlock(packet)+"}");
                LASLogUtils.log("[Time:"+ DateTimeUtils.getNowTime() +"]: { Player:"+this.player.getName().asString()+" | Command:" +packet.getCommand()+" | Type: 连锁 "+checkUpdateCommandBlock(packet)+"}");
            }
        }


        //RecordList.commandBlockRecord.addRecord("[Time:\"+ DateTimeUtils.getNowTime() +\"]: { Player:"+this.player.getName()+" | Command:" +packet.getCommand()+"Type"+packet.getType()+"}");

    }
    @Unique
    private static String checkUpdateCommandBlock(UpdateCommandBlockC2SPacket packet){
        if (packet.isConditional()){
            if (packet.isAlwaysActive()){
                return " | 条件: 有 | 状态: 保持开启";
            }
            else {
                return " | 条件: 有 | 状态: 红石激活";
            }
        }
        else {
            if (packet.isAlwaysActive()){
                return " | 条件: 无 | 状态: 保持开启";
            }
            else {
                return " | 条件: 无 | 状态: 红石激活";
            }
        }

    }
}
