package lazyalienserver.carpetlasaddition.mixin;

import lazyalienserver.carpetlasaddition.CarpetLASSetting;
import net.minecraft.block.BlockState;
import net.minecraft.block.ComposterBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.block.ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE;

@Mixin(ComposterBlock.class)
public class ComposterBlockMixin {
    @Unique
    private static boolean tf=false;
    @Inject(at=@At("HEAD"),method = "onUse")
    public void registerDefaultCompostableItems(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<ActionResult> cir) {
        if (CarpetLASSetting.CompostGradeModification&&!ComposterBlockMixin.tf) {
            ITEM_TO_LEVEL_INCREASE_CHANCE.clear();
            ComposterBlock.registerCompostableItem(1.0f, Items.JUNGLE_LEAVES);
            ComposterBlock.registerCompostableItem(1.0f, Items.OAK_LEAVES);
            ComposterBlock.registerCompostableItem(1.0f, Items.SPRUCE_LEAVES);
            ComposterBlock.registerCompostableItem(1.0f, Items.DARK_OAK_LEAVES);
            ComposterBlock.registerCompostableItem(1.0f, Items.ACACIA_LEAVES);
            ComposterBlock.registerCompostableItem(1.0f, Items.BIRCH_LEAVES);
            ComposterBlock.registerCompostableItem(1.0f, Items.AZALEA_LEAVES);
            ComposterBlock.registerCompostableItem(1.0f, Items.OAK_SAPLING);
            ComposterBlock.registerCompostableItem(1.0f, Items.SPRUCE_SAPLING);
            ComposterBlock.registerCompostableItem(1.0f, Items.BIRCH_SAPLING);
            ComposterBlock.registerCompostableItem(1.0f, Items.JUNGLE_SAPLING);
            ComposterBlock.registerCompostableItem(1.0f, Items.ACACIA_SAPLING);
            ComposterBlock.registerCompostableItem(1.0f, Items.DARK_OAK_SAPLING);
            ComposterBlock.registerCompostableItem(1.0f, Items.BEETROOT_SEEDS);
            ComposterBlock.registerCompostableItem(1.0f, Items.DRIED_KELP);
            ComposterBlock.registerCompostableItem(1.0f, Items.GRASS);
            ComposterBlock.registerCompostableItem(1.0f, Items.KELP);
            ComposterBlock.registerCompostableItem(1.0f, Items.MELON_SEEDS);
            ComposterBlock.registerCompostableItem(1.0f, Items.PUMPKIN_SEEDS);
            ComposterBlock.registerCompostableItem(1.0f, Items.SEAGRASS);
            ComposterBlock.registerCompostableItem(1.0f, Items.SWEET_BERRIES);
            ComposterBlock.registerCompostableItem(1.0f, Items.GLOW_BERRIES);
            ComposterBlock.registerCompostableItem(1.0f, Items.WHEAT_SEEDS);
            ComposterBlock.registerCompostableItem(1.0f, Items.MOSS_CARPET);
            ComposterBlock.registerCompostableItem(1.0f, Items.SMALL_DRIPLEAF);
            ComposterBlock.registerCompostableItem(1.0f, Items.HANGING_ROOTS);
            ComposterBlock.registerCompostableItem(1.0f, Items.DRIED_KELP_BLOCK);
            ComposterBlock.registerCompostableItem(1.0f, Items.TALL_GRASS);
            ComposterBlock.registerCompostableItem(1.0f, Items.FLOWERING_AZALEA_LEAVES);
            ComposterBlock.registerCompostableItem(1.0f, Items.CACTUS);
            ComposterBlock.registerCompostableItem(1.0f, Items.SUGAR_CANE);
            ComposterBlock.registerCompostableItem(1.0f, Items.VINE);
            ComposterBlock.registerCompostableItem(1.0f, Items.NETHER_SPROUTS);
            ComposterBlock.registerCompostableItem(1.0f, Items.WEEPING_VINES);
            ComposterBlock.registerCompostableItem(1.0f, Items.TWISTING_VINES);
            ComposterBlock.registerCompostableItem(1.0f, Items.MELON_SLICE);
            ComposterBlock.registerCompostableItem(1.0f, Items.GLOW_LICHEN);
            ComposterBlock.registerCompostableItem(1.0f, Items.SEA_PICKLE);
            ComposterBlock.registerCompostableItem(1.0f, Items.LILY_PAD);
            ComposterBlock.registerCompostableItem(1.0f, Items.PUMPKIN);
            ComposterBlock.registerCompostableItem(1.0f, Items.CARVED_PUMPKIN);
            ComposterBlock.registerCompostableItem(1.0f, Items.MELON);
            ComposterBlock.registerCompostableItem(1.0f, Items.APPLE);
            ComposterBlock.registerCompostableItem(1.0f, Items.BEETROOT);
            ComposterBlock.registerCompostableItem(1.0f, Items.CARROT);
            ComposterBlock.registerCompostableItem(1.0f, Items.COCOA_BEANS);
            ComposterBlock.registerCompostableItem(1.0f, Items.POTATO);
            ComposterBlock.registerCompostableItem(1.0f, Items.WHEAT);
            ComposterBlock.registerCompostableItem(1.0f, Items.BROWN_MUSHROOM);
            ComposterBlock.registerCompostableItem(1.0f, Items.RED_MUSHROOM);
            ComposterBlock.registerCompostableItem(1.0f, Items.MUSHROOM_STEM);
            ComposterBlock.registerCompostableItem(1.0f, Items.CRIMSON_FUNGUS);
            ComposterBlock.registerCompostableItem(1.0f, Items.WARPED_FUNGUS);
            ComposterBlock.registerCompostableItem(1.0f, Items.NETHER_WART);
            ComposterBlock.registerCompostableItem(1.0f, Items.CRIMSON_ROOTS);
            ComposterBlock.registerCompostableItem(1.0f, Items.WARPED_ROOTS);
            ComposterBlock.registerCompostableItem(1.0f, Items.SHROOMLIGHT);
            ComposterBlock.registerCompostableItem(1.0f, Items.DANDELION);
            ComposterBlock.registerCompostableItem(1.0f, Items.POPPY);
            ComposterBlock.registerCompostableItem(1.0f, Items.BLUE_ORCHID);
            ComposterBlock.registerCompostableItem(1.0f, Items.ALLIUM);
            ComposterBlock.registerCompostableItem(1.0f, Items.AZURE_BLUET);
            ComposterBlock.registerCompostableItem(1.0f, Items.RED_TULIP);
            ComposterBlock.registerCompostableItem(1.0f, Items.ORANGE_TULIP);
            ComposterBlock.registerCompostableItem(1.0f, Items.WHITE_TULIP);
            ComposterBlock.registerCompostableItem(1.0f, Items.PINK_TULIP);
            ComposterBlock.registerCompostableItem(1.0f, Items.OXEYE_DAISY);
            ComposterBlock.registerCompostableItem(1.0f, Items.CORNFLOWER);
            ComposterBlock.registerCompostableItem(1.0f, Items.LILY_OF_THE_VALLEY);
            ComposterBlock.registerCompostableItem(1.0f, Items.WITHER_ROSE);
            ComposterBlock.registerCompostableItem(1.0f, Items.FERN);
            ComposterBlock.registerCompostableItem(1.0f, Items.SUNFLOWER);
            ComposterBlock.registerCompostableItem(1.0f, Items.LILAC);
            ComposterBlock.registerCompostableItem(1.0f, Items.ROSE_BUSH);
            ComposterBlock.registerCompostableItem(1.0f, Items.PEONY);
            ComposterBlock.registerCompostableItem(1.0f, Items.LARGE_FERN);
            ComposterBlock.registerCompostableItem(1.0f, Items.SPORE_BLOSSOM);
            ComposterBlock.registerCompostableItem(1.0f, Items.AZALEA);
            ComposterBlock.registerCompostableItem(1.0f, Items.MOSS_BLOCK);
            ComposterBlock.registerCompostableItem(1.0f, Items.BIG_DRIPLEAF);
            ComposterBlock.registerCompostableItem(1.0f, Items.HAY_BLOCK);
            ComposterBlock.registerCompostableItem(1.0f, Items.BROWN_MUSHROOM_BLOCK);
            ComposterBlock.registerCompostableItem(1.0f, Items.RED_MUSHROOM_BLOCK);
            ComposterBlock.registerCompostableItem(1.0f, Items.NETHER_WART_BLOCK);
            ComposterBlock.registerCompostableItem(1.0f, Items.WARPED_WART_BLOCK);
            ComposterBlock.registerCompostableItem(1.0f, Items.FLOWERING_AZALEA);
            ComposterBlock.registerCompostableItem(1.0f, Items.BREAD);
            ComposterBlock.registerCompostableItem(1.0f, Items.BAKED_POTATO);
            ComposterBlock.registerCompostableItem(1.0f, Items.COOKIE);
            ComposterBlock.registerCompostableItem(1.0f, Items.CAKE);
            ComposterBlock.registerCompostableItem(1.0f, Items.PUMPKIN_PIE);
            tf=true;
        } else if (!CarpetLASSetting.CompostGradeModification&&ComposterBlockMixin.tf) {
            ComposterBlock.registerCompostableItem(0.3f, Items.JUNGLE_LEAVES);
            ComposterBlock.registerCompostableItem(0.3f, Items.OAK_LEAVES);
            ComposterBlock.registerCompostableItem(0.3f, Items.SPRUCE_LEAVES);
            ComposterBlock.registerCompostableItem(0.3f, Items.DARK_OAK_LEAVES);
            ComposterBlock.registerCompostableItem(0.3f, Items.ACACIA_LEAVES);
            ComposterBlock.registerCompostableItem(0.3f, Items.BIRCH_LEAVES);
            ComposterBlock.registerCompostableItem(0.3f, Items.AZALEA_LEAVES);
            ComposterBlock.registerCompostableItem(0.3f, Items.OAK_SAPLING);
            ComposterBlock.registerCompostableItem(0.3f, Items.SPRUCE_SAPLING);
            ComposterBlock.registerCompostableItem(0.3f, Items.BIRCH_SAPLING);
            ComposterBlock.registerCompostableItem(0.3f, Items.JUNGLE_SAPLING);
            ComposterBlock.registerCompostableItem(0.3f, Items.ACACIA_SAPLING);
            ComposterBlock.registerCompostableItem(0.3f, Items.DARK_OAK_SAPLING);
            ComposterBlock.registerCompostableItem(0.3f, Items.BEETROOT_SEEDS);
            ComposterBlock.registerCompostableItem(0.3f, Items.DRIED_KELP);
            ComposterBlock.registerCompostableItem(0.3f, Items.GRASS);
            ComposterBlock.registerCompostableItem(0.3f, Items.KELP);
            ComposterBlock.registerCompostableItem(0.3f, Items.MELON_SEEDS);
            ComposterBlock.registerCompostableItem(0.3f, Items.PUMPKIN_SEEDS);
            ComposterBlock.registerCompostableItem(0.3f, Items.SEAGRASS);
            ComposterBlock.registerCompostableItem(0.3f, Items.SWEET_BERRIES);
            ComposterBlock.registerCompostableItem(0.3f, Items.GLOW_BERRIES);
            ComposterBlock.registerCompostableItem(0.3f, Items.WHEAT_SEEDS);
            ComposterBlock.registerCompostableItem(0.3f, Items.MOSS_CARPET);
            ComposterBlock.registerCompostableItem(0.3f, Items.SMALL_DRIPLEAF);
            ComposterBlock.registerCompostableItem(0.3f, Items.HANGING_ROOTS);
            ComposterBlock.registerCompostableItem(0.5f, Items.DRIED_KELP_BLOCK);
            ComposterBlock.registerCompostableItem(0.5f, Items.TALL_GRASS);
            ComposterBlock.registerCompostableItem(0.5f, Items.FLOWERING_AZALEA_LEAVES);
            ComposterBlock.registerCompostableItem(0.5f, Items.CACTUS);
            ComposterBlock.registerCompostableItem(0.5f, Items.SUGAR_CANE);
            ComposterBlock.registerCompostableItem(0.5f, Items.VINE);
            ComposterBlock.registerCompostableItem(0.5f, Items.NETHER_SPROUTS);
            ComposterBlock.registerCompostableItem(0.5f, Items.WEEPING_VINES);
            ComposterBlock.registerCompostableItem(0.5f, Items.TWISTING_VINES);
            ComposterBlock.registerCompostableItem(0.5f, Items.MELON_SLICE);
            ComposterBlock.registerCompostableItem(0.5f, Items.GLOW_LICHEN);
            ComposterBlock.registerCompostableItem(0.65f, Items.SEA_PICKLE);
            ComposterBlock.registerCompostableItem(0.65f, Items.LILY_PAD);
            ComposterBlock.registerCompostableItem(0.65f, Items.PUMPKIN);
            ComposterBlock.registerCompostableItem(0.65f, Items.CARVED_PUMPKIN);
            ComposterBlock.registerCompostableItem(0.65f, Items.MELON);
            ComposterBlock.registerCompostableItem(0.65f, Items.APPLE);
            ComposterBlock.registerCompostableItem(0.65f, Items.BEETROOT);
            ComposterBlock.registerCompostableItem(0.65f, Items.CARROT);
            ComposterBlock.registerCompostableItem(0.65f, Items.COCOA_BEANS);
            ComposterBlock.registerCompostableItem(0.65f, Items.POTATO);
            ComposterBlock.registerCompostableItem(0.65f, Items.WHEAT);
            ComposterBlock.registerCompostableItem(0.65f, Items.BROWN_MUSHROOM);
            ComposterBlock.registerCompostableItem(0.65f, Items.RED_MUSHROOM);
            ComposterBlock.registerCompostableItem(0.65f, Items.MUSHROOM_STEM);
            ComposterBlock.registerCompostableItem(0.65f, Items.CRIMSON_FUNGUS);
            ComposterBlock.registerCompostableItem(0.65f, Items.WARPED_FUNGUS);
            ComposterBlock.registerCompostableItem(0.65f, Items.NETHER_WART);
            ComposterBlock.registerCompostableItem(0.65f, Items.CRIMSON_ROOTS);
            ComposterBlock.registerCompostableItem(0.65f, Items.WARPED_ROOTS);
            ComposterBlock.registerCompostableItem(0.65f, Items.SHROOMLIGHT);
            ComposterBlock.registerCompostableItem(0.65f, Items.DANDELION);
            ComposterBlock.registerCompostableItem(0.65f, Items.POPPY);
            ComposterBlock.registerCompostableItem(0.65f, Items.BLUE_ORCHID);
            ComposterBlock.registerCompostableItem(0.65f, Items.ALLIUM);
            ComposterBlock.registerCompostableItem(0.65f, Items.AZURE_BLUET);
            ComposterBlock.registerCompostableItem(0.65f, Items.RED_TULIP);
            ComposterBlock.registerCompostableItem(0.65f, Items.ORANGE_TULIP);
            ComposterBlock.registerCompostableItem(0.65f, Items.WHITE_TULIP);
            ComposterBlock.registerCompostableItem(0.65f, Items.PINK_TULIP);
            ComposterBlock.registerCompostableItem(0.65f, Items.OXEYE_DAISY);
            ComposterBlock.registerCompostableItem(0.65f, Items.CORNFLOWER);
            ComposterBlock.registerCompostableItem(0.65f, Items.LILY_OF_THE_VALLEY);
            ComposterBlock.registerCompostableItem(0.65f, Items.WITHER_ROSE);
            ComposterBlock.registerCompostableItem(0.65f, Items.FERN);
            ComposterBlock.registerCompostableItem(0.65f, Items.SUNFLOWER);
            ComposterBlock.registerCompostableItem(0.65f, Items.LILAC);
            ComposterBlock.registerCompostableItem(0.65f, Items.ROSE_BUSH);
            ComposterBlock.registerCompostableItem(0.65f, Items.PEONY);
            ComposterBlock.registerCompostableItem(0.65f, Items.LARGE_FERN);
            ComposterBlock.registerCompostableItem(0.65f, Items.SPORE_BLOSSOM);
            ComposterBlock.registerCompostableItem(0.65f, Items.AZALEA);
            ComposterBlock.registerCompostableItem(0.65f, Items.MOSS_BLOCK);
            ComposterBlock.registerCompostableItem(0.65f, Items.BIG_DRIPLEAF);
            ComposterBlock.registerCompostableItem(0.85f, Items.HAY_BLOCK);
            ComposterBlock.registerCompostableItem(0.85f, Items.BROWN_MUSHROOM_BLOCK);
            ComposterBlock.registerCompostableItem(0.85f, Items.RED_MUSHROOM_BLOCK);
            ComposterBlock.registerCompostableItem(0.85f, Items.NETHER_WART_BLOCK);
            ComposterBlock.registerCompostableItem(0.85f, Items.WARPED_WART_BLOCK);
            ComposterBlock.registerCompostableItem(0.85f, Items.FLOWERING_AZALEA);
            ComposterBlock.registerCompostableItem(0.85f, Items.BREAD);
            ComposterBlock.registerCompostableItem(0.85f, Items.BAKED_POTATO);
            ComposterBlock.registerCompostableItem(0.85f, Items.COOKIE);
            ComposterBlock.registerCompostableItem(1.0f, Items.CAKE);
            ComposterBlock.registerCompostableItem(1.0f, Items.PUMPKIN_PIE);
            tf=false;
        }
    }
}
