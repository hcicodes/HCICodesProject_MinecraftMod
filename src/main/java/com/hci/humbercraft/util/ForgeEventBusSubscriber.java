package com.hci.humbercraft.util;

import com.hci.humbercraft.HumberCraft;
import com.hci.humbercraft.init.DimensionInit;

import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = HumberCraft.MOD_ID, bus = Bus.FORGE)
public class ForgeEventBusSubscriber {

	@SubscribeEvent
	public static void registerDimensions(final RegisterDimensionsEvent event) {
		if(DimensionType.byName(HumberCraft.JAVA_DIMENSION_TYPE) == null) {
			DimensionManager.registerDimension(HumberCraft.JAVA_DIMENSION_TYPE, DimensionInit.JAVA_DIMENSION.get(), null, true);
		}
		HumberCraft.LOGGER.info("Dimensions Registered!");
	}
	
}
