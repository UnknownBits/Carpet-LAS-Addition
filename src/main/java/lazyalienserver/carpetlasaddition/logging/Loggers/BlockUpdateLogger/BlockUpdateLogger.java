package lazyalienserver.carpetlasaddition.logging.Loggers.BlockUpdateLogger;

import carpet.CarpetServer;
import carpet.logging.Logger;
import carpet.utils.Messenger;
import com.mojang.brigadier.Command;
import lazyalienserver.carpetlasaddition.logging.LoggerRegistry;
import lazyalienserver.carpetlasaddition.render.BaseRender;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BlockUpdateLogger {
    private static final Map<BlockPos, UpdateType> BlockUpdateMap = new ConcurrentHashMap<>();
    private static final Logger log=carpet.logging.LoggerRegistry.getLogger("blockUpdate");

    private static int option=0;

    private static boolean isReturn=true;

    private static void setIsReturn(boolean Return){
        isReturn = Return;
    }

    private static void setOption(int option1){
        option=option1;
    }


    public static void RenderBlockUpDate(MatrixStack matrices){
        if (LoggerRegistry.__blockUpdate) {
            for (Map.Entry<BlockPos, UpdateType> entry : BlockUpdateMap.entrySet()) {
                if (entry.getValue() == UpdateType.NC) {
                    BaseRender.drawString(matrices,"NC", entry.getKey(), Formatting.DARK_GRAY.getColorValue(), 0.02F);
                    BaseRender.drawBoxWithLine(matrices,entry.getKey(), entry.getKey().add(1, 1, 1), 1, 0, 0, 0.1F, true);
                } else if (entry.getValue() == UpdateType.PP) {
                    BaseRender.drawString(matrices,"PP", entry.getKey(), Formatting.DARK_GRAY.getColorValue(), 0.02F);
                    BaseRender.drawBoxWithLine(matrices,entry.getKey(), entry.getKey().add(1, 1, 1), 1, 1, 0, 0.1F, true);
                } else {
                    BaseRender.drawString(matrices,"NC&PP", entry.getKey(), Formatting.DARK_GRAY.getColorValue(), 0.02F);
                    BaseRender.drawBoxWithLine(matrices,entry.getKey(), entry.getKey().add(1, 1, 1), 0.5F, 0.5F, 1, 0.1F, true);
                }
            }
        }
    }
    public static void NCUpdate(BlockPos pos){
        tick(pos,UpdateType.NC);
        if (BlockUpdateMap.get(pos)==null){
            BlockUpdateMap.put(new BlockPos(pos),UpdateType.NC);
            //HashMapHelper.put(BlockUpdateMap,pos,UpdateType.NC);
        }
        else if(BlockUpdateMap.get(pos)==UpdateType.PP) {
            BlockUpdateMap.put(new BlockPos(pos), UpdateType.NC_And_PP);
            //HashMapHelper.put(BlockUpdateMap,pos,UpdateType.NC_And_PP);
        } else {
            return;
        }
    }
    public static void PPUpdate(BlockPos pos){
        tick(pos,UpdateType.PP);
        if (BlockUpdateMap.get(pos)==null){
            BlockUpdateMap.put(new BlockPos(pos),UpdateType.PP);
            //HashMapHelper.put(BlockUpdateMap,pos,UpdateType.PP);
        }
        else if(BlockUpdateMap.get(pos)==UpdateType.NC) {
            BlockUpdateMap.put(new BlockPos(pos), UpdateType.NC_And_PP);
            //HashMapHelper.put(BlockUpdateMap,pos,UpdateType.NC_And_PP);
        }
        else {
            return;
        }
    }

    public static int List(){
        for (Map.Entry<BlockPos, UpdateType> entry:BlockUpdateMap.entrySet()) {
            Messenger.print_server_message(CarpetServer.minecraft_server,Messenger.c("  "+entry.getValue().toString(),Messenger.tp("Update",entry.getKey())));
        }
        return Command.SINGLE_SUCCESS;
    }

    public static void ClearHashMap(){
        BlockUpdateMap.clear();
    }
    public static void tickInTickFreeze(){
        if (option!=1) {
            ClearHashMap();
        }
    }
   public static void tick(BlockPos pos,UpdateType type){
        log.log((option) -> {
            switch (option) {
                case "brief" -> {
                    setOption(0);
                    if (isReturn) {
                        return new Text[]{Messenger.c("  " + CarpetServer.minecraft_server.getOverworld().getTime() + "  @" + type.toString(), Messenger.tp("Update", pos))};
                    }
                    else return null;
                }
                case "all" -> {
                    setOption(1);
                    if (isReturn) {
                        return new Text[]{Messenger.c("  " + CarpetServer.minecraft_server.getOverworld().getTime() + "  @" + type.toString(), Messenger.tp("Update", pos))};
                    }
                    else return null;
                }
                case "clean" -> {
                    setOption(0);
                    //BlockUpdateLogger.ClearHashMap();
                    return null;
                }
                case "enableReturn"->{
                    setIsReturn(true);
                    return null;
                }
                case "disableReturn"->{
                    setIsReturn(false);
                    return null;
                }
                default -> {
                    return null;
                }
            }
        });
    }
}
