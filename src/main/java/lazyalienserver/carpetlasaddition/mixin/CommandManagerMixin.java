package lazyalienserver.carpetlasaddition.mixin;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import lazyalienserver.carpetlasaddition.records.RecordList;
import lazyalienserver.carpetlasaddition.utils.DateTimeUtils;
import lazyalienserver.carpetlasaddition.utils.LASLogUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(CommandManager.class)
public class CommandManagerMixin {
    @Inject(at=@At("HEAD"),method = "execute")
    public void execute(ServerCommandSource commandSource, String command, CallbackInfoReturnable<Integer> cir){
            if (!Objects.equals(commandSource.getName(), "@")){
                RecordList.commandRecord.addRecord("[Time:"+ DateTimeUtils.getNowTime() +"]: { From:"+commandSource.getName()+" | Command:"+command+"}");
            }

    }

}
