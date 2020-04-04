package com.github.palomox.advancedelectricity.tiles;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.github.palomox.advancedelectricity.containers.AdvancedTableContainer;
import com.github.palomox.advancedelectricity.init.ModBlocks;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class AdvancedTableTile extends TileEntity implements ITickableTileEntity, INamedContainerProvider {

	private LazyOptional<IItemHandler> handler = LazyOptional.of(this::createHandler);

	public AdvancedTableTile() {
		super(ModBlocks.ADVANCED_TABLE_TILE);
	}

	@Override
	public void tick() {

	}

	private IItemHandler createHandler() {
		return new ItemStackHandler(6);
	}

	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
		if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return handler.cast();
		}
		return super.getCapability(cap, side);
	}
	@Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent(getType().getRegistryName().getPath());
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new AdvancedTableContainer(i, world, pos, playerInventory, playerEntity);
    }

}
