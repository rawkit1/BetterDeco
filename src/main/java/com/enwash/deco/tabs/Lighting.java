package com.enwash.deco.tabs;

import com.enwash.deco.init.ModBlocks;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class Lighting extends CreativeTabs {

	public Lighting(String label) {super("lighting");}
	@Override
	public ItemStack getTabIconItem() { return new ItemStack(ModBlocks.CANDELABRA); }
	
}
