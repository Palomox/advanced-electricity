package com.github.palomox.advancedelectricity.init;

import com.github.palomox.advancedelectricity.AdvancedElectricity;
import com.github.palomox.advancedelectricity.guis.AdvancedTableGui;

import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;


@EventBusSubscriber(modid = AdvancedElectricity.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SoloClient {
	
	@SubscribeEvent
	public static void clientInit(final FMLClientSetupEvent e) {
		ScreenManager.registerFactory(ModBlocks.ADVANCED_TABLE_CONTAINER, AdvancedTableGui::new);
	}
}
