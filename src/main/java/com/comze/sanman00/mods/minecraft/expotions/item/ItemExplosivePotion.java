package com.comze.sanman00.mods.minecraft.expotions.item;

import java.util.List;
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
        this.setUnlocalizedName("potion_explosive")
            .setRegistryName(Main.MOD_ID, "potion_explosive")
            .setMaxStackSize(1)
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
        int strength = stack.getItemDamage();
        System.out.println(strength);
        Explosion explosion = world.newExplosion(null, entity.posX, entity.posY, entity.posZ, stack.getItemDamage() > 0 ? strength * 5.0f : 2.0f, false, true);
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
    
    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        //Get strength here (from nbt?) and put it into the tooltip
        //tooltip.add("Strength: " + strength);
    }
    
    /**
     * A utility method that returns the strength of the supplied potion.
     * 
     * @param stack The item stack that represents an explosive potion
     * @return The strength of this potion
     */
    public static int getStrength(ItemStack stack) {
        return 0; //TODO
    }
    
    /**
     * A utility method that sets the strength of the supplied potion.
     * 
     * @param stack The item stack that represents an explosive potion
     * @param strength The strength that is to be set onto this potion
     */
    public static void setStrength(ItemStack stack, int strength) {
        //TODO
    }
}
