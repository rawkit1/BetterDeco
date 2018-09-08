package com.enwash.deco.util.handlers;

import com.enwash.deco.blocks.container.ContainerMailbox;
import com.enwash.deco.util.Reference;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		//if(ID == Reference.GUI_MAILBOX) {return new ContainerMailbox();}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		//if(ID == Reference.GUI_MAILBOX) {return new GuiMailbox();}
		return null;
	}

	public static void registerGUIs() {
		
	}
}
