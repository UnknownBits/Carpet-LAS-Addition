package lazyalienserver.carpetlasaddition.commands;

import carpet.settings.SettingsManager;
import carpet.utils.Messenger;
import lazyalienserver.carpetlasaddition.CarpetLASSetting;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import static com.mojang.brigadier.arguments.IntegerArgumentType.getInteger;
import static net.minecraft.server.command.CommandManager.argument;

public class DecimalCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        LiteralArgumentBuilder<ServerCommandSource> command = LiteralArgumentBuilder.literal("decimal");
        command.requires((player) -> SettingsManager.canUseCommand(player, CarpetLASSetting.commandDecimal))
                .then(argument("x", IntegerArgumentType.integer())
                .executes((c) -> execute(c, getInteger(c, "x"))));
        dispatcher.register(command);
    }

    private static int execute(CommandContext<ServerCommandSource> c, int x) {
        String strNum = String.valueOf(x);
        for (int i = 0; i < strNum.length(); i++) {
            char a = strNum.charAt(i);
            if (a != '0' && a != '1') {
                Messenger.m(c.getSource(),"w There must only be 1,0 in the number.");
                break;
            } else {
                Messenger.m(c.getSource(),"w result: " + String.valueOf(Integer.parseInt(strNum, 2)));
                break;
            }
        }
        return 0;
    }

}