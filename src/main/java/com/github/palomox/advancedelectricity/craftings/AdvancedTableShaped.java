package com.github.palomox.advancedelectricity.craftings;

import java.util.ArrayList;
import java.util.List;

import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ICraftingRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.crafting.IShapedRecipe;
import net.minecraftforge.common.util.RecipeMatcher;

@Deprecated
public class AdvancedTableShaped implements ICraftingRecipe, IElectricianTableShapedRecipe<CraftingInventory>{
	private final ResourceLocation id;
	private final String group;
	private final ItemStack recipeOutput;
    private final NonNullList<Ingredient> recipeItems;
    private final boolean isSimple;
    
    public AdvancedTableShaped(ResourceLocation id, String group, ItemStack output, NonNullList<Ingredient> ingredients) {
    	this.id = id;
	    this.group = group;
	    this.recipeOutput = output;
	    this.recipeItems = ingredients;
	    this.isSimple = ingredients.stream().allMatch(Ingredient::isSimple);
	}

	@Override
	public boolean matches(CraftingInventory inv, World worldIn) {
		RecipeItemHelper helper = new RecipeItemHelper();
		List<ItemStack> inputs = new ArrayList<>();
		int i = 0;
		
		if(inv.getWidth() == 3 && inv.getHeight() == 3) { 
			for(int j = 0; j < inv.getSizeInventory(); ++j) {
				ItemStack stack = inv.getStackInSlot(j);
				if(!stack.isEmpty()) {
					++i;
					if(isSimple) {
						helper.func_221264_a(stack, 1);
					} else {
						inputs.add(stack);
					}
				}
			}
		}
		
		return i == recipeItems.size() && (isSimple ? helper.canCraft(this, (IntList)null) : RecipeMatcher.findMatches(inputs, recipeItems) != null);

	}

	@Override
	public ItemStack getCraftingResult(CraftingInventory inv) {
		return recipeOutput.copy();
	}

	@Override
	public boolean canFit(int width, int height) {
		return width * height >= this.recipeItems.size();
	}

	@Override
	public ItemStack getRecipeOutput() {
		return this.recipeOutput;
	}
	@Override
	public String getGroup() {
		return group;
	}
	@Override
	public ResourceLocation getId() {
		return id;
	}

	@Override
	public IRecipeSerializer<?> getSerializer() {
		return null;
     	//return IModRecipeType.ELECTRICIAN_TABLE;
	}
	@Override
	public NonNullList<Ingredient> getIngredients() {
		return recipeItems;
	}

	

}
