package com.bte.mod.proxy;

import com.bte.mod.capability.CapabilityHandler;
import com.bte.mod.capability.Charge;
import com.bte.mod.capability.ChargeStorage;
import com.bte.mod.capability.ICharge;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by Timeout on 2017-08-20.
 */
public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event){}
    public void init(FMLInitializationEvent event){
        CapabilityManager.INSTANCE.register(ICharge.class, new ChargeStorage(), Charge.class);
        MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
    }
    public void postInit(FMLPostInitializationEvent event){}
    public void serverStarting(FMLServerStartingEvent event){}
    public void serverStopping(FMLServerStoppingEvent event){}
    public void registerItemRenderer(Item item, int meta, String id) {}

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event){

    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event){

    }
}
