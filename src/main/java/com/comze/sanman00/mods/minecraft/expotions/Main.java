package com.comze.sanman00.mods.minecraft.expotions;

import com.comze.sanman00.mods.minecraft.expotions.proxy.BaseProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = Main.MOD_ID, version = Main.VERSION, name = Main.NAME)
public class Main {
    public static final String MOD_ID = "expotions";
    public static final String VERSION = "0.0.4_02";
    public static final String NAME = "Explosive Potions";
    @SidedProxy(clientSide="com.comze.sanman00.mods.minecraft.expotions.proxy.ClientProxy", serverSide="com.comze.sanman00.mods.minecraft.expotions.proxy.ServerProxy", modId = MOD_ID)
    public static BaseProxy proxy;
    private static Logger logger;
    
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
    
    @Mod.InstanceFactory
    public static Main getInstance() {
        return new Main();
    }
    
    public static Logger getLogger() {
        return logger;
    }
}
