package com.enwash.deco;

import com.enwash.deco.init.BTDCBlocks;
import com.enwash.deco.init.BTDCOreDict;
import com.enwash.deco.proxy.CommonProxy;
import com.enwash.deco.tabs.Busts;
import com.enwash.deco.tabs.Furniture;
import com.enwash.deco.tabs.Lighting;
import com.enwash.deco.tabs.Resources;
import com.enwash.deco.util.ModMaterial;
import com.enwash.deco.util.Reference;
import com.enwash.deco.util.handlers.RegistryHandler;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION, updateJSON = Reference.UPDATE_URL)
public class Main {
	
	@Instance
	public static Main instance;
	
	public static final CreativeTabs lighting = new Lighting("lighting");
	public static final CreativeTabs furniture = new Furniture("furniture");
	public static final CreativeTabs resources = new Resources("btdcresources");
	public static final CreativeTabs busts = new Busts("busts");
	public static final CreativeTabs rugs = new CreativeTabs("rugs"){@Override public ItemStack getTabIconItem() { return new ItemStack(BTDCBlocks.RUG.get(0)); }};
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;
	
	@EventHandler
	public static void PreInit(FMLPreInitializationEvent event)
	{
		ModMaterial.preinit();
		RegistryHandler.preInitRegistries();
		
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent event)
	{
		RegistryHandler.initRegistries();
		BTDCOreDict.initOres();
	}
		
	@EventHandler
	public static void PostInit(FMLPostInitializationEvent event)
	{
		IRecipe recipe = CraftingManager.getRecipe(new ResourceLocation("minecraft:string_to_wool"));
		NonNullList<Ingredient> ingredients = recipe.getIngredients();
		for (Ingredient ingredient : ingredients){
			for (ItemStack stack : ingredient.getMatchingStacks()){
				System.out.println(stack.getDisplayName());
			}
		}
	}
	
	
	
}
