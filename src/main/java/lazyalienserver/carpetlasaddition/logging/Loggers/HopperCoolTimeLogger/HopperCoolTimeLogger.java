package lazyalienserver.carpetlasaddition.logging.Loggers.HopperCoolTimeLogger;

import lazyalienserver.carpetlasaddition.logging.LoggerRegistry;
import lazyalienserver.carpetlasaddition.render.BaseRender;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HopperCoolTimeLogger{
    /*
    * qwq呜呜不要看屎山了啦
    * 呜呜呜
    * */
    private static final Map<BlockPos, Integer> hopperBlockCoolTime = new ConcurrentHashMap<>();

    public static void RenderHopperCoolTime() {
        if(isLoggerHopperCoolTime()) {
            BlockPos pos;
            for (Map.Entry<BlockPos, Integer> entry : hopperBlockCoolTime.entrySet()) {
                pos = entry.getKey();
                double x = (double)pos.getX() + 0.5D;
                double y = (double)pos.getY() + 0.5D;
                double z = (double)pos.getZ() + 0.5D;
                checkState(entry,x,y,z);
                if (entry.getValue() > 0) {
                    BaseRender.drawString(entry.getValue().toString(), pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, Formatting.RED.getColorValue(),true,0.03F);
                } else {
                    BaseRender.drawString(entry.getValue().toString(), pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, Formatting.GREEN.getColorValue(),true,0.03F);
                }
            }
        }
    }

    private static void checkState(Map.Entry<BlockPos, Integer> entry,double x,double y,double z) {
            //检查是否在玩家最大渲染半径内，并且检查方块是否为漏斗
            if (entry != null &&MinecraftClient.getInstance().player.squaredDistanceTo(x, y, z) <= BaseRender.getMaxRenderDistance() * BaseRender.getMaxRenderDistance()&& MinecraftClient.getInstance().world.getBlockState(entry.getKey()).getBlock() == Blocks.HOPPER) {
                return;
            } else {
                hopperBlockCoolTime.remove(entry.getKey());
            }
    }

    public static Map<BlockPos, Integer> getHopperBlockCoolTime() {
        return hopperBlockCoolTime;
    }

    public static boolean isLoggerHopperCoolTime(){
        return LoggerRegistry.__hopperCoolTime;
    }
}
