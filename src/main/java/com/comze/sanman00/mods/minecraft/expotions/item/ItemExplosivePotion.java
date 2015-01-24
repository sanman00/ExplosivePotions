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
    
    public ItemExplosivePotion() {
        this.setUnlocalizedName("potionExplosion").setMaxStackSize(3).setCreativeTab(CreativeTabs.tabBrewing);
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
    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            worldIn.createExplosion(playerIn, playerIn.posX, playerIn.posY, playerIn.posZ, 2.0f, false);
        }
        return true;
    }
    
    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityPlayer playerIn) {
        System.out.println("line 33");
        if (!playerIn.capabilities.isCreativeMode) {
            System.out.println("line 35");
            --stack.stackSize;
        }

        System.out.println("line 39");
        

        //playerIn.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);

        System.out.println("line 47");
        if (!playerIn.capabilities.isCreativeMode) {
            System.out.println("line 49");
            if (stack.stackSize <= 0) {
                System.out.println("line 51");
                return new ItemStack(Items.glass_bottle);
            }

            System.out.println("line 53");
            playerIn.inventory.addItemStackToInventory(new ItemStack(Items.glass_bottle));
        }

        System.out.println("line 59");
        return stack;
    }
    
    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }
}
