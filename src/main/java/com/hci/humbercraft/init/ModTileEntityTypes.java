package com.hci.humbercraft.init;

import com.hci.humbercraft.HumberCraft;
import com.hci.humbercraft.tileentity.JavaDimensionTeleporterTileEntity;
import com.hci.humbercraft.tileentity.JavacRefineryTileEntity;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntityTypes {
	
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = new DeferredRegister(ForgeRegistries.TILE_ENTITIES, HumberCraft.MOD_ID);
	
	public static final RegistryObject<TileEntityType<JavacRefineryTileEntity>> JAVAC_REFINERY = TILE_ENTITY_TYPES
			.register("javac_refinery", () -> TileEntityType.Builder.create(JavacRefineryTileEntity::new,
					BlockInit.JAVAC_REFINERY.get()).build(null));
	
	public static final RegistryObject<TileEntityType<JavaDimensionTeleporterTileEntity>> JAVA_DIMENSION_TELEPORTER = TILE_ENTITY_TYPES
			.register("java_dimension_teleporter", () -> TileEntityType.Builder.create(JavaDimensionTeleporterTileEntity::new,
					BlockInit.JAVA_DIMENSION_TELEPORTER.get()).build(null));
}
