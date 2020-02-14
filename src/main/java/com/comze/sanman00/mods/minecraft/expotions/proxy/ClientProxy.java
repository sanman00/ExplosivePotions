package com.comze.sanman00.mods.minecraft.expotions.proxy;

import com.comze.sanman00.mods.minecraft.expotions.entity.EntityExplosivePotion;
import com.comze.sanman00.mods.minecraft.expotions.entity.EntitySpicyExplosivePotion;
import com.comze.sanman00.mods.minecraft.expotions.entity.render.RenderExplosivePotion;
import com.comze.sanman00.mods.minecraft.expotions.entity.render.RenderSpicyExplosivePotion;
import com.comze.sanman00.mods.minecraft.expotions.item.ItemExplosivePotion;
import com.comze.sanman00.mods.minecraft.expotions.item.ItemSpicyExplosivePotion;
import com.comze.sanman00.mods.minecraft.expotions.item.ItemSpicyThrowableExplosivePotion;
import com.comze.sanman00.mods.minecraft.expotions.item.ItemThrowableExplosivePotion;
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
        RenderingRegistry.registerEntityRenderingHandler(EntitySpicyExplosivePotion.class, RenderSpicyExplosivePotion::new);
    }

    public void init(FMLInitializationEvent event) {
        super.init(event);
        getItemModelMesher().register(ItemExplosivePotion.INSTANCE, 0, new ModelResourceLocation("expotions:potion_explosive", "inventory"));
        getItemModelMesher().register(ItemThrowableExplosivePotion.INSTANCE, 0, new ModelResourceLocation("expotions:potion_throwable_explosive", "inventory"));
        getItemModelMesher().register(ItemSpicyExplosivePotion.INSTANCE, 0, new ModelResourceLocation("expotions:potion_explosive_spicy", "inventory"));
        getItemModelMesher().register(ItemSpicyThrowableExplosivePotion.INSTANCE, 0, new ModelResourceLocation("expotions:potion_throwable_explosive_spicy", "inventory"));
    }
    
    /**
     * Convenience function for getting the {@code ItemModelMesher}.
     * @return The {@code ItemModelMesher} from {@link RenderItem#getItemModelMesher()} via {@link Minecraft#getRenderItem()}
     */
    public static ItemModelMesher getItemModelMesher() {
        return Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
    }
}
