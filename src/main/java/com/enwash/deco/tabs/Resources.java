package com.enwash.deco.tabs;

import com.enwash.deco.init.BTDCBlocks;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class Resources extends CreativeTabs {

	public Resources(String label) {super("btdcresources");}
	@Override
	public ItemStack getTabIconItem() { return new ItemStack(BTDCBlocks.LOG_TINTED); }
	
}
