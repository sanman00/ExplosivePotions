package com.comze.sanman00.mods.minecraft.expotions.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

/**
 * The actual exploding potion item.
 * @author sanman00
 */
public class ItemExplosivePotion extends Item {
    public static final Item instance = new ItemExplosivePotion();
    
    private ItemExplosivePotion() {
        this.setUnlocalizedName("potion_explosive").setMaxStackSize(1).setCreativeTab(CreativeTabs.tabBrewing);
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
        player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
        return stack;
    }
    
    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
        return true;
    }
    
    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityPlayer playerIn) {
        if (!playerIn.capabilities.isCreativeMode) {
            --stack.stackSize;
        }

        if (!playerIn.capabilities.isCreativeMode) {
            if (stack.stackSize <= 0) {
                return new ItemStack(Items.glass_bottle);
            }

            playerIn.inventory.addItemStackToInventory(new ItemStack(Items.glass_bottle));
        }

        if (!worldIn.isRemote) {
            worldIn.createExplosion(playerIn, playerIn.posX, playerIn.posY, playerIn.posZ, 2.0f, false);
        }
        return stack;
    }
    
    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }
}
