package com.comze.sanman00.mods.minecraft.expotions.item;

import com.comze.sanman00.mods.minecraft.expotions.tabs.SanmanCreativeTab;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

/**
 * The exploding potion item.
 *
 * @author sanman00
 */
public class ItemExplosivePotion extends Item {
    public static final Item instance = new ItemExplosivePotion();

    private ItemExplosivePotion() {
        this.setUnlocalizedName("potion_explosive").setMaxStackSize(1).setCreativeTab(new SanmanCreativeTab());
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return false;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.DRINK;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 32;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        player.setItemInUse(stack, getMaxItemUseDuration(stack));
        return stack;
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World world, EntityPlayer player) {
        if (!player.capabilities.isCreativeMode) {
            --stack.stackSize;

            if (stack.stackSize <= 0) {
                return new ItemStack(Items.glass_bottle);
            }

            player.inventory.addItemStackToInventory(new ItemStack(Items.glass_bottle));
            Explosion explosion = world.newExplosion(null, player.posX, player.posY, player.posZ, 5.0f, true, true);
            explosion.doExplosionA();
            explosion.doExplosionB(true);
        }

        if (!world.isRemote) {
            Explosion explosion = world.newExplosion(null, player.posX, player.posY, player.posZ, 2.0f, true, true);
            explosion.doExplosionA();
            explosion.doExplosionB(true);
        }
        return stack;
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }
}
