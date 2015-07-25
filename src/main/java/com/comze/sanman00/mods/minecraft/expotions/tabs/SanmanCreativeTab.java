package com.comze.sanman00.mods.minecraft.expotions.tabs;

import com.comze.sanman00.mods.minecraft.expotions.item.ItemExplosivePotion;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class SanmanCreativeTab extends CreativeTabs {
    public static final SanmanCreativeTab instance = new SanmanCreativeTab();

	private SanmanCreativeTab() {
        super("explodingPotions");
    }
    
    private SanmanCreativeTab(int index) {
        super(index, "explodingPotions");
    }
    
    @Override
    public Item getTabIconItem() {
        return ItemExplosivePotion.instance;
    }
}
