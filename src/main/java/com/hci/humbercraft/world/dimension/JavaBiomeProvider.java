package com.hci.humbercraft.world.dimension;

import java.util.Random;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.hci.humbercraft.init.BiomeInit;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;

public class JavaBiomeProvider extends BiomeProvider{
	
	private Random rand;
	
	public JavaBiomeProvider() {
		super(biomeList);
		rand = new Random();
	}

	private static final Set<Biome> biomeList = ImmutableSet.of(BiomeInit.JAVA_BIOME.get());
	
	@Override
	public Biome getNoiseBiome(int x, int y, int z) {
		return BiomeInit.JAVA_BIOME.get();
	}

}
