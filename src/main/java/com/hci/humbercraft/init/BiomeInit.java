package com.hci.humbercraft.init;

import com.hci.humbercraft.HumberCraft;
import com.hci.humbercraft.world.biomes.JavaBiome;

import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BiomeInit {
	public static final DeferredRegister<Biome> BIOMES = new DeferredRegister<>(ForgeRegistries.BIOMES, HumberCraft.MOD_ID);
	
	public static final RegistryObject<Biome> JAVA_BIOME = BIOMES.register("java_biome", 
			() -> new JavaBiome(new Biome.Builder()
					.precipitation(RainType.RAIN)
					.scale(1.2f)
					.temperature(0.5f)
					.waterColor(14955024)
					.waterFogColor(4955024)					
					.surfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(BlockInit.JAVIUM_BLOCK.get().getDefaultState(), Blocks.STONE.getDefaultState(), Blocks.BEDROCK.getDefaultState()))
					.category(Category.PLAINS)
					.downfall(5.0f)
					.depth(0.12f)
					)
			);
	
	public static void registerBiomes() {
		registerBiome(JAVA_BIOME.get(), Type.PLAINS, Type.OVERWORLD);
	}	
	
	private static void registerBiome(Biome biome, Type... types) {
		BiomeDictionary.addTypes(biome, types);
		BiomeManager.addSpawnBiome(biome);
	}
}
