package com.enwash.deco.blocks;

import java.util.ArrayList;
import java.util.List;

import com.enwash.deco.Main;
import com.enwash.deco.init.ModBlocks;
import com.enwash.deco.init.ModItems;
import com.enwash.deco.util.IHasModel;
import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class CoffeeTable extends Block implements IHasModel
{
    public static final PropertyBool BACK = PropertyBool.create("back");
    public static final PropertyBool FORWARD = PropertyBool.create("forward");
    public static final PropertyBool LEFT = PropertyBool.create("left");
    public static final PropertyBool RIGHT = PropertyBool.create("right");

  
    public static final AxisAlignedBB COFFEE_TABLE_AABB = new AxisAlignedBB(0, 0, 0, 1D, .75D, 1D);
    public CoffeeTable(String name, Material material, SoundType sound)
    {
        super(material);
        setUnlocalizedName("coffee_table_glass");
        setRegistryName("coffee_table_glass");
        this.setHardness(1.0F);
        this.setSoundType(sound);
        this.setDefaultState(this.blockState.getBaseState().withProperty(BACK, false).withProperty(FORWARD, false).withProperty(LEFT, false).withProperty(RIGHT, false));
        this.setCreativeTab(Main.furniture);        
        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        if(placer instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) placer;
        }
        super.onBlockPlacedBy(world, pos, state, placer, stack);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    	return COFFEE_TABLE_AABB;
    }
    
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        return false;
    }

    @Override
    public boolean isSideSolid(IBlockState baseState, IBlockAccess world, BlockPos pos, EnumFacing side)
    {
        return side == EnumFacing.UP;
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        boolean back = world.getBlockState(pos.south()).getBlock() == this;
        boolean forward = world.getBlockState(pos.north()).getBlock() == this;
        boolean left = world.getBlockState(pos.west()).getBlock() == this;
        boolean right = world.getBlockState(pos.east()).getBlock() == this;
        return state.withProperty(BACK, back).withProperty(FORWARD, forward).withProperty(LEFT, left).withProperty(RIGHT, right);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return 0;
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState();
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, BACK, FORWARD, LEFT, RIGHT);
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return BlockFaceShape.UNDEFINED;
    }

    @Override
    public boolean isFullBlock(IBlockState state) 
    {
        return false;
    }
    
    @Override
    public BlockRenderLayer getBlockLayer(){
        
        return BlockRenderLayer.CUTOUT;
    }

	@Override
	public void registerModels() {
		// TODO Auto-generated method stub
		
	}
    
}