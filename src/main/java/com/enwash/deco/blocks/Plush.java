package com.enwash.deco.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class Plush extends Bust {
	public static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB(0.25D, 0.0D, 0.25D, .75D, .75D, .75D);
	
	public Plush(String name, Material material) {
		super(name, material);
		setSoundType(SoundType.CLOTH);
		setHardness(3.0F);
		setResistance(1.0F);
		setUnlocalizedName(name);
		this.setLightOpacity(0);
	}
	
	@Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    	return BOUNDING_BOX;
    }
}
