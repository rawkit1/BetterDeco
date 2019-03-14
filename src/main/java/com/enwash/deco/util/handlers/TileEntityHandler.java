package com.enwash.deco.util.handlers;

import com.enwash.deco.blocks.tileentities.TileEntityWheel;
import com.enwash.deco.util.Reference;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler {
	public static void registerTileEntities(){
		GameRegistry.registerTileEntity(TileEntityWheel.class, new ResourceLocation(Reference.MOD_ID + ":spinning_wheel"));
	}
}
