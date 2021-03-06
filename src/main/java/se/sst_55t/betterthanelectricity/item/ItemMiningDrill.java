package se.sst_55t.betterthanelectricity.item;


import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import se.sst_55t.betterthanelectricity.BTEMod;
import se.sst_55t.betterthanelectricity.capability.ChargeProvider;
import se.sst_55t.betterthanelectricity.capability.ICharge;
import se.sst_55t.betterthanelectricity.util.ModSoundEvents;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Timeout on 2017-09-27.
 */
public class ItemMiningDrill extends Item {
    public ItemMiningDrill() {
        setUnlocalizedName("mining_drill");
        setRegistryName("mining_drill");
        setMaxStackSize(1);
        setCreativeTab(CreativeTabs.TOOLS);
    }

    @Override
    @Nullable
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt){
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

    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemstack = player.getHeldItem(hand);
        if(itemstack.getCapability(ChargeProvider.CHARGE_CAPABILITY,null).getCharge()>0)
        {
            if (!player.canPlayerEdit(pos.offset(facing), facing, itemstack))
            {
                return EnumActionResult.FAIL;
            }
            else
            {
                IBlockState iblockstate = worldIn.getBlockState(pos);
                Block block = iblockstate.getBlock();
                Material material = iblockstate.getMaterial();

                if (block != Blocks.BEDROCK && (material == Material.ROCK || material == Material.GROUND || material == Material.GRASS || block == Blocks.GRAVEL || block == Blocks.SAND))
                {
                    this.mineBlock(itemstack, player, worldIn, pos, iblockstate);
                    return EnumActionResult.SUCCESS;
                }
                else
                {
                    return EnumActionResult.FAIL;
                }
            }
        }
        else
        {
            return EnumActionResult.FAIL;
        }
    }

    protected void mineBlock(ItemStack stack, EntityPlayer player, World worldIn, BlockPos pos, IBlockState state)
    {
        worldIn.playSound(player, pos, ModSoundEvents.DRILL_SPIN, SoundCategory.BLOCKS, 1.0F, 1.0F);

        if (!worldIn.isRemote)
        {
            worldIn.destroyBlock(pos,true);
            //stack.damageItem(1, player);
            ICharge charge = stack.getCapability(ChargeProvider.CHARGE_CAPABILITY,null);
            charge.decreaseCharge();
        }
    }

    public void registerItemModel() {
        BTEMod.proxy.registerItemRenderer(this, 0, "mining_drill");
    }
}
