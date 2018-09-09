package com.enwash.deco.blocks;

import com.enwash.deco.Main;
import com.enwash.deco.init.ModBlocks;
import com.enwash.deco.init.ModItems;
import com.enwash.deco.util.IHasModel;

import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;

public class LogTinted extends BlockRotatedPillar implements IHasModel {

    public LogTinted(String name, Material materialIn) {
        super(materialIn);
        setUnlocalizedName(name);
        setRegistryName(name);
        setHarvestLevel("axe", 0);
        setHardness(2.0F);
        setCreativeTab(Main.resources);
        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
        this.setDefaultState(this.getDefaultState().withProperty(AXIS, EnumFacing.Axis.Y));
    }

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

}