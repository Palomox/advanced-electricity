package com.github.palomox.advancedelectricity.guis;

import com.github.palomox.advancedelectricity.AdvancedElectricity;
import com.github.palomox.advancedelectricity.containers.AdvancedTableContainer;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class AdvancedTableGui extends ContainerScreen<AdvancedTableContainer>{
    private ResourceLocation GUI = new ResourceLocation(AdvancedElectricity.MODID, "textures/gui/advanced_table_gui.png");

    public AdvancedTableGui(AdvancedTableContainer container, PlayerInventory inv, ITextComponent name) {
        super(container, inv, name);
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(GUI);
        this.blit(105, 20, 0, 0, 206, 166);
    }
}
