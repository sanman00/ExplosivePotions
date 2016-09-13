package com.comze.sanman00.mods.minecraft.expotions.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class StackUtil {
    public static NBTTagCompound getOrCreateTagCompound(ItemStack stack, boolean create) {
        if (!stack.hasTagCompound() && create) {
            stack.setTagCompound(new NBTTagCompound());
        }
        
        return stack.getTagCompound();
    }
    
    public static NBTTagCompound getOrCreateTagCompound(ItemStack stack) {
        return getOrCreateTagCompound(stack, true);
    }
}
