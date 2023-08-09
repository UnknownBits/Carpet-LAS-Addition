package lazyalienserver.carpetlasaddition.mixin;

import lazyalienserver.carpetlasaddition.CarpetLASSetting;
import lazyalienserver.carpetlasaddition.utils.ChunkUtils;
import net.minecraft.block.*;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(NoteBlock.class)
public class NoteBlockMixin {
    @Shadow @Final public static IntProperty NOTE;
    @Inject(at=@At("RETURN"),method = "playNote")
    public void neighborUpdate(World world, BlockPos pos, CallbackInfo ci){
        if(CarpetLASSetting.NoteBlockLoadChunk &&world.isReceivingRedstonePower(pos)&&world.getBlockState(pos).get(NOTE)==24&&world.getBlockState(pos.up()).getBlock() == Blocks.POTTED_BLUE_ORCHID){
            ChunkUtils.addNCNoteBlockChunkTicket(world,pos);
        }
    }
}
