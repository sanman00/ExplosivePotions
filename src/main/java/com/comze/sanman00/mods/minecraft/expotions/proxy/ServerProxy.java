package com.comze.sanman00.mods.minecraft.expotions.proxy;

import com.comze.sanman00.mods.minecraft.expotions.Main;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.SERVER)
public class ServerProxy extends BaseProxy {
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        Main.getLogger().info("["+ Main.NAME + "]Starting server-side pre-init of Explosive Potions mod version " + Main.VERSION);
    }
    
    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        Main.getLogger().info("["+ Main.NAME + "]Starting server-side init of Explosive Potions mod version " + Main.VERSION);
    }
    
    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }
}
