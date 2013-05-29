package TreviModdingCrew.Voidcraft.Common;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;

import TreviModdingCrew.Utilities.Block.BlockLumberJacker;
import TreviModdingCrew.Voidcraft.Blocks.BlockGreenPowderExplosive;
import TreviModdingCrew.Voidcraft.Blocks.BlockGreenPowderOre;
import TreviModdingCrew.Voidcraft.Handler.LogHandler;
import TreviModdingCrew.Voidcraft.Handler.SoundHandler;
import TreviModdingCrew.Voidcraft.Handler.WorldGenerationHandler;
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
    public static Block GreenPowderOre;
    
    public static CreativeTabs Voidcraft = new TabVoidcraft(CreativeTabs.getNextID(), "Voidcraft");
   
    public int GreenPowderExplosiveID;
    public int GreenPowderOreID;
    
    @PreInit
    public void preInit(FMLPreInitializationEvent Event)
    {
        // Configuration File
        
        Configuration Config = new Configuration(Event.getSuggestedConfigurationFile());

        Config.load();
        
        GreenPowderExplosiveID = Config.getBlock("Green Powder Explosive", 3000).getInt();
        GreenPowderOreID = Config.getBlock("Green Powder Ore", 3001).getInt();
        
        Config.save();  
   
        LogHandler.Log("Configuration File Loaded");  
        
        
        // Loading Sound Files
        
        MinecraftForge.EVENT_BUS.register(new SoundHandler());
    }
    
    
    @Init
    public void load(FMLInitializationEvent Event)
    {
        // Blocks
        
        GreenPowderExplosive = new BlockGreenPowderExplosive(GreenPowderExplosiveID, Material.tnt).setHardness(0F).setResistance(100F).setUnlocalizedName("Green Powder Explosive");
        GreenPowderOre = new BlockGreenPowderOre(GreenPowderOreID, Material.rock).setHardness(5F).setResistance(5F).setUnlocalizedName("Green Powder Ore");
        
        
        // Block Registry
        
        GameRegistry.registerBlock(GreenPowderExplosive, "Green Powder Explosive"); 
        GameRegistry.registerBlock(GreenPowderOre, "Green Powder Ore"); 
        
        
        // World Generation Registry
        
        GameRegistry.registerWorldGenerator(new WorldGenerationHandler());
        
        
        // Language Registry
        
        LanguageRegistry.addName(GreenPowderExplosive, "Green Powder Explosive");
        LanguageRegistry.addName(GreenPowderOre, "Green Powder Ore");
    }
}
