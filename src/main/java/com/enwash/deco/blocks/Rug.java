package com.enwash.deco.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import com.enwash.deco.Main;
import com.enwash.deco.init.BTDCBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Rug extends Block {
	public static final PropertyBool NORTH = PropertyBool.create("north");
    public static final PropertyBool EAST = PropertyBool.create("east");
    public static final PropertyBool SOUTH = PropertyBool.create("south");
    public static final PropertyBool WEST = PropertyBool.create("west");
    public static final PropertyBool NORTHWEST = PropertyBool.create("northwest");
    public static final PropertyBool NORTHEAST = PropertyBool.create("northeast");
    public static final PropertyBool SOUTHEAST = PropertyBool.create("southeast");
    public static final PropertyBool SOUTHWEST = PropertyBool.create("southwest");
    public static final PropertyEnum<EnumDyeColor> COLOR = PropertyEnum.<EnumDyeColor>create("color", EnumDyeColor.class);
    private EnumDyeColor basecolor;
    
	public static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB(0D, 0.0D, 0D, 1D, .0625D, 1D);
	
	public Rug(String name, Material material, SoundType soundType, EnumDyeColor color) {
		super(material);
		this.basecolor = color;
		setSoundType(soundType);
		setHardness(0.1F);
		setUnlocalizedName(name);
		setRegistryName(name + "." + color.getDyeColorName());
		setDefaultState(this.blockState.getBaseState().withProperty(SOUTH, false).withProperty(WEST, false).withProperty(NORTH, false).withProperty(EAST, false).withProperty(SOUTHEAST, false).withProperty(SOUTHWEST, false).withProperty(NORTHWEST, false).withProperty(NORTHEAST, false).withProperty(COLOR, EnumDyeColor.WHITE));
		setLightOpacity(0);
		setCreativeTab(Main.rugs);
		BTDCBlocks.BLOCKS.add(this);
		
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return BOUNDING_BOX;
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
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        return super.canPlaceBlockAt(worldIn, pos) && this.canBlockStay(worldIn, pos);
    }
    private boolean canBlockStay(World worldIn, BlockPos pos)
    {
        return !worldIn.isAirBlock(pos.down());
    }
    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
    	if (this.checkForDrop(worldIn, pos, state) && !worldIn.isRemote){
    		worldIn.setBlockState(pos, this.getActualState(state, worldIn, pos));
    	}
        
    }
    @Nullable
    private PropertyBool getRelativeProperty(BlockPos mainCarpet, BlockPos otherCarpet){
    	BlockPos relative = mainCarpet.subtract(otherCarpet);
    	if (relative.getY() != 0) return null;
    	if (relative.getZ() == 1){
    		if (relative.getX()==1) return NORTHWEST;
    		else if (relative.getX()==0) return NORTH;
    		else if (relative.getX()==-1) return NORTHEAST;
    		else return null;
    	}
    	else if (relative.getZ() == -1){
    		if (relative.getX()==1) return SOUTHWEST;
    		else if (relative.getX()==0) return SOUTH;
    		else if (relative.getX()==-1) return SOUTHEAST;
    		else return null;
    	}
    	else if (relative.getZ() == 0){
    		if (relative.getX()==1) return WEST;
    		else if (relative.getX()==-1) return EAST;
    		else return null;
    	}
    	else return null;
    }
    
    private boolean checkForDrop(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!this.canBlockStay(worldIn, pos))
        {
            this.dropBlockAsItem(worldIn, pos, state, 0);
            worldIn.setBlockToAir(pos);
            return false;
        }
        else
        {
            return true;
        }
    }
    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {NORTH, EAST, SOUTH, WEST, NORTHWEST, NORTHEAST, SOUTHEAST, SOUTHWEST, COLOR});
    }
    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return face == EnumFacing.DOWN ? BlockFaceShape.SOLID : BlockFaceShape.UNDEFINED;
    }
	@Override
	@SideOnly(Side.CLIENT)
	public boolean canRenderInLayer(IBlockState state, BlockRenderLayer layer){
		return layer == BlockRenderLayer.CUTOUT;
	}
	@Override
	public int getMetaFromState(IBlockState state){
		return state.getValue(COLOR).getMetadata();
	}
	@Override
	public IBlockState getStateFromMeta(int meta){
		return this.getDefaultState().withProperty(COLOR, EnumDyeColor.byMetadata(meta));
	}
	
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos){
		state=state.withProperty(NORTH, worldIn.getBlockState(pos.north()).getBlock()==this);
		state=state.withProperty(EAST, worldIn.getBlockState(pos.east()).getBlock()==this);
		state=state.withProperty(SOUTH, worldIn.getBlockState(pos.south()).getBlock()==this);
		state=state.withProperty(WEST, worldIn.getBlockState(pos.west()).getBlock()==this);
		state=state.withProperty(NORTHWEST, worldIn.getBlockState(pos.north().west()).getBlock()==this);
		state=state.withProperty(NORTHEAST, worldIn.getBlockState(pos.north().east()).getBlock()==this);
		state=state.withProperty(SOUTHEAST, worldIn.getBlockState(pos.south().east()).getBlock()==this);
		state=state.withProperty(SOUTHWEST, worldIn.getBlockState(pos.south().west()).getBlock()==this);
		return state;
	}
	@Override
    public int damageDropped(IBlockState state)
    {
        return this.basecolor.getMetadata()*16+((EnumDyeColor)state.getValue(COLOR)).getMetadata();
    }
	@Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        for (EnumDyeColor color : EnumDyeColor.values()){
        	if (color != this.basecolor){
        		items.add(new ItemStack(BTDCBlocks.RUG_ITEM, 1, this.basecolor.getMetadata()*16+color.getMetadata()));
        	}
        }
    }

	public EnumDyeColor getColor() {
		return this.basecolor;
	}
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return BTDCBlocks.RUG_ITEM;
    }

}
