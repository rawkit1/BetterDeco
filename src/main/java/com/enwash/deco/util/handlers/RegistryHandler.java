package com.enwash.deco.util.handlers;

import com.enwash.deco.Main;
import com.enwash.deco.init.BTDCBlocks;
import com.enwash.deco.init.BTDCItems;
import com.enwash.deco.util.BTDCRugStateMapper;
import com.enwash.deco.util.BTDCStringStateMapper;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@EventBusSubscriber
public class RegistryHandler {

	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll(BTDCItems.ITEMS.toArray(new Item[0]));
	}
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().registerAll(BTDCBlocks.BLOCKS.toArray(new Block[0]));
		
	}
	
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event)
	{
		for(Item item : BTDCItems.ITEMS)
		{
			Main.proxy.registerItemRenderer(item, 0, "inventory");
		}
		
		for(Block block : BTDCBlocks.BLOCKS)
		{
			//Main.proxy.registerItemRenderer(Item.getItemFromBlock(block), 0, "inventory");
		}
		Main.proxy.registerStateMap(BTDCBlocks.COLOR_STRING, new BTDCStringStateMapper());
		for (Block rugVariant: BTDCBlocks.RUG){
			Main.proxy.registerStateMap(rugVariant, new BTDCRugStateMapper());
		}
	}
	public static void preInitRegistries()
	{
		TileEntityHandler.registerTileEntities();
		NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GuiHandler());
	}
	
	public static void initRegistries() {

	}
	
	public static void otherRegistries() {
		
	}
}