package lazyalienserver.carpetlasaddition.logging.Loggers.BlockUpdateLogger;

import carpet.CarpetServer;
import carpet.utils.Messenger;
import lazyalienserver.carpetlasaddition.helper.HashMapHelper;
import lazyalienserver.carpetlasaddition.logging.LoggerRegistry;
import lazyalienserver.carpetlasaddition.render.BaseRender;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BlockUpdateLogger {
    private static final Map<BlockPos, UpdateType> BlockUpdateMap = new ConcurrentHashMap<>();

    public static void RenderBlockUpDate(){
        if (LoggerRegistry.__blockUpdate) {
            for (Map.Entry<BlockPos, UpdateType> entry : BlockUpdateMap.entrySet()) {
                if (entry.getValue() == UpdateType.NC) {
                    BaseRender.drawString("NC", entry.getKey().getX() + 0.5F, entry.getKey().getY() + 0.5F, entry.getKey().getZ() + 0.5F, Formatting.AQUA.getColorValue(), true);
                    BaseRender.drawBoxWithLine(entry.getKey(), entry.getKey().add(1, 1, 1), 1, 0, 0, 0.1F, true);
                } else if (entry.getValue() == UpdateType.PP) {
                    BaseRender.drawString("PP", entry.getKey().getX() + 0.5F, entry.getKey().getY() + 0.5F, entry.getKey().getZ() + 0.5F, Formatting.AQUA.getColorValue(), true);
                    BaseRender.drawBoxWithLine(entry.getKey(), entry.getKey().add(1, 1, 1), 1, 1, 0, 0.1F, true);
                } else {
                    BaseRender.drawString("NC&PP", entry.getKey().getX() + 0.5F, entry.getKey().getY() + 0.5F, entry.getKey().getZ() + 0.5F, Formatting.AQUA.getColorValue(), true);
                    BaseRender.drawBoxWithLine(entry.getKey(), entry.getKey().add(1, 1, 1), 1, 1, 1, 0.1F, true);
                }
            }
        }
    }
    public static void NCUpdate(BlockPos pos){
        if (HashMapHelper.get(BlockUpdateMap,pos)==null){
            //BlockUpdateMap.put(pos,UpdateType.NC);
            HashMapHelper.put(BlockUpdateMap,pos,UpdateType.NC);
        }
        else if(HashMapHelper.get(BlockUpdateMap,pos)==UpdateType.PP) {
            //BlockUpdateMap.put(pos, UpdateType.NC_And_PP);
            HashMapHelper.put(BlockUpdateMap,pos,UpdateType.NC_And_PP);
        } else {
            return;
        }
    }
    public static void PPUpdate(BlockPos pos){
        if (HashMapHelper.get(BlockUpdateMap,pos)==null){
            //BlockUpdateMap.put(pos,UpdateType.PP);
            HashMapHelper.put(BlockUpdateMap,pos,UpdateType.PP);
        }
        else if(HashMapHelper.get(BlockUpdateMap,pos)==UpdateType.NC) {
            //BlockUpdateMap.put(pos, UpdateType.NC_And_PP);
            HashMapHelper.put(BlockUpdateMap,pos,UpdateType.NC_And_PP);
        }
        else {
            return;
        }
    }




    public static void List(){
        for (Map.Entry<BlockPos, UpdateType> entry:BlockUpdateMap.entrySet()) {
            Messenger.print_server_message(CarpetServer.minecraft_server,Messenger.c("  "+entry.getValue().toString(),Messenger.tp("Update",entry.getKey())));
        }
    }

    public static void ClearHashMap(){
        BlockUpdateMap.clear();
    }
}
