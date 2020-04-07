package com.github.palomox.advancedelectricity;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.palomox.advancedelectricity.guis.AdvancedTableGui;
import com.github.palomox.advancedelectricity.init.ModBlocks;

import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod(AdvancedElectricity.MODID)
public class AdvancedElectricity {
	public static final String MODID = "advancedelectricity";
	
	public static final Logger LOGGER = LogManager.getLogger();
	
	public AdvancedElectricity() {
		LOGGER.debug("Cargando Mod...");
	}
}
