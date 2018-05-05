package com.comze.sanman00.mods.minecraft.expotions.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityExplosivePotion extends EntityThrowable {
    private int strength;
    private boolean spicy;
    
    public EntityExplosivePotion(World worldIn) {
        super(worldIn);
    }

    public EntityExplosivePotion(World world, double x, double y, double z) {
        super(world, x, y, z);
    }

    public EntityExplosivePotion(World world, EntityLivingBase thrower) {
        super(world, thrower);
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }
    
    public void setSpicy(boolean spicy) {
        this.spicy = spicy;
    }
    
    @Override
    protected void onImpact(RayTraceResult result) {
        if (!this.world.isRemote && result != null && result.getBlockPos() != null) {
            BlockPos pos = result.getBlockPos();
            this.world.newExplosion(this, pos.getX(), pos.getY(), pos.getZ(), Math.min(2.0f, Math.min(10, this.strength) * 5.0f), this.spicy, true); //prevent explosion strength from being too high
            this.world.playBroadcastSound(2002, new BlockPos(this), 1);
            this.setDead();
        }
    }
}
