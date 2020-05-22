package com.github.palomox.advancedelectricity.init;

import com.github.palomox.advancedelectricity.craftings.IElectricianTableRecipe;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;

public interface IModRecipeType <T extends IRecipe<?>>{
	
	IRecipeType<IElectricianTableRecipe> ELECTRICIAN_TABLE = IRecipeType.register("electrician_table");
	
}
