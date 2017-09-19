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

    public static BlockBase planks_white = new BlockBase(Material.WOOD,"planks_white").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    public static BlockBase planks_lightgray = new BlockBase(Material.WOOD,"planks_lightgray").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    public static BlockBase planks_gray = new BlockBase(Material.WOOD,"planks_gray").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    public static BlockBase planks_black = new BlockBase(Material.WOOD,"planks_black").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    public static BlockBase planks_brown = new BlockBase(Material.WOOD,"planks_brown").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    public static BlockBase planks_red = new BlockBase(Material.WOOD,"planks_red").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    public static BlockBase planks_orange = new BlockBase(Material.WOOD,"planks_orange").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    public static BlockBase planks_yellow = new BlockBase(Material.WOOD,"planks_yellow").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    public static BlockBase planks_lime = new BlockBase(Material.WOOD,"planks_lime").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    public static BlockBase planks_green = new BlockBase(Material.WOOD,"planks_green").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    public static BlockBase planks_cyan = new BlockBase(Material.WOOD,"planks_cyan").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    public static BlockBase planks_lightblue = new BlockBase(Material.WOOD,"planks_lightblue").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    public static BlockBase planks_blue = new BlockBase(Material.WOOD,"planks_blue").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    public static BlockBase planks_purple = new BlockBase(Material.WOOD,"planks_purple").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    public static BlockBase planks_magenta = new BlockBase(Material.WOOD,"planks_magenta").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    public static BlockBase planks_pink = new BlockBase(Material.WOOD,"planks_pink").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);


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
                solarPanel,

                planks_white,
                planks_lightgray,
                planks_gray,
                planks_black,
                planks_brown,
                planks_red,
                planks_orange,
                planks_yellow,
                planks_lime,
                planks_green,
                planks_cyan,
                planks_lightblue,
                planks_blue,
                planks_purple,
                planks_magenta,
                planks_pink
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
                solarPanel.createItemBlock(),
                planks_white.createItemBlock(),
                planks_lightgray.createItemBlock(),
                planks_gray.createItemBlock(),
                planks_black.createItemBlock(),
                planks_brown.createItemBlock(),
                planks_red.createItemBlock(),
                planks_orange.createItemBlock(),
                planks_yellow.createItemBlock(),
                planks_lime.createItemBlock(),
                planks_green.createItemBlock(),
                planks_cyan.createItemBlock(),
                planks_lightblue.createItemBlock(),
                planks_blue.createItemBlock(),
                planks_purple.createItemBlock(),
                planks_magenta.createItemBlock(),
                planks_pink.createItemBlock()
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

        planks_white.registerItemModel(Item.getItemFromBlock(planks_white));
        planks_lightgray.registerItemModel(Item.getItemFromBlock(planks_lightgray));
        planks_gray.registerItemModel(Item.getItemFromBlock(planks_gray));
        planks_black.registerItemModel(Item.getItemFromBlock(planks_black));
        planks_brown.registerItemModel(Item.getItemFromBlock(planks_brown));
        planks_red.registerItemModel(Item.getItemFromBlock(planks_red));
        planks_orange.registerItemModel(Item.getItemFromBlock(planks_orange));
        planks_yellow.registerItemModel(Item.getItemFromBlock(planks_yellow));
        planks_lime.registerItemModel(Item.getItemFromBlock(planks_lime));
        planks_green.registerItemModel(Item.getItemFromBlock(planks_green));
        planks_cyan.registerItemModel(Item.getItemFromBlock(planks_cyan));
        planks_lightblue.registerItemModel(Item.getItemFromBlock(planks_lightblue));
        planks_blue.registerItemModel(Item.getItemFromBlock(planks_blue));
        planks_purple.registerItemModel(Item.getItemFromBlock(planks_purple));
        planks_magenta.registerItemModel(Item.getItemFromBlock(planks_magenta));
        planks_pink.registerItemModel(Item.getItemFromBlock(planks_pink));
    }

}