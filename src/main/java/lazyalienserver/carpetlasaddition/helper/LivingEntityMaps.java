package lazyalienserver.carpetlasaddition.helper;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;

import java.util.HashMap;


public class LivingEntityMaps {

    private static HashMap<EntityType<? extends LivingEntity>,Long> StartTime=new HashMap<>();
    private static HashMap<EntityType<? extends LivingEntity>,Long> AverageAliveTime=new HashMap<>();
    public static void putStartTime(EntityType<? extends LivingEntity> Type,Long Time)
    {
        StartTime.put(Type,Time);
    }
    public static void putDeadTime(EntityType<? extends LivingEntity> Type,Long DeadTime)
    {
        AverageAliveTime.put(Type,(DeadTime-StartTime.get(Type)));
    }
    public static Long getAverageAliveTime(EntityType<? extends LivingEntity> Type){

        return AverageAliveTime.get(Type);
    }

}
