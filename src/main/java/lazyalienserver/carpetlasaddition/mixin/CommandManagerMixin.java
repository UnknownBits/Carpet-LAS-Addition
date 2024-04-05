package lazyalienserver.carpetlasaddition.mixin;

import com.mojang.brigadier.ParseResults;
import lazyalienserver.carpetlasaddition.helper.CommandCheckHelper;
import lazyalienserver.carpetlasaddition.records.RecordList;
import lazyalienserver.carpetlasaddition.utils.DateTimeUtils;
import lazyalienserver.carpetlasaddition.utils.LASLogUtils;
import lazyalienserver.carpetlasaddition.utils.LASResource;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(CommandManager.class)
public class CommandManagerMixin {
    @Inject(at = @At("HEAD"), method = "execute")
    public void execute(ParseResults<ServerCommandSource> parseResults, String command, CallbackInfoReturnable<Integer> cir) {
        //LASLogUtils.log("Source"+commandSource.getName());
        if(!Objects.equals(parseResults.getContext().getSource().getName(), "@")||Objects.equals(parseResults.getContext().getSource().getName(), "server")){
            try{
                CommandCheckHelper.checkCommand(command,parseResults.getContext().getSource().getPlayer());
            }catch (Exception e){
                LASLogUtils.error("No player",e.getMessage());
            }
        }
        if (LASResource.getLASConfig("CommandRecord").equals("true")) {
            if (!Objects.equals(parseResults.getContext().getSource().getName(), "@")) {
                RecordList.commandRecord.addRecord("[Time:" + DateTimeUtils.getNowTime() + "]: { From:" + parseResults.getContext().getSource().getName() + " | Command:" + command + "}");
            }
        }
    }

}
