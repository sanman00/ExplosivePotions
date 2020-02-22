package com.comze.sanman00.expotions.entity;

import static com.comze.sanman00.expotions.util.ModUtil.THROWABLE_EXPLOSIVE_POTION_NAME;

import com.comze.sanman00.expotions.Main;

import net.minecraft.entity.EntityType;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Main.MOD_ID)
public class ModEntities {
    @ObjectHolder(THROWABLE_EXPLOSIVE_POTION_NAME)
    public static final EntityType<EntityExplosivePotion> THROWABLE_EXPLOSIVE_POTION = null;
}