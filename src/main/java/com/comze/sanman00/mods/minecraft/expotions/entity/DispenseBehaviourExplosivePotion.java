package com.comze.sanman00.mods.minecraft.expotions.entity;

import com.comze.sanman00.mods.minecraft.expotions.item.ItemSpicyThrowableExplosivePotion;
import com.comze.sanman00.mods.minecraft.expotions.util.ItemUtil;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.IProjectile;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class DispenseBehaviourExplosivePotion extends BehaviorProjectileDispense {

    @Override
    protected IProjectile getProjectileEntity(World world, IPosition position, ItemStack stack) {
        boolean spicyItem = stack.getItem() == ItemSpicyThrowableExplosivePotion.INSTANCE;
        
        double x = position.getX();
        double y = position.getY();
        double z = position.getZ();
        EntityExplosivePotion potion = spicyItem ? new EntitySpicyExplosivePotion(world, x, y, z) : new EntityExplosivePotion(world, x, y, z);
        potion.setStrength(ItemUtil.getStrength(stack));
        return potion;
    }

}
