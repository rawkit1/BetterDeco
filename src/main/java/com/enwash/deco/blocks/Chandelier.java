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

public class Chandelier extends BlockBase {
	
	public static final AxisAlignedBB CANDELABRA_AABB = new AxisAlignedBB(0D, 0D, 0D, 1D, .25D, 1D);
	
	public Chandelier(String name, Material material, Float light, SoundType soundType) {
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
    	return CANDELABRA_AABB;
    }
    
   
    
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {

    	/*
    	   double[][] posList = {
    		{(double)pos.getX() + .625D, (double)pos.getY() + 6D, (double)pos.getZ() + .5D},
    		{(double)pos.getX() + .9375D, (double)pos.getY() + 6D, (double)pos.getZ() + 0.5D},
    		{(double)pos.getX() + .5D, (double)pos.getY() + 6D, (double)pos.getZ() + .0625D},
    		{(double)pos.getX() + .5D, (double)pos.getY() + 6D, (double)pos.getZ() + .9375D}
    		};
    	 */
    	
        double a0 = (double)pos.getX() + .125D;
        double a1 = (double)pos.getY() + .6D;
        double a2 = (double)pos.getZ() + .5D;
        double b0 = (double)pos.getX() + .875D;
        double b1 = (double)pos.getY() + .6D;
        double b2 = (double)pos.getZ() + .5D;
        double c0 = (double)pos.getX() + .5D;
        double c1 = (double)pos.getY() + .6D;
        double c2 = (double)pos.getZ() + .125D;
        double d0 = (double)pos.getX() + .5;
        double d1 = (double)pos.getY() + .6D;
        double d2 = (double)pos.getZ() + .875D;

        worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, a0, a1, a2, 0.0D, 0.0D, 0.0D);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, a0, a1, a2, 0.0D, 0.0D, 0.0D);
        worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, b0, b1, b2, 0.0D, 0.0D, 0.0D);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, b0, b1, b2, 0.0D, 0.0D, 0.0D);
        worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, c0, c1, c2, 0.0D, 0.0D, 0.0D);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, c0, c1, c2, 0.0D, 0.0D, 0.0D);
        worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D);
    }
    
}
