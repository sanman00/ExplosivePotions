package com.comze.sanman00.mods.minecraft.expotions.proxy;

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
