package com.enwash.deco.blocks;

import java.util.Random;

import com.enwash.deco.Main;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Candelabra extends BlockBase{
	
	public static final AxisAlignedBB CANDELABRA_AABB = new AxisAlignedBB(0.25D, 0D, 0.25D, 0.75D, 1D, 0.75D);
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	
	public Candelabra(String name, Material material, Float light, SoundType soundType) {
		super(name, material);
		setSoundType(soundType);
		setHardness(1.0F);
		setResistance(1.0F);
		setHarvestLevel("axe", 0);
		setLightLevel(light);
		setUnlocalizedName(name);
		setCreativeTab(Main.lighting);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
	}

	// Rotation
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) 
	{
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) 
	{
		worldIn.setBlockState(pos, this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
	}
	
	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot)
	{
		return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
	}
	
	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) 
	{
		return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
	}
	
	@Override
	protected BlockStateContainer createBlockState() 
	{
		return new BlockStateContainer(this, new IProperty[] {FACING});
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) 
	{
		EnumFacing facing = EnumFacing.getFront(meta);
		if(facing.getAxis() == EnumFacing.Axis.Y) facing = EnumFacing.NORTH;
		return this.getDefaultState().withProperty(FACING, facing);
	}
	
	@Override
	public int getMetaFromState(IBlockState state) 
	{
		return ((EnumFacing)state.getValue(FACING)).getIndex();
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
    	
        double s1 = (double)pos.getY() + .8125D;
        double m0 = (double)pos.getX() + .5D;
        double m1 = (double)pos.getY() + .75D;
        double m2 = (double)pos.getZ() + .5D;
        double a0;
        double b0;
        double a2;
        double b2;
        if (stateIn.getValue(FACING) == EnumFacing.NORTH || stateIn.getValue(FACING) == EnumFacing.SOUTH){
        	a0 = (double)pos.getX() + .75D;
        	b0 = (double)pos.getX() + .25D;
        	a2 = (double)pos.getZ() + .5D;
        	b2 = a2;
        }
        else{
        	a2 = (double)pos.getZ() + .75D;
        	b2 = (double)pos.getZ() + .25D;
        	a0 = (double)pos.getX() + .5D;
        	b0 = a0;
        }
        worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, a0, s1, a2, 0.0D, 0.0D, 0.0D);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, a0, s1, a2, 0.0D, 0.0D, 0.0D);
        worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, b0, s1, b2, 0.0D, 0.0D, 0.0D);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, b0, s1, b2, 0.0D, 0.0D, 0.0D);
        
        worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, m0, m1, m2, 0.0D, 0.0D, 0.0D);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, m0, m1, m2, 0.0D, 0.0D, 0.0D);
    }
}
