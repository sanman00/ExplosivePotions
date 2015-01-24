package com.comze.sanman00.mods.minecraft.expotions.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * The actual exploding potion item.
 * @author sanman00
 */
public class ItemExplosivePotion extends Item {
    public ItemExplosivePotion() {
        this.setUnlocalizedName("potionExplosion").setMaxStackSize(3).setCreativeTab(CreativeTabs.tabBrewing).setPotionEffect("explosion");
    }
    
    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.DRINK;
    }
    
    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityPlayer playerIn) {
        if (!playerIn.capabilities.isCreativeMode) {
            --stack.stackSize;
        }

        if (!worldIn.isRemote) {
            worldIn.createExplosion(playerIn, playerIn.posX, playerIn.posY, playerIn.posZ, 2.0f, false);
        }

        //playerIn.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);

        if (!playerIn.capabilities.isCreativeMode) {
            if (stack.stackSize <= 0) {
                return new ItemStack(Items.glass_bottle);
            }

            playerIn.inventory.addItemStackToInventory(new ItemStack(Items.glass_bottle));
        }

        return stack;
    }
}
