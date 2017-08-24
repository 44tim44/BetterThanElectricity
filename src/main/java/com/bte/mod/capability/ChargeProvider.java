package com.bte.mod.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

/**
 * Created by Timeout on 2017-08-23.
 */
public class ChargeProvider implements ICapabilitySerializable<NBTBase>
{
    @CapabilityInject(ICharge.class)
    public static final Capability<ICharge> CHARGE_CAPABILITY = null;

    private ICharge instance = CHARGE_CAPABILITY.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        return capability == CHARGE_CAPABILITY;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        //return capability == CHARGE_CAPABILITY ? CHARGE_CAPABILITY.<T> cast(this.instance) : null;
        if (capability == CHARGE_CAPABILITY)
        {
            return (T) instance;
        }

        return null;
    }

    @Override
    public NBTBase serializeNBT()
    {
        //return CHARGE_CAPABILITY.getStorage().writeNBT(CHARGE_CAPABILITY, this.instance, null);
        return CHARGE_CAPABILITY.writeNBT(this.instance,null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt)
    {
        //CHARGE_CAPABILITY.getStorage().readNBT(CHARGE_CAPABILITY, this.instance, null, nbt);
        CHARGE_CAPABILITY.readNBT(this.instance, null, nbt);
    }
}
