package com.bte.mod.block;

import com.bte.mod.BTEMod;
import com.bte.mod.ModEnums.FireType;
import com.bte.mod.ModEnums.BlockType;
import com.bte.mod.item.ItemSlabCustom;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

/**
 * Created by Timeout on 2017-09-21.
 */
public class BlockSlabBase extends BlockSlab {
    protected String name;
    protected final BlockType type;


    public BlockSlabBase(BlockType type, String name) {
        super(type.getMaterialType().getMaterial());
        this.setSoundType(type.getMaterialType().getSound());

        this.name = name;
        this.type = type;
        this.useNeighborBrightness = true;
        this.setHardness(2.0F);
        this.setResistance(5.0F);

        setUnlocalizedName(name);
        setRegistryName(name);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return this.isDouble() ? new BlockStateContainer(this) : new BlockStateContainer(this, HALF);
    }


    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState iblockstate = this.getDefaultState();

        if (!this.isDouble()) {
            iblockstate = iblockstate.withProperty(HALF, (meta & 8) == 0 ? BlockSlab.EnumBlockHalf.BOTTOM : BlockSlab.EnumBlockHalf.TOP);
        }

        return iblockstate;
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;

        if (!this.isDouble() && state.getValue(HALF) == BlockSlab.EnumBlockHalf.TOP) {
            i |= 8;
        }

        return i;
    }

    @Override
    public Comparable<?> getTypeForItem(ItemStack stack) {
        return null;
    }

    @Override
    public String getUnlocalizedName(int meta) {
        return this.getUnlocalizedName();
    }

    @Override
    public IProperty<?> getVariantProperty() {
        return null;
    }

    @Override
    public boolean isDouble() {
        return false;
    }

    @Override
    public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face) {
        return FireType.SLAB.getFireSpread();
    }

    @Override
    public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
        return FireType.SLAB.getFlamability();
    }

    public void registerItemModel(Item itemBlock) {
        BTEMod.proxy.registerItemRenderer(itemBlock, 0, name);
    }

    public Item createItemSlab(BlockSlabBase block, BlockSlabBase singleSlab, BlockSlabBase doubleSlab) {
        return new ItemSlabCustom(this,singleSlab,doubleSlab).setRegistryName(this.getRegistryName());
    }

    @Override
    public BlockSlabBase setCreativeTab(CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }

}