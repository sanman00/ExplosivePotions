package com.comze.sanman00.expotions.item;

import com.comze.sanman00.expotions.util.ItemUtil;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSpicyExplosivePotion extends ItemExplosivePotion {
    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World world, LivingEntity entity) {
        return ItemUtil.usePotion(stack, world, entity, true);
    }
}
