package com.comze.sanman00.mods.minecraft.expotions.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class EntitySpicyExplosivePotion extends EntityExplosivePotion {
    public EntitySpicyExplosivePotion(World worldIn) {
        super(worldIn);
    }

    public EntitySpicyExplosivePotion(World world, double x, double y, double z) {
        super(world, x, y, z);
    }

    public EntitySpicyExplosivePotion(World world, EntityLivingBase thrower) {
        super(world, thrower);
    }
    {
        this.spicy = true;
    }
}
