package com.hci.humbercraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.ToolType;

public class JaviumOreBlock extends OreBlock{
	
	public JaviumOreBlock() {
		super(Block.Properties.create(Material.IRON)
				.hardnessAndResistance(5.0f, 5.0f)
				.sound(SoundType.METAL)
				.harvestLevel(3)
				.harvestTool(ToolType.PICKAXE));
	}

	@Override
	public int getExpDrop(BlockState state, IWorldReader reader, BlockPos pos, int fortune, int silktouch) {
		if(silktouch == 1) 
			return 0;	
		else
			return 5 + 5 * fortune;
	}
}
