package com.comze.sanman00.mods.minecraft.expotions.util;

import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraftforge.common.brewing.BrewingRecipe;

public class WaterBottleOnlyInputBrewingRecipe extends BrewingRecipe {
    public WaterBottleOnlyInputBrewingRecipe(ItemStack input, ItemStack ingredient, ItemStack output) {
        super(input, ingredient, output);
    }

    @Override
    public boolean isInput(ItemStack stack) {
        return super.isInput(stack) && PotionUtils.getPotionFromItem(stack).equals(PotionTypes.WATER);
    }
}
