package com.bte.mod.block;

import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;

/**
 * Created by Timeout on 2017-09-20.
 */
public class BlockHalfSlabBase extends BlockSlabBase
{

    public BlockHalfSlabBase(Material material, String name) {
        super(material, name);
    }

    @Override
    public boolean isDouble() {
        return false;
    }

    @Override
    public Comparable<?> getTypeForItem(ItemStack stack) {
        return null;
    }

}
