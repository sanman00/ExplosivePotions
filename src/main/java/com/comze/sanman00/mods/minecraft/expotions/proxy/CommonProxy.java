package com.comze.sanman00.mods.minecraft.expotions.proxy;

import com.comze.sanman00.mods.minecraft.expotions.Main;
import com.comze.sanman00.mods.minecraft.expotions.entity.EntityExplosivePotion;
import com.comze.sanman00.mods.minecraft.expotions.item.ItemExplosivePotion;
import com.comze.sanman00.mods.minecraft.expotions.item.ItemThrowableExplosivePotion;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraftforge.common.brewing.BrewingRecipe;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * A proxy that loads things that can be done on both the client and server.
 */
public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        Main.getLogger().info("Starting pre-initialisation of Explosive Potions mod version " + Main.MOD_VERSION);
        GameRegistry.register(ItemExplosivePotion.instance);
        GameRegistry.register(ItemThrowableExplosivePotion.instance);
        EntityRegistry.registerModEntity(EntityExplosivePotion.class, "ThrowableExplosivePotion", 690, Main.instance, 2, 5, true);
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(new ItemStack(Items.POTIONITEM), new ItemStack(Blocks.TNT), new ItemStack(ItemExplosivePotion.instance)) {
            //Hack to make sure that only water bottles work with this
            @Override
            public boolean isInput(ItemStack stack) {
                return super.isInput(stack) && PotionUtils.getPotionFromItem(stack).equals(PotionTypes.WATER);
            }
        });
        BrewingRecipeRegistry.addRecipe(new ItemStack(ItemExplosivePotion.instance), new ItemStack(Items.GUNPOWDER), new ItemStack(ItemThrowableExplosivePotion.instance));
    }

    public void init(FMLInitializationEvent event) {
        Main.getLogger().info("Starting init of Explosive Potions mod version " + Main.MOD_VERSION);
    }

    public void postInit(FMLPostInitializationEvent event) {
        
    }
}
