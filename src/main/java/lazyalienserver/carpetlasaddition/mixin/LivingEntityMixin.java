package lazyalienserver.carpetlasaddition.mixin;

import lazyalienserver.carpetlasaddition.CarpetLASSetting;
import lazyalienserver.carpetlasaddition.helper.LivingEntityMapsManager;
import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.fabricmc.loader.impl.util.log.LogLevel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    LivingEntityMapsManager livingEntityMapsManager=LivingEntityMapsManager.getLivingEntityMapsManager();
    long spawnTime;
    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(at = @At("HEAD"),method = "readFromPacket")
    private void readFromPacket(CallbackInfo ci){
        if(CarpetLASSetting.PillagerAliveTime) {
            if(livingEntityMapsManager ==null){
                Log.log(LogLevel.INFO, LogCategory.LOG,"NULL");
            }
            spawnTime = world.getTime();
            livingEntityMapsManager.putStartTime(this.uuid,spawnTime);
        }
    }
    @Inject(at = @At("HEAD"),method = "onDeath")
    private void onDeath(CallbackInfo ci){
        if(CarpetLASSetting.PillagerAliveTime) {
            livingEntityMapsManager.resetEntityAliveTime((EntityType<? extends LivingEntity>)this.getType(),this.uuid, world.getTime());
        }
    }


}
