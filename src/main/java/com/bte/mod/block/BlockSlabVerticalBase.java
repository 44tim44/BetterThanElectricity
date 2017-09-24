package com.bte.mod.block;

import com.bte.mod.BTEMod;
import com.bte.mod.ModEnums;
import com.bte.mod.item.ItemSlabCustom;
import com.bte.mod.item.ItemVerticalSlab;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * Created by Timeout on 2017-09-24.
 */
public class BlockSlabVerticalBase extends BlockSlab {

    public static final PropertyEnum<BlockSlabVerticalBase.EnumPosition> POSITION = PropertyEnum.<BlockSlabVerticalBase.EnumPosition>create("half", BlockSlabVerticalBase.EnumPosition.class);
    public static final PropertyEnum<BlockSlabVerticalBase.EnumShape> SHAPE = PropertyEnum.<BlockSlabVerticalBase.EnumShape>create("shape", BlockSlabVerticalBase.EnumShape.class);
    protected static final AxisAlignedBB AABB_NORTH_HALF = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.5D);
    protected static final AxisAlignedBB AABB_SOUTH_HALF = new AxisAlignedBB(0.0D, 0.0D, 0.5D, 1.0D, 1.0D, 1.0D);
    protected static final AxisAlignedBB AABB_EAST_HALF = new AxisAlignedBB(0.5D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
    protected static final AxisAlignedBB AABB_WEST_HALF = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.5D, 1.0D, 1.0D);

    protected String name;
    protected final ModEnums.BlockType type;

    public BlockSlabVerticalBase(ModEnums.BlockType type, String name) {
        super(type.getMaterialType().getMaterial());
        this.setSoundType(type.getMaterialType().getSound());

        this.name = name;
        this.type = type;
        this.useNeighborBrightness = true;
        this.setResistance(5.0F);

        setUnlocalizedName(name);
        setRegistryName(name);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return this.isDouble() ? new BlockStateContainer(this) : new BlockStateContainer(this, POSITION);
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState iblockstate = this.getDefaultState();

        switch (meta){
            case 0:
                return iblockstate;
            case 1:
                return iblockstate.withProperty(POSITION,EnumPosition.NORTH);
            case 2:
                return iblockstate.withProperty(POSITION,EnumPosition.SOUTH);
            case 3:
                return iblockstate.withProperty(POSITION,EnumPosition.EAST);
            case 4:
                return iblockstate.withProperty(POSITION,EnumPosition.WEST);
        }
        return iblockstate;
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;

        if(!isDouble()) {
            switch (state.getValue(POSITION)) {
                case NORTH:
                    return 1;
                case SOUTH:
                    return 2;
                case EAST:
                    return 3;
                case WEST:
                    return 4;
            }
        }
        return i;
    }


    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        if (this.isDouble())
        {
            return FULL_BLOCK_AABB;
        }
        else
        {
            switch (state.getValue(POSITION))
            {
                case NORTH:
                    return AABB_NORTH_HALF;
                case SOUTH:
                    return AABB_SOUTH_HALF;
                case EAST:
                    return AABB_EAST_HALF;
                case WEST:
                    return AABB_WEST_HALF;
            }
        }
        return AABB_NORTH_HALF;
    }

    /**
     * Determines if the block is solid enough on the top side to support other blocks, like redstone components.
     */
    public boolean isTopSolid(IBlockState state)
    {
        return ((BlockSlab)state.getBlock()).isDouble();
    }

    public BlockFaceShape getBlockFaceShape(IBlockAccess access, IBlockState state, BlockPos pos, EnumFacing facing)
    {
        if (((BlockSlab)state.getBlock()).isDouble())
        {
            return BlockFaceShape.SOLID;
        }
        else if (facing == EnumFacing.UP || facing == EnumFacing.DOWN)
        {
            return BlockFaceShape.UNDEFINED;
        }
        else
        {
            switch (state.getValue(POSITION))
            {
                case NORTH:
                    if(facing == EnumFacing.NORTH)
                    {
                        return BlockFaceShape.SOLID;
                    }
                case SOUTH:
                    if(facing == EnumFacing.SOUTH)
                    {
                        return BlockFaceShape.SOLID;
                    }
                case EAST:
                    if(facing == EnumFacing.EAST)
                    {
                        return BlockFaceShape.SOLID;
                    }
                case WEST:
                    if(facing == EnumFacing.WEST)
                    {
                        return BlockFaceShape.SOLID;
                    }
            }
            return BlockFaceShape.UNDEFINED;
        }
    }

    @Override
    public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
        if (net.minecraftforge.common.ForgeModContainer.disableStairSlabCulling)
        {
            return super.doesSideBlockRendering(state, world, pos, face);
        }

        if (state.isOpaqueCube())
        {
            return true;
        }

        EnumPosition side = state.getValue(POSITION);
        switch (side){
            case NORTH:
                return face == EnumFacing.NORTH;
            case SOUTH:
                return face == EnumFacing.SOUTH;
            case EAST:
                return face == EnumFacing.EAST;
            case WEST:
                return face == EnumFacing.WEST;
        }
        return isDouble();
    }

    /**
     * Called by ItemBlocks just before a block is actually set in the world, to allow for adjustments to the
     * IBlockstate
     */
    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        IBlockState iblockstate = this.getStateFromMeta(meta);

        if (this.isDouble())
        {
            return iblockstate.withProperty(POSITION, EnumPosition.NORTH);
        }
        else
        {
            switch (facing) {
                case NORTH:
                    return iblockstate.withProperty(POSITION, EnumPosition.SOUTH);
                case SOUTH:
                    return iblockstate.withProperty(POSITION, EnumPosition.NORTH);
                case EAST:
                    return iblockstate.withProperty(POSITION, EnumPosition.WEST);
                case WEST:
                    return iblockstate.withProperty(POSITION, EnumPosition.EAST);
                default:
                    switch (placer.getHorizontalFacing()){
                        case NORTH:
                            return iblockstate.withProperty(POSITION, EnumPosition.NORTH);
                        case SOUTH:
                            return iblockstate.withProperty(POSITION, EnumPosition.SOUTH);
                        case EAST:
                            return iblockstate.withProperty(POSITION, EnumPosition.EAST);
                        case WEST:
                            return iblockstate.withProperty(POSITION, EnumPosition.WEST);
                    }
            }
        }
        return iblockstate.withProperty(POSITION, EnumPosition.NORTH);
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
        return ModEnums.FireType.SLAB.getFireSpread();
    }

    @Override
    public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
        return ModEnums.FireType.SLAB.getFlamability();
    }

    public void registerItemModel(Item itemBlock) {
        BTEMod.proxy.registerItemRenderer(itemBlock, 0, name);
    }

    public Item createItemSlab(BlockSlabVerticalBase block, BlockSlabVerticalBase singleSlab, BlockSlabVerticalBase doubleSlab) {
        return new ItemVerticalSlab(this,singleSlab,doubleSlab).setRegistryName(this.getRegistryName());
    }

    @Override
    public BlockSlabVerticalBase setCreativeTab(CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }

    /**
     * Sets how many hits it takes to break a block.
     */
    @Override
    public BlockSlabVerticalBase setHardness(float hardness)
    {
        this.blockHardness = hardness;

        if (this.blockResistance < hardness * 5.0F)
        {
            this.blockResistance = hardness * 5.0F;
        }

        return this;
    }

    public static enum EnumPosition implements IStringSerializable
    {
        NORTH("north"),
        SOUTH("south"),
        EAST("east"),
        WEST("west");

        private final String name;

        private EnumPosition(String name)
        {
            this.name = name;
        }

        public String toString()
        {
            return this.name;
        }

        public String getName()
        {
            return this.name;
        }
    }

    public static enum EnumShape implements IStringSerializable
    {
        STRAIGHT("straight"),
        INNER_CORNER("inner_corner"),
        OUTER_CORNER("outer_corner");

        private final String name;

        private EnumShape(String name)
        {
            this.name = name;
        }

        public String toString()
        {
            return this.name;
        }

        public String getName()
        {
            return this.name;
        }
    }
}
