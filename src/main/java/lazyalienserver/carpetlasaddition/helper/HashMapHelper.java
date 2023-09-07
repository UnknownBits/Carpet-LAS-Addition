package lazyalienserver.carpetlasaddition.helper;

import net.minecraft.util.math.BlockPos;

import java.util.Map;

/**
 * 只是为了测试 BlockPos存储HashMap qwq。————笨比Fanzhi
 * */
public class HashMapHelper {
    public static <T> void put(Map<BlockPos,T> map,BlockPos pos,T Value){
        for (Map.Entry<BlockPos,?> entry: map.entrySet()){
            if (entry.getKey().equals(pos)){
                map.remove(entry.getKey());
                map.put(pos,Value);
                return;
            }
        }
        map.put(pos,Value);
    }

    public static <T> Object get(Map<BlockPos,T> map,BlockPos pos){
        for (Map.Entry<BlockPos,?> entry: map.entrySet()){
            if (entry.getKey().equals(pos)){
                return entry.getValue();
            }
        }
        return null;
    }
}
