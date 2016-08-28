package com.comze.sanman00.mods.minecraft.expotions.item;

import com.comze.sanman00.mods.minecraft.expotions.Main;
import com.comze.sanman00.mods.minecraft.expotions.tabs.ExplosivePotionsCreativeTab;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

/**
 * The exploding potion item.
 */
public class ItemExplosivePotion extends Item {
    public static final Item instance = new ItemExplosivePotion();

    private ItemExplosivePotion() {
        this.setUnlocalizedName("potion_explosive").setRegistryName(Main.MOD_ID, "potion_explosive").setMaxStackSize(1)
                .setCreativeTab(ExplosivePotionsCreativeTab.instance);
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
    public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand) {
        player.setActiveHand(hand);
        return new ActionResult<>(EnumActionResult.SUCCESS, stack);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World world, EntityLivingBase entity) {
        Explosion explosion = world.newExplosion(null, entity.posX, entity.posY, entity.posZ, 5.0f, false, true);
        explosion.doExplosionA();
        explosion.doExplosionB(true);
        if (!(entity instanceof EntityPlayer)) {
            return stack;
        }
        EntityPlayer player = (EntityPlayer) entity;
        if (!player.capabilities.isCreativeMode) {
            --stack.stackSize;

            if (stack.stackSize <= 0) {
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
}
