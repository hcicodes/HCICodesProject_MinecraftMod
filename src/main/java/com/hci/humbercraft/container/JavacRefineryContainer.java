package com.hci.humbercraft.container;

import com.hci.humbercraft.init.ModContainerTypes;

import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;

public class JavacRefineryContainer extends Container{

	public JavacRefineryContainer(final int windowID, final PlayerInventory playerInv, final JavacRefineryTileEntity tile) {
		super(ModContainerTypes.JAVAC_REFINERY.get(), windowID);
	}

}
