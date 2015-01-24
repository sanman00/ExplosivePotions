package com.comze.sanman00.mods.minecraft.expotions.proxy;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public abstract class BaseProxy {
    public void preInit(FMLPreInitializationEvent event) {
        String mcVersion = Minecraft.getMinecraft().getVersion();
        if (!(mcVersion != null && mcVersion.equals("1.8"))) {
            event.getModLog().warn("This mod is designed for Minecraft 1.8, not \"" + mcVersion + "\"!");
        }
    }
    
    public void init(FMLInitializationEvent event) {
        
    }
    
    public void postInit(FMLPostInitializationEvent event) {
        
    }
}
