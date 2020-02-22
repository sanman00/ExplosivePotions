package com.comze.sanman00.expotions.item;

import static com.comze.sanman00.expotions.util.ModUtil.EXPLOSIVE_POTION_NAME;
import static com.comze.sanman00.expotions.util.ModUtil.SPICY_EXPLOSIVE_POTION_NAME;
import static com.comze.sanman00.expotions.util.ModUtil.SPICY_THROWABLE_EXPLOSIVE_POTION_NAME;
import static com.comze.sanman00.expotions.util.ModUtil.THROWABLE_EXPLOSIVE_POTION_NAME;

import com.comze.sanman00.expotions.Main;

import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Main.MOD_ID)
public class ModItems {
    @ObjectHolder(EXPLOSIVE_POTION_NAME)
    public static final Item EXPLOSIVE_POTION = null;

    @ObjectHolder(SPICY_EXPLOSIVE_POTION_NAME)
    public static final Item SPICY_EXPLOSIVE_POTION = null;

    @ObjectHolder(THROWABLE_EXPLOSIVE_POTION_NAME)
    public static final Item THROWABLE_EXPLOSIVE_POTION = null;

    @ObjectHolder(SPICY_THROWABLE_EXPLOSIVE_POTION_NAME)
    public static final Item SPICY_THROWABLE_EXPLOSIVE_POTION = null;
}