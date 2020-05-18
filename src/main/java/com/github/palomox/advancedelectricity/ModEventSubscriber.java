package com.github.palomox.advancedelectricity;


import com.github.palomox.advancedelectricity.blocks.AdvancedTableBlock;
import com.github.palomox.advancedelectricity.blocks.CopperBlock;
import com.github.palomox.advancedelectricity.blocks.CopperOreBlock;
import com.github.palomox.advancedelectricity.containers.AdvancedTableContainer;
import com.github.palomox.advancedelectricity.init.ModBlocks;
import com.github.palomox.advancedelectricity.init.ModItemGroups;
import com.github.palomox.advancedelectricity.items.CopperIngotItem;
//import com.github.palomox.advancedelectricity.tiles.AdvancedTableTile;
import com.github.palomox.advancedelectricity.tiles.AdvancedTableTile;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = AdvancedElectricity.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventSubscriber {
	@SubscribeEvent
	public static void onBlockRegistry(final RegistryEvent.Register<Block> e) {
		e.getRegistry().register(new CopperOreBlock());
		e.getRegistry().register(new CopperBlock());
		e.getRegistry().register(new AdvancedTableBlock());
	}
	
	@SubscribeEvent
	public static void onItemRegistry(final RegistryEvent.Register<Item> e) {
		Item.Properties properties = new Item.Properties().group(ModItemGroups.ADVANCED_ELECTICITY);
		e.getRegistry().register(new CopperIngotItem());
        e.getRegistry().register(new BlockItem(ModBlocks.COPPER_ORE, properties).setRegistryName("copper_ore"));
        e.getRegistry().register(new BlockItem(ModBlocks.COPPER_BLOCK, properties).setRegistryName("copper_block"));
        e.getRegistry().register(new BlockItem(ModBlocks.ADVANCED_TABLE, properties).setRegistryName("advanced_table"));
	}
	
	@SubscribeEvent
	public static void onTileEntityRegistry(final RegistryEvent.Register<TileEntityType<?>> event) {
        event.getRegistry().register(TileEntityType.Builder.create(AdvancedTableTile::new, ModBlocks.ADVANCED_TABLE).build(null).setRegistryName("advanced_table"));
    }

	@SubscribeEvent
	public static void onContainerRegistry(final RegistryEvent.Register<ContainerType<?>> event) {
		event.getRegistry().register(IForgeContainerType.create((windowId, inv, data) -> {
			
			//BlockPos pos = data.readBlockPos();
			
			return new AdvancedTableContainer(windowId, inv/*, IWorldPosCallable.of(inv.player.world, pos)*/);
		}).setRegistryName("advanced_table"));
	}
}
