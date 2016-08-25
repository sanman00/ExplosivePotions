package com.comze.sanman00.mods.minecraft.expotions.render;

import com.comze.sanman00.mods.minecraft.expotions.entity.EntityExplosivePotion;
import com.comze.sanman00.mods.minecraft.expotions.item.ItemThrowableExplosivePotion;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderExplosivePotion extends RenderSnowball<EntityExplosivePotion> {
	public RenderExplosivePotion() {
		super(Minecraft.getMinecraft().getRenderManager(), ItemThrowableExplosivePotion.instance, Minecraft.getMinecraft().getRenderItem());
	}

	/*@Override
	protected ResourceLocation getEntityTexture(EntityExplosivePotion entity) {
		return new ResourceLocation("expotions:textures/items/potion_explosive.png");
	}*/
}
