package lazyalienserver.carpetlasaddition.mixin;

import carpet.CarpetServer;
import carpet.utils.Messenger;
import lazyalienserver.carpetlasaddition.logging.LoggerRegistry;
import lazyalienserver.carpetlasaddition.logging.Loggers.BlockUpdateLogger.BlockUpdateLogger;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBlock.class)
public class AbstractBlockMixin {
    @Inject(at=@At("HEAD"),method = "getStateForNeighborUpdate")
    //PP Update
    public void getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos, CallbackInfoReturnable<BlockState> cir){
        if (LoggerRegistry.__blockUpdate) {
            Messenger.print_server_message(CarpetServer.minecraft_server, Messenger.c("  " + "HashCode:" + pos.hashCode() + "PP:", Messenger.tp("Update", pos)));
            BlockUpdateLogger.PPUpdate(pos);
        }
    }
    @Inject(at=@At("HEAD"),method = "neighborUpdate")
    //NC Update
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify, CallbackInfo ci){
        if (LoggerRegistry.__blockUpdate) {
            Messenger.print_server_message(CarpetServer.minecraft_server, Messenger.c("  " + "HashCode:" + pos.hashCode() + "NC:", Messenger.tp("Update", pos)));
            BlockUpdateLogger.NCUpdate(pos);
        }
    }
}
