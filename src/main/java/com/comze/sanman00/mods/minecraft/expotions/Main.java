package com.comze.sanman00.mods.minecraft.expotions;

import com.comze.sanman00.mods.minecraft.expotions.brewing.BrewingManager;
import com.comze.sanman00.mods.minecraft.expotions.entity.DispenseBehaviourExplosivePotion;
import com.comze.sanman00.mods.minecraft.expotions.entity.EntityExplosivePotion;
import com.comze.sanman00.mods.minecraft.expotions.item.ItemExplosivePotion;
import com.comze.sanman00.mods.minecraft.expotions.item.ItemSpicyExplosivePotion;
import com.comze.sanman00.mods.minecraft.expotions.item.ItemSpicyThrowableExplosivePotion;
import com.comze.sanman00.mods.minecraft.expotions.item.ItemThrowableExplosivePotion;
import com.comze.sanman00.mods.minecraft.expotions.proxy.CommonProxy;
import net.minecraft.block.BlockDispenser;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

import org.apache.logging.log4j.Logger;

@Mod(modid = Main.MOD_ID, version = Main.MOD_VERSION, name = Main.MOD_NAME)
public class Main {
    public static final String MOD_ID = "expotions";
    public static final String MOD_VERSION = "0.0.13";
    public static final String MOD_NAME = "Explosive Potions";
    @SidedProxy(clientSide = "com.comze.sanman00.mods.minecraft.expotions.proxy.ClientProxy", 
                serverSide = "com.comze.sanman00.mods.minecraft.expotions.proxy.CommonProxy", 
                modId = MOD_ID)
    public static CommonProxy proxy;
    private static Logger logger;
    @Mod.Instance
    public static Main instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        logger.info("Starting pre-initialisation of Explosive Potions mod version " + Main.MOD_VERSION);
        GameRegistry.register(ItemExplosivePotion.INSTANCE);
        GameRegistry.register(ItemThrowableExplosivePotion.INSTANCE);
        GameRegistry.register(ItemSpicyExplosivePotion.INSTANCE);
        GameRegistry.register(ItemSpicyThrowableExplosivePotion.INSTANCE);
        BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(ItemThrowableExplosivePotion.INSTANCE, new DispenseBehaviourExplosivePotion());
        BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(ItemSpicyThrowableExplosivePotion.INSTANCE, new DispenseBehaviourExplosivePotion());
        EntityRegistry.registerModEntity(new ResourceLocation("expotions:potion_throwable_explosive"), EntityExplosivePotion.class, "ThrowableExplosivePotion", 690, Main.instance, 2, 5, true);
        BrewingManager.INSTANCE.init();
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        logger.info("Starting initialisation of Explosive Potions mod version " + Main.MOD_VERSION);
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        logger.info("Starting post-initialisation of Explosive Potions mod version " + Main.MOD_VERSION);
        proxy.postInit(event);
    }

    public static Logger getLogger() {
        return logger;
    }
}
