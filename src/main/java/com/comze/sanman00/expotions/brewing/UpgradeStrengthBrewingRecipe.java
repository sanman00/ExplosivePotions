package com.comze.sanman00.expotions.brewing;

import com.comze.sanman00.expotions.util.ItemUtil;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.brewing.BrewingRecipe;

public class UpgradeStrengthBrewingRecipe extends BrewingRecipe {
    public UpgradeStrengthBrewingRecipe(ItemStack input) {
        super(Ingredient.fromStacks(input), Ingredient.fromStacks(new ItemStack(Items.TNT)), input.copy());
    }

    @Override
    public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
        if (ItemUtil.getStrength(input) >= ItemUtil.MAX_STRENGTH || ingredient.getItem() != Items.TNT) {
            return ItemStack.EMPTY;
        }
        CompoundNBT compound = ItemUtil.getOrCreateTagCompound(input);
        compound.putBoolean(ItemUtil.POTION_STRENGTH_CHECK_TAG_NAME, true);
        return input;
    }
}