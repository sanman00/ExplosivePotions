package com.comze.sanman00.mods.minecraft.expotions.item;

import com.comze.sanman00.mods.minecraft.expotions.Main;
import com.comze.sanman00.mods.minecraft.expotions.entity.EntityExplosivePotion;
import com.comze.sanman00.mods.minecraft.expotions.tabs.SanmanCreativeTab;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemThrowableExplosivePotion extends Item {
	public static final Item instance = new ItemThrowableExplosivePotion();

    private ItemThrowableExplosivePotion() {
        this.setUnlocalizedName("potion_throwable_explosive").setMaxStackSize(1).setCreativeTab(SanmanCreativeTab.instance);
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return false;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
    	Main.getLogger().debug("ITEM RIGHT CLICK");
        world.spawnEntityInWorld(new EntityExplosivePotion(world));
        return stack;
    }

    @Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
		Main.getLogger().debug("ITEM USE");
    	world.spawnEntityInWorld(new EntityExplosivePotion(world));
    	return true;
	}

	@Override
    public ItemStack onItemUseFinish(ItemStack stack, World world, EntityPlayer player) {
		Main.getLogger().debug("ITEM USE FINISH");
    	world.spawnEntityInWorld(new EntityExplosivePotion(world));
        
        if (!player.capabilities.isCreativeMode) {
            --stack.stackSize;
        }
        
        return stack;
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }
}
