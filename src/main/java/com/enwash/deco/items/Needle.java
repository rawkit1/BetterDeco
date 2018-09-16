package com.enwash.deco.items;

import com.enwash.deco.Main;
import com.enwash.deco.init.BTDCItems;
import com.enwash.deco.util.ICanHazModel;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class Needle extends Item implements ICanHazModel {

	public Needle(String name)
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.resources);
		setMaxStackSize(1);
		BTDCItems.ITEMS.add(this);
	}
	
	@Override
	public void registerModels() {
		
		Main.proxy.registerItemRenderer(this, 0, "inventory");
		
	}
	
	@Override
	public Item getContainerItem() {
		return BTDCItems.SEWING_NEEDLE;
	}
	
}
