package com.enwash.deco.proxy;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.item.Item;

public class CommonProxy {

	public void registerItemRenderer(Item item, int meta, String id) {}
	public void registerStateMap(Block block, IStateMapper mapper){}
}
