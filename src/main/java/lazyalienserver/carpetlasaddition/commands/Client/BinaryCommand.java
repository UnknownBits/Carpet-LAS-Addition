package lazyalienserver.carpetlasaddition.commands.Client;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

import static com.mojang.brigadier.arguments.IntegerArgumentType.getInteger;
import static com.mojang.brigadier.arguments.IntegerArgumentType.integer;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;

public class BinaryCommand {

    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(literal("binary").then(ClientCommandManager.argument("x", integer()).executes(c-> execute(getInteger(c, "x")))));
    }

    private static int execute(int x) {
        String result = Integer.toBinaryString(x);
        MinecraftClient.getInstance().player.sendMessage(Text.of(("w result: " + result)),false);
        return 0;
    }

}