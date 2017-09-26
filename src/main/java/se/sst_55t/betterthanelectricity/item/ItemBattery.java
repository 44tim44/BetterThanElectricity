package se.sst_55t.betterthanelectricity.item;

import se.sst_55t.betterthanelectricity.BTEMod;
import se.sst_55t.betterthanelectricity.capability.ChargeProvider;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Timeout on 2017-08-22.
 */
public class ItemBattery extends Item {

    public ItemBattery() {
        setUnlocalizedName("battery");
        setRegistryName("battery");
        setMaxStackSize(1);
        setCreativeTab(CreativeTabs.MISC);
    }


    @Override
    @Nullable
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt){
        //ICharge charge = stack.getCapability(ChargeProvider.CHARGE_CAPABILITY,null);
        //charge.setCharge(0);
        //charge.setMaxCharge(640);
        return new ChargeProvider();
    }



    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        tooltip.add("Charge: " + stack.getCapability(ChargeProvider.CHARGE_CAPABILITY,null).getCharge() + "/" + stack.getCapability(ChargeProvider.CHARGE_CAPABILITY,null).getMaxCharge());
    }


    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return true;
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        return 1 -((double)stack.getCapability(ChargeProvider.CHARGE_CAPABILITY,null).getCharge() / (double)stack.getCapability(ChargeProvider.CHARGE_CAPABILITY,null).getMaxCharge());
    }

    public void registerItemModel() {
        BTEMod.proxy.registerItemRenderer(this, 0, "battery");
    }
}
