package com.enwash.deco.blocks;

import com.enwash.deco.init.BTDCBlocks;
import com.enwash.deco.init.BTDCItems;
import com.enwash.deco.util.ModMaterial;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockFallingBase extends BlockFalling{

	public BlockFallingBase(String name, Material material)
	{
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		BTDCBlocks.BLOCKS.add(this);
		BTDCItems.addItemBlock(this);
	}

	
	@Override
	protected void onStartFalling(EntityFallingBlock fallingEntity)
    {
		if (this instanceof ITileEntityProvider){
			TileEntity tileentity = fallingEntity.world.getTileEntity(fallingEntity.getOrigin());
			if (tileentity instanceof IInventory){
				fallingEntity.tileEntityData = tileentity.writeToNBT(new NBTTagCompound());//store items into fallingblock
				NBTTagCompound tagcopy = fallingEntity.tileEntityData.copy();
				int b = ((IInventory)tileentity).getSizeInventory();
				ItemStackHelper.saveAllItems(tagcopy,NonNullList.<ItemStack>withSize(b, ItemStack.EMPTY));//remove items from tileentity
				tileentity.readFromNBT(tagcopy);
				fallingEntity.setDropItemsWhenDead(false);//prevent it from dropping block: players need to use an axe to obtain item.
				EntityFallingBlock fallingReplacement = new EntityFallingBlock(fallingEntity.world){
					@Override
					public EntityItem entityDropItem(ItemStack stack, float offsetY){
						NonNullList<ItemStack> drops = NonNullList.<ItemStack>withSize(b, ItemStack.EMPTY);
						ItemStackHelper.loadAllItems(this.tileEntityData, drops);
						for (ItemStack drop: drops){
				            EntityItem entityitem = new EntityItem(this.world, this.posX, this.posY + (double)offsetY, this.posZ, drop);
				            entityitem.setDefaultPickupDelay();
				            if (captureDrops)
				                this.capturedDrops.add(entityitem);
				            else
				                this.world.spawnEntity(entityitem);
						}
						return new EntityItem(this.world, this.posX, this.posY + (double)offsetY, this.posZ, stack);
				    }
				};
				fallingReplacement.deserializeNBT(fallingEntity.serializeNBT());
				fallingEntity.setDead();
				fallingEntity.world.spawnEntity(fallingReplacement);
			}
		}
    }
    @Override
    public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face){
    	return ModMaterial.getFlammability(this.blockMaterial);
    }
    @Override
    public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face){
    	return ModMaterial.getFireSpread(this.blockMaterial);
    }
}