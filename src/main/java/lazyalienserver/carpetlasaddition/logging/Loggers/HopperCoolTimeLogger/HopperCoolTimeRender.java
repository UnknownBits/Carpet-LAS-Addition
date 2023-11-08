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
        RenderList.add(HopperCoolTimeLogger.ID,HopperCoolTimeRender.coolTimeMap::clear);
    }

    public static void render(MatrixStack matrices) {
        BlockPos pos;
        for (Map.Entry<BlockPos, Integer> entry : coolTimeMap.entrySet()) {
            pos = entry.getKey();
            if (entry.getValue() > 0) {
                BaseRender.drawString(entry.getValue().toString(), pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, Formatting.RED.getColorValue(), true, 0.03F);
            } else if(entry.getValue() == 0){
                BaseRender.drawString(entry.getValue().toString(), pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, Formatting.GREEN.getColorValue(), true, 0.03F);
            }else coolTimeMap.remove(pos);
        }
    }

    public static void getServerLogger(PacketByteBuf buf) {
        String[] strings = NetWorkPacket.readStringPacket(buf).split(", ", 4);
        coolTimeMap.put(new BlockPos(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]), Integer.parseInt(strings[2])), Integer.parseInt(strings[3]));
    }
}
