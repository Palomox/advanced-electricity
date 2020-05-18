package com.github.palomox.advancedelectricity.guis;

import com.github.palomox.advancedelectricity.AdvancedElectricity;
import com.github.palomox.advancedelectricity.containers.AdvancedTableContainer;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Text;

@OnlyIn(Dist.CLIENT)
public class AdvancedTableGui extends ContainerScreen<AdvancedTableContainer>{
    private ResourceLocation GUI = new ResourceLocation(AdvancedElectricity.MODID, "textures/gui/advanced_table_gui.png");
    private String text = "Electrician Table";

    public AdvancedTableGui(AdvancedTableContainer container, PlayerInventory inv, ITextComponent name) {
        super(container, inv, name);
       // this.font.drawString("Electrician Table", 10, 5, 0x000000);
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }
    @Override
    protected void init() {
    	super.init();
    	//this.setSize(xSize, ySize);
    	
    }


    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
    this.font.drawString(this.title.getFormattedText(), 28.0F, 6.0F, 4210752);
    }
    public void tick() {
    	super.tick();
    }
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(GUI);
        //AdvancedElectricity.LOGGER.info("Width:" + this.width + " XSIZE: "+ this.xSize);
        /*int relX = (this.width - this.xSize)/2;
        int relY = (this.height - this.ySize)/2;
        this.blit(relX, relY, 0, 0, 206, 166);*/
        int i = this.guiLeft;
        int j = (this.height - this.ySize) / 2;
        this.blit(i, j, 0, 0, this.xSize, this.ySize);
    }
    public void removed() {
        super.removed();
    }
    protected void handleMouseClick(Slot slotIn, int slotId, int mouseButton, ClickType type) {
        super.handleMouseClick(slotIn, slotId, mouseButton, type);
     }
}