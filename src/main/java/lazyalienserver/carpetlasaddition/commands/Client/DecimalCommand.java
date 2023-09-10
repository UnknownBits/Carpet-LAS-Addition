package lazyalienserver.carpetlasaddition.commands.Client;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;

public class DecimalCommand {

    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(literal("decimal").then(ClientCommandManager.argument("x", IntegerArgumentType.integer()).executes(c-> execute(IntegerArgumentType.getInteger(c, "x")))));
    }

    private static int execute(int x) {
        String strNum = String.valueOf(x);
        for (int i = 0; i < strNum.length(); i++) {
            char a = strNum.charAt(i);
            if (a != '0' && a != '1') {
                MinecraftClient.getInstance().player.sendMessage(Text.of("w There must only be 1,0 in the number."),false);
                break;
            } else {
                MinecraftClient.getInstance().player.sendMessage(Text.of("w result: " + Integer.parseInt(strNum, 2)),false);
                break;
            }
        }
        return 0;
    }

}