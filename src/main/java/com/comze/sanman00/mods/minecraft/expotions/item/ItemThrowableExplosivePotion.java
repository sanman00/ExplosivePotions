package com.comze.sanman00.mods.minecraft.expotions.item;

import java.util.List;
import com.comze.sanman00.mods.minecraft.expotions.Main;
import com.comze.sanman00.mods.minecraft.expotions.entity.EntityExplosivePotion;
import com.comze.sanman00.mods.minecraft.expotions.tabs.ExplosivePotionsCreativeTab;
import com.comze.sanman00.mods.minecraft.expotions.util.ItemUtil;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
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
        ItemStack originalStack = player.getHeldItem(hand);
        ItemStack stack = player.capabilities.isCreativeMode ? originalStack.copy() : originalStack.splitStack(1);
        world.playSound(null, player.getPosition(), SoundEvents.ENTITY_SPLASH_POTION_THROW, SoundCategory.PLAYERS, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
        
        if (!world.isRemote) {
            EntityExplosivePotion potion = new EntityExplosivePotion(player.world, player);
            potion.setStrength(ItemUtil.getStrength(stack));
            potion.setHeadingFromThrower(player, player.rotationPitch, player.rotationYaw, -20.0F, 0.5F, 1.0F);
            player.world.spawnEntity(potion);
        }
        player.addStat(StatList.getObjectUseStats(this));
        return new ActionResult<>(EnumActionResult.SUCCESS, stack);
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }
    
    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        tooltip.add("Strength: " + ItemUtil.getStrength(stack));
    }
    
    @Override
    public void getSubItems(Item item, CreativeTabs tab, NonNullList<ItemStack> subItems) {
        ItemUtil.addItemVariants(item, ItemThrowableExplosivePotion.INSTANCE, tab, ExplosivePotionsCreativeTab.INSTANCE, subItems);
    }
}
