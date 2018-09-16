package com.enwash.deco.blocks;

import com.enwash.deco.Main;
import com.enwash.deco.init.BTDCBlocks;
import com.enwash.deco.init.BTDCItems;
import com.enwash.deco.util.ICanHazModel;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BDTorch extends Block implements ICanHazModel{
	
	public BDTorch(String name, Material material, Float light) {
		super(material);
		setSoundType(SoundType.METAL);
		setHardness(1.0F);
		setResistance(1.0F);
		setHarvestLevel("pickaxe", 0);
		setLightLevel(light);
		setUnlocalizedName(name);
		
		BTDCBlocks.BLOCKS.add(this);
		BTDCItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	
	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
		
	}
	
}
