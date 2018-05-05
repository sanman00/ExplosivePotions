package com.comze.sanman00.mods.minecraft.expotions.brewing;

import java.util.stream.Stream;
import com.comze.sanman00.mods.minecraft.expotions.item.ItemExplosivePotion;
import com.comze.sanman00.mods.minecraft.expotions.item.ItemThrowableExplosivePotion;
import com.comze.sanman00.mods.minecraft.expotions.util.StackUtil;
import com.comze.sanman00.mods.minecraft.expotions.util.WaterBottleOnlyInputBrewingRecipe;
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
        registerEventHandlers();
    }
    
    public void registerRecipes() {
        BrewingRecipeRegistry.addRecipe(new WaterBottleOnlyInputBrewingRecipe(new ItemStack(Blocks.TNT), new ItemStack(ItemExplosivePotion.instance)));
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(new ItemStack(ItemExplosivePotion.instance), new ItemStack(Items.GUNPOWDER), new ItemStack(ItemThrowableExplosivePotion.instance)) {
                @Override
                public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
                    if (ingredient.getItem() != Items.GUNPOWDER) {
                        return null;
                    }
                    ItemStack output = getOutput();
                    int strength = ItemExplosivePotion.getStrength(input);
                    ItemExplosivePotion.setStrength(output, strength);
                    return output;
                }
            });
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(new ItemStack(ItemExplosivePotion.instance), new ItemStack(Blocks.TNT), new ItemStack(ItemExplosivePotion.instance)) {
            @Override
            public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
                if (ingredient.getItem() != Item.getItemFromBlock(Blocks.TNT)) {
                    return null;
                }
                NBTTagCompound compound = StackUtil.getOrCreateTagCompound(input);
                compound.setBoolean("PotionStrengthCheck", true);
                return input;
            }
        });
    }
    
    @SubscribeEvent
    public void onPotionBrewPost(PotionBrewEvent.Post e) {
        Stream.of(e.getItem(0), e.getItem(1), e.getItem(2))
            .filter(stack -> stack != null && stack.getItem() == ItemExplosivePotion.instance)
            .map(StackUtil::getOrCreateTagCompound)
            .filter(compound -> compound.getBoolean("PotionStrengthCheck"))
            .forEach(compound -> {
                int strength = compound.getInteger("PotionStrength");
                
                if (strength < 10) {
                    compound.setInteger("PotionStrength", strength + 1);
                }
                compound.removeTag("PotionStrengthCheck");
            });
    }
    
    public void registerEventHandlers() {
        MinecraftForge.EVENT_BUS.register(this);
    }
}
