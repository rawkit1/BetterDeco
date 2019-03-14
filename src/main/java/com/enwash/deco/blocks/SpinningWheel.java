package com.enwash.deco.blocks;

import com.enwash.deco.Main;
import com.enwash.deco.blocks.tileentities.TileEntityWheel;
import com.enwash.deco.init.BTDCBlocks;
import com.enwash.deco.util.Reference;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

//made by Merlijn (DutchM/merren2306)

public class SpinningWheel extends BlockFallingBase implements ITileEntityProvider{
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	public static final PropertyBool SPINNING = PropertyBool.create("spinning");
	public static final PropertyBool HAS_STRING = PropertyBool.create("hasstring");
	public static final AxisAlignedBB BOUNDING_BOX_X = new AxisAlignedBB(3.0D/16.0D, 0.0D, 3.0D/16.0D, 12.0D/16.0D, 16.0D/16.0D, 13.0D/16.0D);
	public static final AxisAlignedBB BOUNDING_BOX_Z = new AxisAlignedBB(3.0D/16.0D, 0.0D, 3.0D/16.0D, 13.0D/16.0D, 16.0D/16.0D, 12.0D/16.0D);

	public SpinningWheel(String name, Material material) { 
			super(name, material);
			this.hasTileEntity = true;
			setSoundType(SoundType.WOOD);
			setCreativeTab(Main.furniture);
			setHardness(3.0F);
			setResistance(2.0F);
			setHarvestLevel("axe", 1);
			setLightOpacity(0);
			setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(SPINNING, false).withProperty(HAS_STRING, false));
	}
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
		return new BlockStateContainer(this, new IProperty[] {FACING, SPINNING, HAS_STRING});
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
	@SideOnly(Side.CLIENT)
	public boolean canRenderInLayer(IBlockState state, BlockRenderLayer layer){
		return layer == BlockRenderLayer.CUTOUT;
	}
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    	return (state.getValue(FACING).getAxis() == EnumFacing.Axis.X) ? BOUNDING_BOX_X : BOUNDING_BOX_Z;
    	
    }
    @Override
    public boolean isFullBlock(IBlockState state) 
    {
        return false;
    }
    @Override
	public boolean isFullCube(IBlockState state){
		return false;
	}
    @Override
	public boolean isOpaqueCube(IBlockState state){
		return false;
	}
    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return BlockFaceShape.UNDEFINED;
    }
    //tile entity stuff down here
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (!worldIn.isRemote)
        {
            playerIn.openGui(Main.instance, Reference.GUI_WHEEL, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }

        return true;
    }
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityWheel();
	}
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state){
		TileEntity tileentity = worldIn.getTileEntity(pos);
		if (tileentity instanceof TileEntityWheel){
			InventoryHelper.dropInventoryItems(worldIn, pos, (TileEntityWheel)tileentity);
			worldIn.updateComparatorOutputLevel(pos, this);
		}
		super.breakBlock(worldIn, pos, state);
	}
	public static void setState(boolean spinning, boolean hasstring, World worldIn, BlockPos pos){
		IBlockState state = worldIn.getBlockState(pos);
		TileEntity tileentity = worldIn.getTileEntity(pos);
		worldIn.setBlockState(pos, BTDCBlocks.SPINNING_WHEEL.getDefaultState().withProperty(FACING, state.getValue(FACING)).withProperty(SPINNING, spinning).withProperty(HAS_STRING, hasstring));
		if (tileentity != null){
			tileentity.validate();
			worldIn.setTileEntity(pos, tileentity);
		}
	}
	@Override
	public boolean hasComparatorInputOverride(IBlockState state){
		return true;
	}
	@Override
	public int getComparatorInputOverride(IBlockState blockstate, World worldIn, BlockPos pos){
		return Container.calcRedstone(worldIn.getTileEntity(pos));
	}
	@SuppressWarnings("deprecation")
	@Override
	public boolean eventReceived(IBlockState state, World worldIn, BlockPos pos, int id, int param)
    {
        super.eventReceived(state, worldIn, pos, id, param);
        TileEntity tileentity = worldIn.getTileEntity(pos);
        return tileentity == null ? false : tileentity.receiveClientEvent(id, param);
    }
}
