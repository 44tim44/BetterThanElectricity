package com.bte.mod.block.solarpanel2.solarpanel;

import net.darkhax.tesla.api.ITeslaConsumer;
import net.darkhax.tesla.capability.TeslaCapabilities;
import net.darkhax.tesla.lib.TeslaUtils;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

/**
 * Created by Timeout on 2017-08-22.
 */
public class TileEntitySolarPanel extends TileEntity implements ITickable {

    private final ContainerSolarPanel container;

    public TileEntitySolarPanel(){
        this.container = new ContainerSolarPanel();
    }

    public void update () {

        if (this.hasWorld()) {

            if (!this.world.provider.isNether() && this.world.canBlockSeeSky(this.pos.offset(EnumFacing.UP)) && !this.world.isRaining() && this.world.getSkylightSubtracted() == 0 && this.container.getStoredPower() != this.container.getCapacity())
                this.container.generatePower();

            final TileEntity tile = this.getWorld().getTileEntity(this.getPos().offset(EnumFacing.DOWN));

            if (tile != null && !tile.isInvalid()) {

                if (tile.hasCapability(TeslaCapabilities.CAPABILITY_CONSUMER, EnumFacing.UP)) {

                    ITeslaConsumer consumer = TeslaUtils.getTeslaConsumer(tile, EnumFacing.UP);

                    if (consumer != null)
                        this.container.takePower(consumer.givePower(Math.min(this.container.getStoredPower(), SolarPanelConfig.panelTransferRate), false), false);
                }

                else if (tile.hasCapability(CapabilityEnergy.ENERGY, EnumFacing.UP)) {

                    IEnergyStorage consumer = tile.getCapability(CapabilityEnergy.ENERGY, EnumFacing.UP);

                    if (consumer != null)
                        this.container.extractEnergy(consumer.receiveEnergy(ContainerSolarPanel.getIntPower(Math.min(this.container.getStoredPower(), SolarPanelConfig.panelTransferRate)), false), false);
                }
            }
        }
    }

    @Override
    public void readFromNBT (NBTTagCompound compound) {

        super.readFromNBT(compound);
        this.container.setPower(compound.getLong("StoredPower"));
    }

    @Override
    public NBTTagCompound writeToNBT (NBTTagCompound compound) {

        compound.setLong("StoredPower", this.container.getStoredPower());
        return super.writeToNBT(compound);
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket () {

        return new SPacketUpdateTileEntity(this.pos, 0, this.getUpdateTag());
    }

    @Override
    public NBTTagCompound getUpdateTag () {

        return this.writeToNBT(new NBTTagCompound());
    }

    @Override
    public void onDataPacket (NetworkManager net, SPacketUpdateTileEntity packet) {

        super.onDataPacket(net, packet);
        this.readFromNBT(packet.getNbtCompound());
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getCapability (Capability<T> capability, EnumFacing facing) {

        if (facing == EnumFacing.DOWN && (TeslaUtils.isProducerCapability(capability) || TeslaUtils.isHolderCapability(capability) || capability == CapabilityEnergy.ENERGY))
            return (T) this.container;

        return super.getCapability(capability, facing);
    }

    @Override
    public boolean hasCapability (Capability<?> capability, EnumFacing facing) {

        if (facing == EnumFacing.DOWN && (TeslaUtils.isProducerCapability(capability) || TeslaUtils.isHolderCapability(capability) || capability == CapabilityEnergy.ENERGY))
            return true;

        return super.hasCapability(capability, facing);
    }
}
