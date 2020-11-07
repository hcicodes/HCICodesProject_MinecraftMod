package com.hci.humbercraft.util;

import net.minecraft.item.*;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraftforge.registries.*;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.*;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import com.hci.humbercraft.items.BlockItemBase;
import com.hci.humbercraft.items.ItemBase;
import com.hci.humbercraft.tools.ModItemTier;
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
	
	public static final RegistryObject<Block> JAVIUM_BLOCK = BLOCKS.register("javium_block", () -> 
	new BlockBase(Block.Properties.create(Material.IRON).hardnessAndResistance(5.0f, 5.0f).sound(SoundType.STONE).harvestLevel(2).harvestTool(ToolType.PICKAXE)));		
	public static final RegistryObject<Item> JAVIUM_BLOCK_ITEM = ITEMS.register("javium_block", () -> new BlockItemBase(JAVIUM_BLOCK.get()));
	
	public static final RegistryObject<Block> JAVIUM_ORE_BLOCK = BLOCKS.register("javium_ore_block", JaviumOreBlock::new);	
	public static final RegistryObject<Item> JAVIUM_ORE_BLOCK_ITEM = ITEMS.register("javium_ore_block", () -> new BlockItemBase(JAVIUM_ORE_BLOCK.get()));	
	
	public static final RegistryObject<Block> SOURCITE_ORE_BLOCK = BLOCKS.register("sourcite_ore_block", () -> 
	new BlockBase(Block.Properties.create(Material.IRON).hardnessAndResistance(5.0f, 5.0f).sound(SoundType.STONE).harvestLevel(2).harvestTool(ToolType.PICKAXE)));	
	public static final RegistryObject<Item> SOURCITE_ORE_BLOCK_ITEM = ITEMS.register("sourcite_ore_block", () -> new BlockItemBase(SOURCITE_ORE_BLOCK.get()));
	
	public static final RegistryObject<Item> SOURCITE = ITEMS.register("sourcite", ItemBase::new);	
	public static final RegistryObject<Item> CLASSITE = ITEMS.register("classite", ItemBase::new);
	public static final RegistryObject<Item> METHODITE = ITEMS.register("methodite", ItemBase::new);
	
	public static final RegistryObject<Item> JARIUM = ITEMS.register("jarium", ItemBase::new);
	public static final RegistryObject<Block> JARIUM_BLOCK = BLOCKS.register("jarium_block", () -> 
	new BlockBase(Block.Properties.create(Material.IRON).lightValue(10).hardnessAndResistance(5.0f, 5.0f).sound(SoundType.STONE).harvestLevel(2).harvestTool(ToolType.PICKAXE)));		
	public static final RegistryObject<Item> JARIUM_BLOCK_ITEM = ITEMS.register("jarium_block", () -> new BlockItemBase(JARIUM_BLOCK.get()));
		
	//tools
	public static final RegistryObject<SwordItem> JARIUM_SWORD = ITEMS.register("jarium_sword", () -> 
	new SwordItem(ModItemTier.JARIUM, 9, -3.0f, new Item.Properties().group(HumberCraft.TAB)));
	

	
}
