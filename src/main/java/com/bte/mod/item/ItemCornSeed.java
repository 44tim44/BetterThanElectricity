package com.bte.mod.item;

/**
 * Created by Timeout on 2017-08-20.
 */
import com.bte.mod.BTEMod;
import com.bte.mod.block.ModBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;

public class ItemCornSeed extends ItemSeeds {

    public ItemCornSeed() {
        super(ModBlocks.cropCorn, Blocks.FARMLAND);
        setUnlocalizedName("corn_seed");
        setRegistryName("corn_seed");
    }

    public void registerItemModel() {
        BTEMod.proxy.registerItemRenderer(this, 0, "corn_seed");
    }

}