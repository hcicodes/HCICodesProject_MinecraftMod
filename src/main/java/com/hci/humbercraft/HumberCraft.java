package com.hci.humbercraft;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hci.humbercraft.init.BiomeInit;
import com.hci.humbercraft.init.BlockInit;
import com.hci.humbercraft.init.DimensionInit;
import com.hci.humbercraft.init.ItemInit;
import com.hci.humbercraft.portal.PortalBlockInit;
import com.hci.humbercraft.util.RegistryHandler;

@Mod("humbercraft")
@Mod.EventBusSubscriber(modid = HumberCraft.MOD_ID, bus = Bus.FORGE)
public class HumberCraft
{
    // Logger reference - use this object to log anything to the console.
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "humbercraft";
    
    public static final ResourceLocation JAVA_DIMENSION_TYPE = new ResourceLocation(MOD_ID, "java");
    
    
    public HumberCraft() 
    {
    	final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::doClientStuff);

        
        //initializing
        ItemInit.ITEMS.register(modEventBus);
        BlockInit.BLOCKS.register(modEventBus);
        //Tile Entitities
        BiomeInit.BIOMES.register(modEventBus);
        DimensionInit.MOD_DIMENSIONS.register(modEventBus);
        
        PortalBlockInit.BLOCKS.register(modEventBus);
        
      //BiomeInit.BIOMES.register(modEventBus);
      //DimensionInit.MOD_DIMENSIONS.register(modEventBus);
        
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
    
    
    @SubscribeEvent
    public static void onRegisterBiomes(final RegistryEvent.Register<Biome> event) {
    	BiomeInit.registerBiomes();
    }
    
  
    
    public static final ItemGroup TAB = new ItemGroup("humberCraft") 
    {
    	@Override
    	public ItemStack createIcon() {
    		return new ItemStack(com.hci.humbercraft.init.ItemInit.JAVIUM.get());
    	}
    };
    
}
