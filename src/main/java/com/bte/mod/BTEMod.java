package com.bte.mod;

import com.bte.mod.block.ModBlocks;
import com.bte.mod.capability.CapabilityHandler;
import com.bte.mod.capability.Charge;
import com.bte.mod.capability.ChargeStorage;
import com.bte.mod.capability.ICharge;
import com.bte.mod.entity.EntitySittableBlock;
import com.bte.mod.item.ModItems;
import com.bte.mod.network.PacketRequestUpdatePedestal;
import com.bte.mod.network.PacketUpdatePedestal;
import com.bte.mod.proxy.CommonProxy;
import com.bte.mod.recipe.ModRecipes;
import com.bte.mod.world.ModWorldGen;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.init.Blocks;
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

    public static SimpleNetworkWrapper network;

    public static final Logger LOGGER = Logger.getLogger(MODID);

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        LOGGER.info("Starting Pre-Intialization...");
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
        LOGGER.info("Starting Intialization...");
        ModRecipes.init();
        EntityRegistry.registerModEntity(new ResourceLocation("bte:mountable_block"), EntitySittableBlock.class, "MountableBlock", 0, this, 80, 1, false);
        initColorsBlocksItems();
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){
        LOGGER.info("Starting Post-Intialization...");
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
        public static void registerModels(ModelRegistryEvent event) {
            ModItems.registerModels();
            ModBlocks.registerModels();
        }
    }

    @SuppressWarnings("all")
    public void initColorsBlocksItems(){
        final BlockColors blockcolors = Minecraft.getMinecraft().getBlockColors();
        blockcolors.registerBlockColorHandler(new IBlockColor()
        {
            public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex)
            {
                return worldIn != null && pos != null ? BiomeColorHelper.getGrassColorAtPos(worldIn, pos) : ColorizerGrass.getGrassColor(0.5D, 1.0D);
            }
        }, ModBlocks.grass_slab);
        blockcolors.registerBlockColorHandler(new IBlockColor()
        {
            public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex)
            {
                return worldIn != null && pos != null ? BiomeColorHelper.getGrassColorAtPos(worldIn, pos) : ColorizerGrass.getGrassColor(0.5D, 1.0D);
            }
        }, ModBlocks.grass_doubleslab);
        final ItemColors itemcolors = Minecraft.getMinecraft().getItemColors();
        itemcolors.registerItemColorHandler(new IItemColor()
        {
            public int getColorFromItemstack(ItemStack stack, int tintIndex)
            {
                IBlockState iblockstate = ((ItemBlock)stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata());
                return blockcolors.colorMultiplier(iblockstate, (IBlockAccess)null, (BlockPos)null, tintIndex);
            }
        }, ModBlocks.grass_slab, ModBlocks.grass_doubleslab);
    }
}
