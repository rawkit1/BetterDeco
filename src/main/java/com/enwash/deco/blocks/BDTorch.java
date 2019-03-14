package com.enwash.deco.blocks;

import com.enwash.deco.init.BTDCBlocks;
import com.enwash.deco.init.BTDCItems;
import com.enwash.deco.util.ModMaterial;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BDTorch extends Block{
	
	public BDTorch(String name, Material material, Float light) {
		super(material);
		setSoundType(SoundType.METAL);
		setHardness(1.0F);
		setResistance(1.0F);
		setHarvestLevel("pickaxe", 0);
		setLightLevel(light);
		setUnlocalizedName(name);
		
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
