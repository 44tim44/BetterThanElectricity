package com.bte.mod.proxy;

import com.bte.mod.BTEMod;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.*;

/**
 * Created by Timeout on 2017-08-20.
 */
public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent event){
        super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event){
        super.init(event);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event){
        super.postInit(event);
    }

    @Override
    public void serverStarting(FMLServerStartingEvent event){
        super.serverStarting(event);
    }

    @Override
    public void serverStopping(FMLServerStoppingEvent event){
        super.serverStopping(event);
    }

    @Override
    public void registerItemRenderer(Item item, int meta, String id) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(BTEMod.MODID + ":" + id, "inventory"));
    }
}
