package com.github.palomox.advancedelectricity.craftings;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.mojang.realmsclient.util.JsonUtils;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class ElectricianTableShaped implements IElectricianTableRecipe, IElectricianTableShapedRecipe<CraftingInventory>{
	private final NonNullList<Ingredient> recipeItems;
	private final ItemStack recipeOutput;
	private final ResourceLocation id;
	private final String group;
	private static int size;
	
	public ElectricianTableShaped(ResourceLocation idIn, String groupIn, NonNullList<Ingredient> recipeItemsIn, ItemStack recipeOutputIn, int size) {
		this.id = idIn; 
		this.group = groupIn;
		this.recipeItems = recipeItemsIn;
		this.recipeOutput = recipeOutputIn;
		this.size = size;
		
	}
	public static void setCraftingSize(int size) {
		size = 5;
	}
	
	
	public static void setSize(int size) {
		ElectricianTableShaped.size = size;
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
	public static ItemStack deserializeItem(JsonObject json) {
		String s = JSONUtils.getString(json, "item");
		Item item = Registry.ITEM.getValue(new ResourceLocation(s)).orElseThrow(() -> {
			return new JsonSyntaxException("No se que es " +s);
		});
		
		if(json.has("data")) {
			throw new JsonParseException("Datos no soportados detectados");
			
		} else {
			int i = JSONUtils.getInt(json, "count", 1);
			return CraftingHelper.getItemStack(json, true);
		}
	}
	private static Map<String, Ingredient> deserializeKey(JsonObject json){
		Map<String, Ingredient> map = Maps.newHashMap();
		
		for(Entry<String, JsonElement> entry : json.entrySet()) {
			if(entry.getKey().length() !=1) {
				throw new JsonSyntaxException("La key"+(String)entry.getKey()+"es demasiado larga para mi capacidad mental");
				
			}
			if(" ".equals(entry.getKey())) {
				throw new JsonSyntaxException("Hay que ser un poco lento para no pensar que ' ' significa un slot vacio por defecto");
			}
			map.put(entry.getKey(), Ingredient.deserialize(entry.getValue()));
		}
		map.put(" ", Ingredient.EMPTY);
		return map;
	}
	private static NonNullList<Ingredient> deserializeIngredients(String[] pattern, Map<String, Ingredient> keys, int size){
		NonNullList<Ingredient> ingredientes = NonNullList.withSize(size, Ingredient.EMPTY);
		Set<String> set = Sets.newHashSet(keys.keySet());
		set.remove(" ");

		for(int i=0; i<pattern.length; i++) {
			for(int j=0; j<pattern[i].length(); j++) {
				String s = pattern[i].substring(j, j+1);
				Ingredient ingredient = keys.get(s);
				if(ingredient == null) {
					throw new JsonSyntaxException("Te has olvidado de decir a que hace referencia "+s);
				}
				set.remove(s);
				ingredientes.set(i, ingredient);
			}
		}
		if(!set.isEmpty()) {
			throw new JsonSyntaxException("La leyenda define cosas que luego no usas el el patron arreglalo porfi. este es el patron"+set);
		}else {
			return ingredientes;
		}
		}
	private static String[] patternFromJson(JsonArray json) {
		String[] pattern = new String[json.size()];
		if(pattern.length > size) {
			throw new JsonSyntaxException("Receta demasiado grande");
		} else if (pattern.length == 0) {
			throw new JsonSyntaxException("Te has olvidado del patrón, campeón");
		}else {
			for(int i=0; i<pattern.length; i++) {
				String s =JSONUtils.getString(json.get(i), "pattern["+ i +"]");
				pattern[i] = s;
			}
			return pattern;
		}
	}

	@Override
	public ItemStack getRecipeOutput() {
		return this.recipeOutput;
	}
	public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<ElectricianTableShaped>{
		private static final ResourceLocation NAME = new ResourceLocation("advancedelectricity", "electrician_shaped");
		
		public ElectricianTableShaped read(ResourceLocation recipeId, JsonObject json) {
			String s = JSONUtils.getString(json, "group", "");
			Map<String, Ingredient> map = ElectricianTableShaped.deserializeKey(JSONUtils.getJsonObject(json, "key"));
			String[] pattern = ElectricianTableShaped.patternFromJson(JSONUtils.getJsonArray(json, "pattern"));
			int total = pattern.length;
			NonNullList<Ingredient> ingredientes = ElectricianTableShaped.deserializeIngredients(pattern, map, total);
			ItemStack stack = ElectricianTableShaped.deserializeItem(JSONUtils.getJsonObject(json, "result"));
			return new ElectricianTableShaped(recipeId, s, ingredientes, stack, size);
		}

		public ElectricianTableShaped read(ResourceLocation recipeId, PacketBuffer buffer) {
			int total = buffer.readVarInt();
			NonNullList<Ingredient> ingredients = NonNullList.withSize(total, Ingredient.EMPTY);
			for(int k =0; k<ingredients.size(); k++) {
				ingredients.set(k, Ingredient.read(buffer));
			}
			ItemStack stack = buffer.readItemStack();
			return new ElectricianTableShaped(recipeId, buffer.readString(32767), ingredients, stack, total);
		}

		public void write(PacketBuffer buffer, ElectricianTableShaped recipe) {
			buffer.writeVarInt(recipe.size);
			buffer.writeString(recipe.group);
			
			for(Ingredient ingredient : recipe.recipeItems) {
				ingredient.write(buffer);
			}
			buffer.writeItemStack(recipe.recipeOutput);
		}
		
	}
	public int getRecipeSize() {
		return this.size;
	}

	
}
