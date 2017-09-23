package com.bte.mod.block;

import com.bte.mod.ModEnums;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created by Timeout on 2017-09-23.
 */
public class BlockSlabDirt extends BlockSlabBase {
    public BlockSlabDirt(ModEnums.BlockType type, String name) {
        super(type, name);
        setHardness(0.6F);
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        if (this.isDouble())
        {
            worldIn.setBlockState(pos, Blocks.DIRT.getDefaultState());
        }
    }

    @Override
    public BlockSlabDirt setCreativeTab(CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }
}
