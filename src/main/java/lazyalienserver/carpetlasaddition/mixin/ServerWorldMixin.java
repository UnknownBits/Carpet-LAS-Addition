package lazyalienserver.carpetlasaddition.mixin;

import lazyalienserver.carpetlasaddition.logging.Loggers.BlockUpdateLogger.BlockUpdateLogger;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerWorld.class)
public class ServerWorldMixin {
    @Inject(at=@At("HEAD"),method = "tickTime")
    public void tickInTickFreeze(CallbackInfo ci){
        //呜呜我也不知道放哪qwq,别骂别骂┭┮﹏┭┮
        BlockUpdateLogger.tickInTickFreeze();
    }
}
