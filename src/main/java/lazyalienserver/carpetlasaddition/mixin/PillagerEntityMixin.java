package lazyalienserver.carpetlasaddition.mixin;



import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@Mixin(LivingEntity.class)
public abstract class PillagerEntityMixin extends Entity {
    long spawnTime;
    public PillagerEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }
    @Inject(at = @At("HEAD"),method = "readFromPacket")
    private void readFromPacket(CallbackInfo ci){
        Logger.getGlobal().setLevel(Level.ALL);
        spawnTime=world.getTime();
        Logger.getLogger("PillagerMixin.spawn").info(Objects.toString(world.getTime()));
        Logger.getGlobal().setLevel(Level.OFF);
    }
    @Inject(at = @At("HEAD"),method = "onDeath")
    private void onDeath(CallbackInfo ci){
        Logger.getGlobal().setLevel(Level.ALL);
        Logger.getLogger("PillagerMixin.dead").info(Objects.toString(world.getTime()));
        Logger.getLogger("PillagerMixin.period").info(Objects.toString((world.getTime()-spawnTime)));
        Logger.getGlobal().setLevel(Level.OFF);
    }
}
