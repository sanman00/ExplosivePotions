package com.comze.sanman00.expotions;

import net.minecraft.block.DispenserBlock;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.comze.sanman00.expotions.brewing.BrewingManager;
import com.comze.sanman00.expotions.entity.DispenseBehaviourExplosivePotion;
import com.comze.sanman00.expotions.item.ModItems;

@Mod(Main.MOD_ID)
public class Main {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "expotions";

    public Main() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        //evtBus.addListener(this::setupClient);
    }

    public void setup(FMLCommonSetupEvent event) {
        DispenserBlock.registerDispenseBehavior(ModItems.THROWABLE_EXPLOSIVE_POTION, new DispenseBehaviourExplosivePotion());
        DispenserBlock.registerDispenseBehavior(ModItems.SPICY_THROWABLE_EXPLOSIVE_POTION, new DispenseBehaviourExplosivePotion());
        
        BrewingManager.init();
        //proxy.preInit(event);
    }
}
