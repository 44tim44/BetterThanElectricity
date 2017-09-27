package se.sst_55t.betterthanelectricity.block.solarpanel;


import se.sst_55t.betterthanelectricity.capability.ChargeProvider;
import se.sst_55t.betterthanelectricity.capability.ICharge;
import se.sst_55t.betterthanelectricity.item.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

/**
 * Created by Timeout on 2017-08-22.
 */
public class TileEntitySolarPanel extends TileEntity implements ITickable {

    private ItemStackHandler inventory = new ItemStackHandler(1);

    public void update () {
        ItemStack itemstack = inventory.getStackInSlot(0);
        if (isCharging() && (itemstack.getItem() == ModItems.battery || itemstack.getItem() == ModItems.miningDrill))
        {
            ICharge charge = itemstack.getCapability(ChargeProvider.CHARGE_CAPABILITY,null);
            if(charge.getCharge() < charge.getMaxCharge()) {
                charge.increaseCharge();
            }
            /*
            ItemBattery battery = (ItemBattery)itemstack.getItem();
            if (battery.getCharge() < battery.getMaxCharge())
            {
                battery.increaseCharge();
            }
            */
        }
    }

    public int getCharge(){
        ItemStack itemstack = inventory.getStackInSlot(0);
        if (itemstack.isEmpty()){
            return -1;
        }
        else if (itemstack.getItem() == ModItems.battery)
        {
            ICharge charge = itemstack.getCapability(ChargeProvider.CHARGE_CAPABILITY,null);
            return charge.getCharge();
            /*
            ItemBattery battery = (ItemBattery)itemstack.getItem();
            return battery.getCharge();
            */
        }
        else
        {
            return -1;
        }
    }

    public boolean isCharging()
    {
        if (this.hasWorld())
        {
            if (!this.world.provider.isNether() && this.world.canBlockSeeSky(this.pos.offset(EnumFacing.UP)) && !this.world.isRaining() && world.getWorldTime()%24000 < 12000 && world.getWorldTime()%24000 > 0) {
                return true;
            }
            else return false;
        }
        return false;
    }

    @Override
    public void readFromNBT (NBTTagCompound compound) {
        inventory.deserializeNBT(compound.getCompoundTag("inventory"));
        super.readFromNBT(compound);
    }

    @Override
    public NBTTagCompound writeToNBT (NBTTagCompound compound) {
        compound.setTag("inventory", inventory.serializeNBT());
        return super.writeToNBT(compound);
    }


    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T)inventory : super.getCapability(capability, facing);
    }
}
