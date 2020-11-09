package com.hci.humbercraft.blocks;

import com.hci.humbercraft.init.BlockInit;

import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

public class BlockBase extends Block{

	public BlockBase(Properties properties) {
		super(properties);
	}

	public boolean isPortalFrame(BlockState state, IWorldReader world, BlockPos pos){
        return state.getBlock() == BlockInit.JAVIUM_BLOCK.get();
    }
}
