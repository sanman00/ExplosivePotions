package com.comze.sanman00.mods.minecraft.expotions.util;

import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraftforge.common.brewing.BrewingRecipe;

/**
 * A {@link BrewingRecipe} implementation that requires the input of the recipe to be water bottles.
 *
 */
public class WaterBottleOnlyInputBrewingRecipe extends BrewingRecipe {
    public WaterBottleOnlyInputBrewingRecipe(ItemStack ingredient, ItemStack output) {
        super(new ItemStack(Items.POTIONITEM), ingredient, output);
    }

    @Override
    public boolean isInput(ItemStack stack) {
        return super.isInput(stack) && PotionUtils.getPotionFromItem(stack).equals(PotionTypes.WATER);
    }
}
