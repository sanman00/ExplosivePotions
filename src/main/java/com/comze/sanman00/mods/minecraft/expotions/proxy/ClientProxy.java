package com.comze.sanman00.mods.minecraft.expotions.proxy;

import com.comze.sanman00.mods.minecraft.expotions.entity.EntityExplosivePotion;
import com.comze.sanman00.mods.minecraft.expotions.item.ItemExplosivePotion;
import com.comze.sanman00.mods.minecraft.expotions.item.ItemThrowableExplosivePotion;
import com.comze.sanman00.mods.minecraft.expotions.client.render.RenderExplosivePotion;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * The proxy that loads things that cannot be loaded on the server. Things that
 * cannot be loaded include models, etc.
 */
@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        RenderingRegistry.registerEntityRenderingHandler(EntityExplosivePotion.class, RenderExplosivePotion::new);
    }

    public void init(FMLInitializationEvent event) {
        super.init(event);
        getItemModelMesher().register(ItemExplosivePotion.instance, 0, new ModelResourceLocation("expotions:potion_explosive", "inventory"));
        getItemModelMesher().register(ItemThrowableExplosivePotion.instance, 0, new ModelResourceLocation("expotions:potion_throwable_explosive", "inventory"));
    }
    
    /**
     * Convenience function for getting the {@code ItemModelMesher}.
     * @return The {@code ItemModelMesher} from {@link RenderItem#getItemModelMesher()} via {@link Minecraft#getRenderItem()}
     */
    public static ItemModelMesher getItemModelMesher() {
        return Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
    }
}
