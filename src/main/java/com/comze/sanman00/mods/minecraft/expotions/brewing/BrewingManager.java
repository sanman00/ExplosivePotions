package com.comze.sanman00.mods.minecraft.expotions.brewing;

import com.comze.sanman00.mods.minecraft.expotions.item.ItemExplosivePotion;
import com.comze.sanman00.mods.minecraft.expotions.item.ItemThrowableExplosivePotion;
import com.comze.sanman00.mods.minecraft.expotions.util.StackUtil;
import com.comze.sanman00.mods.minecraft.expotions.util.WaterBottleOnlyInputBrewingRecipe;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.brewing.BrewingRecipe;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.brewing.PotionBrewEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public final class BrewingManager {
    public static void registerRecipes() {
        BrewingRecipeRegistry.addRecipe(new WaterBottleOnlyInputBrewingRecipe(new ItemStack(Blocks.TNT), new ItemStack(ItemExplosivePotion.instance)) /*{
            @Override
            public ItemStack getOutput() {
                System.out.println("test1");
                ItemStack outputStack = super.getOutput();
                StackUtil.getOrCreateTagCompound(outputStack).setInteger("PotionStrength", 0);
                return outputStack;
            }
        }*/);
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(new ItemStack(ItemExplosivePotion.instance), new ItemStack(Blocks.TNT), new ItemStack(ItemExplosivePotion.instance)) /*{
            @Override
            public ItemStack getOutput() {
                System.out.println("test2");
                ItemStack outputStack = super.getOutput();
                NBTTagCompound compound = StackUtil.getOrCreateTagCompound(outputStack);
                int strength = compound.getInteger("PotionStrength");
                compound.setInteger("PotionStrength", strength + 1);
                return outputStack;
            }
        }*/);
        BrewingRecipeRegistry.addRecipe(new ItemStack(ItemExplosivePotion.instance), new ItemStack(Items.GUNPOWDER), new ItemStack(ItemThrowableExplosivePotion.instance));
    }
    
    @SubscribeEvent
    public void onBrew(PotionBrewEvent.Post event) {
        for (int x = 0;x < 10;x++) {
            try {
                System.out.println(event.getItem(x));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
