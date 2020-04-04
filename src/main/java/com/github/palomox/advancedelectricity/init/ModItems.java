
package com.github.palomox.advancedelectricity.init;

import com.github.palomox.advancedelectricity.blocks.AdvancedTableBlock;
import com.github.palomox.advancedelectricity.blocks.CopperBlock;
import com.github.palomox.advancedelectricity.blocks.CopperOreBlock;
import com.github.palomox.advancedelectricity.items.CopperIngotItem;

import net.minecraftforge.registries.ObjectHolder;



public class ModItems {
   @ObjectHolder("advancedelectricity:copper_ingot")
   public static CopperIngotItem COPPER_INGOT;
   
   @ObjectHolder("advancedelectricity:copper_block")
   public static CopperBlock COPPER_BLOCK;
   
   @ObjectHolder("advancedelectricity:copper_ore")
   public static CopperOreBlock COPPER_ORE;
   
   @ObjectHolder("advancedelectricity:advanced_table")
   public static AdvancedTableBlock ADVANCED_TABLE;
   
}