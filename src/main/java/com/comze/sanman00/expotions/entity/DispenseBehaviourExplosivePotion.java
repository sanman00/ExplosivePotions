package com.comze.sanman00.expotions.entity;

import net.minecraft.dispenser.ProjectileDispenseBehavior;

import com.comze.sanman00.expotions.item.ModItems;
import com.comze.sanman00.expotions.util.ItemUtil;

import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.IProjectile;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class DispenseBehaviourExplosivePotion extends ProjectileDispenseBehavior {
    @Override
    protected IProjectile getProjectileEntity(World world, IPosition position, ItemStack stack) {
        boolean spicyItem = stack.getItem() == ModItems.SPICY_THROWABLE_EXPLOSIVE_POTION;
        
        double x = position.getX();
        double y = position.getY();
        double z = position.getZ();
        EntityExplosivePotion potion = new EntityExplosivePotion(x, y, z, world);
        potion.setStrength(ItemUtil.getStrength(stack));
        potion.setSpicy(spicyItem);
        return potion;
    }
}
