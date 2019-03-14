package com.enwash.deco.util;

import java.util.Map;

import com.enwash.deco.blocks.ColoredWire;
import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
@SideOnly(Side.CLIENT)
public class BTDCStringStateMapper extends StateMapperBase {
	public BTDCStringStateMapper() {
	}

	@Override
	protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
		String name;
		name = Reference.MOD_ID + ":" + ((ResourceLocation)Block.REGISTRY.getNameForObject(state.getBlock())).getResourcePath();
		name = (state.getValue(ColoredWire.ATTACHED)) ? name + "_attached" : name;
		boolean N = state.getValue(ColoredWire.NORTH);
		boolean W = state.getValue(ColoredWire.WEST);
		boolean S = state.getValue(ColoredWire.SOUTH);
		boolean E = state.getValue(ColoredWire.EAST);
		int t = (N?1:0)+(S?1:0)+(W?1:0)+(E?1:0);
		String inner = (t==0) ? "zero" :(t!=1) ? "none" : S ? "north" : W ? "east" : N ? "south" : "west";
		Map < IProperty<?>, Comparable<? >> map = Maps. < IProperty<?>, Comparable<? >> newLinkedHashMap(state.getProperties());
		map.remove(ColoredWire.ATTACHED);
		map.remove(ColoredWire.POWERED);
		map.remove(ColoredWire.DISARMED);
		return new ModelResourceLocation(name, this.getPropertyString(map) + ",xtra=" + inner);
	}
}
