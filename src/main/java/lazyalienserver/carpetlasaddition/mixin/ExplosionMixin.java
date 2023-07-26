package lazyalienserver.carpetlasaddition.mixin;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lazyalienserver.carpetlasaddition.utils.ChunkUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Iterator;

@Mixin(Explosion.class)
public class ExplosionMixin {
    @Shadow
    @Final
    private World world;

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;I)Z"), method = "affectWorld", locals = LocalCapture.CAPTURE_FAILHARD)
    public void affectWorld(boolean particles, CallbackInfo ci, boolean bl, ObjectArrayList objectArrayList, Iterator var4, BlockPos blockPos, BlockState blockState, Block block) {
        if (this.world.getBlockState(blockPos).getBlock() == Blocks.NOTE_BLOCK) {
            ChunkUtils.RemoveNoteBlockChunkLoad(world, blockPos);
        }
    }
}
