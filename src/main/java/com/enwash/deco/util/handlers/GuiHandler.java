package com.enwash.deco.util.handlers;


import com.enwash.deco.blocks.container.ContainerSpinningWheel;
import com.enwash.deco.blocks.tileentities.TileEntityWheel;
import com.enwash.deco.gui.GUISpinningWheel;
import com.enwash.deco.util.Reference;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		//if(ID == Reference.GUI_MAILBOX) {return new ContainerMailbox();}
		if(ID == Reference.GUI_WHEEL) {
			BlockPos xyz = new BlockPos(x, y, z);
			TileEntity tileEntity = world.getTileEntity(xyz);
			if (tileEntity instanceof TileEntityWheel) return new ContainerSpinningWheel(player.inventory, (TileEntityWheel)tileEntity);
			}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		//if(ID == Reference.GUI_MAILBOX) {return new GuiMailbox();}
		if(ID == Reference.GUI_WHEEL) {
			BlockPos xyz = new BlockPos(x, y, z);
			TileEntity tileEntity = world.getTileEntity(xyz);
			if (tileEntity instanceof TileEntityWheel) return new GUISpinningWheel(player.inventory, (TileEntityWheel)tileEntity);
			}
		return null;
	}
}
