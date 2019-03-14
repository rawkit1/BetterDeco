package com.enwash.deco.specialrecipes;


import java.util.HashMap;
import java.util.Map;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class SpinningRecipes {
	private static final SpinningRecipes INSTANCE = new SpinningRecipes();
	private final Map<ItemStack, ItemStack> spinningList = new HashMap<ItemStack, ItemStack>();
	
	public static SpinningRecipes getInstance(){
		return INSTANCE;
	}
	private SpinningRecipes(){
		addSpinningRecipe(new ItemStack(Blocks.WOOL), new ItemStack(Items.STRING, 1));
	}
	
	public void addSpinningRecipe(ItemStack input, ItemStack output){
		if(getSpinningResult(input) != ItemStack.EMPTY) return;
		this.spinningList.put(input, output);
	}
	
	public ItemStack getSpinningResult(ItemStack input){
		for (Map.Entry<ItemStack, ItemStack> entry: this.spinningList.entrySet()){
			if (compareItemStacks(input, entry.getKey())){
				return entry.getValue();
			}
		}
		return ItemStack.EMPTY;
	}
	public Map<ItemStack, ItemStack> getSpinningList(){
		return this.spinningList;
	}
	private boolean compareItemStacks(ItemStack stack1, ItemStack stack2){
		return stack1.getItem() == stack2.getItem() && (stack2.getMetadata() >= 32767 || stack1.getMetadata() == stack2.getMetadata());
	}
}
