package com.hci.humbercraft.world.gen;

import com.hci.humbercraft.HumberCraft;
import com.hci.humbercraft.init.BlockInit;

import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = HumberCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModOreGen {

	@SubscribeEvent
	public static void generateOres(FMLLoadCompleteEvent event) {
		for(Biome biome : ForgeRegistries.BIOMES) {		
			//Overworld
			genOre(biome, 12, 7, 4, 20, OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.JAVIUM_ORE_BLOCK.get().getDefaultState(), 8);	
			genOre(biome, 12, 7, 4, 20, OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.SOURCITE_ORE_BLOCK.get().getDefaultState(), 8);		
		}
	}
	
	private static void genOre(Biome biome, int count, int bottomOffset, int topOffset, int max, OreFeatureConfig.FillerBlockType filler, BlockState defaultBlockState, int size) {
		CountRangeConfig range = new CountRangeConfig(count, bottomOffset, topOffset, max);
		OreFeatureConfig feature = new OreFeatureConfig(filler, defaultBlockState, size);
		ConfiguredPlacement config = Placement.COUNT_RANGE.configure(range);
		biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(feature).withPlacement(config));
	}
}
