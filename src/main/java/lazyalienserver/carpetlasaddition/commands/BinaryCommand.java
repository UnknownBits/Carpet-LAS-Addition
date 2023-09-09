package lazyalienserver.carpetlasaddition.commands;

import carpet.settings.SettingsManager;
import carpet.utils.Messenger;
import lazyalienserver.carpetlasaddition.CarpetLASSetting;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;

import static com.mojang.brigadier.arguments.IntegerArgumentType.getInteger;
import static net.minecraft.server.command.CommandManager.argument;

public class BinaryCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        LiteralArgumentBuilder<ServerCommandSource> command = LiteralArgumentBuilder.literal("binary");
        command.requires((player) -> SettingsManager.canUseCommand(player, CarpetLASSetting.commandBinary))
                .then(argument("x", IntegerArgumentType.integer())
                .executes((c) -> execute(c, getInteger(c, "x"))));
        dispatcher.register(command);
    }

    private static int execute(CommandContext<ServerCommandSource> c, int x) {
        String result = Integer.toBinaryString(x);
        Messenger.m(c.getSource(),"w result: " + result);
        return 0;
    }

}