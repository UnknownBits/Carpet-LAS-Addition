package lazyalienserver.carpetlasaddition.logging.Loggers.BlockUpdateLogger;

import carpet.CarpetServer;
import carpet.logging.Logger;
import carpet.utils.Messenger;
import lazyalienserver.carpetlasaddition.logging.LoggerRegistry;
import lazyalienserver.carpetlasaddition.render.BaseRender;
import net.minecraft.text.BaseText;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BlockUpdateLogger {
    private static final Map<BlockPos, UpdateType> BlockUpdateMap = new ConcurrentHashMap<>();
    private static Logger log=carpet.logging.LoggerRegistry.getLogger("blockUpdate");


    public static void RenderBlockUpDate(){
        if (LoggerRegistry.__blockUpdate) {
            for (Map.Entry<BlockPos, UpdateType> entry : BlockUpdateMap.entrySet()) {
                if (entry.getValue() == UpdateType.NC) {
                    BaseRender.drawString("NC", entry.getKey().getX() + 0.5F, entry.getKey().getY() + 0.5F, entry.getKey().getZ() + 0.5F, Formatting.DARK_GRAY.getColorValue(), true,0.02F);
                    BaseRender.drawBoxWithLine(entry.getKey(), entry.getKey().add(1, 1, 1), 1, 0, 0, 0.1F, true);
                } else if (entry.getValue() == UpdateType.PP) {
                    BaseRender.drawString("PP", entry.getKey().getX() + 0.5F, entry.getKey().getY() + 0.5F, entry.getKey().getZ() + 0.5F, Formatting.DARK_GRAY.getColorValue(), true,0.02F);
                    BaseRender.drawBoxWithLine(entry.getKey(), entry.getKey().add(1, 1, 1), 1, 1, 0, 0.1F, true);
                } else {
                    BaseRender.drawString("NC&PP", entry.getKey().getX() + 0.5F, entry.getKey().getY() + 0.5F, entry.getKey().getZ() + 0.5F, Formatting.DARK_GRAY.getColorValue(), true,0.02F);
                    BaseRender.drawBoxWithLine(entry.getKey(), entry.getKey().add(1, 1, 1), 0.5F, 0.5F, 1, 0.1F, true);
                }
            }
        }
    }
    public static void NCUpdate(BlockPos pos){
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

    public static void List(){
        for (Map.Entry<BlockPos, UpdateType> entry:BlockUpdateMap.entrySet()) {
            Messenger.print_server_message(CarpetServer.minecraft_server,Messenger.c("  "+entry.getValue().toString(),Messenger.tp("Update",entry.getKey())));
        }
    }

    public static void ClearHashMap(){
        BlockUpdateMap.clear();
    }
    public static void tickInTickFreeze(){
        ClearHashMap();
    }
   /* public static void tick(){
        log.log((option) -> {
            switch (option) {
                case "brief":
                    return new BaseText[]{Messenger.c()};
                case "full":
                    return new BaseText[]{Messenger.c(new Object[]{"r #" + tntCount, "m @" + gametime, "g : ", "l P ", Messenger.dblf("l", new double[]{this.primedX, this.primedY, this.primedZ}), "w  ", Messenger.dblf("l", new double[]{this.primedAngle.x, this.primedAngle.y, this.primedAngle.z}), "r  E ", Messenger.dblf("r", new double[]{x, y, z})})};
                default:
                    return null;
            }
        });
    }*/
}
