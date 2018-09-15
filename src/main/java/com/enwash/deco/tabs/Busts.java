package com.enwash.deco.tabs;

import com.enwash.deco.init.BTDCBlocks;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class Busts extends CreativeTabs {

	public Busts(String label) {super("busts");}
	@Override
	public ItemStack getTabIconItem() { return new ItemStack(BTDCBlocks.BUST_NOTCH); }
	
}
