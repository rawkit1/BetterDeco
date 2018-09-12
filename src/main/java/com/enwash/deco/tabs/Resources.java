package com.enwash.deco.tabs;

import com.enwash.deco.init.ModBlocks;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class Resources extends CreativeTabs {

	public Resources(String label) {super("bdresources");}
	@Override
	public ItemStack getTabIconItem() { return new ItemStack(ModBlocks.MAILBOX_WOOD); }
	
}
