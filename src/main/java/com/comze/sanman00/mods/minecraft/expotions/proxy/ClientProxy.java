package com.comze.sanman00.mods.minecraft.expotions.proxy;

import com.comze.sanman00.mods.minecraft.expotions.Main;
import com.comze.sanman00.mods.minecraft.expotions.entity.EntityExplosivePotion;
import com.comze.sanman00.mods.minecraft.expotions.item.ItemExplosivePotion;
import com.comze.sanman00.mods.minecraft.expotions.item.ItemThrowableExplosivePotion;
import com.comze.sanman00.mods.minecraft.expotions.render.RenderExplosivePotion;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends BaseProxy {
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        Main.getLogger().info("Starting client-side pre-init of Explosive Potions mod version " + Main.VERSION);
        GameRegistry.registerItem(ItemExplosivePotion.instance, "potion_explosive");
        GameRegistry.registerItem(ItemThrowableExplosivePotion.instance, "potion_throwable_explosive");
        EntityRegistry.registerModEntity(EntityExplosivePotion.class, "ThrowableExplosivePotion", 690, Main.instance, 2, 5, true);
    }
    
    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        Main.getLogger().info("Starting client-side init of Explosive Potions mod version " + Main.VERSION);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(ItemExplosivePotion.instance, 0, new ModelResourceLocation("expotions:potion_explosive", "inventory"));
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(ItemThrowableExplosivePotion.instance, 0, new ModelResourceLocation("expotions:potion_throwable_explosive", "inventory"));
        GameRegistry.addShapelessRecipe(new ItemStack(ItemExplosivePotion.instance), new ItemStack(Items.glass_bottle), new ItemStack(Items.gunpowder));
        RenderingRegistry.registerEntityRenderingHandler(EntityExplosivePotion.class, new RenderExplosivePotion());
        MinecraftForge.EVENT_BUS.register(this);
    }
    
    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }
    
    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent e) {
    	if (e.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR || e.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
    		for (ItemStack stack : e.entityPlayer.getInventory()) {
    			if (stack != null && e.entityPlayer.getItemInUse() != null && stack.getItem() != null && e.entityPlayer.getItemInUse().equals(stack.getItem().equals(ItemThrowableExplosivePotion.instance))) {
    				e.world.spawnEntityInWorld(new EntityExplosivePotion(e.world));
    			}
    		}
    	}
    }
}
