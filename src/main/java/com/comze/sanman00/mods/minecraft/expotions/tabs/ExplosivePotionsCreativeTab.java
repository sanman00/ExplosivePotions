package com.comze.sanman00.mods.minecraft.expotions.tabs;

import com.comze.sanman00.mods.minecraft.expotions.item.ItemExplosivePotion;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ExplosivePotionsCreativeTab extends CreativeTabs {
    public static final ExplosivePotionsCreativeTab instance = new ExplosivePotionsCreativeTab();

	private ExplosivePotionsCreativeTab() {
        super("explodingPotions");
    }
    
    private ExplosivePotionsCreativeTab(int index) {
        super(index, "explodingPotions");
    }
    
    @Override
    public Item getTabIconItem() {
        return ItemExplosivePotion.instance;
    }
}
