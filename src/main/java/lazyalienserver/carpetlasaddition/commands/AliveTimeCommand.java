package lazyalienserver.carpetlasaddition.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import lazyalienserver.carpetlasaddition.helper.LivingEntityMapsManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;

import static net.minecraft.server.command.CommandManager.literal;

public class AliveTimeCommand {
    public static void commandregister(CommandDispatcher<ServerCommandSource> dispatcher){
        dispatcher.register(literal("alivetime").requires(C->C.hasPermissionLevel(0)).executes(c-> AllAliveTime(c.getSource().getPlayer())));
    }
    private static int AllAliveTime(PlayerEntity player){

        if(LivingEntityMapsManager.getLivingEntityMapsManager() !=null){
            player.sendMessage(new LiteralText("Success"),false);
            LivingEntityMapsManager.getLivingEntityMapsManager().AllAverageAliveTime();
            return Command.SINGLE_SUCCESS;
        }
        else {
            player.sendMessage(new LiteralText("nothing"),false);
        }
        return 0;
    }
}
