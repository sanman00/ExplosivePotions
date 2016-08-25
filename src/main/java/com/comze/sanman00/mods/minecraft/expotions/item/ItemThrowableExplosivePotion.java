package com.comze.sanman00.mods.minecraft.expotions.item;

import com.comze.sanman00.mods.minecraft.expotions.Main;
import com.comze.sanman00.mods.minecraft.expotions.entity.EntityExplosivePotion;
import com.comze.sanman00.mods.minecraft.expotions.tabs.ExplosivePotionsCreativeTab;
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
		this.setUnlocalizedName("potion_throwable_explosive").setRegistryName(Main.MOD_ID, "potion_throwable_explosive").setMaxStackSize(1).setCreativeTab(ExplosivePotionsCreativeTab.instance);
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
		return EnumAction.BLOCK;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand) {
	    if (!player.capabilities.isCreativeMode) {
            --stack.stackSize;
        }
        world.playSound(player, player.getPosition(), SoundEvents.entity_arrow_shoot, SoundCategory.NEUTRAL,  0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
        if (!world.isRemote) {
            world.spawnEntityInWorld(new EntityExplosivePotion(world, player));
        }
        return new ActionResult<>(EnumActionResult.PASS, stack);
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return true;
	}
}
