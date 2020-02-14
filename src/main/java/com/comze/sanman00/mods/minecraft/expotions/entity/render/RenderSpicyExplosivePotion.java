package com.comze.sanman00.mods.minecraft.expotions.entity.render;

import com.comze.sanman00.mods.minecraft.expotions.entity.EntitySpicyExplosivePotion;
import com.comze.sanman00.mods.minecraft.expotions.item.ItemSpicyThrowableExplosivePotion;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSpicyExplosivePotion extends RenderSnowball<EntitySpicyExplosivePotion> {
    public RenderSpicyExplosivePotion(RenderManager renderManager) {
        super(renderManager, ItemSpicyThrowableExplosivePotion.INSTANCE, Minecraft.getMinecraft().getRenderItem());
    }
}
