package com.github.palomox.advancedelectricity.craftings;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.registries.IForgeRegistryEntry;

public interface IModRecipeSerializer<T extends IRecipe<?>> extends IForgeRegistryEntry<IModRecipeSerializer<?>>{
	IRecipeSerializer<ElectricianTableShaped> ELECTRICIAN_SHAPED = IRecipeSerializer.register("electrician_shaped", new ElectricianTableShaped.Serializer());
}
