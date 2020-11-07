package com.hci.humbercraft;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hci.humbercraft.tools.RegistryHandler;

@Mod("humbercraft")
public class HumberCraft
{
    // Logger reference - use this object to log anything to the console.
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "humbercraft";
    
    public HumberCraft() 
    {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        RegistryHandler.init();
        
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
    
    public static final ItemGroup TAB = new ItemGroup("humberCraft") 
    {
    	@Override
    	public ItemStack createIcon() {
    		return new ItemStack(RegistryHandler.JAVA.get());
    	}
    };
    
}
