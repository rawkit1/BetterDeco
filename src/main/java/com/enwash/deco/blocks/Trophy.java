package com.enwash.deco.blocks;

import com.enwash.deco.Main;
import com.enwash.deco.init.BTDCBlocks;
import com.enwash.deco.init.BTDCItems;
import com.enwash.deco.util.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.BlockShulkerBox;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Trophy extends Block implements IHasModel {

	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	public static final AxisAlignedBB BB_SOUTH = new AxisAlignedBB(0, 0, 0, 1D, 1D, 0.1875D);
	public static final AxisAlignedBB BB_NORTH = new AxisAlignedBB(0, 0, 1D, 1D, 1D, 0.8125D);
	public static final AxisAlignedBB BB_EAST = new AxisAlignedBB(0, 0, 0, 0.1875D, 1D, 1D);
	public static final AxisAlignedBB BB_WEST = new AxisAlignedBB(1D, 0, 1D, 0.8125D, 1D, 0);
	
	public Trophy(String name, Material material)
	{
		
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.furniture);
		setSoundType(SoundType.STONE);
		setHardness(3.0F);
		setResistance(1.0F);
		setHarvestLevel("pickaxe", 1);
		setUnlocalizedName(name);
		this.setLightOpacity(0);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		BTDCBlocks.BLOCKS.add(this);
		BTDCItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

	public AxisAlignedBB bbFromFacing(IBlockState state) {
		if ((EnumFacing)state.getValue(FACING) == EnumFacing.NORTH) {
			return BB_NORTH;
		}
		else if ((EnumFacing)state.getValue(FACING) == EnumFacing.SOUTH) {
			return BB_SOUTH;
		}
		else if ((EnumFacing)state.getValue(FACING) == EnumFacing.EAST) {
			return BB_EAST;
		}
		return BB_WEST;
	}
	
	
	// Block Placement
	
	public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side)
    {
        return side != EnumFacing.DOWN && side != EnumFacing.UP && this.canAttachTo(worldIn, pos, side);
    }

    public boolean canAttachTo(World p_193395_1_, BlockPos p_193395_2_, EnumFacing p_193395_3_)
    {
        Block block = p_193395_1_.getBlockState(p_193395_2_.up()).getBlock();
        return this.isAcceptableNeighbor(p_193395_1_, p_193395_2_.offset(p_193395_3_.getOpposite()), p_193395_3_) && (block == Blocks.AIR || block == Blocks.VINE || this.isAcceptableNeighbor(p_193395_1_, p_193395_2_.up(), EnumFacing.UP));
    }

    private boolean isAcceptableNeighbor(World p_193396_1_, BlockPos p_193396_2_, EnumFacing p_193396_3_)
    {
        IBlockState iblockstate = p_193396_1_.getBlockState(p_193396_2_);
        return iblockstate.getBlockFaceShape(p_193396_1_, p_193396_2_, p_193396_3_) == BlockFaceShape.SOLID && !isExceptBlockForAttaching(iblockstate.getBlock());
    }

    protected static boolean isExceptBlockForAttaching(Block p_193397_0_)
    {
        return p_193397_0_ instanceof BlockShulkerBox || p_193397_0_ == Blocks.BEACON || p_193397_0_ == Blocks.CAULDRON || p_193397_0_ == Blocks.PISTON || p_193397_0_ == Blocks.STICKY_PISTON || p_193397_0_ == Blocks.PISTON_HEAD || p_193397_0_ == Blocks.TRAPDOOR;
    }

    
	// Rotation
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) 
	{
		if (!worldIn.isRemote) 
        {
            IBlockState north = worldIn.getBlockState(pos.north());
            IBlockState south = worldIn.getBlockState(pos.south());
            IBlockState west = worldIn.getBlockState(pos.west());
            IBlockState east = worldIn.getBlockState(pos.east());
            EnumFacing face = (EnumFacing)state.getValue(FACING);

            if (face == EnumFacing.NORTH && north.isFullBlock() && !south.isFullBlock()) face = EnumFacing.SOUTH;
            else if (face == EnumFacing.SOUTH && south.isFullBlock() && !north.isFullBlock()) face = EnumFacing.NORTH;
            else if (face == EnumFacing.WEST && west.isFullBlock() && !east.isFullBlock()) face = EnumFacing.EAST;
            else if (face == EnumFacing.EAST && east.isFullBlock() && !west.isFullBlock()) face = EnumFacing.WEST;
            worldIn.setBlockState(pos, state.withProperty(FACING, face), 2);
        }
	}

	@Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    	return bbFromFacing(state);
    }
	
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
	
	// Rendering
	
	@Override
	public void registerModels() {
		
		Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
		
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
    
    
}