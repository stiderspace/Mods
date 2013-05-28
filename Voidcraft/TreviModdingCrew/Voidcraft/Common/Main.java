package TreviModdingCrew.Voidcraft.Common;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;

import TreviModdingCrew.Utilities.Block.BlockLumberJacker;
import TreviModdingCrew.Voidcraft.Handler.LogHandler;
import TreviModdingCrew.Voidcraft.Handler.SoundHandler;
import TreviModdingCrew.Voidcraft.Items.BlockGreenPowderExplosive;
import TreviModdingCrew.Voidcraft.Packet.PacketManager;
import TreviModdingCrew.Voidcraft.Proxy.ClientProxy;
import TreviModdingCrew.Voidcraft.Proxy.CommonProxy;
import TreviModdingCrew.Voidcraft.Tab.TabVoidcraft;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid=Reference.ModID, name=Reference.ModName, version=Reference.Version, dependencies = Reference.Dependencies)
@NetworkMod(clientSideRequired=true, serverSideRequired=false, channels = {Reference.ChannelName}, packetHandler = PacketManager.class)

public class Main
{
    // Instance

    @Instance(Reference.ModID)
    public static Main Instance;
    
    
    // Proxy
    
    @SidedProxy(clientSide = "TreviModdingCrew.Voidcraft.Proxy.ClientProxy", serverSide = "TreviModdingCrew.Voidcraft.Proxy.CommonProxy")
    
    public static CommonProxy CommonProxy;
    public static ClientProxy ClientProxy;
    
    
    // Declaration
    
    public static Block GreenPowderExplosive;
    
    public static CreativeTabs Voidcraft = new TabVoidcraft(CreativeTabs.getNextID(), "Voidcraft");
   
    public int GreenPowderExplosiveID;
    
    @PreInit
    public void preInit(FMLPreInitializationEvent Event)
    {
        // Configuration File
        
        Configuration Config = new Configuration(Event.getSuggestedConfigurationFile());

        Config.load();
        
        GreenPowderExplosiveID = Config.getBlock("Green Powder Explosive", 3000).getInt();
        
        Config.save();  
   
        LogHandler.Log("Configuration File Loaded");  
        
        
        // Loading Sound Files
        
        MinecraftForge.EVENT_BUS.register(new SoundHandler());
    }
    
    
    @Init
    public void load(FMLInitializationEvent Event)
    {
        // Blocks
        
        GreenPowderExplosive = new BlockGreenPowderExplosive(GreenPowderExplosiveID, Material.tnt).setHardness(0.25F).setResistance(0.25F).setUnlocalizedName("Green Powder Explosive");
        
        
        // Block Registry
        
        GameRegistry.registerBlock(GreenPowderExplosive, "Green Powder Explosive"); 
        
        
        // Language Registry
        
        LanguageRegistry.addName(GreenPowderExplosive, "Green Powder Explosive");
    }
}
