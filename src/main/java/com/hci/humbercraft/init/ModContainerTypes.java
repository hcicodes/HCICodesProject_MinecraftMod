package com.hci.humbercraft.init;

import com.hci.humbercraft.HumberCraft;
import com.hci.humbercraft.container.JavacRefineryContainer;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.common.extensions.IForgeContainerType;

public class ModContainerTypes {
	
	public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = new DeferredRegister<>(ForgeRegistries.CONTAINERS, HumberCraft.MOD_ID);
	
	public static final RegistryObject<ContainerType<JavacRefineryContainer>> JAVAC_REFINERY = CONTAINER_TYPES.register("javac_refinery", () -> IForgeContainerType.create(JavacRefineryContainer::new));

}
