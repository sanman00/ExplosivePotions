package com.comze.sanman00.mods.minecraft.expotions.util;

import java.util.Random;

import com.comze.sanman00.mods.minecraft.expotions.entity.EntityExplosivePotion;
import com.comze.sanman00.mods.minecraft.expotions.entity.EntitySpicyExplosivePotion;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemUtil {
    public static final int MIN_STRENGTH = 1;
    public static final int MAX_STRENGTH = 10;
    public static final String POTION_STRENGTH_TAG_NAME = "PotionStrength";
    public static final String POTION_STRENGTH_CHECK_TAG_NAME = "PotionStrengthCheck";
    public static final String SPICY_TAG_NAME = "Spicy";
    
    public static NBTTagCompound getOrCreateTagCompound(ItemStack stack, boolean create) {
        if (!stack.hasTagCompound() && create) {
            stack.setTagCompound(new NBTTagCompound());
        }
        
        return stack.getTagCompound();
    }
    
    public static NBTTagCompound getOrCreateTagCompound(ItemStack stack) {
        return getOrCreateTagCompound(stack, true);
    }
    
    /**
     * A utility method that returns the strength of the supplied potion.
     * 
     * @param stack The item stack that represents an explosive potion
     * @return The strength of this potion
     */
    public static int getStrength(ItemStack stack) {
        return Math.max(MIN_STRENGTH, getOrCreateTagCompound(stack).getInteger(POTION_STRENGTH_TAG_NAME));
    }
    
    /**
     * A utility method that sets the strength of the supplied potion.
     * 
     * @param stack The item stack that represents an explosive potion
     * @param strength The strength that is to be set onto this potion
     */
    public static void setStrength(ItemStack stack, int strength) {
        getOrCreateTagCompound(stack).setInteger(POTION_STRENGTH_TAG_NAME, strength);
    }
    
    public static void addItemVariants(Item item, CreativeTabs tab, CreativeTabs expectedTab, NonNullList<ItemStack> subItems) {
        if (tab == expectedTab || tab == CreativeTabs.SEARCH) {
            for (int strength = MIN_STRENGTH;strength <= MAX_STRENGTH;strength++) {
                ItemStack stack = new ItemStack(item);
                setStrength(stack, strength);
                subItems.add(stack);
            }
        }
    }

    public static ActionResult<ItemStack> throwPotion(Item item, Random rand, World world, EntityPlayer player, EnumHand hand, boolean spicy) {
        ItemStack originalStack = player.getHeldItem(hand);
        ItemStack stack = player.capabilities.isCreativeMode ? originalStack.copy() : originalStack.splitStack(1);
        world.playSound(null, player.getPosition(), SoundEvents.ENTITY_SPLASH_POTION_THROW, SoundCategory.PLAYERS, 0.5F, 0.4F / (rand.nextFloat() * 0.4F + 0.8F));
        
        if (!world.isRemote) {
            EntityExplosivePotion potion = spicy ? new EntitySpicyExplosivePotion(world, player) : new EntityExplosivePotion(world, player);
            potion.setStrength(getStrength(stack));
            //potion.setSpicy(spicy);
            potion.shoot(player, player.rotationPitch, player.rotationYaw, -20.0F, 0.5F, 1.0F);
            world.spawnEntity(potion);
        }
        player.addStat(StatList.getObjectUseStats(item));
        return new ActionResult<>(EnumActionResult.SUCCESS, stack);
    }

    public static ItemStack usePotion(ItemStack stack, World world, EntityLivingBase entity, boolean spicy) {
        int strength = getStrength(stack);
        world.newExplosion(null, entity.posX, entity.posY, entity.posZ, Math.max(2.0f, strength * 5.0f), spicy, true);
        
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
            if (!player.capabilities.isCreativeMode) {
                stack.shrink(1);
        
                if (stack.getCount() <= 0) {
                    return new ItemStack(Items.GLASS_BOTTLE);
                }
                player.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
            }
        }
    
        return stack;
    }
}
