package com.hci.humbercraft.tools;

import net.minecraft.item.*;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraftforge.registries.*;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.*;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import com.hci.humbercraft.items.BlockItemBase;
import com.hci.humbercraft.items.ItemBase;
import com.hci.humbercraft.HumberCraft;
import com.hci.humbercraft.blocks.BlockBase;
import com.hci.humbercraft.blocks.JaviumOreBlock;

public class RegistryHandler {

	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, HumberCraft.MOD_ID);
	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, HumberCraft.MOD_ID);
		
	public static void init() {
		ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
	
	// Items
	public static final RegistryObject<Item> JAVIUM = ITEMS.register("javium", ItemBase::new);
	
	public static final RegistryObject<Block> JAVIUM_BLOCK = BLOCKS.register("javium_block", () -> new BlockBase(Block.Properties.create(Material.IRON).hardnessAndResistance(5.0f, 5.0f).sound(SoundType.STONE).harvestLevel(2).harvestTool(ToolType.PICKAXE)));		
	public static final RegistryObject<Item> JAVIUM_BLOCK_ITEM = ITEMS.register("javium_block", () -> new BlockItemBase(JAVIUM_BLOCK.get()));
	
	public static final RegistryObject<Block> JAVIUM_ORE_BLOCK = BLOCKS.register("javium_ore_block", JaviumOreBlock::new);	
	public static final RegistryObject<Item> JAVIUM_ORE_BLOCK_ITEM = ITEMS.register("javium_ore_block", () -> new BlockItemBase(JAVIUM_ORE_BLOCK.get()));
	
	
}
