package com.comze.sanman00.mods.minecraft.expotions.proxy;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * The base-class for all proxies for this mod.
 * @author sanman00
 */
public abstract class BaseProxy {
    /**
     * Called by <code>Main.preInit()</code> during mod pre-initialisation.
     * @param event 
     */
    public void preInit(FMLPreInitializationEvent event) {
        String mcVersion = Minecraft.getMinecraft().getVersion();
        if (!(mcVersion != null && mcVersion.equals("1.8-Forge11.14.0.1290-1.8"))) {
            event.getModLog().warn("This mod is designed for Minecraft Forge 11.14.1290 for Minecraft 1.8.\n"
                                 + "Your version: \"" + mcVersion + "\"");
        }
    }
    /**
     * Called by <code>Main.init()</code> during mod initialisation.
     * @param event 
     */
    public void init(FMLInitializationEvent event) {
        
    }
    
    /**
     * Called by <code>Main.postInit()</code> during mod post-initialisation.
     * @param event 
     */
    public void postInit(FMLPostInitializationEvent event) {
        
    }
}
