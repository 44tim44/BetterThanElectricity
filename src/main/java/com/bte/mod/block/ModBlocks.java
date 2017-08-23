package com.bte.mod.block;

/**
 * Created by Timeout on 2017-08-20.
 */
import com.bte.mod.block.counter.BlockCounter;
import com.bte.mod.block.electricfurnace.BlockElectricFurnace;
import com.bte.mod.block.pulverizer.BlockPulverizer;
import com.bte.mod.block.solarpanel.BlockSolarPanel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class ModBlocks {

    public static BlockOre oreCopper = new BlockOre("ore_copper").setCreativeTab(CreativeTabs.MATERIALS);
    public static BlockOre oreTin = new BlockOre("ore_tin").setCreativeTab(CreativeTabs.MATERIALS);
    public static BlockOre oreAluminum = new BlockOre("ore_aluminum").setCreativeTab(CreativeTabs.MATERIALS);

    public static BlockCropCorn cropCorn = new BlockCropCorn();

    public static BlockCounter counter = new BlockCounter();
    public static BlockPulverizer pulverizer = (BlockPulverizer) new BlockPulverizer(false,"pulverizer_block").setCreativeTab(CreativeTabs.DECORATIONS);
    public static BlockPulverizer pulverizer_on = new BlockPulverizer(true,"pulverizer_block_on");
    public static BlockElectricFurnace electricFurnace = (BlockElectricFurnace) new BlockElectricFurnace(false,"electric_furnace_block").setCreativeTab(CreativeTabs.DECORATIONS);
    public static BlockElectricFurnace electricFurnace_on = new BlockElectricFurnace(true,"electric_furnace_block_on");

    public static BlockBase machineblock = new BlockBase(Material.IRON,"machine_block").setCreativeTab(CreativeTabs.DECORATIONS);
    public static BlockSolarPanel solarPanel = new BlockSolarPanel();

    public static void register(IForgeRegistry<Block> registry) {
        registry.registerAll(
                oreCopper,
                oreTin,
                oreAluminum,
                cropCorn,
                counter,
                pulverizer,
                pulverizer_on,
                electricFurnace,
                electricFurnace_on,
                machineblock,
                solarPanel
        );

        GameRegistry.registerTileEntity(pulverizer.getTileEntityClass(), pulverizer.getRegistryName().toString());
        GameRegistry.registerTileEntity(electricFurnace.getTileEntityClass(), electricFurnace.getRegistryName().toString());
        GameRegistry.registerTileEntity(solarPanel.getTileEntityClass(), solarPanel.getRegistryName().toString());
    }

    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
        registry.registerAll(
                oreCopper.createItemBlock(),
                oreTin.createItemBlock(),
                oreAluminum.createItemBlock(),
                counter.createItemBlock(),
                pulverizer.createItemBlock(),
                pulverizer_on.createItemBlock(),
                electricFurnace.createItemBlock(),
                electricFurnace_on.createItemBlock(),
                machineblock.createItemBlock(),
                solarPanel.createItemBlock()
        );
    }

    public static void registerModels() {
        oreCopper.registerItemModel(Item.getItemFromBlock(oreCopper));
        oreTin.registerItemModel(Item.getItemFromBlock(oreTin));
        oreAluminum.registerItemModel(Item.getItemFromBlock(oreAluminum));
        counter.registerItemModel(Item.getItemFromBlock(counter));
        pulverizer.registerItemModel(Item.getItemFromBlock(pulverizer));
        pulverizer_on.registerItemModel(Item.getItemFromBlock(pulverizer_on));
        electricFurnace.registerItemModel(Item.getItemFromBlock(electricFurnace));
        electricFurnace_on.registerItemModel(Item.getItemFromBlock(electricFurnace_on));
        machineblock.registerItemModel(Item.getItemFromBlock(machineblock));
        solarPanel.registerItemModel(Item.getItemFromBlock(solarPanel));
    }

}