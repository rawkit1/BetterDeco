package com.enwash.deco.util;

import java.util.Map;

import com.google.common.collect.Maps;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class ModMaterial extends Material {

	private static final Map<Material, Integer> encouragements = Maps.<Material, Integer>newIdentityHashMap();
    private static final Map<Material, Integer> flammabilities = Maps.<Material, Integer>newIdentityHashMap();
	public static final Material HARDWOOD = new ModMaterial(MapColor.WOOD).setBurning().setRequiresTool();
	public ModMaterial(MapColor color) {
		super(color);
	}
	@Override
	protected ModMaterial setRequiresTool() {
		return (ModMaterial) super.setRequiresTool();
	}
	@Override
	protected ModMaterial setBurning() {
		return (ModMaterial) super.setBurning();
	}
	@Override
	public Material setReplaceable() {
		return super.setReplaceable();
	}
	@Override
	protected ModMaterial setNoPushMobility() {
		return (ModMaterial) super.setNoPushMobility();
	}
	@Override
	protected ModMaterial setImmovableMobility() {
		return (ModMaterial) super.setImmovableMobility();
	}
	@Override
	protected ModMaterial setAdventureModeExempt() {
		return (ModMaterial) super.setAdventureModeExempt();
	}

	private static void setInfo(Material mat, int encourage, int flammability){
		encouragements.put(mat, encourage);
        flammabilities.put(mat, flammability);
	}
	public static void preinit(){
		setInfo(Material.WOOD, 5, 20);
		setInfo(ModMaterial.HARDWOOD, 5, 20);
		setInfo(Material.CLOTH, 30, 60);
	}
	public static int getFlammability(Material mat){
		Integer ret =  mat.getCanBurn() ? flammabilities.get(mat) : 0;
		return ret == null ? 0 : ret;
	}
	public static int getFireSpread(Material mat){
		Integer ret =  mat.getCanBurn() ? encouragements.get(mat) : 0;
		return ret == null ? 0 : ret;
	}
}
