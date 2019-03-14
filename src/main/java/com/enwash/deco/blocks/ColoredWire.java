package com.enwash.deco.blocks;

import java.util.Random;

import com.enwash.deco.init.BTDCBlocks;
import com.enwash.deco.init.BTDCItems;
import com.enwash.deco.items.ItemColorString;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTripWire;
import net.minecraft.block.BlockTripWireHook;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ColoredWire extends BlockTripWire {
	public static final PropertyEnum<EnumDyeColor> COLOR = PropertyEnum.<EnumDyeColor>create("color", EnumDyeColor.class);
	public ColoredWire(String name) {
		super();
		setRegistryName("minecraft", "tripwire");
		setUnlocalizedName(name);
		setCreativeTab(CreativeTabs.MATERIALS);
		setDefaultState(this.blockState.getBaseState().withProperty(COLOR, EnumDyeColor.WHITE).withProperty(POWERED, Boolean.valueOf(false)).withProperty(ATTACHED, Boolean.valueOf(false)).withProperty(DISARMED, Boolean.valueOf(false)).withProperty(NORTH, Boolean.valueOf(false)).withProperty(EAST, Boolean.valueOf(false)).withProperty(SOUTH, Boolean.valueOf(false)).withProperty(WEST, Boolean.valueOf(false)));
		BTDCBlocks.BLOCKS.add(this);
		BTDCItems.ITEMS.add(new ItemColorString(this).setRegistryName("minecraft", "string"));
	}
    @Override
    public int damageDropped(IBlockState state)
    {
        return ((EnumDyeColor)state.getValue(COLOR)).getMetadata();
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        for (EnumDyeColor enumdyecolor : EnumDyeColor.values())
        {
            items.add(new ItemStack(this, 1, enumdyecolor.getMetadata()));
        }
    }


    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(COLOR, EnumDyeColor.byMetadata(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int i= ((EnumDyeColor)state.getValue(COLOR)).getMetadata();
        return i;
    }
    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {COLOR, POWERED, ATTACHED, DISARMED, NORTH, EAST, WEST, SOUTH});
    }
    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state){
    	super.onBlockAdded(worldIn, pos, state);
    	this.updateTick(worldIn, pos, state, new Random());
    }
    
	public static boolean isConnectedTo(IBlockAccess worldIn, BlockPos pos, IBlockState state, EnumFacing direction)
	{
	    BlockPos blockpos = pos.offset(direction);
	    IBlockState iblockstate = worldIn.getBlockState(blockpos);
	    Block block = iblockstate.getBlock();
	
	    if (block == Blocks.TRIPWIRE_HOOK)
	    {
	        EnumFacing enumfacing = direction.getOpposite();
	        return iblockstate.getValue(BlockTripWireHook.FACING) == enumfacing;
	    }
	    else if (block == BTDCBlocks.COLOR_STRING)
	    {
	        return iblockstate.getValue(COLOR) == state.getValue(COLOR);
	    }
	    else return false;
	}
	
	@Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return state.withProperty(NORTH, Boolean.valueOf(isConnectedTo(worldIn, pos, state, EnumFacing.NORTH))).withProperty(EAST, Boolean.valueOf(isConnectedTo(worldIn, pos, state, EnumFacing.EAST))).withProperty(SOUTH, Boolean.valueOf(isConnectedTo(worldIn, pos, state, EnumFacing.SOUTH))).withProperty(WEST, Boolean.valueOf(isConnectedTo(worldIn, pos, state, EnumFacing.WEST)));
    }
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
		return Item.getItemFromBlock(this);
    }
	@Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(this);
    }
}

