package lazyalienserver.carpetlasaddition.commands;

import com.mojang.brigadier.CommandDispatcher;
import lazyalienserver.carpetlasaddition.logging.Loggers.HopperCoolTimeLogger.HopperCoolTimeLogger;
import net.fabricmc.fabric.api.client.command.v1.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

import static net.fabricmc.fabric.api.client.command.v1.ClientCommandManager.literal;


public class TestCommand {
    public static boolean tf=false;
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher){
        dispatcher.register(literal("Test").executes(c->RenderLine()));
    }

    public static int RenderLine(){
        MinecraftClient client=MinecraftClient.getInstance();
        tf=!tf;
        if(tf){
            client.player.sendMessage(Text.of("启动渲染"),false);
        }
        else {
            client.player.sendMessage(Text.of("关闭渲染"),false);
            HopperCoolTimeLogger.getHopperBlockCoolTime().clear();
        }
        return 0;
    }

}
