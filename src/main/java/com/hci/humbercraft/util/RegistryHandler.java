package com.hci.humbercraft.util;

import net.minecraft.item.*;
import net.minecraft.world.biome.Biome;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.registries.*;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.*;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import com.hci.humbercraft.items.BlockItemBase;
import com.hci.humbercraft.items.ItemBase;
import com.hci.humbercraft.tools.ModItemTier;
import com.hci.humbercraft.HumberCraft;
import com.hci.humbercraft.armor.ModArmorMaterial;
import com.hci.humbercraft.blocks.BlockBase;
import com.hci.humbercraft.blocks.JaviumOreBlock;

public class RegistryHandler {

	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, HumberCraft.MOD_ID);
	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, HumberCraft.MOD_ID);		
	
	public static void init() {
		ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
	

	//TOOLS
	//Jarium
	
	
}
