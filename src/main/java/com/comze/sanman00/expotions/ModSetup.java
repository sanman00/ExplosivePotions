package com.comze.sanman00.expotions;

import com.comze.sanman00.expotions.item.ItemExplosivePotion;
import com.comze.sanman00.expotions.item.ItemSpicyExplosivePotion;
import com.comze.sanman00.expotions.item.ItemSpicyThrowableExplosivePotion;
import com.comze.sanman00.expotions.item.ItemThrowableExplosivePotion;
import com.comze.sanman00.expotions.entity.EntityExplosivePotion;
import com.comze.sanman00.expotions.util.ModUtil;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Main.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
class ModSetup {
    @SubscribeEvent
    static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(new ItemExplosivePotion(), new ItemThrowableExplosivePotion(), 
                new ItemSpicyExplosivePotion(), new ItemSpicyThrowableExplosivePotion());
    }

    @SubscribeEvent
    static void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
        event.getRegistry().register(EntityType.Builder.<EntityExplosivePotion>create(EntityExplosivePotion::new, EntityClassification.MISC)
                .size(0.25f, 0.25f).setTrackingRange(2).setUpdateInterval(5).setShouldReceiveVelocityUpdates(true)
                .build(ModUtil.qualify(ModUtil.THROWABLE_EXPLOSIVE_POTION_NAME)).setRegistryName(ModUtil.qualify(ModUtil.THROWABLE_EXPLOSIVE_POTION_NAME)));
    }
}
