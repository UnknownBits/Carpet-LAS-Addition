package lazyalienserver.carpetlasaddition.mixin;

import lazyalienserver.carpetlasaddition.CarpetLASSetting;
import lazyalienserver.carpetlasaddition.utils.ChunkUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.entity.projectile.thrown.ThrownEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ThrownEntity.class)
public abstract class ThrownEntityMixin extends Entity
{
    private ThrownEntityMixin(EntityType<?> type, World world)
    {
        super(type, world);
    }

    @Inject(at = @At("HEAD"),method ="tick()V")
    private void chunkLoadNextChunk(CallbackInfo ci)
    {
        if (CarpetLASSetting.enderPearlChunkLoading &&
                ((Object) this) instanceof EnderPearlEntity)
        {
            ChunkUtils.addEnderPearlChunkTicket(this);
        }
    }
}
