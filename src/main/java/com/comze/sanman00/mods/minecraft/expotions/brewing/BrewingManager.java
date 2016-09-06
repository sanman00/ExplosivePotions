package com.comze.sanman00.mods.minecraft.expotions.brewing;

import java.util.List;
import com.comze.sanman00.mods.minecraft.expotions.item.ItemExplosivePotion;
import com.comze.sanman00.mods.minecraft.expotions.item.ItemThrowableExplosivePotion;
import com.comze.sanman00.mods.minecraft.expotions.util.StackUtil;
import com.comze.sanman00.mods.minecraft.expotions.util.WaterBottleOnlyInputBrewingRecipe;
import com.google.common.collect.Lists;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
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
    
    public static void init() {
        registerRecipes();
        registerEventHandlers();
    }
    
    public static void registerRecipes() {
        BrewingRecipeRegistry.addRecipe(new WaterBottleOnlyInputBrewingRecipe(new ItemStack(Blocks.TNT), new ItemStack(ItemExplosivePotion.instance)));
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(new ItemStack(ItemExplosivePotion.instance), new ItemStack(Blocks.TNT), new ItemStack(ItemExplosivePotion.instance)));
        BrewingRecipeRegistry.addRecipe(new ItemStack(ItemExplosivePotion.instance), new ItemStack(Items.GUNPOWDER), new ItemStack(ItemThrowableExplosivePotion.instance));
    }
    
    public static void registerEventHandlers() {
        MinecraftForge.EVENT_BUS.register(INSTANCE);
    }
    
    @SubscribeEvent
    public void onBrew(PotionBrewEvent.Post event) {
        List<ItemStack> potions = Lists.newArrayList(event.getItem(0), event.getItem(1), event.getItem(2));
        potions.stream()
            .filter(stack -> stack != null ? stack.getItem().equals(ItemExplosivePotion.instance) : false)
            .forEach(stack -> {
                NBTTagCompound compound = StackUtil.getOrCreateTagCompound(stack);
                int strength = compound.getInteger("PotionStrength");
                compound.setInteger("PotionStrength", strength + 1);
            });
    }
}
