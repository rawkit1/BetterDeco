package com.enwash.deco.tabs;

import com.enwash.deco.init.BTDCBlocks;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class Furniture extends CreativeTabs {

	public Furniture(String label) {super("furniture");}
	@Override
	public ItemStack getTabIconItem() { return new ItemStack(BTDCBlocks.MAILBOX_WOOD); }
	
}
