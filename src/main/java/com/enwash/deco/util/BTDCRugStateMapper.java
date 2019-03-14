package com.enwash.deco.util;

import com.enwash.deco.blocks.Rug;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
@SideOnly(Side.CLIENT)
public class BTDCRugStateMapper extends StateMapperBase {
	public BTDCRugStateMapper() {
	}

	@Override
	protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
		String name;
		name = Reference.MOD_ID + ":" + "rug";
		int N = state.getValue(Rug.NORTH) ? 1 : 0;
		int W = state.getValue(Rug.WEST) ? 1 : 0;
		int S = state.getValue(Rug.SOUTH) ? 1 : 0;
		int E = state.getValue(Rug.EAST) ? 1 : 0;
		int NW = state.getValue(Rug.NORTHWEST) ? 1 : 0;
		int NE = state.getValue(Rug.NORTHEAST) ? 1 : 0;
		int SE = state.getValue(Rug.SOUTHEAST) ? 1 : 0;
		int SW = state.getValue(Rug.SOUTHWEST) ? 1 : 0;
		String c1 = ((Rug)state.getBlock()).getColor().getDyeColorName();
		String c2 = state.getValue(Rug.COLOR).getDyeColorName();
		//check if corners are to be considered part of the same rug-part that is 2x2 or more in size.
		NW = NW*(1+N*W);
		NE = NE*(1+N*E);
		SE = SE*(1+S*E);
		SW = SW*(1+S*W);
		//do the same thing for the egdes.
		N = N+ (NW==2 || NE==2 ? 1 : 0);
		E = E+ (NE==2 || SE==2 ? 1 : 0);
		S = S+ (SW==2 || SE==2 ? 1 : 0);
		W = W+ (NW==2 || SW==2 ? 1 : 0);
		if (N==2 && E==2 && S==2 && W==2){ //there's multiple tiles that have only solid edges
			return new ModelResourceLocation(name, String.format("c1=%s,c2=%s,n=%d,ne=%d,se=%d,sw=%d", c1, c2, NW, NE, SE, SW));//northwest is set to n instead of nw in order for me to essentially cheat the forge blockstate format. It's a bit hacky, but it's better than the other solutions I could come up with
		}
		else{
			return new ModelResourceLocation(name, String.format("c1=%s,c2=%s,n=%d,e=%d,s=%d,w=%d", c1, c2, N, E, S, W));
		}
	}
}
