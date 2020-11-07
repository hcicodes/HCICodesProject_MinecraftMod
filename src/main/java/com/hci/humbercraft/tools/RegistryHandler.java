package com.hci.humbercraft.tools;

import net.minecraft.item.*;
import net.minecraftforge.registries.*;
import net.minecraftforge.fml.*;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import com.hci.humbercraft.items.ItemBase;
import com.hci.humbercraft.HumberCraft;

public class RegistryHandler {

	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, HumberCraft.MOD_ID);
		
	public static void init() {
		ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
	
	//Items
	public static final RegistryObject<Item> JAVA = ITEMS.register("java", ItemBase::new);

}
