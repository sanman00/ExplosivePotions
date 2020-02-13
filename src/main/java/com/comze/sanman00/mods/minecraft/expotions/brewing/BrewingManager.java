package com.comze.sanman00.mods.minecraft.expotions.brewing;

import java.util.stream.Stream;
import com.comze.sanman00.mods.minecraft.expotions.item.ItemExplosivePotion;
import com.comze.sanman00.mods.minecraft.expotions.item.ItemSpicyExplosivePotion;
import com.comze.sanman00.mods.minecraft.expotions.item.ItemSpicyThrowableExplosivePotion;
import com.comze.sanman00.mods.minecraft.expotions.item.ItemThrowableExplosivePotion;
import com.comze.sanman00.mods.minecraft.expotions.util.ItemUtil;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipe;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.brewing.PotionBrewEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public final class BrewingManager {
    public static final BrewingManager INSTANCE = new BrewingManager();
    
    private BrewingManager() {
        
    }
    
    public void init() {
        registerRecipes();
        MinecraftForge.EVENT_BUS.register(this);
    }
    
    public void registerRecipes() {
        BrewingRecipeRegistry.addRecipe(new WaterBottleOnlyInputBrewingRecipe(new ItemStack(Blocks.TNT), new ItemStack(ItemExplosivePotion.INSTANCE)));
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(new ItemStack(ItemExplosivePotion.INSTANCE), new ItemStack(Items.GUNPOWDER), new ItemStack(ItemThrowableExplosivePotion.INSTANCE)) {
            @Override
            public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
                ItemStack output = super.getOutput(input, ingredient);
                if (output != ItemStack.EMPTY) {
                    int strength = ItemUtil.getStrength(input);
                    ItemUtil.setStrength(output, strength);
                }
                return output;
            }
        });
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(new ItemStack(ItemExplosivePotion.INSTANCE), new ItemStack(Blocks.TNT), new ItemStack(ItemExplosivePotion.INSTANCE)) {
            @Override
            public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
                if (ItemUtil.getStrength(input) >= ItemUtil.MAX_STRENGTH || ingredient.getItem() != Item.getItemFromBlock(Blocks.TNT)) {
                    return ItemStack.EMPTY;
                }
                NBTTagCompound compound = ItemUtil.getOrCreateTagCompound(input);
                compound.setBoolean(ItemUtil.POTION_STRENGTH_CHECK_TAG_NAME, true);
                return input;
            }
        });
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(new ItemStack(ItemExplosivePotion.INSTANCE), new ItemStack(Items.BLAZE_POWDER), new ItemStack(ItemSpicyExplosivePotion.INSTANCE)) {
            @Override
            public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
                if (ingredient.getItem() != Items.BLAZE_POWDER) {
                    return ItemStack.EMPTY;
                }
                ItemStack output = super.getOutput(input, ingredient);
                int strength = ItemUtil.getStrength(input);
                System.out.println(strength);
                System.out.println(input.getTagCompound());
                System.out.println(input);
                ItemUtil.setStrength(output, strength);
                
                return output;
            }
        });
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(new ItemStack(ItemSpicyExplosivePotion.INSTANCE), new ItemStack(Items.GUNPOWDER), new ItemStack(ItemSpicyThrowableExplosivePotion.INSTANCE)) {
            @Override
            public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
                if (ingredient.getItem() != Items.GUNPOWDER) {
                    return ItemStack.EMPTY;
                }
                ItemStack output = super.getOutput(input, ingredient);
                int strength = ItemUtil.getStrength(input);
                ItemUtil.setStrength(output, strength);
                
                return output;
            }
        });
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(new ItemStack(ItemSpicyExplosivePotion.INSTANCE), new ItemStack(Blocks.TNT), new ItemStack(ItemSpicyExplosivePotion.INSTANCE)) {
            @Override
            public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
                if (ItemUtil.getStrength(input) >= ItemUtil.MAX_STRENGTH || ingredient.getItem() != Item.getItemFromBlock(Blocks.TNT)) {
                    return ItemStack.EMPTY;
                }
                NBTTagCompound compound = ItemUtil.getOrCreateTagCompound(input);
                compound.setBoolean(ItemUtil.POTION_STRENGTH_CHECK_TAG_NAME, true);
                return input;
            }
        });
        /*BrewingRecipeRegistry.addRecipe(new InitialPotionBrewingRecipe());
        BrewingRecipeRegistry.addRecipe(new ConvertPotionBrewingRecipe(new ItemStack(ItemExplosivePotion.INSTANCE), new ItemStack(ItemThrowableExplosivePotion.INSTANCE), new ItemStack(Items.GUNPOWDER)));
        BrewingRecipeRegistry.addRecipe(new UpgradeStrengthBrewingRecipe(new ItemStack(ItemExplosivePotion.INSTANCE)));
        BrewingRecipeRegistry.addRecipe(new ConvertPotionBrewingRecipe(new ItemStack(ItemExplosivePotion.INSTANCE), new ItemStack(ItemSpicyExplosivePotion.INSTANCE), new ItemStack(Items.BLAZE_POWDER)));
        BrewingRecipeRegistry.addRecipe(new ConvertPotionBrewingRecipe(new ItemStack(ItemSpicyExplosivePotion.INSTANCE), new ItemStack(ItemSpicyThrowableExplosivePotion.INSTANCE), new ItemStack(Items.GUNPOWDER)));
        BrewingRecipeRegistry.addRecipe(new UpgradeStrengthBrewingRecipe(new ItemStack(ItemSpicyExplosivePotion.INSTANCE)));*/
    }
    
    @SubscribeEvent
    public void onPotionBrewPost(PotionBrewEvent.Post e) {
        Stream.of(e.getItem(0), e.getItem(1), e.getItem(2))
            .filter(stack -> stack != null)
            .filter(stack -> stack.getItem() == ItemExplosivePotion.INSTANCE || stack.getItem() == ItemSpicyExplosivePotion.INSTANCE)
            //.filter(stack -> e.getItem(3).getItem() == Item.getItemFromBlock(Blocks.TNT))
            .map(ItemUtil::getOrCreateTagCompound)
            .forEach(compound -> {
                int strength = Math.max(1, compound.getInteger(ItemUtil.POTION_STRENGTH_TAG_NAME));
                
                if (strength < ItemUtil.MAX_STRENGTH && compound.getBoolean(ItemUtil.POTION_STRENGTH_CHECK_TAG_NAME)) {
                    compound.setInteger(ItemUtil.POTION_STRENGTH_TAG_NAME, strength + 1);
                    compound.removeTag(ItemUtil.POTION_STRENGTH_CHECK_TAG_NAME);
                }
            });
    }
}
