package com.comze.sanman00.mods.minecraft.expotions.tabs;

import com.comze.sanman00.mods.minecraft.expotions.item.ItemExplosivePotion;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SanmanCreativeTab extends CreativeTabs {
    public SanmanCreativeTab() {
        super("explodingPotions");
    }
    
    public SanmanCreativeTab(int index) {
        super(index, "explodingPotions");
    }
    
    @Override
    public Item getTabIconItem() {
        return ItemExplosivePotion.instance;
    }
}
