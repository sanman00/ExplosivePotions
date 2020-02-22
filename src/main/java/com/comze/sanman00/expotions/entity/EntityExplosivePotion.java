package com.comze.sanman00.expotions.entity;

import com.comze.sanman00.expotions.item.ModItems;
import com.comze.sanman00.expotions.util.ItemUtil;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

@OnlyIn(
    value = Dist.CLIENT,
    _interface = IRendersAsItem.class
)
@SuppressWarnings("unchecked")
public class EntityExplosivePotion extends ThrowableEntity implements IRendersAsItem {
    public static final DataParameter<Integer> STRENGTH = EntityDataManager.createKey(EntityExplosivePotion.class, DataSerializers.VARINT);
    public static final DataParameter<Boolean> SPICY = EntityDataManager.createKey(EntityExplosivePotion.class, DataSerializers.BOOLEAN);
    
    public EntityExplosivePotion(EntityType<? extends ThrowableEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public EntityExplosivePotion(double x, double y, double z, World worldIn) {
        super((EntityType<? extends ThrowableEntity>) ModEntities.THROWABLE_EXPLOSIVE_POTION, x, y, z, worldIn);
    }
     
    public EntityExplosivePotion(LivingEntity livingEntity, World world) {
        super((EntityType<? extends ThrowableEntity>) ModEntities.THROWABLE_EXPLOSIVE_POTION, livingEntity, world);
    }

    public void setStrength(int strength) {
        this.getDataManager().set(STRENGTH, strength);
    }
    
    public void setSpicy(boolean spicy) {
        this.getDataManager().set(SPICY, spicy);
    }
    
    public int getStrength() {
        return this.getDataManager().get(STRENGTH);
    }
    
    public boolean isSpicy() {
        return this.getDataManager().get(SPICY);
    }

    @Override
    protected float getGravityVelocity() {
        return 0.05f;
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (!this.world.isRemote && result.getType() != RayTraceResult.Type.MISS) {
            Type type = result.getType();
            BlockPos pos = type == RayTraceResult.Type.BLOCK ? 
                    ((BlockRayTraceResult) result).getPos() :
                    ((EntityRayTraceResult) result).getEntity().getPosition();
            this.world.createExplosion(this, pos.getX(), pos.getY(), pos.getZ(), Math.max(2.0f, this.getStrength() * 5.0f), this.isSpicy(), Explosion.Mode.DESTROY);
            this.world.playBroadcastSound(2002, new BlockPos(this), 1);
            this.remove();
        }
    }
    
    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt(ItemUtil.POTION_STRENGTH_TAG_NAME, this.getStrength());
        compound.putBoolean(ItemUtil.SPICY_TAG_NAME, this.isSpicy());
    }
    
    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.setStrength(compound.getInt(ItemUtil.POTION_STRENGTH_TAG_NAME));
        this.setSpicy(compound.getBoolean(ItemUtil.SPICY_TAG_NAME));
    }

    @Override
    protected void registerData() {
        EntityDataManager dm = this.getDataManager();
        dm.register(STRENGTH, ItemUtil.MIN_STRENGTH);
        dm.register(SPICY, false);
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(this.isSpicy() ? ModItems.SPICY_THROWABLE_EXPLOSIVE_POTION : ModItems.THROWABLE_EXPLOSIVE_POTION);
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
