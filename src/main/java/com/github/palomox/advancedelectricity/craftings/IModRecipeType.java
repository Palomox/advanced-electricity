package com.github.palomox.advancedelectricity.craftings;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraftforge.common.util.RecipeMatcher;

public interface IModRecipeType <T extends IRecipe<?>>{
	
	IRecipeType<IElectricianTableRecipe> ELECTRICIAN_TABLE = IRecipeType.register("electrician_table");
	
}
