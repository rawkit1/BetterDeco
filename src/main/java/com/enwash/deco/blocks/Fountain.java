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
  public BlockRenderLayer getBlockLayer(){
      
      return BlockRenderLayer.TRANSLUCENT;
  }
  @Override
  public boolean isFullBlock(IBlockState state) {
	  return false;
  }
  @Override
  public boolean isFullCube(IBlockState state)
  {
      return false;
  }
  public boolean isOpaqueBlock(IBlockState state)
  {
      return false;
  }
}