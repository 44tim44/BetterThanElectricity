package se.sst_55t.betterthanelectricity.capability;

import net.minecraft.util.ResourceLocation;
import se.sst_55t.betterthanelectricity.BTEMod;

/**
 * Created by Timeout on 2017-08-23.
 */

public class CapabilityHandler
{

    public static final ResourceLocation CHARGE_CAPABILITY = new ResourceLocation(BTEMod.MODID, "charge");

/*
    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent<Item> item)
    {
        if (item.getObject() instanceof ItemBattery) {
            item.addCapability(CHARGE_CAPABILITY, new ChargeProvider());
        }
    }
    */
}