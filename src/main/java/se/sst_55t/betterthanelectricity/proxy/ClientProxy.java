package se.sst_55t.betterthanelectricity.proxy;

import se.sst_55t.betterthanelectricity.BTEMod;
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
    }

    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        //ModItems.registerModels();
        //ModBlocks.registerModels();
    }

    @Override
    public void init(FMLInitializationEvent event)
    {
    }

    @Override
    public void postInit(FMLPostInitializationEvent event)
    {
    }
}
