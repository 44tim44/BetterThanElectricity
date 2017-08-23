package com.bte.mod.item.tool;

import com.bte.mod.BTEMod;
import net.minecraft.item.Item;

/**
 * Created by Timmy on 2016-11-26.
 */
public class ItemSword extends net.minecraft.item.ItemSword{

    private String name;

    public ItemSword(ToolMaterial material, String name) {
        super(material);
        setRegistryName(name);
        setUnlocalizedName(name);
        this.name = name;
    }

    public void registerItemModel() {
        BTEMod.proxy.registerItemRenderer(this, 0, name);
    }

}