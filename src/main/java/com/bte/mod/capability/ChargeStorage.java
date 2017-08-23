package com.bte.mod.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

/**
 * Created by Timeout on 2017-08-23.
 */
public class ChargeStorage implements Capability.IStorage<ICharge> {

    @Override
    public NBTBase writeNBT(Capability<ICharge> capability, ICharge instance, EnumFacing side)
    {
        return new NBTTagInt(instance.getCharge());
    }

    @Override
    public void readNBT(Capability<ICharge> capability, ICharge instance, EnumFacing side, NBTBase nbt)
    {
        instance.setCharge(((NBTPrimitive) nbt).getInt());
    }
}
