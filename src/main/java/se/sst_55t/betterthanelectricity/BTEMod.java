package se.sst_55t.betterthanelectricity;

import net.minecraftforge.fml.common.registry.EntityEntry;
import se.sst_55t.betterthanelectricity.block.ModBlocks;
import se.sst_55t.betterthanelectricity.capability.CapabilityHandler;
import se.sst_55t.betterthanelectricity.capability.Charge;
import se.sst_55t.betterthanelectricity.capability.ChargeStorage;
import se.sst_55t.betterthanelectricity.capability.ICharge;
import se.sst_55t.betterthanelectricity.entity.EntitySittableBlock;
import se.sst_55t.betterthanelectricity.item.ModItems;
import se.sst_55t.betterthanelectricity.network.PacketRequestUpdatePedestal;
import se.sst_55t.betterthanelectricity.network.PacketUpdatePedestal;
import se.sst_55t.betterthanelectricity.proxy.CommonProxy;
import se.sst_55t.betterthanelectricity.recipe.ModRecipes;
import se.sst_55t.betterthanelectricity.world.ModWorldGen;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeColorHelper;
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
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

import javax.annotation.Nullable;
import java.util.logging.Logger;

/**
 * Created by Timeout on 2017-08-20.
 */

@Mod(modid = BTEMod.MODID, name = BTEMod.MODNAME, version = BTEMod.VERSION)
public class BTEMod {

    public static final String MODID = "betterthanelectricity";
    public static final String MODNAME = "Better Than Electricity";
    public static final String VERSION = "1.0.0";
    public static final String CLIENT_PROXY = "se.sst_55t.betterthanelectricity.proxy.ClientProxy";
    public static final String COMMON_PROXY = "se.sst_55t.betterthanelectricity.proxy.CommonProxy";

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

    public static SimpleNetworkWrapper network;

    public static final Logger LOGGER = Logger.getLogger(MODID);

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        GameRegistry.registerWorldGenerator(new ModWorldGen(), 3);
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new ModGuiHandler());
        CapabilityManager.INSTANCE.register(ICharge.class, new ChargeStorage(), Charge.class);
        MinecraftForge.EVENT_BUS.register(new CapabilityHandler());

        proxy.registerRenderers();

        network = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);
        network.registerMessage(new PacketUpdatePedestal.Handler(), PacketUpdatePedestal.class, 0, Side.CLIENT);
        network.registerMessage(new PacketRequestUpdatePedestal.Handler(), PacketRequestUpdatePedestal.class, 1, Side.SERVER);

        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
        ModRecipes.init();
        EntityRegistry.registerModEntity(new ResourceLocation("betterthanelectricity:mountable_block"), EntitySittableBlock.class, "MountableBlock", 0, this, 80, 1, false);

        //MinecraftForge.EVENT_BUS.register(new EventHandlerCommon());
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){
        proxy.postInit(event);
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
        public static void registerEntities(RegistryEvent.Register<EntityEntry> event)
        {
        }

    }


}
