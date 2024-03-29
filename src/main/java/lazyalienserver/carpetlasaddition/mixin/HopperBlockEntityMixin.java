package lazyalienserver.carpetlasaddition.mixin;

import lazyalienserver.carpetlasaddition.logging.Loggers.HopperCoolTimeLogger.HopperCoolTimeLogger;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HopperBlockEntity.class)
public class HopperBlockEntityMixin {

    @Inject(at=@At(value = "INVOKE",target = "Lnet/minecraft/world/World;getTime()J"),method = "serverTick")
    private static void serverTick(World world, BlockPos pos, BlockState state, HopperBlockEntity blockEntity, CallbackInfo ci){
//        if(HopperCoolTimeLogger.isLoggerHopperCoolTime()&&blockEntity.transferCooldown<0) {
//            HopperCoolTimeLogger.getHopperBlockCoolTime().remove(pos);
//        }
//        else HopperCoolTimeLogger.getHopperBlockCoolTime().put(pos, blockEntity.transferCooldown);
        HopperCoolTimeLogger.addHopperCoolTime(world,pos,blockEntity);
    }
}
