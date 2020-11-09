package com.hci.humbercraft.world.biomes;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;

public class JavaBiome extends Biome{

	public JavaBiome(Builder biomeBuilder) {
		super(biomeBuilder);
		this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.VILLAGER, 20, 2, 10));
		this.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(WorldCarver.CAVE, new ProbabilityConfig(0.14285715F)));
		this.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(WorldCarver.HELL_CAVE, new ProbabilityConfig(0.2F)));
		DefaultBiomeFeatures.addOres(this);
	}
	
	
	
}
