package com.comze.sanman00.mods.minecraft.expotions;

import com.comze.sanman00.mods.minecraft.expotions.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = Main.MOD_ID, version = Main.MOD_VERSION, name = Main.MOD_NAME)
public class Main {
    public static final String MOD_ID = "expotions";
    public static final String MOD_VERSION = "0.0.12pre2";
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
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    public static Logger getLogger() {
        return logger;
    }
}
