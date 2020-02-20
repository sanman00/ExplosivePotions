package com.comze.sanman00.expotions.tabs;

import com.comze.sanman00.expotions.item.ModItems;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ExplosivePotionsCreativeTab extends ItemGroup {
    public static final ExplosivePotionsCreativeTab INSTANCE = new ExplosivePotionsCreativeTab();

    private ExplosivePotionsCreativeTab() {
        super("expotions");
    }

    private ExplosivePotionsCreativeTab(int index) {
        super(index, "expotions");
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(ModItems.EXPLOSIVE_POTION);
    }
}
