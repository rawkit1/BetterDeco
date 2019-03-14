package com.enwash.deco.proxy;

import com.enwash.deco.util.Reference;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class ClientProxy extends CommonProxy {
	public void registerItemRenderer(Item item, int meta, String id)
	{
		if (item.getHasSubtypes()){
			NonNullList<ItemStack> items = NonNullList.<ItemStack>create();
			item.getSubItems(item.getCreativeTab(), items);
			for (ItemStack type: items){
				int n = item.getUnlocalizedName().length();
				//kinda shotty imo but it works and I can change it if I have to so eh who cares
				ModelLoader.setCustomModelResourceLocation(item, type.getMetadata(), new ModelResourceLocation(new ResourceLocation(Reference.MOD_ID, item.getRegistryName().getResourcePath() + "/" + type.getUnlocalizedName().substring(n+1)), id));
			}
		}
		else{
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(new ResourceLocation(Reference.MOD_ID,item.getRegistryName().getResourcePath()), id));
		}
	}
	public void registerStateMap(Block block, IStateMapper mapper){
		ModelLoader.setCustomStateMapper(block, mapper);
	}
}
