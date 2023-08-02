package lazyalienserver.carpetlasaddition.mixin;

import lazyalienserver.carpetlasaddition.CarpetLASSetting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.entity.vehicle.ChestMinecartEntity;
import net.minecraft.entity.vehicle.FurnaceMinecartEntity;
import net.minecraft.entity.vehicle.HopperMinecartEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;

@Mixin(AbstractMinecartEntity.class)
public abstract class AbstractMinecartEntityMixin {
    @Unique
    private AbstractMinecartEntity abstractMinecartEntity=(AbstractMinecartEntity)(Object)this;
    /**
     * @author Fanzhitianyu
     * @reason ModifyMinecartDrops
     */
    @Overwrite
    public void dropItems(DamageSource damageSource) {
        abstractMinecartEntity.remove(Entity.RemovalReason.KILLED);
        if (abstractMinecartEntity.world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
            ItemStack itemStack =null;
            if(CarpetLASSetting.MinecartDropModify) {
                if (abstractMinecartEntity instanceof HopperMinecartEntity) {
                    itemStack = new ItemStack(Items.HOPPER_MINECART);
                } else if (abstractMinecartEntity instanceof ChestMinecartEntity) {
                    itemStack = new ItemStack(Items.CHEST_MINECART);
                } else if (abstractMinecartEntity instanceof FurnaceMinecartEntity) {
                    itemStack = new ItemStack(Items.FURNACE_MINECART);
                }
            }
            else {
                itemStack = new ItemStack(Items.MINECART);
            }
            if (abstractMinecartEntity.hasCustomName()) {
                itemStack.setCustomName(abstractMinecartEntity.getCustomName());
            }
            abstractMinecartEntity.dropStack(itemStack);
        }
    }
}
