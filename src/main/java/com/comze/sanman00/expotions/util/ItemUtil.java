package com.comze.sanman00.expotions.util;

import java.util.List;
import java.util.Random;

import com.comze.sanman00.expotions.entity.EntityExplosivePotion;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class ItemUtil {
    public static final int MIN_STRENGTH = 1;
    public static final int MAX_STRENGTH = 10;
    public static final String POTION_STRENGTH_TAG_NAME = "PotionStrength";
    public static final String POTION_STRENGTH_CHECK_TAG_NAME = "PotionStrengthCheck";
    public static final String SPICY_TAG_NAME = "Spicy";
    
    public static CompoundNBT getOrCreateTag(ItemStack stack, boolean create) {
        if (!stack.hasTag() && create) {
            stack.setTag(new CompoundNBT());
        }
        
        return stack.getTag();
    }
    
    public static CompoundNBT getOrCreateTag(ItemStack stack) {
        return getOrCreateTag(stack, true);
    }
    
    /**
     * A utility method that returns the strength of the supplied potion.
     * 
     * @param stack The item stack that represents an explosive potion
     * @return The strength of this potion
     */
    public static int getStrength(ItemStack stack) {
        return Math.max(MIN_STRENGTH, getOrCreateTag(stack).getInt(POTION_STRENGTH_TAG_NAME));
    }
    
    /**
     * A utility method that sets the strength of the supplied potion.
     * 
     * @param stack The item stack that represents an explosive potion
     * @param strength The strength that is to be set onto this potion
     */
    public static void setStrength(ItemStack stack, int strength) {
        getOrCreateTag(stack).putInt(POTION_STRENGTH_TAG_NAME, strength);
    }
    
    public static void addItemVariants(Item item, ItemGroup tab, ItemGroup expectedTab, NonNullList<ItemStack> subItems) {
        if (tab == expectedTab || tab == ItemGroup.SEARCH) {
            for (int strength = MIN_STRENGTH;strength <= MAX_STRENGTH;strength++) {
                ItemStack stack = new ItemStack(item);
                setStrength(stack, strength);
                subItems.add(stack);
            }
        }
    }

    public static ActionResult<ItemStack> throwPotion(Item item, Random rand, World world, PlayerEntity player, Hand hand, boolean spicy) {
        ItemStack originalStack = player.getHeldItem(hand);
        ItemStack stack = player.abilities.isCreativeMode ? originalStack.copy() : originalStack.split(1);
        
        world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_SPLASH_POTION_THROW, SoundCategory.PLAYERS, 0.5F, 0.4F / (rand.nextFloat() * 0.4F + 0.8F));

        if (!world.isRemote) {
            EntityExplosivePotion potion = new EntityExplosivePotion(player, world);
            potion.setStrength(getStrength(stack));
            potion.setSpicy(spicy);
            potion.shoot(player, player.rotationPitch, player.rotationYaw, -20.0F, 0.5F, 1.0F);
            world.addEntity(potion);
        }
        player.addStat(Stats.ITEM_USED.get(item));
        
        return ActionResult.resultSuccess(originalStack);
    }

    public static ItemStack usePotion(ItemStack stack, World world, LivingEntity entity, boolean spicy) {
        int strength = getStrength(stack);
        world.createExplosion(null, entity.getPosX(), entity.getPosY(), entity.getPosZ(), Math.max(2.0f, strength * 5.0f), spicy, Explosion.Mode.BREAK/*,  true*/);
        
        if (entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entity;
            
            if (player instanceof ServerPlayerEntity) {
                CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayerEntity) player, stack);
            }
            player.addStat(Stats.ITEM_USED.get(stack.getItem()));
            
            if (!player.abilities.isCreativeMode) {
                stack.shrink(1);
        
                if (stack.isEmpty()) {
                    return new ItemStack(Items.GLASS_BOTTLE);
                }
                player.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
            }
        }
    
        return stack;
    }

    public static void addTooltip(ItemStack stack, List<ITextComponent> tooltip) {
        tooltip.add(new TranslationTextComponent("tooltip.expotions.strength", getStrength(stack)).applyTextStyle(TextFormatting.GRAY));
    }

    public static ActionResult<ItemStack> onItemRightClick(PlayerEntity player, Hand hand) {
        player.setActiveHand(hand);
        return ActionResult.resultSuccess(player.getHeldItem(hand));
    }
}
