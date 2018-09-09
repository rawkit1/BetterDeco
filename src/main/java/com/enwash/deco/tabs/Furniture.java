package com.enwash.deco.tabs;

import com.enwash.deco.init.ModBlocks;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class Furniture extends CreativeTabs {

	public Furniture(String label) {super("busts");}
	@Override
	public ItemStack getTabIconItem() { return new ItemStack(ModBlocks.BUST_NOTCH); }
	
}
