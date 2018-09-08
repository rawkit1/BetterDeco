package com.enwash.deco.blocks.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class ContainerMailbox extends Container {

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/*
	private final int numRows;
	private final TileEntityMailbox chestInventory;
	
	public ContainerMailbox(InventoryPlayer playerInv, TileEntityMailbox chestInventory, EntityPlayer player) {
		this.chestInventory = chestInventory;
		this.numRows = chestInventory / 9;
		chestInventory.openInventory(player);
		
		for (int i = 0; i < this.numRows; ++ i) {
			for (int j == 0; j < 9; ++ j) {
				
			}
		}
	}*/
}
