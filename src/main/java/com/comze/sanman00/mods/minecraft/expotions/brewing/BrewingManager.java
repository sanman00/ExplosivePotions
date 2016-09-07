package com.comze.sanman00.mods.minecraft.expotions.brewing;

import java.util.List;
import java.util.Map;
import com.comze.sanman00.mods.minecraft.expotions.item.ItemExplosivePotion;
import com.comze.sanman00.mods.minecraft.expotions.item.ItemThrowableExplosivePotion;
import com.comze.sanman00.mods.minecraft.expotions.util.StackUtil;
import com.comze.sanman00.mods.minecraft.expotions.util.WaterBottleOnlyInputBrewingRecipe;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBrewingStand;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipe;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.brewing.PotionBrewEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public final class BrewingManager {
    private final Map<Integer, Integer> potionBrewingMap = Maps.newHashMap();
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
    public void onBlockPlace(BlockEvent.PlaceEvent event) {
        TileEntity te = event.getBlockSnapshot().getTileEntity();
        if (!(te instanceof TileEntityBrewingStand)) {
            return;
        }
        
        TileEntityBrewingStand brewingStand = (TileEntityBrewingStand) te;
        //TODO add to the map or something
    }
    
    @SubscribeEvent
    public void onPrePotionBrew(PotionBrewEvent.Pre event) {
        for (int x = 0;x < 3;x++) {
            this.potionBrewingMap.put(x, ItemExplosivePotion.getStrength(event.getItem(x)));
        }
    }
    
    @SubscribeEvent
    public void onPostPotionBrew(PotionBrewEvent.Post event) {
        List<ItemStack> potions = Lists.newArrayList(event.getItem(0), event.getItem(1), event.getItem(2));
        potions.stream()
            .filter(stack -> stack != null ? stack.getItem().equals(ItemExplosivePotion.instance) || stack.getItem().equals(ItemThrowableExplosivePotion.instance) : false)
            .forEach(stack -> {
                NBTTagCompound compound = StackUtil.getOrCreateTagCompound(stack);
                int strength = compound.getInteger("PotionStrength");
                //compound.setInteger("PotionStrength", strength + 1);
                stack.setTagInfo("PotionStrength", new NBTTagInt(strength + 1));
            });
        potions.stream().map(ItemExplosivePotion::getStrength).forEach(System.out::println);
    }
}
