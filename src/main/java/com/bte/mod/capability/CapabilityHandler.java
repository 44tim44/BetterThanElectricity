package com.bte.mod.capability;

import com.bte.mod.BTEMod;
import com.bte.mod.item.ItemBattery;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by Timeout on 2017-08-23.
 */

public class CapabilityHandler
{
    /*
    public static final ResourceLocation CHARGE_CAPABILITY = new ResourceLocation(BTEMod.MODID, "charge");


    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent<Item> item)
    {
        if (item.getObject() instanceof ItemBattery) {
            item.addCapability(CHARGE_CAPABILITY, new ChargeProvider());
        }
    }
    */
}