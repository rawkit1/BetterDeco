package com.enwash.deco.blocks.tileentities;

import javax.annotation.Nullable;

import com.enwash.deco.blocks.SpinningWheel;
import com.enwash.deco.specialrecipes.SpinningRecipes;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

public class TileEntityWheel extends TileEntity implements ISidedInventory, ITickable{
	private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(2, ItemStack.EMPTY);
	private String customName;
	private int spinTime;
	private int totalSpinTime;
	private static final int[] SLOTS_TOP = new int[]{0};
	private static final int[] SLOTS_BOTTOM = new int[]{1};

	@Override
	public String getName(){
		return this.hasCustomName() ? this.customName : "container.spinning_wheel";
	}
	
	@Override
	public boolean hasCustomName(){
		return this.customName != null && !this.customName.isEmpty();
	}
	
	public void setCustomName(String customName) {
		this.customName = customName;
	}
	
	@Override
	public ITextComponent getDisplayName(){
		return this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName());
	}
	
	@Override
	public int getSizeInventory()
	{
		return this.inventory.size();
	}
	
	@Override
	public boolean isEmpty(){
		for (ItemStack stack : this.inventory){
			if(!stack.isEmpty()) return false;
		}
		return true;
	}
	
	@Override
	public ItemStack getStackInSlot(int index){
		return this.inventory.get(index);
	}
	
	@Override
	public ItemStack decrStackSize(int index, int count){
		return ItemStackHelper.getAndSplit(this.inventory, index, count);
	}
	
	@Override
	public ItemStack removeStackFromSlot(int index){
		return ItemStackHelper.getAndRemove(this.inventory, index);
	}
	
	@Override
	public void setInventorySlotContents(int index, ItemStack inputstack){
		ItemStack targetstack = (ItemStack)this.inventory.get(index);
		boolean flag = !inputstack.isEmpty() && inputstack.isItemEqual(targetstack) && ItemStack.areItemStackTagsEqual(inputstack, targetstack);
		this.inventory.set(index, inputstack);
		if(inputstack.getCount() > this.getInventoryStackLimit()) inputstack.setCount(this.getInventoryStackLimit());
		if(index == 0 && !flag){
			this.spinTime = 0;
			this.totalSpinTime = this.getSpinTime(inputstack);
			this.markDirty();
		}
		
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound){
		super.writeToNBT(compound);
		compound.setInteger("spinTime", (short)this.spinTime);
		compound.setInteger("totalSpinTime", (short)this.totalSpinTime);
		ItemStackHelper.saveAllItems(compound, this.inventory);
		if (this.hasCustomName()) compound.setString("customName", this.customName);
		return compound;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound){
		super.readFromNBT(compound);
		this.inventory = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, this.inventory);
		this.spinTime = compound.getInteger("spinTime");
		this.totalSpinTime = compound.getInteger("totalSpinTime");
		if(compound.hasKey("customName", 8)) this.setCustomName(compound.getString("customName"));
	}
	
	public int getSpinTime(ItemStack stack){
		return 100;
	}
	
	public boolean canSpin(){
		if (((ItemStack)this.inventory.get(0)).isEmpty()) return false;
		else{
			ItemStack result = SpinningRecipes.getInstance().getSpinningResult((ItemStack)this.inventory.get(0));
			if(result.isEmpty()) return false;
			else{
				ItemStack output = (ItemStack)this.inventory.get(1);
				if(output.isEmpty()) return true;
				if(!output.isItemEqual(result)) return false;
				int res = output.getCount() + result.getCount();
				return res <= getInventoryStackLimit() && res <= output.getMaxStackSize();
			}
		}
	}
	
	public void spinItem(){
		if(this.canSpin()){
			ItemStack input = (ItemStack)this.inventory.get(0);
			ItemStack result = SpinningRecipes.getInstance().getSpinningResult((ItemStack)this.inventory.get(0));
			ItemStack output = (ItemStack)this.inventory.get(1);
			if(output.isEmpty()) this.inventory.set(1, result.copy());
			else if (output.getItem() == result.getItem()) output.grow(result.getCount());
			input.shrink(1);
			this.markDirty();
		}
	}

	@Override
	public void update() {
		if(this.canSpin()){
			++this.spinTime;
			if(this.spinTime >= this.totalSpinTime){
				this.spinTime=0;
				this.totalSpinTime = this.getSpinTime((ItemStack)this.inventory.get(0));
				if (!this.world.isRemote){
					this.spinItem();
				}
			}
		}
		else{
			this.spinTime=0;
		}
		
		if (!this.world.isRemote){
			SpinningWheel.setState(this.canSpin(), !this.inventory.get(1).isEmpty(), this.world, this.pos);	
		}
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory(EntityPlayer player) {
		
	}

	@Override
	public void closeInventory(EntityPlayer player) {
		
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return index == 0;
	}
	@Override
	public int[] getSlotsForFace(EnumFacing side){
		return side==EnumFacing.DOWN ? SLOTS_BOTTOM : SLOTS_TOP;
	}
	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction){
		return this.isItemValidForSlot(index, itemStackIn);
	}
	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction){
		return true;
	}
	@Override
	public int getField(int id) {
		switch (id){
		case 0:
			return this.spinTime;
		case 1:
			return this.totalSpinTime;
		default:
			return 0;
		}
	}

	@Override
	public void setField(int id, int value) {
		switch (id){
		case 0:
			this.spinTime = value;
			break;
		case 1:
			this.totalSpinTime = value;
			break;
		}
	}

	@Override
	public int getFieldCount() {
		return 2;
	}

	@Override
	public void clear() {
		this.inventory.clear();
		
	}
	@Override
	@Nullable
	public SPacketUpdateTileEntity getUpdatePacket(){
		NBTTagCompound tag = getUpdateTag();
		return new SPacketUpdateTileEntity(this.pos, 0, tag);
	}
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt){
		NBTTagCompound updateTag = pkt.getNbtCompound();
		handleUpdateTag(updateTag);
	}
	@Override
	public NBTTagCompound getUpdateTag(){
		NBTTagCompound nbt = new NBTTagCompound();
		writeToNBT(nbt);
		return nbt;
	}
	@Override
	public void handleUpdateTag(NBTTagCompound tag){
		this.readFromNBT(tag);
	}
}
