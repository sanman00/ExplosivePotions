package com.comze.sanman00.mods.minecraft.expotions.render;

import com.comze.sanman00.mods.minecraft.expotions.item.ItemThrowableExplosivePotion;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderExplosivePotion extends RenderSnowball {
	public RenderExplosivePotion() {
		super(Minecraft.getMinecraft().getRenderManager(), ItemThrowableExplosivePotion.instance, Minecraft.getMinecraft().getRenderItem());
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation("expotions:textures/items/potion_explosive.png");
	}
}
