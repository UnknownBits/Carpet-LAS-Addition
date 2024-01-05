package lazyalienserver.carpetlasaddition.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Fertilizable;
import net.minecraft.block.WetSpongeBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import lazyalienserver.carpetlasaddition.CarpetLASSetting;
import net.minecraft.world.biome.BiomeKeys;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.Random;
@Mixin(WetSpongeBlock.class)
public class WetSpongeBlockMixin implements Fertilizable {
    @Override
    public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
        return CarpetLASSetting.SpongeRespawn;
    }
    @Unique
    private static boolean haveWaterAround(World world, BlockPos pos) {
        //TODO 纵向检测
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if ((i == 0 && j == 0)||i*j!=0) {
                    continue;
                }
                if (world.getBlockState(pos.add(i, 0, j)).getBlock().equals(Blocks.WATER)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return CarpetLASSetting.SpongeRespawn&&(world.getBiome(pos).getKey().isPresent() && (world.getBiome(pos).getKey().get().equals(BiomeKeys.WARM_OCEAN) || world.getBiome(pos).getKey().get().equals(BiomeKeys.LUKEWARM_OCEAN)))&&haveWaterAround(world,pos);
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        //TODO 纵向生成
        if(CarpetLASSetting.SpongeRespawn){
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if ((i == 0 && j == 0)||i*j!=0) {
                        continue;
                    }
                    if (world.getBlockState(pos.add(i, 0, j)).getBlock().equals(Blocks.WATER)&&random.nextFloat()>0.99) {
                        world.setBlockState(pos.add(i, 0, j), Blocks.WET_SPONGE.getDefaultState());
                        return;
                    }
                }
            }
        }
    }
}
