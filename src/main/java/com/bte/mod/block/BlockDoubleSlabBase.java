package com.bte.mod.block;

import com.bte.mod.block.BlockSlabBase;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;

/**
 * Created by Timeout on 2017-09-20.
 */
public class BlockDoubleSlabBase extends BlockSlabBase
{

    public BlockDoubleSlabBase(Material material, String name) {
        super(material, name);
    }

    @Override
    public boolean isDouble() {
        return true;
    }

    @Override
    public Comparable<?> getTypeForItem(ItemStack stack) {
        return null;
    }

}
