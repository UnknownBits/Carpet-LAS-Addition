package lazyalienserver.carpetlasaddition.logging.Loggers.BlockUpdateLogger;

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
public class BlockUpdateRender {
    public static final Map<BlockPos, Integer> blockUpdateMap = new ConcurrentHashMap<>();

    static {
        RenderList.add(BlockUpdateLogger.ID,BlockUpdateRender.blockUpdateMap::clear);
    }

    public static void render(MatrixStack matrices) {

        //TODO 实际渲染有偏差，可能与锂钠有关系
        for (Map.Entry<BlockPos, Integer> entry : blockUpdateMap.entrySet()) {
            if (entry.getValue() == UpdateType.NC.s) {
                BaseRender.drawString(matrices,"NC", new BlockPos(entry.getKey().getX(), entry.getKey().getY(), entry.getKey().getZ()), Formatting.DARK_AQUA.getColorValue(),  0.025F);
                BaseRender.drawBoxWithLine(matrices,entry.getKey(), entry.getKey().add(1, 1, 1), 1, 0, 0, 0.1F, true);
            } else if (entry.getValue() == UpdateType.PP.s) {
                BaseRender.drawString(matrices,"PP", new BlockPos(entry.getKey().getX(), entry.getKey().getY() , entry.getKey().getZ()), Formatting.DARK_AQUA.getColorValue(), 0.025F);
                BaseRender.drawBoxWithLine(matrices,entry.getKey(), entry.getKey().add(1, 1, 1), 1, 1, 0, 0.1F, true);
            } else {
                BaseRender.drawString(matrices,"NC&PP", new BlockPos(entry.getKey().getX(), entry.getKey().getY(), entry.getKey().getZ()), Formatting.DARK_AQUA.getColorValue(), 0.025F);
                BaseRender.drawBoxWithLine(matrices,entry.getKey(), entry.getKey().add(1, 1, 1), 0.5F, 0.5F, 1, 0.1F, true);
            }
        }
    }

    public static void getServerLogger(PacketByteBuf buf) {
        String[] strings = NetWorkPacket.readStringPacket(buf).split(", ", 4);
        blockUpdateMap.put(new BlockPos(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]), Integer.parseInt(strings[2])), Integer.parseInt(strings[3]));
    }
}
