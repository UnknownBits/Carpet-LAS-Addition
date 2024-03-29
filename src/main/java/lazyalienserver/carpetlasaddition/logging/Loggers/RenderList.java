package lazyalienserver.carpetlasaddition.logging.Loggers;

import lazyalienserver.carpetlasaddition.network.NetWorkPacket;
import net.minecraft.network.PacketByteBuf;

import java.util.HashMap;
import java.util.Map;

public class RenderList {
    private static final Map<String,Runnable> renderClearList = new HashMap<>();
    public static void add(String ID,Runnable runnable) {
        renderClearList.put(ID,runnable);
    }
    public static Map<String,Runnable> getRenderClearList() {
        return renderClearList;
    }

    public static void clear(PacketByteBuf buf) {
        String ID=NetWorkPacket.readStringPacket(buf);
        if(ID.equals("all")){
            for (Map.Entry<String, Runnable> entry : renderClearList.entrySet()){
                entry.getValue().run();
            }
            return;
        }
        for (Map.Entry<String, Runnable> entry : renderClearList.entrySet()){
            if(entry.getKey().equals(ID)){
                entry.getValue().run();
            }
        }
    }
}
