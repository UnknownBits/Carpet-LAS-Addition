package lazyalienserver.carpetlasaddition.mixin;

import lazyalienserver.carpetlasaddition.utils.ChunkUtils;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(World.class)
public abstract class WorldMixin {

    @Shadow
    public abstract BlockState getBlockState(BlockPos pos);
    @Inject(at = @At("HEAD"), method = "breakBlock")
    public void breakBlock(BlockPos pos, boolean drop, Entity breakingEntity, int maxUpdateDepth, CallbackInfoReturnable<Boolean> cir) {
        if (this.getBlockState(pos).getBlock() == Blocks.NOTE_BLOCK) {
            ChunkUtils.RemoveNoteBlockChunkLoad((World) (Object) this, pos);
        }
    }
    @Inject(at = @At("HEAD"), method = "removeBlock")
    public void removeBlock(BlockPos pos, boolean move, CallbackInfoReturnable<Boolean> cir) {
        if (this.getBlockState(pos).getBlock() == Blocks.NOTE_BLOCK) {
            ChunkUtils.RemoveNoteBlockChunkLoad((World) (Object) this, pos);
        }
    }
}
