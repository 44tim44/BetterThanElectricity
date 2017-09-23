package com.bte.mod.block;

import com.bte.mod.ModEnums;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by Timeout on 2017-09-23.
 */
public class BlockSlabGrassSnowed extends BlockSlabBase {
    public BlockSlabGrassSnowed(ModEnums.BlockType type, String name) {
        super(type, name);
        setHardness(0.6F);
        this.setTickRandomly(true);
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        if (this.isDouble())
        {
            worldIn.setBlockState(pos, Blocks.GRASS.getDefaultState());
            if(worldIn.getBlockState(pos.up()).getBlock() == Blocks.AIR)
            {
                worldIn.setBlockState(pos.up(), Blocks.SNOW_LAYER.getDefaultState());
            }
        }
        else if(state.getValue(HALF) == BlockSlab.EnumBlockHalf.TOP)
        {
            worldIn.setBlockState(pos, ModBlocks.grass_slab.getDefaultState().withProperty(HALF,BlockSlab.EnumBlockHalf.TOP));
            if(worldIn.getBlockState(pos.up()).getBlock() == Blocks.AIR)
            {
                worldIn.setBlockState(pos.up(), Blocks.SNOW_LAYER.getDefaultState());
            }
        }
    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (worldIn.getLightFor(EnumSkyBlock.BLOCK, pos.up()) > 11)
        {
            if(state.getValue(HALF) == BlockSlab.EnumBlockHalf.TOP) {
                worldIn.setBlockState(pos, ModBlocks.grass_slab.getDefaultState().withProperty(HALF,BlockSlab.EnumBlockHalf.TOP));
            }
            else
            {
                worldIn.setBlockState(pos, ModBlocks.grass_slab.getDefaultState().withProperty(HALF,BlockSlab.EnumBlockHalf.BOTTOM));
            }
        }
    }

    @Override
    public BlockSlabGrassSnowed setCreativeTab(CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }
}
