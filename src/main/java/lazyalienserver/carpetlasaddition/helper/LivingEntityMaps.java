package lazyalienserver.carpetlasaddition.helper;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import java.util.HashMap;
import java.util.UUID;


public class LivingEntityMaps {

    private HashMap<UUID,Long> StartTime=new HashMap<>();
    private HashMap<EntityType<? extends LivingEntity>,Long> AverageAliveTime=new HashMap<>();
    public void putStartTime(UUID uuid,Long startTime)
    {
        StartTime.put(uuid,startTime);
    }
    public void resetEntityAliveTime(EntityType<? extends LivingEntity> entityType,UUID uuid,Long DeathTime)
    {
        AverageAliveTime.put(entityType,Average((DeathTime-StartTime.get(uuid)),AverageAliveTime.get(entityType)));
    }
    private static Long Average(Long... longs)
    {
        //迭代法求平均值
        return null;
    }

}
