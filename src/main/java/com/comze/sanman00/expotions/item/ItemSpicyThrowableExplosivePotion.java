package com.comze.sanman00.expotions.item;

import com.comze.sanman00.expotions.util.ItemUtil;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class ItemSpicyThrowableExplosivePotion extends ItemThrowableExplosivePotion {
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        return ItemUtil.throwPotion(this, random, world, player, hand, true);
    }
}
