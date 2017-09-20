package com.bte.mod;

import com.bte.mod.block.ModBlocks;
import com.bte.mod.capability.CapabilityHandler;
import com.bte.mod.capability.Charge;
import com.bte.mod.capability.ChargeStorage;
import com.bte.mod.capability.ICharge;
import com.bte.mod.entity.EntitySittableBlock;
import com.bte.mod.item.ModItems;
import com.bte.mod.proxy.CommonProxy;
import com.bte.mod.recipe.ModRecipes;
import com.bte.mod.world.ModWorldGen;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.logging.Logger;

/**
 * Created by Timeout on 2017-08-20.
 */

@Mod(modid = BTEMod.MODID, name = BTEMod.MODNAME, version = BTEMod.VERSION)
public class BTEMod {

    public static final String MODID = "bte";
    public static final String MODNAME = "Better Than Electricity";
    public static final String VERSION = "1.0.0";
    public static final String CLIENT_PROXY = "com.bte.mod.proxy.ClientProxy";
    public static final String COMMON_PROXY = "com.bte.mod.proxy.CommonProxy";

    public static final Item.ToolMaterial copperToolMaterial = EnumHelper
            .addToolMaterial("COPPER", 2, 250, 6, 2, 14);

    public static final Item.ToolMaterial bronzeToolMaterial = EnumHelper
            .addToolMaterial("BRONZE", 2, 500, 8, 4, 20);

    public static final Item.ToolMaterial steelToolMaterial = EnumHelper
            .addToolMaterial("STEEL", 2, 1000, 10, 3, 20);

    @SidedProxy(clientSide = CLIENT_PROXY, serverSide = COMMON_PROXY)
    public static CommonProxy proxy;

    @Mod.Instance
    public static BTEMod instance;

    public static final Logger LOGGER = Logger.getLogger(MODID);

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        LOGGER.info("Starting Pre-Intialization...");
        GameRegistry.registerWorldGenerator(new ModWorldGen(), 3);
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new ModGuiHandler());
        CapabilityManager.INSTANCE.register(ICharge.class, new ChargeStorage(), Charge.class);
        MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
        LOGGER.info("Starting Intialization...");
        ModRecipes.init();
        EntityRegistry.registerModEntity(new ResourceLocation("bte:mountable_block"), EntitySittableBlock.class, "MountableBlock", 0, this, 80, 1, false);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){
        LOGGER.info("Starting Post-Intialization...");
    }

    @Mod.EventBusSubscriber
    public static class RegistrationHandler {

        @SubscribeEvent
        public static void registerBlocks(RegistryEvent.Register<Block> event) {
            ModBlocks.register(event.getRegistry());
        }

        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event) {
            ModItems.register(event.getRegistry());
            ModBlocks.registerItemBlocks(event.getRegistry());
        }

        @SubscribeEvent
        public static void registerModels(ModelRegistryEvent event) {
            ModItems.registerModels();
            ModBlocks.registerModels();
        }
    }
}
