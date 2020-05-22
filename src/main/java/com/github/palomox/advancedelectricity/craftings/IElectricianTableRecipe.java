package com.github.palomox.advancedelectricity.craftings;

import com.github.palomox.advancedelectricity.init.IModRecipeType;


import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;

public interface IElectricianTableRecipe extends IRecipe<CraftingInventory>{
	default IRecipeType<?> getType(){
		return IModRecipeType.ELECTRICIAN_TABLE;
	}
}
