package com.enwash.deco.blocks.container;

import com.enwash.deco.blocks.tileentities.TileEntityWheel;
import com.enwash.deco.specialrecipes.SpinningRecipes;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerSpinningWheel extends Container {
	private final TileEntityWheel tileentity;
	private int spinTime, totalSpinTime;
	
	public ContainerSpinningWheel(InventoryPlayer player, TileEntityWheel tileentity){
		this.tileentity = tileentity;
		this.addSlotToContainer(new Slot(tileentity, 0, 53, 35));
		this.addSlotToContainer(new Slot(tileentity, 1, 113, 35){@Override public boolean isItemValid(ItemStack stack){return false;}});
		
		for (int y = 0; y<3; ++y){
			for(int x = 0; x<9; ++x){
				this.addSlotToContainer(new Slot(player, x+y*9+9, 8+x*18, 84+y*18));
			}
		}
		
		for (int x = 0; x<9; ++x){
			this.addSlotToContainer(new Slot(player, x, 8+x*18, 142));
		}
	}
	
	@Override
	public void addListener(IContainerListener listener){
		super.addListener(listener);
		listener.sendAllWindowProperties(this, this.tileentity);
	}
	
	@Override
	public void detectAndSendChanges(){
		super.detectAndSendChanges();
		for(int i = 0; i < this.listeners.size(); ++i){
			IContainerListener listener = (IContainerListener)this.listeners.get(i);
			if(this.spinTime != this.tileentity.getField(0)) listener.sendWindowProperty(this, 0, this.tileentity.getField(0));
			if(this.totalSpinTime != this.tileentity.getField(1)) listener.sendWindowProperty(this, 1, this.tileentity.getField(1));
		}
		this.spinTime = this.tileentity.getField(0);
		this.totalSpinTime = this.tileentity.getField(1);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data){
		this.tileentity.setField(id, data);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return this.tileentity.isUsableByPlayer(playerIn);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index){
		ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (!(slot != null && slot.getHasStack())) return itemstack; //make sure slot exists
        ItemStack itemstack1 = slot.getStack();
        itemstack = itemstack1.copy();
        if (index == 1){ //take items from output
        	if (!this.mergeItemStack(itemstack1, 2, 38, true)) return ItemStack.EMPTY; //mergeItemStack returns false if it failed to transfer any items
        	slot.onSlotChange(itemstack1, itemstack);
        }
        else if (index != 0){ //slot is in player's inventory
        	if (!SpinningRecipes.getInstance().getSpinningResult(itemstack1).isEmpty()){//item is input
        		if (!this.mergeItemStack(itemstack1, 0, 1, false)) return ItemStack.EMPTY;
        	}
        	else if (index >= 2 && index <29){ //slot is in player inventory
        		if (!this.mergeItemStack(itemstack1, 29, 38	, false)) return ItemStack.EMPTY; //put items into hotbar
        	}
        	else if (index >=29 && index < 38){//slot is in player hotbar
        		if (!this.mergeItemStack(itemstack1, 2, 29, false)) return ItemStack.EMPTY; //put items into inventory	
        	}
        }
        else if (!this.mergeItemStack(itemstack1, 2, 38, false)) return ItemStack.EMPTY; //take input from tileentity and put it in inventory/horbar
        if (itemstack1.isEmpty()){ //all items have been transferred
        	slot.putStack(ItemStack.EMPTY);//thus the slot is empty
        }
        else{
        	slot.onSlotChanged(); //the itemstack in the slot changed so we call this method
        }
        if (itemstack1.getCount() == itemstack.getCount()){//if no items were transferred
        	return ItemStack.EMPTY;
        }
        slot.onTake(playerIn, itemstack1);
        return itemstack;
	}
	
}
