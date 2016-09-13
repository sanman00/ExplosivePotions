package com.comze.sanman00.mods.minecraft.expotions.item;

import java.util.List;
import com.comze.sanman00.mods.minecraft.expotions.Main;
import com.comze.sanman00.mods.minecraft.expotions.entity.EntityExplosivePotion;
import com.comze.sanman00.mods.minecraft.expotions.tabs.ExplosivePotionsCreativeTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemThrowableExplosivePotion extends Item {
    public static final Item instance = new ItemThrowableExplosivePotion();

    private ItemThrowableExplosivePotion() {
        this.setUnlocalizedName("potion_throwable_explosive")
            .setRegistryName(Main.MOD_ID, "potion_throwable_explosive")
            .setMaxStackSize(1)
            .setCreativeTab(ExplosivePotionsCreativeTab.instance);
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
    public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand) {
        if (!player.capabilities.isCreativeMode) {
            --stack.stackSize;
        }
        world.playSound(null, player.getPosition(), SoundEvents.ENTITY_SPLASH_POTION_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
        if (!world.isRemote) {
            EntityExplosivePotion potion = new EntityExplosivePotion(world, player);
            potion.setStrength(ItemExplosivePotion.getStrength(stack));
            potion.setHeadingFromThrower(player, player.rotationPitch, player.rotationYaw, -20.0F, 0.5F, 1.0F);
            world.spawnEntityInWorld(potion);
        }
        return new ActionResult<>(EnumActionResult.SUCCESS, stack);
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }
    
    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        tooltip.add("Strength: " + ItemExplosivePotion.getStrength(stack));
    }
    
    @Override
    public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> subItems) {
        if (tab == ExplosivePotionsCreativeTab.instance && item == ItemThrowableExplosivePotion.instance) {
            for (int strength = 0;strength <= 10;strength++) {
                ItemStack stack = new ItemStack(item);
                ItemExplosivePotion.setStrength(stack, strength);
                subItems.add(stack);
            }
        }
    }
}
