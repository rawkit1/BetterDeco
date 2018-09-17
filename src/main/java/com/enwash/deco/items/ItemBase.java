package com.enwash.deco.items;

import com.enwash.deco.Main;
import com.enwash.deco.init.BTDCItems;
import com.enwash.deco.util.ICanHazModel;

import net.minecraft.creativetab.CreativeTabs;
import com.enwash.deco.util.ICanHazModel;
import net.minecraft.item.Item;

public class ItemBase extends Item implements ICanHazModel {

	public ItemBase(String name)
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		BTDCItems.ITEMS.add(this);
	}
	
	@Override
	public void registerModels() {
		
		Main.proxy.registerItemRenderer(this, 0, "inventory");
		
	}

	
}
