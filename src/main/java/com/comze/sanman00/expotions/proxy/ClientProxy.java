package com.comze.sanman00.expotions.proxy;

import com.comze.sanman00.expotions.Main;
import com.comze.sanman00.expotions.entity.EntityExplosivePotion;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
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
public class ClientProxy {
    @SubscribeEvent
    public static void init(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(EntityExplosivePotion.class, m -> new SpriteRenderer<>(m, Minecraft.getInstance().getItemRenderer()));
    }
    
    /**
     * Convenience function for getting the {@code ItemModelMesher}.
     * @return The {@code ItemModelMesher} from {@link RenderItem#getItemModelMesher()} via {@link Minecraft#getItemRenderer()}
     */
    public static ItemModelMesher getItemModelMesher() {
        return Minecraft.getInstance().getItemRenderer().getItemModelMesher();
    }
}
