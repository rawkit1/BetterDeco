package com.enwash.deco.init;


import java.util.ArrayList;
import java.util.List;

import com.enwash.deco.items.CraftTool;
import com.enwash.deco.items.ItemBase;

import net.minecraft.item.Item;

public class BTDCItems {

	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	//public static final ToolMaterial MATERIAL_ETH_SWORD = EnumHelper.addToolMaterial("material_eth_sword", 3, 1800, 8.0F, 8.0F, 10);
	
	//public static final Item ETHEREUM_SHARD = new ItemBase("ethereum_shard");
	public static final Item ITEM_404 = new ItemBase("item_404");
	public static final Item SEWING_KIT = new CraftTool("sewing_kit", 16);
}
