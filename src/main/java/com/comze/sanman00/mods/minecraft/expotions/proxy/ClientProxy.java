package com.comze.sanman00.mods.minecraft.expotions.proxy;

import com.comze.sanman00.mods.minecraft.expotions.Main;
import com.comze.sanman00.mods.minecraft.expotions.item.ItemExplosivePotion;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends BaseProxy {
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        Main.getLogger().info("Starting client-side pre-init of Explosive Potions mod version " + Main.VERSION);
        System.out.println("Registering item...");
        GameRegistry.registerItem(new ItemExplosivePotion(), "potion_explosive");
    }
    
    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        Main.getLogger().info("Starting client-side init of Explosive Potions mod version " + Main.VERSION);
        System.out.println("Creating resource location instance...");
        ModelResourceLocation res = new ModelResourceLocation("expotions:models/item/potion_explosive", "inventory");
        System.out.println("Registering location...");
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(ItemExplosivePotion.instance, 0, res);
        System.out.println("Adding shapeless recipe...");
        GameRegistry.addShapelessRecipe(new ItemStack(ItemExplosivePotion.instance), new ItemStack(Items.glass_bottle), new ItemStack(Items.gunpowder));
    }
    
    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }
}
