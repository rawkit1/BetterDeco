package com.enwash.deco.items;

import java.util.List;

import javax.annotation.Nullable;

import com.enwash.deco.blocks.Rug;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
public class ItemRug extends Item {
	private List<Block> blocks;
	public ItemRug(List<Block> blocks) {
		super();
		this.blocks = blocks;
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
	}
	/**
     * Called when a Block is right-clicked with this Item
     */
    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        Block block = iblockstate.getBlock();

        if (!block.isReplaceable(worldIn, pos))
        {
            pos = pos.offset(facing);
        }

        ItemStack itemstack = player.getHeldItem(hand);
        int j = this.getMetadata(itemstack.getMetadata());
        Block blockOut = this.getBlockRaw(j);
        int blockMeta = j%16;
        if (!itemstack.isEmpty() && player.canPlayerEdit(pos, facing, itemstack) && worldIn.mayPlace(blockOut, pos, false, facing, (Entity)null))
        {
            
            IBlockState iblockstate1 = blockOut.getStateForPlacement(worldIn, pos, facing, hitX, hitY, hitZ, blockMeta, player, hand);

            if (placeBlockAt(itemstack, player, worldIn, pos, facing, hitX, hitY, hitZ, iblockstate1))
            {
                iblockstate1 = worldIn.getBlockState(pos);
                SoundType soundtype = iblockstate1.getBlock().getSoundType(iblockstate1, worldIn, pos, player);
                worldIn.playSound(player, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
                itemstack.shrink(1);
            }

            return EnumActionResult.SUCCESS;
        }
        else
        {
            return EnumActionResult.FAIL;
        }
    }

    public static boolean setTileEntityNBT(World worldIn, @Nullable EntityPlayer player, BlockPos pos, ItemStack stackIn)
    {
        MinecraftServer minecraftserver = worldIn.getMinecraftServer();

        if (minecraftserver == null)
        {
            return false;
        }
        else
        {
            NBTTagCompound nbttagcompound = stackIn.getSubCompound("BlockEntityTag");

            if (nbttagcompound != null)
            {
                TileEntity tileentity = worldIn.getTileEntity(pos);

                if (tileentity != null)
                {
                    if (!worldIn.isRemote && tileentity.onlyOpsCanSetNbt() && (player == null || !player.canUseCommandBlock()))
                    {
                        return false;
                    }

                    NBTTagCompound nbttagcompound1 = tileentity.writeToNBT(new NBTTagCompound());
                    NBTTagCompound nbttagcompound2 = nbttagcompound1.copy();
                    nbttagcompound1.merge(nbttagcompound);
                    nbttagcompound1.setInteger("x", pos.getX());
                    nbttagcompound1.setInteger("y", pos.getY());
                    nbttagcompound1.setInteger("z", pos.getZ());

                    if (!nbttagcompound1.equals(nbttagcompound2))
                    {
                        tileentity.readFromNBT(nbttagcompound1);
                        tileentity.markDirty();
                        return true;
                    }
                }
            }

            return false;
        }
    }

    
    @SideOnly(Side.CLIENT)
    public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side, EntityPlayer player, ItemStack stack)
    {
        Block block = worldIn.getBlockState(pos).getBlock();

        if (block == Blocks.SNOW_LAYER && block.isReplaceable(worldIn, pos))
        {
            side = EnumFacing.UP;
        }
        else if (!block.isReplaceable(worldIn, pos))
        {
            pos = pos.offset(side);
        }

        return worldIn.mayPlace( this.getBlockRaw(stack.getMetadata()), pos, false, side, (Entity)null);
    }

    /**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT.
     */
    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return this.getUnlocalizedName() + "." + ((Rug)this.getBlockRaw(stack.getMetadata())).getColor().getUnlocalizedName()+ "." + EnumDyeColor.byMetadata(stack.getMetadata()%16).getUnlocalizedName();
    }

    /**
     * Returns the unlocalized name of this item.
     */
    @Override
    public String getUnlocalizedName()
    {
        return this.blocks.get(0).getUnlocalizedName();
    }

    /**
     * gets the CreativeTab this item is displayed on
     */
    @Override
    public CreativeTabs getCreativeTab()
    {
        return this.blocks.get(0).getCreativeTabToDisplayOn();
    }

    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if (this.isInCreativeTab(tab))
        {
        	for (Block block : this.blocks){
        		block.getSubBlocks(tab, items);
        	}
        }
    }

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        this.getBlockRaw(stack.getMetadata()).addInformation(stack, worldIn, tooltip, flagIn);
    }

    public Block getBlock(int meta)
    {
        return this.getBlockRaw(meta) == null ? null : this.getBlockRaw(meta).delegate.get();
    }
    private Block getBlockRaw(int meta)
    {
        return this.blocks.get(meta/16);
    }

    /**
     * Called to actually place the block, after the location is determined
     * and all permission checks have been made.
     *
     * @param stack The item stack that was used to place the block. This can be changed inside the method.
     * @param player The player who is placing the block. Can be null if the block is not being placed by a player.
     * @param side The side the player (or machine) right-clicked on.
     */
    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, IBlockState newState)
    {
        if (!world.setBlockState(pos, newState, 11)) return false;

        IBlockState state = world.getBlockState(pos);
        if (state.getBlock() ==  this.getBlockRaw(stack.getMetadata()))
        {
            setTileEntityNBT(world, player, pos, stack);
            this.getBlockRaw(stack.getMetadata()).onBlockPlacedBy(world, pos, state, player, stack);

            if (player instanceof EntityPlayerMP)
                CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)player, pos, stack);
        }

        return true;
    }
    @Override
    public int getMetadata(int damage)
    {
        return damage;
    }
}
