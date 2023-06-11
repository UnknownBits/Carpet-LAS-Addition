package lazyalienserver.carpetlasaddition.mixin;



import lazyalienserver.carpetlasaddition.CarpetLASSetting;
import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.fabricmc.loader.impl.util.log.LogLevel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.PillagerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    long spawnTime;
    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(at = @At("HEAD"),method = "readFromPacket")
    private void readFromPacket(CallbackInfo ci){
        if(CarpetLASSetting.PillagerAliveTime&&this.getType()==EntityType.PILLAGER) {
            spawnTime = world.getTime();
            Log.log(LogLevel.INFO, LogCategory.LOG,"PillagerMixin.spawn");
        }
    }
    @Inject(at = @At("HEAD"),method = "onDeath")
    private void onDeath(CallbackInfo ci){
        if(CarpetLASSetting.PillagerAliveTime&&this.getType()==EntityType.PILLAGER) {
            Logger.getGlobal().setLevel(Level.INFO);
            Log.log(LogLevel.INFO, LogCategory.LOG,Objects.toString((world.getTime() - spawnTime)));
            Logger.getGlobal().setLevel(Level.OFF);
        }
    }
}
