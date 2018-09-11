package com.enwash.deco.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;

public class Fountain extends BlockBase{
  public Fountain(String name, Material material) {
          
          super(name, material);
          setSoundType(SoundType.STONE);
          setHardness(0);
          setResistance(0);
      }
  @Override
  public boolean canRenderInLayer(IBlockState state, BlockRenderLayer layer){
	  return layer == BlockRenderLayer.SOLID || layer == BlockRenderLayer.TRANSLUCENT;
  }
  
  public boolean isFullCube(IBlockState state)
  {
      return false;
  }
  public boolean isOpaqueCube(IBlockState state)
  {
      return false;
  }
}