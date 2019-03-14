package com.enwash.deco.gui;

import com.enwash.deco.blocks.container.ContainerSpinningWheel;
import com.enwash.deco.blocks.tileentities.TileEntityWheel;
import com.enwash.deco.util.Reference;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GUISpinningWheel extends GuiContainer {
	private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MOD_ID + ":textures/gui/spinning_wheel.png");
	private final InventoryPlayer player;
	private final TileEntityWheel tileentity;

	public GUISpinningWheel(InventoryPlayer player, TileEntityWheel tileentity) {
		super(new ContainerSpinningWheel(player, tileentity));
		this.player = player;
		this.tileentity = tileentity; 
		
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String tileName = this.tileentity.getDisplayName().getUnformattedText();
		this.fontRenderer.drawString(tileName, (this.xSize - this.fontRenderer.getStringWidth(tileName))/2, 8, 4210752);
		this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 8, this.ySize-96+2, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(TEXTURES);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		
		int l = this.getSpunProgressScaled(24);
		this.drawTexturedModalRect(this.guiLeft+77, this.guiTop+34, 176, 0, l+1, 16);
		
	}

	private int getSpunProgressScaled(int pixels){
		int i = this.tileentity.getField(1);
		int j = this.tileentity.getField(0);
		return j != 0 && i != 0 ? j*pixels/i : 0;
	}
	@Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }
}
