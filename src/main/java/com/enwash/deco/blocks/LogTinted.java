package com.enwash.deco.blocks;

import com.enwash.deco.Main;
import com.enwash.deco.init.BTDCBlocks;
import com.enwash.deco.init.BTDCItems;
import com.enwash.deco.util.ModMaterial;

import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class LogTinted extends BlockRotatedPillar{

    public LogTinted(String name, Material materialIn) {
        super(materialIn);
        setUnlocalizedName(name);
        setRegistryName(name);
        setHarvestLevel("axe", 0);
        setHardness(2.0F);
        setSoundType(SoundType.WOOD);
        setCreativeTab(Main.resources);
        BTDCBlocks.BLOCKS.add(this);
        BTDCItems.addItemBlock(this);
        this.setDefaultState(this.getDefaultState().withProperty(AXIS, EnumFacing.Axis.Y));
    }

    @Override
    public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face){
    	return ModMaterial.getFlammability(this.blockMaterial);
    }
    @Override
    public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face){
    	return ModMaterial.getFireSpread(this.blockMaterial);
    }
}