package com.enwash.deco.blocks;

import com.enwash.deco.init.BTDCBlocks;
import com.enwash.deco.init.BTDCItems;
import com.enwash.deco.util.ModMaterial;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockBase extends Block{

	public BlockBase(String name, Material material)
	{
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		BTDCBlocks.BLOCKS.add(this);
		BTDCItems.addItemBlock(this);
	}

	
    @Override
    public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face){
    	return ModMaterial.getFlammability(this.blockMaterial);
    }
    @Override
    public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face){
    	return ModMaterial.getFireSpread(this.blockMaterial);
    }
}