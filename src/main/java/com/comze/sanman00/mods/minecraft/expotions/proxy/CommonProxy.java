package com.comze.sanman00.mods.minecraft.expotions.proxy;

import com.comze.sanman00.mods.minecraft.expotions.Main;
import com.comze.sanman00.mods.minecraft.expotions.entity.EntityExplosivePotion;
import com.comze.sanman00.mods.minecraft.expotions.item.ItemExplosivePotion;
import com.comze.sanman00.mods.minecraft.expotions.item.ItemThrowableExplosivePotion;

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
        Main.getLogger().info("Starting pre-init of Explosive Potions mod version " + Main.MOD_VERSION);
        GameRegistry.register(ItemExplosivePotion.instance);
        GameRegistry.register(ItemThrowableExplosivePotion.instance);
        EntityRegistry.registerModEntity(EntityExplosivePotion.class, "ThrowableExplosivePotion", 690, Main.instance, 2, 5, true);
    }
	
	public void init(FMLInitializationEvent event) {
        Main.getLogger().info("Starting init of Explosive Potions mod version " + Main.MOD_VERSION);
    }
	
	public void postInit(FMLPostInitializationEvent event) {
		//Main.getLogger().info("Starting post-init of Explosive Potions mod version " + Main.MOD_VERSION);
	}
}
