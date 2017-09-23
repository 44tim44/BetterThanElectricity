package com.bte.mod.block;

import com.bte.mod.ModEnums;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by Timeout on 2017-09-23.
 */
public class BlockSlabDoubleDirt extends BlockSlabDirt {

    public BlockSlabDoubleDirt(ModEnums.BlockType type, String name) {
        super(type, name);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(ModBlocks.dirt_slab);
    }

    @Override
    public boolean isDouble() {
        return true;
    }
}
