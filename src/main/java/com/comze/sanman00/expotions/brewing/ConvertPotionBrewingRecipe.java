package com.comze.sanman00.expotions.brewing;

import com.comze.sanman00.expotions.util.ItemUtil;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.common.brewing.BrewingRecipe;

public class ConvertPotionBrewingRecipe extends BrewingRecipe {
    public ConvertPotionBrewingRecipe(Ingredient input, Ingredient ingredient, ItemStack output) {
        super(input, ingredient, output);
    }

    @Override
    public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
        ItemStack output = super.getOutput(input, ingredient);
        if (output != ItemStack.EMPTY) {
            int strength = ItemUtil.getStrength(input);
            ItemUtil.setStrength(output, strength);
        }
        return output;
    }
}