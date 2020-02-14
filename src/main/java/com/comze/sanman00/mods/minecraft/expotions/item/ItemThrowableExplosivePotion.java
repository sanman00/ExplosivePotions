package com.comze.sanman00.mods.minecraft.expotions.item;

import java.util.List;
import com.comze.sanman00.mods.minecraft.expotions.Main;
import com.comze.sanman00.mods.minecraft.expotions.tabs.ExplosivePotionsCreativeTab;
import com.comze.sanman00.mods.minecraft.expotions.util.ItemUtil;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

public class ItemThrowableExplosivePotion extends Item {
    public static final Item INSTANCE = new ItemThrowableExplosivePotion();

    private ItemThrowableExplosivePotion() {
        this.setUnlocalizedName("potion_throwable_explosive")
            .setRegistryName(Main.MOD_ID, "potion_throwable_explosive")
            .setMaxStackSize(1)
            .setCreativeTab(ExplosivePotionsCreativeTab.INSTANCE);
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return false;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 32;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.DRINK;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        return ItemUtil.throwPotion(this, itemRand, world, player, hand, false);
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }
    
    @Override
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
        tooltip.add("Strength: " + ItemUtil.getStrength(stack));
    }
    
    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
        ItemUtil.addItemVariants(ItemThrowableExplosivePotion.INSTANCE, tab, ExplosivePotionsCreativeTab.INSTANCE, subItems);
    }
}
