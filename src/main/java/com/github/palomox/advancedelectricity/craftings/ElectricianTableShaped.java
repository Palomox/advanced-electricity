package com.github.palomox.advancedelectricity.craftings;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class ElectricianTableShaped implements IElectricianTableRecipe, IElectricianTableShapedRecipe<CraftingInventory>{
	private final NonNullList<Ingredient> recipeItems;
	private final ItemStack recipeOutput;
	private final ResourceLocation id;
	private final String group;
	
	public ElectricianTableShaped(ResourceLocation idIn, String groupIn, NonNullList<Ingredient> recipeItemsIn, ItemStack recipeOutputIn) {
		this.id = idIn; 
		this.group = groupIn;
		this.recipeItems = recipeItemsIn;
		this.recipeOutput = recipeOutputIn;
		
	}
	
	public ResourceLocation getId() {
		return this.id;
	}
	
	public IRecipeSerializer<?> getSerializer(){
		return IModRecipeSerializer.ELECTRICIAN_SHAPED;
	}
	@Override
	public boolean matches(CraftingInventory inv, World worldIn) {
		for(int i=0; i<= inv.getSizeInventory(); i++) {
			if(this.checkMatch(inv, i)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean checkMatch(CraftingInventory inv, int slid) {
		Ingredient ingredient = Ingredient.EMPTY;
		ingredient = this.recipeItems.get(slid);
		if(!ingredient.test(inv.getStackInSlot(slid))) {
			return false;
		}else {
		return true;
		}
	}

	@Override
	public ItemStack getCraftingResult(CraftingInventory inv) {
		return this.getRecipeOutput().copy();
	}

	@Override
	public boolean canFit(int width, int height) {
		return true;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return this.recipeOutput;
	}
	public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<ElectricianTableShaped>{
		
	}

	
}
