package com.comze.sanman00.mods.minecraft.expotions.item;

import java.util.List;
import com.comze.sanman00.mods.minecraft.expotions.Main;
import com.comze.sanman00.mods.minecraft.expotions.tabs.ExplosivePotionsCreativeTab;
import com.comze.sanman00.mods.minecraft.expotions.util.ItemUtil;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

public class ItemExplosivePotion extends Item {
    public static final Item INSTANCE = new ItemExplosivePotion();

    private ItemExplosivePotion() {
        this.setUnlocalizedName("potion_explosive")
            .setRegistryName(Main.MOD_ID, "potion_explosive")
            .setMaxStackSize(1)
            .setCreativeTab(ExplosivePotionsCreativeTab.INSTANCE);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
        ItemUtil.addItemVariants(ItemExplosivePotion.INSTANCE, tab, ExplosivePotionsCreativeTab.INSTANCE, subItems);
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return false;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.DRINK;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 32;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        player.setActiveHand(hand);
        return new ActionResult<>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World world, EntityLivingBase entity) {
        return ItemUtil.usePotion(stack, world, entity, false);
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }
    
    @Override
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
        tooltip.add("Strength: " + ItemUtil.getStrength(stack));
    }
}
