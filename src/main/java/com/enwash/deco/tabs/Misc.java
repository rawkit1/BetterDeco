package com.enwash.deco.tabs;

import com.enwash.deco.init.ModBlocks;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class Misc extends CreativeTabs {

	public Misc(String label) {super("bdmisc");}
	@Override
	public ItemStack getTabIconItem() { return new ItemStack(ModBlocks.MAILBOX_WOOD); }
	
}
