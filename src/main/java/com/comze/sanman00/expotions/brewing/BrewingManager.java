package com.comze.sanman00.expotions.brewing;

import java.util.stream.Stream;

import com.comze.sanman00.expotions.item.ModItems;
import com.comze.sanman00.expotions.util.ItemUtil;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.brewing.PotionBrewEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public final class BrewingManager {
    private BrewingManager() {
        
    }
    
    public static void init() {
        registerRecipes();
        MinecraftForge.EVENT_BUS.register(BrewingManager.class);
    }
    
    public static void registerRecipes() {
        BrewingRecipeRegistry.addRecipe(new WaterBottleOnlyInputBrewingRecipe(Ingredient.fromItems(Items.TNT), new ItemStack(ModItems.EXPLOSIVE_POTION)));
        BrewingRecipeRegistry.addRecipe(new ConvertPotionBrewingRecipe(Ingredient.fromItems(ModItems.EXPLOSIVE_POTION), Ingredient.fromItems(Items.GUNPOWDER), new ItemStack(ModItems.THROWABLE_EXPLOSIVE_POTION)));
        BrewingRecipeRegistry.addRecipe(new UpgradeStrengthBrewingRecipe(new ItemStack(ModItems.EXPLOSIVE_POTION)));
        BrewingRecipeRegistry.addRecipe(new ConvertPotionBrewingRecipe(Ingredient.fromItems(ModItems.EXPLOSIVE_POTION), Ingredient.fromItems(Items.BLAZE_POWDER), new ItemStack(ModItems.SPICY_EXPLOSIVE_POTION)));
        BrewingRecipeRegistry.addRecipe(new ConvertPotionBrewingRecipe(Ingredient.fromItems(ModItems.SPICY_EXPLOSIVE_POTION), Ingredient.fromItems(Items.GUNPOWDER), new ItemStack(ModItems.SPICY_THROWABLE_EXPLOSIVE_POTION)));
        BrewingRecipeRegistry.addRecipe(new UpgradeStrengthBrewingRecipe(new ItemStack(ModItems.SPICY_EXPLOSIVE_POTION)));
        BrewingRecipeRegistry.addRecipe(new ConvertPotionBrewingRecipe(Ingredient.fromItems(ModItems.THROWABLE_EXPLOSIVE_POTION), Ingredient.fromItems(Items.BLAZE_POWDER), new ItemStack(ModItems.SPICY_THROWABLE_EXPLOSIVE_POTION)));
    }

    @SubscribeEvent 
    public static void onPotionBrewPost(PotionBrewEvent.Post e) { //hack to allow potions to get correct strength
        //System.out.println(e.getItem(3));
        Stream.of(e.getItem(0), e.getItem(1), e.getItem(2))
        .filter(stack -> stack.getItem() == ModItems.EXPLOSIVE_POTION || stack.getItem() == ModItems.SPICY_EXPLOSIVE_POTION)
        //.filter(stack -> e.getItem(3).getItem() == Items.TNT) 
        .map(ItemUtil::getOrCreateTagCompound)
        .forEach(compound -> {
            int strength = Math.max(1, compound.getInt(ItemUtil.POTION_STRENGTH_TAG_NAME));
            
            if (strength < ItemUtil.MAX_STRENGTH && compound.getBoolean(ItemUtil.POTION_STRENGTH_CHECK_TAG_NAME)) {
                compound.putInt(ItemUtil.POTION_STRENGTH_TAG_NAME, strength + 1);
                compound.remove(ItemUtil.POTION_STRENGTH_CHECK_TAG_NAME);
            }
        });
    }
}
