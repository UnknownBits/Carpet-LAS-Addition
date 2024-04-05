package lazyalienserver.carpetlasaddition.logging.Loggers.HopperCoolTimeLogger;

import lazyalienserver.carpetlasaddition.logging.Loggers.RenderList;
import lazyalienserver.carpetlasaddition.network.NetWorkPacket;
import lazyalienserver.carpetlasaddition.render.BaseRender;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Environment(EnvType.CLIENT)
public class HopperCoolTimeRender {

    public static final Map<BlockPos, Integer> coolTimeMap = new ConcurrentHashMap<>();

    static {
        //RenderList.add(HopperCoolTimeLogger.ID,HopperCoolTimeRender.coolTimeMap::clear);
    }

    public static void render(MatrixStack matrices) {

        //TODO 实际渲染有偏差，可能与锂钠有关系
        BlockPos pos;
        for (Map.Entry<BlockPos, Integer> entry : coolTimeMap.entrySet()) {
            pos = entry.getKey();
            if (entry.getValue() > 0) {
                BaseRender.drawString(matrices,entry.getValue().toString(), new BlockPos(pos.getX(), pos.getY(), pos.getZ()), Formatting.RED.getColorValue(),  0.03F);
            } else if(entry.getValue() == 0){
                BaseRender.drawString(matrices,entry.getValue().toString(), new BlockPos(pos.getX(), pos.getY(), pos.getZ()), Formatting.GREEN.getColorValue(),  0.03F);
            }else coolTimeMap.remove(pos);
        }
    }

    public static void getServerLogger(PacketByteBuf buf) {
        String[] strings = NetWorkPacket.readStringPacket(buf).split(", ", 4);
        coolTimeMap.put(new BlockPos(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]), Integer.parseInt(strings[2])), Integer.parseInt(strings[3]));
    }
}
