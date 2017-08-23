package com.bte.mod;

import com.bte.mod.block.electricfurnace.ContainerElectricFurnace;
import com.bte.mod.block.electricfurnace.GuiElectricFurnace;
import com.bte.mod.block.electricfurnace.TileEntityElectricFurnace;
import com.bte.mod.block.pulverizer.ContainerPulverizer;
import com.bte.mod.block.pulverizer.TileEntityPulverizer;
import com.bte.mod.block.pulverizer.GuiPulverizer;
import com.bte.mod.block.solarpanel.ContainerSolarPanel;
import com.bte.mod.block.solarpanel.GuiSolarPanel;
import com.bte.mod.block.solarpanel.TileEntitySolarPanel;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;


/**
 * Created by Timmy on 2016-11-27.
 */
public class ModGuiHandler implements IGuiHandler {

    public static final int PULVERIZER = 0;
    public static final int ELECFURNACE = 1;
    public static final int SOLARPANEL = 2;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case PULVERIZER:
                return new ContainerPulverizer(player.inventory, (TileEntityPulverizer)world.getTileEntity(new BlockPos(x, y, z)));
            case ELECFURNACE:
                return new ContainerElectricFurnace(player.inventory, (TileEntityElectricFurnace)world.getTileEntity(new BlockPos(x, y, z)));
            case SOLARPANEL:
                return new ContainerSolarPanel(player.inventory,(TileEntitySolarPanel)world.getTileEntity(new BlockPos(x, y, z)));
            default:
                return null;
        }
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);
        switch (ID) {
            case PULVERIZER:
                TileEntityPulverizer tep = (TileEntityPulverizer) te;
                return new GuiPulverizer(player.inventory, tep);
            case ELECFURNACE:
                TileEntityElectricFurnace teef = (TileEntityElectricFurnace) te;
                return new GuiElectricFurnace(player.inventory, teef);
            case SOLARPANEL:
                TileEntitySolarPanel tesp = (TileEntitySolarPanel) te;
                return new GuiSolarPanel((Container) getServerGuiElement(ID, player, world, x, y, z), player.inventory, tesp);
            default:
                return null;
        }
    }
}

