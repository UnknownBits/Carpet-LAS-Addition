package lazyalienserver.carpetlasaddition.commands;

import com.mojang.brigadier.CommandDispatcher;
import lazyalienserver.carpetlasaddition.records.RecordList;
import lazyalienserver.carpetlasaddition.utils.LASLogUtils;
import net.fabricmc.fabric.api.client.command.v1.FabricClientCommandSource;

import static net.fabricmc.fabric.api.client.command.v1.ClientCommandManager.literal;


public class TestCommand {
    public static boolean tf=false;
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher){
        dispatcher.register(literal("Test").executes(c->RenderLine()).then(literal("list").executes(c->RenderLine())));
    }

    public static int RenderLine(){

        for (String s: RecordList.commandRecord.getRecord()){
            LASLogUtils.log(s);
        }
        /*MinecraftClient client=MinecraftClient.getInstance();
        tf=!tf;
        if(tf){
            client.player.sendMessage(Text.of("启动渲染"),false);
            BlockUpdateLogger.ClearHashMap();
            //BlockUpdateLogger.getBlockUpdateMap().put(MinecraftClient.getInstance().player.getBlockPos(),true);
            LASLogUtils.log("LAS-Log");
        }
        else {
            client.player.sendMessage(Text.of("关闭渲染"),false);
            BlockUpdateLogger.ClearHashMap();
            //BlockUpdateLogger.getBlockUpdateMap().put(MinecraftClient.getInstance().player.getBlockPos(),false);
            //HopperCoolTimeLogger.getHopperBlockCoolTime().clear();
            LASLogUtils.log("LAS-Log");
        }
        return 0;
    }

    public static int List(){
        BlockUpdateLogger.List();
        return 0;
    }
*/
        return 0;
    }
}
