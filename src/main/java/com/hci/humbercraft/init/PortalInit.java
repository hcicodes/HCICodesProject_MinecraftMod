package com.hci.humbercraft.init;

import com.hci.humbercraft.HumberCraft;
import com.hci.humbercraft.portal.PortalBlockInit.PortalBlock;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PortalInit {
	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<Block>(ForgeRegistries.BLOCKS,
			HumberCraft.MOD_ID);

	public static final RegistryObject<PortalBlock> JAVA_PORTAL = BLOCKS.register("java_portal",
			() -> new PortalBlock(Block.Properties.create(Material.PORTAL)));
}
