package lazyalienserver.carpetlasaddition.mixin;

import lazyalienserver.carpetlasaddition.CarpetLASSetting;
import lazyalienserver.carpetlasaddition.utils.ChunkUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.NoteBlock;
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
    @Inject(at=@At("RETURN"),method = "neighborUpdate")
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify, CallbackInfo ci){
        if(CarpetLASSetting.NC_noteBlockLoadChunk &&world.isReceivingRedstonePower(pos)&&state.get(NOTE)==24){
            ChunkUtils.addNCNoteBlockChunkTicket(world,pos);
        }
        if(CarpetLASSetting.NoteBlockLoadChunk &&!world.isReceivingRedstonePower(pos)&&state.get(NOTE)==24){
            ChunkUtils.RemoveNoteBlockChunkLoad(world, pos);
        }
    }
    @Inject(at=@At("HEAD"),method = "playNote")
    public void playNote(World world, BlockPos pos, CallbackInfo ci){
        if(CarpetLASSetting.NoteBlockLoadChunk &&world.isReceivingRedstonePower(pos)&&world.getBlockState(pos).get(NOTE)==24){
            ChunkUtils.addNoteBlockChunkLoad(world,pos);
        }
    }
}
