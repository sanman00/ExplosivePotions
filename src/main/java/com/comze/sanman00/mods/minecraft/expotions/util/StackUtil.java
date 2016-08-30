package com.comze.sanman00.mods.minecraft.expotions.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class StackUtil {
    public static NBTTagCompound getOrCreateTagCompound(ItemStack stack) {
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }
        
        return stack.getTagCompound();
    }
}
