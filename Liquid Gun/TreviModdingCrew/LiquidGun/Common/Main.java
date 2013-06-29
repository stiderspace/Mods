package TreviModdingCrew.LiquidGun.Common;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

import TreviModdingCrew.LiquidGun.Handler.LogHandler;
import TreviModdingCrew.LiquidGun.Items.ItemLiquidGun;
import TreviModdingCrew.LiquidGun.Packet.PacketManager;
import TreviModdingCrew.LiquidGun.Proxy.ClientProxy;
import TreviModdingCrew.LiquidGun.Proxy.CommonProxy;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.EntityRegistry;
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
    
    @SidedProxy(clientSide = "TreviModdingCrew.LiquidGun.Proxy.ClientProxy", serverSide = "TreviModdingCrew.LiquidGun.Proxy.CommonProxy")
    
    public static CommonProxy CommonProxy;
    public static ClientProxy ClientProxy;
    
    
    // Declaration
    
    public static Item LiquidGun;
   
    public int LiquidGunID;
    
    public Property RecipeLiquidGun;
    
    
    @PreInit
    public void preInit(FMLPreInitializationEvent Event)
    {
        // Configuration File
        
        Configuration Config = new Configuration(Event.getSuggestedConfigurationFile());

        Config.load();
       
        LiquidGunID = Config.getItem("Liquid Gun", 5000).getInt();
        
        RecipeLiquidGun = Config.get("Recipes", "Liquid Gun", true);
        
        Config.save();  
   
        LogHandler.Log("Configuration File Loaded", 0);  
    }
    
    @Init
    public void load(FMLInitializationEvent Event)
    {
        // Items
        
        LiquidGun = new ItemLiquidGun(LiquidGunID).setUnlocalizedName("Liquid Gun");
    
        
        // Item Registry
        
        GameRegistry.registerItem(LiquidGun, "Liquid Gun");
        
        
        // Entity Registry
        
        EntityRegistry.registerModEntity(TreviModdingCrew.LiquidGun.Entity.EntityLiquidBullet.class, "EntityLiquidBullet", 99, this, 99, 1, true);
        
        
        // Register Proxy Methods
        
        CommonProxy.LoadRenderers();
        
        
        // Shaped Recipe
        
        if (RecipeLiquidGun.getBoolean(false) == true)
        {
            GameRegistry.addRecipe(new ItemStack(LiquidGun, 1), new Object[]
            {
                " G ", "III", " II", 'G', Block.glass, 'I', Item.ingotIron
            });
        }
        
        
        // Language Registry
        
        LanguageRegistry.addName(LiquidGun, "Liquid Gun");
    }
}