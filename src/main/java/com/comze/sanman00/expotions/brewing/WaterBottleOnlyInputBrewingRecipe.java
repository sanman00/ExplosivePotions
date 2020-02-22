package com.comze.sanman00.expotions.brewing;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraftforge.common.brewing.BrewingRecipe;

/**
 * A {@link BrewingRecipe} implementation that requires the input of the recipe to be a water bottle.
 *
 */
public class WaterBottleOnlyInputBrewingRecipe extends BrewingRecipe {
    public WaterBottleOnlyInputBrewingRecipe(Ingredient ingredient, ItemStack output) {
        super(Ingredient.fromItems(Items.POTION), ingredient, output);
    }

    @Override
    public boolean isInput(ItemStack stack) {
        return super.isInput(stack) && PotionUtils.getPotionFromItem(stack).equals(Potions.WATER);
    }
}
