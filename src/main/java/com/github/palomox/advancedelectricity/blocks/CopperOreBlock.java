package com.github.palomox.advancedelectricity.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class CopperOreBlock extends Block{
	public CopperOreBlock() {
		super(Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F));
		setRegistryName("copper_ore");
	}
	
}
