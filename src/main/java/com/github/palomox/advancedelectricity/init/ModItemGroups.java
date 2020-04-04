package com.github.palomox.advancedelectricity.init;

import java.util.function.Supplier;

import com.github.palomox.advancedelectricity.AdvancedElectricity;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;


public class ModItemGroups {
	public static final ItemGroup ADVANCED_ELECTICITY = new ModItemGroup(AdvancedElectricity.MODID, () -> new ItemStack(ModItems.COPPER_INGOT));
	
	public static class ModItemGroup extends ItemGroup{
		private final Supplier<ItemStack> iconSupplier;

		public ModItemGroup(final String name, final Supplier<ItemStack> iconSupplier) {
			super(name);
			this.iconSupplier = iconSupplier;
		}

		@Override
		public ItemStack createIcon() {
			return iconSupplier.get();
		}
	}
}
