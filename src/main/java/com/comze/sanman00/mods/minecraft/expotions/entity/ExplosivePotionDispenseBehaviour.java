package com.comze.sanman00.mods.minecraft.expotions.entity;

import com.comze.sanman00.mods.minecraft.expotions.item.ItemExplosivePotion;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.IProjectile;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ExplosivePotionDispenseBehaviour extends BehaviorProjectileDispense {

    @Override
    protected IProjectile getProjectileEntity(World world, IPosition position, ItemStack stack) {
        EntityExplosivePotion potion = new EntityExplosivePotion(world, position.getX(), position.getY(), position.getZ());
        potion.setStrength(ItemExplosivePotion.getStrength(stack));
        return potion;
    }

}
