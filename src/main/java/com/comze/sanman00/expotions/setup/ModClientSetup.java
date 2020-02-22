package com.comze.sanman00.expotions.setup;

import com.comze.sanman00.expotions.Main;
import com.comze.sanman00.expotions.entity.ModEntities;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
//import net.minecraftforge.fml.common.;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

/**
 * The proxy that loads things that cannot be loaded on the server. Things that
 * cannot be loaded include models, etc.
 */
@EventBusSubscriber(modid = Main.MOD_ID, value = {Dist.CLIENT}, bus = EventBusSubscriber.Bus.MOD)
class ModClientSetup {
    @SubscribeEvent
    static void setupClient(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.THROWABLE_EXPLOSIVE_POTION, m -> new SpriteRenderer<>(m, Minecraft.getInstance().getItemRenderer()));
    }
}
