package com.github.palomox.advancedelectricity.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class CopperBlock extends Block{

	public CopperBlock() {
		super(Properties.create(Material.IRON).hardnessAndResistance(3.0F, 3.0F));
		setRegistryName("copper_block");
	}
	

}
