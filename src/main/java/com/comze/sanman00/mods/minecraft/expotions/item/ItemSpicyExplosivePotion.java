package com.comze.sanman00.mods.minecraft.expotions.item;

import java.util.List;

import com.comze.sanman00.mods.minecraft.expotions.Main;
import com.comze.sanman00.mods.minecraft.expotions.tabs.ExplosivePotionsCreativeTab;
import com.comze.sanman00.mods.minecraft.expotions.util.ItemUtil;
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

public class ItemSpicyExplosivePotion extends Item {
    public static final Item INSTANCE = new ItemSpicyExplosivePotion();

    private ItemSpicyExplosivePotion() {
        this.setUnlocalizedName("potion_explosive_spicy")
            .setRegistryName(Main.MOD_ID, "potion_explosive_spicy")
            .setMaxStackSize(1)
            .setCreativeTab(ExplosivePotionsCreativeTab.instance);
    }
    
    @Override
    public void getSubItems(Item item, CreativeTabs tab, NonNullList<ItemStack> subItems) {
        if (tab == ExplosivePotionsCreativeTab.instance && item == ItemSpicyExplosivePotion.INSTANCE) {
            for (int strength = 0;strength <= 10;strength++) {
                ItemStack stack = new ItemStack(item);
                ItemUtil.setStrength(stack, strength);
                subItems.add(stack);
            }
        }
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
        int strength = ItemUtil.getStrength(stack);
        world.newExplosion(null, entity.posX, entity.posY, entity.posZ, strength > 0 ? strength * 5.0f : 2.0f, true, true);
        
        if (!(entity instanceof EntityPlayer)) {
            return stack;
        }
        EntityPlayer player = (EntityPlayer) entity;
        if (!player.capabilities.isCreativeMode) {
            stack.shrink(1);

            if (stack.getCount() <= 0) {
                return new ItemStack(Items.GLASS_BOTTLE);
            }
            player.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
        }

        return stack;
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }
    
    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        tooltip.add("Strength: " + ItemUtil.getStrength(stack));
    }
}
