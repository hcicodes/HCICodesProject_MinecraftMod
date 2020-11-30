package com.hci.humbercraft;

import net.minecraft.block.NetherPortalBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hci.humbercraft.blocks.BlockBase;
import com.hci.humbercraft.init.BiomeInit;
import com.hci.humbercraft.init.BlockInit;
import com.hci.humbercraft.init.DimensionInit;
import com.hci.humbercraft.init.ItemInit;
import com.hci.humbercraft.init.ModContainerTypes;
import com.hci.humbercraft.init.ModTileEntityTypes;
import com.hci.humbercraft.init.PortalInit;
import com.hci.humbercraft.init.RecipeSerializerInit;
import com.hci.humbercraft.items.BlockItemBase;
import com.hci.humbercraft.portal.PortalBlockInit;
import com.hci.humbercraft.util.RegistryHandler;

@Mod("humbercraft")
@Mod.EventBusSubscriber(modid = HumberCraft.MOD_ID, bus = Bus.FORGE)
public class HumberCraft
{
    // Logger reference - use this object to log anything to the console.
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "humbercraft";
    public static HumberCraft instance;
    public static final ResourceLocation JAVA_DIMENSION_TYPE = new ResourceLocation(MOD_ID, "java");
    
    
    public HumberCraft() 
    {
    	final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::doClientStuff);
        
        //initializing
        
        RecipeSerializerInit.RECIPE_SERIALIZERS.register(modEventBus);
        BlockInit.BLOCKS.register(modEventBus);
        ItemInit.ITEMS.register(modEventBus);
        ModTileEntityTypes.TILE_ENTITY_TYPES.register(modEventBus);
        ModContainerTypes.CONTAINER_TYPES.register(modEventBus);
        
        PortalInit.BLOCKS.register(modEventBus);
        
        BiomeInit.BIOMES.register(modEventBus);
      	DimensionInit.MOD_DIMENSIONS.register(modEventBus);
        
      	instance = this;
        MinecraftForge.EVENT_BUS.register(this);     
    }

    private void setup(final FMLCommonSetupEvent event)
    {
    }

	private void doClientStuff(final FMLClientSetupEvent event) 
	{
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) 
    {
    }
    
    /**not working right now
     * 
     * it's supposed to create an item for each block
     */
    
     /*
	@SubscribeEvent
	public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
		final IForgeRegistry<Item> registry = event.getRegistry();

		BlockInit.BLOCKS.getEntries().stream().map(RegistryObject::get)
				.forEach(block -> {
					final Item.Properties properties = new Item.Properties().group(HumbercraftItemGroup.instance);
					final BlockItem blockItem = new BlockItem(block, properties);
					blockItem.setRegistryName(block.getRegistryName());
					registry.register(blockItem);
				});

		LOGGER.debug("Registered BlockItems!");
	}
	*/
    
    /*
    @SubscribeEvent
    public static boolean onTrySpawnPortal(IWorld world, BlockPos pos, NetherPortalBlock.Size size)
    {
        return MinecraftForge.EVENT_BUS.post(new BlockEvent.PortalSpawnEvent(world, pos, world.getBlockState(pos), size));
    }*/
    
    @SubscribeEvent
    public static void onRegisterBiomes(final RegistryEvent.Register<Biome> event) {
    	BiomeInit.registerBiomes();
    }
    
    public static class HumbercraftItemGroup extends ItemGroup {
		public static final ItemGroup instance = new HumbercraftItemGroup(ItemGroup.GROUPS.length, "humbercraft");

		private HumbercraftItemGroup(int index, String label) {
			super(index, label);
		}

		@Override
		public ItemStack createIcon() {
			return new ItemStack(ItemInit.JAVIUM.get());
		}
	}
}
