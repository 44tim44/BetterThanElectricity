package com.bte.mod.block;

import com.bte.mod.ModEnums.BlockType;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

/**
 * Created by Timeout on 2017-09-21.
 */
public class BlockSlabDoubleGrass extends BlockSlabGrass {

    public BlockSlabDoubleGrass(BlockType type, String name) {
        super(type, name);

    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(ModBlocks.grass_slab);
    }

    @Override
    public boolean isDouble() {
        return true;
    }
}