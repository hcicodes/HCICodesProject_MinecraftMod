package com.hci.humbercraft.tileentity;

import javax.annotation.Nullable;

import com.hci.humbercraft.HumberCraft;
import com.hci.humbercraft.init.ModTileEntityTypes;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class JavaDimensionTeleporterTileEntity extends TileEntity{	

	public JavaDimensionTeleporterTileEntity(TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
	}

	public JavaDimensionTeleporterTileEntity() {
		this(ModTileEntityTypes.JAVA_DIMENSION_TELEPORTER.get());
	}
	

}
