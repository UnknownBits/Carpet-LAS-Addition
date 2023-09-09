package lazyalienserver.carpetlasaddition.commands;

import carpet.settings.SettingsManager;
import carpet.utils.Messenger;
import lazyalienserver.carpetlasaddition.CarpetLASSetting;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import lazyalienserver.carpetlasaddition.utils.LASLogUtils;
import net.minecraft.server.command.ServerCommandSource;
import lazyalienserver.carpetlasaddition.exp4j.Expression;
import lazyalienserver.carpetlasaddition.exp4j.ExpressionBuilder;

import static com.mojang.brigadier.arguments.StringArgumentType.getString;
import static net.minecraft.server.command.CommandManager.argument;

public class CalculateCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        LiteralArgumentBuilder<ServerCommandSource> command = LiteralArgumentBuilder.literal("calculate");
        command.requires((player) -> SettingsManager.canUseCommand(player, CarpetLASSetting.commandCalculate))
                .then(argument("x", StringArgumentType.greedyString())
                .executes((c) -> execute(c, getString(c, "x"))));
        dispatcher.register(command);
    }

    private static int execute(CommandContext<ServerCommandSource> c, String x) {
        Expression e = new ExpressionBuilder(x)
                .build();
        double result = e.evaluate();
        LASLogUtils.log(result+"");
        Messenger.m(c.getSource(),"w result: " + result);
        return 0;
    }

}