package se.sst_55t.betterthanelectricity.proxy;

import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import se.sst_55t.betterthanelectricity.BTEMod;
import se.sst_55t.betterthanelectricity.block.BlockDoorBase;
import se.sst_55t.betterthanelectricity.block.ModBlocks;
import se.sst_55t.betterthanelectricity.block.table.TESRTable;
import se.sst_55t.betterthanelectricity.block.table.TileEntityTable;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.*;
import se.sst_55t.betterthanelectricity.item.ModItems;

/**
 * Created by Timeout on 2017-08-20.
 */
public class ClientProxy extends CommonProxy
{
    @Override
    public void registerItemRenderer(Item item, int meta, String id)
    {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(BTEMod.MODID + ":" + id, "inventory"));
    }

    @Override
    public void registerRenderers() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTable.class, new TESRTable());
        ModelLoader.setCustomStateMapper(ModBlocks.glassDoor, (new StateMap.Builder()).ignore(BlockDoorBase.POWERED).build());
    }

    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
    }

    @Override
    public void init(FMLInitializationEvent event)
    {
    }

    @Override
    public void postInit(FMLPostInitializationEvent event)
    {
    }

    @Mod.EventBusSubscriber
    public static class RegistrationHandler
    {
        @SubscribeEvent
        public static void registerModels(ModelRegistryEvent event)
        {
            ModItems.registerModels();
            ModBlocks.registerModels();
        }

    }
}
