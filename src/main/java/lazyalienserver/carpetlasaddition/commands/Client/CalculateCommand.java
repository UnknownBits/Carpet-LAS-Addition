package lazyalienserver.carpetlasaddition.commands.Client;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import lazyalienserver.carpetlasaddition.exp4j.Expression;
import lazyalienserver.carpetlasaddition.exp4j.ExpressionBuilder;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;

public class CalculateCommand {

    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(literal("calculate").then(ClientCommandManager.argument("x", StringArgumentType.greedyString()).executes(c-> execute(StringArgumentType.getString(c, "x")))));
    }

    private static int execute(String x) {
        Expression e = new ExpressionBuilder(x)
                .build();
        double result = e.evaluate();
        MinecraftClient.getInstance().player.sendMessage(Text.of("w result: " + result),false);
        return 0;
    }

}