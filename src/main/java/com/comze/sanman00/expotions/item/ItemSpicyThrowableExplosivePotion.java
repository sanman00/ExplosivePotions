package com.comze.sanman00.expotions.item;

import java.util.List;

import com.comze.sanman00.expotions.Main;
import com.comze.sanman00.expotions.tabs.ExplosivePotionsCreativeTab;
import com.comze.sanman00.expotions.util.ItemUtil;
import com.comze.sanman00.expotions.util.ModUtil;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.UseAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public class ItemSpicyThrowableExplosivePotion extends Item {
    public ItemSpicyThrowableExplosivePotion() {
        super(new Item.Properties().maxStackSize(1).group(ExplosivePotionsCreativeTab.INSTANCE));
       // EffectInstance i;
       // this.setUnlocalizedName("potion_explosive")
        this.setRegistryName(Main.MOD_ID, ModUtil.SPICY_THROWABLE_EXPLOSIVE_POTION_NAME);
    }

    @Override
    public void fillItemGroup(ItemGroup tab, NonNullList<ItemStack> subItems) {
        ItemUtil.addItemVariants(ModItems.SPICY_THROWABLE_EXPLOSIVE_POTION, tab, ExplosivePotionsCreativeTab.INSTANCE, subItems);
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return false;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 32;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        return ItemUtil.throwPotion(this, random, world, player, hand, true);
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }
    
    @Override
    public void addInformation(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        ItemUtil.addTooltip(stack, tooltip);
        //tooltip.add("Strength: " + ItemUtil.getStrength(stack));
    }
}
