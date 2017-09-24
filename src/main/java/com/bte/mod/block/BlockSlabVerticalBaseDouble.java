package com.bte.mod.block;

import com.bte.mod.ModEnums;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

import java.util.Random;

/**
 * Created by Timeout on 2017-09-24.
 */
public class BlockSlabVerticalBaseDouble extends BlockSlabVerticalBase
{
    public BlockSlabVerticalBaseDouble(ModEnums.BlockType type, String name) {
        super(type, name);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        switch (type) {
            case SMOOTHSTONE:
                return Item.getItemFromBlock(ModBlocks.smoothstone_slab);
            default:
                return Item.getItemFromBlock(Blocks.AIR);
        }
    }

    @Override
    public boolean isDouble() {
        return true;
    }
}
