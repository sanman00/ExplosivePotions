package com.comze.sanman00.mods.minecraft.expotions.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemUtil {
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
        return stack != null ? getOrCreateTagCompound(stack).getInteger("PotionStrength") : 0;
    }
    
    /**
     * A utility method that sets the strength of the supplied potion.
     * 
     * @param stack The item stack that represents an explosive potion
     * @param strength The strength that is to be set onto this potion
     */
    public static void setStrength(ItemStack stack, int strength) {
        if (stack != null) {
            getOrCreateTagCompound(stack).setInteger("PotionStrength", strength);
        }
    }
}
