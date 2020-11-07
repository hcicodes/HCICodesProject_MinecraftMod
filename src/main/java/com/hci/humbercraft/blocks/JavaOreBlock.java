package com.hci.humbercraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class JavaOreBlock extends Block{
	
	public JavaOreBlock() {
		super(Block.Properties.create(Material.IRON)
				.hardnessAndResistance(5.0f)
				.sound(SoundType.METAL)
				.harvestLevel(3)
				.harvestTool(ToolType.PICKAXE)
		);
	}

}
