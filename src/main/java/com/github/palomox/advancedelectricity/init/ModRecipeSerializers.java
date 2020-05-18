package com.github.palomox.advancedelectricity.init;


import com.github.palomox.advancedelectricity.AdvancedElectricity;
import com.github.palomox.advancedelectricity.craftings.AdvancedTableShaped;

import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(AdvancedElectricity.MODID)
public class ModRecipeSerializers {
	public static IRecipeSerializer<AdvancedTableShaped> advt_shaped = Registry.register(Registry.RECIPE_SERIALIZER, new ResourceLocation("advt_shaped"), new ModRecipeSerializers()); 
	public static IRecipeType<AdvancedTableShaped> advt_shapedr = Registry.register(Registry.RECIPE_TYPE, new ResourceLocation("advt_shapedr"), new ModRecipeSerializers());
}
