package com.hci.humbercraft.init;

import com.hci.humbercraft.HumberCraft;
import com.hci.humbercraft.blocks.BlockBase;
import com.hci.humbercraft.blocks.BlockJavacRefinery;
import com.hci.humbercraft.blocks.JaviumOreBlock;
import com.hci.humbercraft.portal.JavaPortalBlock;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.NetherPortalBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

//This class stores all the blocks (not block items)
public class BlockInit {
	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, HumberCraft.MOD_ID);	
	
	//Blocks
	public static final RegistryObject<Block> JAVIUM_BLOCK = BLOCKS.register("javium_block", () -> 
		new BlockBase(Block.Properties.create(Material.IRON).hardnessAndResistance(5.0f, 5.0f)
				.sound(SoundType.STONE).harvestLevel(2).harvestTool(ToolType.PICKAXE)));	
	
	public static final RegistryObject<Block> JARIUM_BLOCK = BLOCKS.register("jarium_block", () -> 
		new BlockBase(Block.Properties.create(Material.IRON).lightValue(10).hardnessAndResistance(5.0f, 5.0f)
				.sound(SoundType.STONE).harvestLevel(2).harvestTool(ToolType.PICKAXE)));	
	
	//Ore Blocks
	public static final RegistryObject<Block> JAVIUM_ORE_BLOCK = BLOCKS.register("javium_ore_block", JaviumOreBlock::new);	
	
	public static final RegistryObject<Block> SOURCITE_ORE_BLOCK = BLOCKS.register("sourcite_ore_block", () -> 
		new BlockBase(Block.Properties.create(Material.IRON).hardnessAndResistance(5.0f, 5.0f)
				.sound(SoundType.STONE).harvestLevel(2).harvestTool(ToolType.PICKAXE)));
	
	public static final RegistryObject<Block> JAVAC_REFINERY = BLOCKS.register("javac_refinery", 
			() -> new BlockJavacRefinery(Block.Properties.from(Blocks.FURNACE)));

}
