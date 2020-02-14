package com.comze.sanman00.mods.minecraft.expotions.brewing;

import com.comze.sanman00.mods.minecraft.expotions.util.ItemUtil;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.brewing.BrewingRecipe;

public class UpgradeStrengthBrewingRecipe extends BrewingRecipe {
    public UpgradeStrengthBrewingRecipe(ItemStack input) {
        super(input, new ItemStack(Blocks.TNT), input.copy());
    }

    @Override
    public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
        if (ItemUtil.getStrength(input) >= ItemUtil.MAX_STRENGTH || ingredient.getItem() != Item.getItemFromBlock(Blocks.TNT)) {
            return ItemStack.EMPTY;
        }
        NBTTagCompound compound = ItemUtil.getOrCreateTagCompound(input);
        compound.setBoolean(ItemUtil.POTION_STRENGTH_CHECK_TAG_NAME, true);
        return input;
    }
}