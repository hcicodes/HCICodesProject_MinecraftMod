package com.hci.humbercraft.init;

import com.hci.humbercraft.HumberCraft;
import com.hci.humbercraft.armor.ModArmorMaterial;
import com.hci.humbercraft.items.BlockItemBase;
import com.hci.humbercraft.items.ItemBase;
import com.hci.humbercraft.tools.ModItemTier;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {
	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, HumberCraft.MOD_ID);
	
	// Items
	public static final RegistryObject<Item> JAVIUM = ITEMS.register("javium", ItemBase::new);
	public static final RegistryObject<Item> JAVIUM_BLOCK_ITEM = ITEMS.register("javium_block", () -> new BlockItemBase(BlockInit.JAVIUM_BLOCK.get()));
	public static final RegistryObject<Item> JAVIUM_ORE_BLOCK_ITEM = ITEMS.register("javium_ore_block", () -> new BlockItemBase(BlockInit.JAVIUM_ORE_BLOCK.get()));	
		
	public static final RegistryObject<Item> SOURCITE_ORE_BLOCK_ITEM = ITEMS.register("sourcite_ore_block", () -> new BlockItemBase(BlockInit.SOURCITE_ORE_BLOCK.get()));
	
	public static final RegistryObject<Item> SOURCITE = ITEMS.register("sourcite", ItemBase::new);	
	public static final RegistryObject<Item> CLASSITE = ITEMS.register("classite", ItemBase::new);
	public static final RegistryObject<Item> METHODITE = ITEMS.register("methodite", ItemBase::new);
	
	public static final RegistryObject<Item> JARIUM = ITEMS.register("jarium", ItemBase::new);		
	public static final RegistryObject<Item> JARIUM_BLOCK_ITEM = ITEMS.register("jarium_block", () -> new BlockItemBase(BlockInit.JARIUM_BLOCK.get()));
		
	public static final RegistryObject<SwordItem> JARIUM_SWORD = ITEMS.register("jarium_sword", () -> 
	new SwordItem(ModItemTier.JARIUM, 9, -3.0f, new Item.Properties().group(HumberCraft.TAB)));
	
	public static final RegistryObject<PickaxeItem> JARIUM_PICKAXE = ITEMS.register("jarium_pickaxe", () -> 
	new PickaxeItem(ModItemTier.JARIUM, 4, -1.0f, new Item.Properties().group(HumberCraft.TAB)));
	
	public static final RegistryObject<ShovelItem> JARIUM_SHOVEL = ITEMS.register("jarium_shovel", () -> 
	new ShovelItem(ModItemTier.JARIUM, 4, -1.0f, new Item.Properties().group(HumberCraft.TAB)));
	
	public static final RegistryObject<AxeItem> JARIUM_AXE = ITEMS.register("jarium_axe", () -> 
	new AxeItem(ModItemTier.JARIUM, 4, -1.0f, new Item.Properties().group(HumberCraft.TAB)));
	
	public static final RegistryObject<HoeItem> JARIUM_HOE = ITEMS.register("jarium_hoe", () -> 
	new HoeItem(ModItemTier.JARIUM, 4, new Item.Properties().group(HumberCraft.TAB)));
	
	//Javium
		public static final RegistryObject<SwordItem> JAVIUM_SWORD = ITEMS.register("javium_sword", () -> 
		new SwordItem(ModItemTier.JAVIUM, 9, -3.0f, new Item.Properties().group(HumberCraft.TAB)));
		
		public static final RegistryObject<PickaxeItem> JAVIUM_PICKAXE = ITEMS.register("javium_pickaxe", () -> 
		new PickaxeItem(ModItemTier.JAVIUM, 4, -1.0f, new Item.Properties().group(HumberCraft.TAB)));
		
		public static final RegistryObject<ShovelItem> JAVIUM_SHOVEL = ITEMS.register("javium_shovel", () -> 
		new ShovelItem(ModItemTier.JAVIUM, 4, -1.0f, new Item.Properties().group(HumberCraft.TAB)));
		
		public static final RegistryObject<AxeItem> JAVIUM_AXE = ITEMS.register("javium_axe", () -> 
		new AxeItem(ModItemTier.JAVIUM, 4, -1.0f, new Item.Properties().group(HumberCraft.TAB)));
		
		public static final RegistryObject<HoeItem> JAVIUM_HOE = ITEMS.register("javium_hoe", () -> 
		new HoeItem(ModItemTier.JAVIUM, 4, new Item.Properties().group(HumberCraft.TAB)));
		
		//ARMOR
		//Jarium
		public static final RegistryObject<ArmorItem> JARIUM_HELMET = ITEMS.register("jarium_helmet", 
				() -> new ArmorItem(ModArmorMaterial.JARIUM, EquipmentSlotType.HEAD, new Item.Properties().group(HumberCraft.TAB)));
		
		public static final RegistryObject<ArmorItem> JARIUM_CHESTPLATE = ITEMS.register("jarium_chestplate", 
				() -> new ArmorItem(ModArmorMaterial.JARIUM, EquipmentSlotType.CHEST, new Item.Properties().group(HumberCraft.TAB)));
		
		public static final RegistryObject<ArmorItem> JARIUM_LEGGINGS = ITEMS.register("jarium_leggings", 
				() -> new ArmorItem(ModArmorMaterial.JARIUM, EquipmentSlotType.LEGS, new Item.Properties().group(HumberCraft.TAB)));
		
		public static final RegistryObject<ArmorItem> JARIUM_BOOTS = ITEMS.register("jarium_boots", 
				() -> new ArmorItem(ModArmorMaterial.JARIUM, EquipmentSlotType.FEET, new Item.Properties().group(HumberCraft.TAB)));
		
		//Javium
		public static final RegistryObject<ArmorItem> JAVIUM_HELMET = ITEMS.register("javium_helmet", 
				() -> new ArmorItem(ModArmorMaterial.JAVIUM, EquipmentSlotType.HEAD, new Item.Properties().group(HumberCraft.TAB)));
		
		public static final RegistryObject<ArmorItem> JAVIUM_CHESTPLATE = ITEMS.register("javium_chestplate", 
				() -> new ArmorItem(ModArmorMaterial.JAVIUM, EquipmentSlotType.CHEST, new Item.Properties().group(HumberCraft.TAB)));
		
		public static final RegistryObject<ArmorItem> JAVIUM_LEGGINGS = ITEMS.register("javium_leggings", 
				() -> new ArmorItem(ModArmorMaterial.JAVIUM, EquipmentSlotType.LEGS, new Item.Properties().group(HumberCraft.TAB)));
		
		public static final RegistryObject<ArmorItem> JAVIUM_BOOTS = ITEMS.register("javium_boots", 
				() -> new ArmorItem(ModArmorMaterial.JAVIUM, EquipmentSlotType.FEET, new Item.Properties().group(HumberCraft.TAB)));
}
