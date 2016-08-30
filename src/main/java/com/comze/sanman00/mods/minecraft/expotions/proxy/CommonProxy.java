package com.comze.sanman00.mods.minecraft.expotions.proxy;

import java.lang.reflect.Field;
import java.util.List;
import org.apache.logging.log4j.Level;
import com.comze.sanman00.mods.minecraft.expotions.Main;
import com.comze.sanman00.mods.minecraft.expotions.entity.EntityExplosivePotion;
import com.comze.sanman00.mods.minecraft.expotions.item.ItemExplosivePotion;
import com.comze.sanman00.mods.minecraft.expotions.item.ItemThrowableExplosivePotion;
import com.comze.sanman00.mods.minecraft.expotions.util.WaterBottleOnlyInputBrewingRecipe;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
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
        BrewingRecipeRegistry.addRecipe(new WaterBottleOnlyInputBrewingRecipe(new ItemStack(Items.POTIONITEM), new ItemStack(Blocks.TNT), new ItemStack(ItemExplosivePotion.instance)) {
            @Override
            public ItemStack getOutput() {
                ItemStack outputStack = super.getOutput();
                NBTTagCompound displayCompound = outputStack.getSubCompound("display", true);
                NBTTagList lore = displayCompound.getTagList("Lore", 8);
                lore.appendTag(new NBTTagString("Strength: " + outputStack.getItemDamage()));
                displayCompound.setTag("Lore", lore);
                return outputStack;
            }
        });
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(new ItemStack(ItemExplosivePotion.instance), new ItemStack(Blocks.TNT), new ItemStack(ItemExplosivePotion.instance)) {
            @Override
            public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
                ItemStack outputStack = new ItemStack(ItemExplosivePotion.instance, 1, input.getItemDamage() + 1);
                NBTTagCompound displayCompound = outputStack.getSubCompound("display", true);
                NBTTagList lore = displayCompound.getTagList("Lore", 8);
                applyStrengthLore(lore, outputStack.getItemDamage());
                displayCompound.setTag("Lore", lore);
                return outputStack;
            }
        });
        BrewingRecipeRegistry.addRecipe(new ItemStack(ItemExplosivePotion.instance), new ItemStack(Items.GUNPOWDER), new ItemStack(ItemThrowableExplosivePotion.instance));
    }
    
    private static void applyStrengthLore(NBTTagList lore, int strength) {
        //replace with AT?
        try {
            Field tagListField = lore.getClass().getDeclaredField("tagList");
            tagListField.setAccessible(true);
            @SuppressWarnings("unchecked")
            List<NBTBase> tagList = (List<NBTBase>) tagListField.get(lore);
            tagList.clear();
            tagListField.set(lore, tagList);
            lore.appendTag(new NBTTagString("Strength: " + strength));
        }
        
        catch (NoSuchFieldException | IllegalAccessException e) {
            Main.getLogger().log(Level.ERROR, "Could not access tagList field, unable to apply strength lore");
            e.printStackTrace();
        }
    }

    public void init(FMLInitializationEvent event) {
        Main.getLogger().info("Starting init of Explosive Potions mod version " + Main.MOD_VERSION);
    }

    public void postInit(FMLPostInitializationEvent event) {
        
    }
}
