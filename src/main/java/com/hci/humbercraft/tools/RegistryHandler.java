package com.hci.humbercraft.tools;

import net.minecraft.item.*;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraftforge.registries.*;
import net.minecraftforge.fml.*;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import com.hci.humbercraft.items.BlockItemBase;
import com.hci.humbercraft.items.ItemBase;
import com.hci.humbercraft.HumberCraft;
import com.hci.humbercraft.blocks.BlockBase;

public class RegistryHandler {

	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, HumberCraft.MOD_ID);
	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, HumberCraft.MOD_ID);
		
	public static void init() {
		ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
	
	// Items
	public static final RegistryObject<Item> JAVA = ITEMS.register("java", ItemBase::new);

	// Blocks
	public static final RegistryObject<Block> BLOCKBOI = BLOCKS.register("blockboi", () -> new BlockBase(Block.Properties.create(Material.IRON).lightValue(10)));
	public static final RegistryObject<Item> TUTORIAL_BLOCK = ITEMS.register("blockboi", () -> new BlockItemBase(BLOCKBOI.get()));
}
