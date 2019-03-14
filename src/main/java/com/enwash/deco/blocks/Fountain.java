package com.enwash.deco.blocks;

import com.enwash.deco.Main;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

//made by Merlijn (DutchM/merren2306)

public class Fountain extends BlockBase{
	public static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 11.0D/16.0D, 1.0D);

	public Fountain(String name, Material material) { 
			super(name, material);
			setSoundType(SoundType.STONE);
			setHardness(1);
			setResistance(1);
			setCreativeTab(Main.furniture);
	}
	@Override
	@SideOnly(Side.CLIENT)
	public boolean canRenderInLayer(IBlockState state, BlockRenderLayer layer){
		return layer == BlockRenderLayer.SOLID || layer == BlockRenderLayer.TRANSLUCENT;
	}
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    	return BOUNDING_BOX;
    }
	public boolean isFullCube(IBlockState state){
		return false;
	}
	public boolean isOpaqueCube(IBlockState state){
		return false;
	}
}