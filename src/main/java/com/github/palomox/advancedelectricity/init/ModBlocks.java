package com.github.palomox.advancedelectricity.init;

import com.github.palomox.advancedelectricity.blocks.AdvancedTableBlock;
import com.github.palomox.advancedelectricity.blocks.CopperBlock;
import com.github.palomox.advancedelectricity.blocks.CopperOreBlock;
import com.github.palomox.advancedelectricity.containers.AdvancedTableContainer;
//import com.github.palomox.advancedelectricity.tiles.AdvancedTableTile;
import com.github.palomox.advancedelectricity.tiles.AdvancedTableTile;

import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;


public class ModBlocks {
	@ObjectHolder("advancedelectricity:copper_ore")
	public static CopperOreBlock COPPER_ORE;
	
	@ObjectHolder("advancedelectricity:copper_block")
	public static CopperBlock COPPER_BLOCK;
	
	@ObjectHolder("advancedelectricity:advanced_table")
	public static AdvancedTableBlock ADVANCED_TABLE;
	
    @ObjectHolder("advancedelectricity:advanced_table")
    public static TileEntityType<AdvancedTableTile> ADVANCED_TABLE_TILE;
    
    @ObjectHolder("advancedelectricity:advanced_table")
    public static ContainerType<AdvancedTableContainer> ADVANCED_TABLE_CONTAINER;
    
}