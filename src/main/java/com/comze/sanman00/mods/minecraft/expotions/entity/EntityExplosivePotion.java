package com.comze.sanman00.mods.minecraft.expotions.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityExplosivePotion extends EntityThrowable {
	public EntityExplosivePotion(World worldIn) {
		super(worldIn);
	}

	public EntityExplosivePotion(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	public EntityExplosivePotion(World world, EntityLivingBase thrower) {
		super(world, thrower);
	}

    @Override
    protected void onImpact(RayTraceResult result) {
        World world = super.worldObj;
        
        if (!world.isRemote && result != null && result.getBlockPos() != null) {
            world.newExplosion(null, result.getBlockPos().getX(), result.getBlockPos().getY(), result.getBlockPos().getZ(), 100.0f, false, true);
            this.worldObj.playAuxSFX(2002, new BlockPos(this), 1);
            this.setDead();
        }
    }
}
