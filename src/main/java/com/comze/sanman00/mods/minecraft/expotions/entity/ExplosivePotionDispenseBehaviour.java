package com.comze.sanman00.mods.minecraft.expotions.entity;

import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.IProjectile;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ExplosivePotionDispenseBehaviour extends BehaviorProjectileDispense {

    @Override
    protected IProjectile getProjectileEntity(World world, IPosition position, ItemStack stack) {
        return new EntityExplosivePotion(world, position.getX(), position.getY(), position.getZ());
    }

}
