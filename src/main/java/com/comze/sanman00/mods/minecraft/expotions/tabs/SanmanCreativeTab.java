package com.comze.sanman00.mods.minecraft.expotions.tabs;

import com.comze.sanman00.mods.minecraft.expotions.item.ItemExplosivePotion;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class SanmanCreativeTab extends CreativeTabs {
    public SanmanCreativeTab(String label) {
        super(label);
    }
    
    public SanmanCreativeTab(int index, String label) {
        super(index, label);
    }
    
    @Override
    public Item getTabIconItem() {
        return ItemExplosivePotion.instance;
    }
}
