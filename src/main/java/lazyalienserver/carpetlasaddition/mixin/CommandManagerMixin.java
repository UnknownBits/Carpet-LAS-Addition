package lazyalienserver.carpetlasaddition.mixin;

import lazyalienserver.carpetlasaddition.records.RecordList;
import lazyalienserver.carpetlasaddition.utils.DateTimeUtils;
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
    public void execute(ServerCommandSource commandSource, String command, CallbackInfoReturnable<Integer> cir) {
        if (LASResource.LASConfig.get("CommandRecord").equals("true")) {
            if (!Objects.equals(commandSource.getName(), "@")) {
                RecordList.commandRecord.addRecord("[Time:" + DateTimeUtils.getNowTime() + "]: { From:" + commandSource.getName() + " | Command:" + command + "}");
            }
        }
    }

}
