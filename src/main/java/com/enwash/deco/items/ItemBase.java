package com.enwash.deco.items;

import com.enwash.deco.init.BTDCItems;
import net.minecraft.item.Item;

public class ItemBase extends Item{

	public ItemBase(String name)
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		BTDCItems.ITEMS.add(this);
	}	
}
