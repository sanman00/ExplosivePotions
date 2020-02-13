package com.comze.sanman00.mods.minecraft.expotions.util;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;

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
        return  Math.max(MIN_STRENGTH, getOrCreateTagCompound(stack).getInteger(POTION_STRENGTH_TAG_NAME));
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
    
    public static void addItemVariants(Item item, Item expectedItem, CreativeTabs tab, CreativeTabs expectedTab, NonNullList<ItemStack> subItems) {
            if (tab == expectedTab && item == expectedItem) {
                for (int strength = MIN_STRENGTH;strength <= MAX_STRENGTH;strength++) {
                    ItemStack stack = new ItemStack(item);
                    setStrength(stack, strength);
                    subItems.add(stack);
                }
            }
    }
}
