package lazyalienserver.carpetlasaddition.helper;

import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.fabricmc.loader.impl.util.log.LogLevel;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
public class LivingEntityMapsManager {

    static LivingEntityMapsManager livingEntityMapsManager=new LivingEntityMapsManager();
    private HashMap<UUID,Long> StartTime=new HashMap<UUID,Long>();
    private HashMap<EntityType<? extends LivingEntity>,Long> AverageAliveTime=new HashMap<EntityType<? extends LivingEntity>,Long>();
    private HashMap<EntityType<? extends LivingEntity>,Long> AverageSeconds=new HashMap<EntityType<? extends LivingEntity>,Long>();
    private static HashMap<EntityType<? extends LivingEntity>,Long> setEntitySeconds(EntityType<? extends LivingEntity> entityType, HashMap<EntityType<? extends LivingEntity>,Long> averageSeconds)
    {
        if(averageSeconds.get(entityType)==null){
            averageSeconds.put(entityType,1L);
            return averageSeconds;
        }
        else {
            averageSeconds.put(entityType,(averageSeconds.get(entityType)+1L));
            return averageSeconds;
        }
    }
    private static Long Average(Long Range,Long AverageAliveTime,HashMap<EntityType<? extends LivingEntity>,Long> averageSeconds,EntityType entityType)
    {
        return ((AverageAliveTime*(averageSeconds.get(entityType)-1)+Range)/(averageSeconds.get(entityType)));
    }

    public void putStartTime(UUID uuid,Long startTime)
    {
        StartTime.put(uuid,startTime);
    }
    public void resetEntityAliveTime(EntityType<? extends LivingEntity> entityType,UUID uuid,Long DeathTime)
    {
        if(this.StartTime.get(uuid)!=null){
            AverageSeconds= setEntitySeconds(entityType,AverageSeconds);
            AverageAliveTime.put(entityType,Average((DeathTime-StartTime.get(uuid)),AverageAliveTime.get(entityType),AverageSeconds,entityType));
        }
    }

    public void AllAverageAliveTime(){
        if(AverageAliveTime!=null)
        {
            Log.log(LogLevel.INFO,LogCategory.TEST,"Success");
            for(Map.Entry<EntityType<? extends LivingEntity>,Long> entry:AverageAliveTime.entrySet()) {
                Log.log(LogLevel.INFO, LogCategory.LOG, "key:" + entry.getKey().toString() + "Value:" + entry.getValue().toString());
            }
        }
        else {
            Log.log(LogLevel.INFO,LogCategory.TEST,"null?");
        }
    }

    public static LivingEntityMapsManager getLivingEntityMapsManager(){
        return livingEntityMapsManager;
    }

}
