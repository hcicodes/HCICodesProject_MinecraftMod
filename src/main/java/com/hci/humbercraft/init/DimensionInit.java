package com.hci.humbercraft.init;

import com.hci.humbercraft.HumberCraft;
import com.hci.humbercraft.world.dimension.JavaModDimension;

import net.minecraftforge.common.ModDimension;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class DimensionInit {

	public static final DeferredRegister<ModDimension> MOD_DIMENSIONS = new DeferredRegister<>(ForgeRegistries.MOD_DIMENSIONS, HumberCraft.MOD_ID);
			
	public static final RegistryObject<ModDimension> JAVA_DIMENSION = MOD_DIMENSIONS.register("java_dimension", () -> new JavaModDimension());
}
