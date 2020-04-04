package com.github.palomox.advancedelectricity.items;

import com.github.palomox.advancedelectricity.init.ModItemGroups;

import net.minecraft.item.Item;

public class CopperIngotItem extends Item{
	public CopperIngotItem() {
		super(new Item.Properties().group(ModItemGroups.ADVANCED_ELECTICITY));
		setRegistryName("copper_ingot");
	}
	
}
