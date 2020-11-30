package com.hci.humbercraft.items;

import com.hci.humbercraft.HumberCraft;
import com.hci.humbercraft.HumberCraft.HumbercraftItemGroup;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;

public final class BlockItemBase extends BlockItem {

	public BlockItemBase(Block blockIn) {
		super(blockIn, new BlockItem.Properties().group(HumbercraftItemGroup.instance));
	}

}
