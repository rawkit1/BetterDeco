package com.enwash.deco.init;


import java.util.ArrayList;
import java.util.List;

import com.enwash.deco.items.CraftTool;
import com.enwash.deco.items.ItemBase;
import com.enwash.deco.util.ModMaterial;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class BTDCItems {

	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	//public static final ToolMaterial MATERIAL_ETH_SWORD = EnumHelper.addToolMaterial("material_eth_sword", 3, 1800, 8.0F, 8.0F, 10);
	
	public static final Item ITEM_404 = new ItemBase("item_404");
	public static final Item SEWING_KIT = new CraftTool("sewing_kit", 16);
	@SuppressWarnings("deprecation")
	public static void addItemBlock(Block block){
		if (block.getMaterial(block.getDefaultState()) == ModMaterial.HARDWOOD){
			ITEMS.add(new ItemBlock(block){@Override public int getItemBurnTime(ItemStack itemStack){return 300;}}.setRegistryName(block.getRegistryName()));
		}
		else{
			ITEMS.add(new ItemBlock(block).setRegistryName(block.getRegistryName()));	
		}
	}
}
