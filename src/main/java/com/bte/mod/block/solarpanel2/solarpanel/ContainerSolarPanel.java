package com.bte.mod.block.solarpanel2.solarpanel;

import net.darkhax.tesla.api.ITeslaHolder;
import net.darkhax.tesla.api.ITeslaProducer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

/**
 * Created by Timeout on 2017-08-22.
 */
public class ContainerSolarPanel implements ITeslaHolder, ITeslaProducer, IEnergyStorage{

    private long storedPower = 0;

    @Override
    public long getStoredPower() {
        return this.storedPower;
    }

    @Override
    public long getCapacity() {
        return SolarPanelConfig.panelCapacity;
    }

    @Override
    public long takePower(long tesla, boolean simulated) {
        final long removedPower = Math.min(this.storedPower, Math.min(SolarPanelConfig.panelTransferRate, tesla));

        if (!simulated)
            this.storedPower -= removedPower;

        return removedPower;
    }

    public void generatePower(){
        this.storedPower += SolarPanelConfig.panelPowerGen;

        if (this.storedPower > this.getCapacity())
            this.storedPower = this.getCapacity();
    }

    protected void setPower (long power) {

        this.storedPower = power;
    }

    public static int getIntPower (long power) {

        if (power < Integer.MIN_VALUE)
            return Integer.MIN_VALUE;

        if (power > Integer.MAX_VALUE)
            return Integer.MAX_VALUE;

        return (int) power;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        return 0;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        return getIntPower(this.takePower(maxExtract, simulate));
    }

    @Override
    public int getEnergyStored() {
        return getIntPower(this.storedPower);
    }

    @Override
    public int getMaxEnergyStored() {
        return getIntPower(this.getCapacity());
    }

    @Override
    public boolean canExtract() {
        return true;
    }

    @Override
    public boolean canReceive() {
        return false;
    }
}
