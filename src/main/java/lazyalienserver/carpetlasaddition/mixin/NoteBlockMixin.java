package lazyalienserver.carpetlasaddition.mixin;

import lazyalienserver.carpetlasaddition.CarpetLASSetting;
import lazyalienserver.carpetlasaddition.utils.ChunkUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.NoteBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ChunkTicketType;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Comparator;


@Mixin(NoteBlock.class)
public class NoteBlockMixin {
    @Shadow @Final public static BooleanProperty POWERED;
    @Shadow @Final public static IntProperty NOTE;
    @Inject(at=@At("RETURN"),method = "neighborUpdate")
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify, CallbackInfo ci){
        if(CarpetLASSetting.NC_noteBlockLoadChunk &&world.isReceivingRedstonePower(pos)&&state.get(NOTE)==24){
            ChunkUtils.addNoteBlockChunkTicket(world,pos);
            System.out.println("Need chunkLoad");
        }
        /*if(CarpetLASSetting.noteBlockLoadChunk && !state.get(POWERED)){
            System.out.println("Need chunkUnLoad");
        }*/
    }
}
