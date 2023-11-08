package lazyalienserver.carpetlasaddition.mixin;

import lazyalienserver.carpetlasaddition.logging.Loggers.BlockUpdateLogger.BlockUpdateLogger;
import lazyalienserver.carpetlasaddition.logging.Loggers.BlockUpdateLogger.UpdateType;
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

@Mixin(AbstractBlock.AbstractBlockState.class)
public class AbstractBlockMixin {
    @Inject(at=@At("HEAD"),method = "getStateForNeighborUpdate")
    //PP Update
    public void getStateForNeighborUpdate(Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos, CallbackInfoReturnable<BlockState> cir){
            World world1 = (World) world;
            //Messenger.print_server_message(CarpetServer.minecraft_server, Messenger.c("  " + "PP:", Messenger.tp("Update", pos)));
            BlockUpdateLogger.addBlockUpdate(world1.getRegistryKey().getValue(),new BlockPos(pos), UpdateType.PP);
            //BlockUpdateLogger.PPUpdate(pos);

    }
    @Inject(at=@At("HEAD"),method = "neighborUpdate")
    //NC Update
    public void neighborUpdate(World world, BlockPos pos, Block block, BlockPos posFrom, boolean notify, CallbackInfo ci){
            //Messenger.print_server_message(CarpetServer.minecraft_server, Messenger.c("  " + "NC:", Messenger.tp("Update", pos)));
            BlockUpdateLogger.addBlockUpdate(world.getRegistryKey().getValue(),pos, UpdateType.NC);
            //BlockUpdateLogger.NCUpdate(pos);

    }
}
