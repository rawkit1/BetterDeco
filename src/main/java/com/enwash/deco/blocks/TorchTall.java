package com.enwash.deco.blocks;

import java.util.Random;

import com.enwash.deco.Main;
import com.enwash.deco.init.ModBlocks;
import com.enwash.deco.init.ModItems;
import com.enwash.deco.util.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TorchTall extends BlockBase {
	
	public static final AxisAlignedBB TORCH_TALL_AABB = new AxisAlignedBB(0.375D, 0, 0.375D, 0.625D, 2D, 0.625D);
	
	public TorchTall(String name, Material material, Float light, SoundType soundType) {
		super(name, material);
		setSoundType(soundType);
		setHardness(1.0F);
		setResistance(1.0F);
		setHarvestLevel("axe", 0);
		setLightLevel(light);
		setUnlocalizedName(name);
		setCreativeTab(Main.lighting);
	}
	


    @Override
    public boolean isOpaqueCube(IBlockState state) 
    {
        return false;
    }

    @Override
    public boolean isFullBlock(IBlockState state) 
    {
        return false;
    }
    
    @Override
    public boolean isFullCube(IBlockState state) {
    	return false;
    }
	
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    	return TORCH_TALL_AABB;
    }
    
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {

        double d0 = (double)pos.getX() + 0.5D;
        double d1 = (double)pos.getY() + 2.1D;
        double d2 = (double)pos.getZ() + 0.5D;
        double d3 = 0.22D;
        double d4 = 0.27D;

        worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D);
    }
    
}
