package com.bte.mod.block;

import com.bte.mod.BTEMod;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import java.util.Random;

/**
 * Created by Timeout on 2017-09-20.
 */
public abstract class BlockSlabBase extends BlockSlab
{

    public static final PropertyBool VARIANT = PropertyBool.create("variant");
    private static final int HALF_META_BIT = 8;
    protected String name;

    public BlockSlabBase(Material material, String name)
    {
        super(material);

        this.name = name;
        setUnlocalizedName(name);
        setRegistryName(name);

        IBlockState iblockstate = this.blockState.getBaseState();
        iblockstate = iblockstate.withProperty(VARIANT, false);
        if (!this.isDouble())
        {
            iblockstate = iblockstate.withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM);
        }

        this.setDefaultState(iblockstate);
    }


    /**
     * Returns the slab block name with the type associated with it
     */
    public String getUnlocalizedName(int meta)
    {
        return this.getUnlocalizedName();
    }

    public IProperty<?> getVariantProperty()
    {
        return VARIANT;
    }

    /**
     * Gets a block state from metadata.
     * @param meta the metadata or color value.
     * @return a block state with the meta encoded as the variant property.
     */
    @Override
    public final IBlockState getStateFromMeta(final int meta) {
        IBlockState blockState = this.getDefaultState();
        blockState = blockState.withProperty(VARIANT, false);
        if (!this.isDouble()) {
            EnumBlockHalf value = EnumBlockHalf.BOTTOM;
            if ((meta & HALF_META_BIT) != 0) {
                value = EnumBlockHalf.TOP;
            }

            blockState = blockState.withProperty(HALF, value);
        }

        return blockState;
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        if (this.isDouble())
        {
            return 0;
        }

        if (state.getValue(HALF) == BlockSlab.EnumBlockHalf.TOP)
        {
            return HALF_META_BIT;
        }
        else
        {
            return 0;
        }
    }


    public int damageDropped(IBlockState state)
    {
        return 0;
    }

    /**
     * Get the Item that this Block should drop when harvested.
     */
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(Blocks.STONE_SLAB);
    }

    protected BlockStateContainer createBlockState()
    {
        if (this.isDouble()) {
            return new BlockStateContainer(this, new IProperty[] {VARIANT});
        } else {
            return new BlockStateContainer(
                    this,
                    new IProperty[] {HALF, VARIANT});
        }
    }

    public void registerItemModel(Item itemBlock) {
        BTEMod.proxy.registerItemRenderer(itemBlock, 0, name);
    }

    public Item createItemBlock() {
        return new ItemBlock(this).setRegistryName(this.getRegistryName());
    }

    @Override
    public BlockSlabBase setCreativeTab(CreativeTabs tab) {
        if (!this.isDouble()) {
            super.setCreativeTab(tab);
        }
        return this;
    }

}
