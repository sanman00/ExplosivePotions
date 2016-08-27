package com.comze.sanman00.mods.minecraft.expotions.proxy;

import com.comze.sanman00.mods.minecraft.expotions.Main;
import com.comze.sanman00.mods.minecraft.expotions.entity.EntityExplosivePotion;
import com.comze.sanman00.mods.minecraft.expotions.item.ItemExplosivePotion;
import com.comze.sanman00.mods.minecraft.expotions.item.ItemThrowableExplosivePotion;
import com.comze.sanman00.mods.minecraft.expotions.client.render.RenderExplosivePotion;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
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
        Main.getLogger().info("Starting client-side init of Explosive Potions mod version " + Main.MOD_VERSION);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(ItemExplosivePotion.instance, 0, new ModelResourceLocation("expotions:potion_explosive", "inventory"));
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(ItemThrowableExplosivePotion.instance, 0, new ModelResourceLocation("expotions:potion_throwable_explosive", "inventory"));
        GameRegistry.addShapelessRecipe(new ItemStack(ItemExplosivePotion.instance), new ItemStack(Items.GLASS_BOTTLE), new ItemStack(Items.GUNPOWDER));
    }
}
