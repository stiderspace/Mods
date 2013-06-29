/**
 * Minecraft Utilities
 *
 * 
 * @Author Trevi Awater 
 * @License Gnu Public License Version 3
 * 
 **/

package TreviModdingCrew.Utilities.Common;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Property;

import TreviModdingCrew.Utilities.Block.BlockEggHatcher;
import TreviModdingCrew.Utilities.Block.BlockLumberJacker;
import TreviModdingCrew.Utilities.Block.BlockRockCutter;
import TreviModdingCrew.Utilities.Block.BlockRockGrinder;
import TreviModdingCrew.Utilities.Block.BlockWashingMachine;
import TreviModdingCrew.Utilities.Entity.EntityBomb;
import TreviModdingCrew.Utilities.Handler.FuelHandler;
import TreviModdingCrew.Utilities.Handler.LogHandler;
import TreviModdingCrew.Utilities.Items.ItemBomb;
import TreviModdingCrew.Utilities.Items.ItemFilter;
import TreviModdingCrew.Utilities.Manager.RecipeManager;
import TreviModdingCrew.Utilities.Packet.PacketManager;
import TreviModdingCrew.Utilities.Proxy.ClientProxy;
import TreviModdingCrew.Utilities.Proxy.CommonProxy;
import TreviModdingCrew.Utilities.Tab.TabUtilitiesBlock;
import TreviModdingCrew.Utilities.Tab.TabUtilitiesItem;
import TreviModdingCrew.Utilities.Tile.TileEntityEggHatcher;
import TreviModdingCrew.Utilities.Tile.TileEntityLumberJacker;
import TreviModdingCrew.Utilities.Tile.TileEntityRockGrinder;
import TreviModdingCrew.Utilities.Tile.TileEntityWashingMachine;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
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
	
	@SidedProxy(clientSide = "TreviModdingCrew.Utilities.Proxy.ClientProxy", serverSide = "TreviModdingCrew.Utilities.Proxy.CommonProxy")
	
	public static CommonProxy CommonProxy;
	public static ClientProxy ClientProxy;
	
	
	// Declaration

	public static Item Filter;
	public static Item Bomb;
	
	public static Block RockGrinder;
	public static Block RockCutter;
	public static Block EggHatcher;
	public static Block LumberJacker;
	public static Block WashingMachine;

	public static CreativeTabs UtilitiesBlock = new TabUtilitiesBlock(CreativeTabs.getNextID(), "Utilities Blocks");
	public static CreativeTabs UtilitiesItem = new TabUtilitiesItem(CreativeTabs.getNextID(), "Utilities Items");
	
	public int FilterID;
	public int BombID;
	
	public int RockGrinderID;
	public int RockCutterID;
	public int EggHatcherID;
	public int LumberJackerID;
	public int WashingMachineID;
	public int ScarecrowID;
	
	public Property RecipeBone;
	public Property RecipeCyanDye;
	public Property RecipeIce;
	public Property RecipeGravel;
	public Property RecipeObsidian;
	public Property RecipeMossyCobblestone;
	public Property RecipeFlint;
	public Property RecipeNetherBricks;
	public Property RecipeNetherBrick;
	public Property RecipeNetherStairs;
	public Property RecipeLeather;
	public Property RecipeBomb;
	public Property RecipeSaddle;
	public Property RecipeWeb;
	public Property RecipeClay;
	public Property RecipeFilter;
	public Property RecipePaper;
	public Property RecipeString;
	public Property RecipeRockCutter;
	public Property RecipeRockGrinder;
	public Property RecipeEggHatcher;
	public Property RecipeLumberJacker;
	public Property RecipeWashingMachine;
	
	public static Property OverideRockCutter;
	public static Property OverideRockGrinder;
	
    @PreInit
    public void preInit(FMLPreInitializationEvent Event)
    {
    	// Configuration File
    	
    	Configuration Config = new Configuration(Event.getSuggestedConfigurationFile());

        Config.load();
            
        FilterID = Config.getItem("Filter", 5500).getInt();
        BombID = Config.getItem("Bomb", 5501).getInt();
        
        RockGrinderID = Config.getBlock("Rock Grinder", 2500).getInt();
        RockCutterID = Config.getBlock("Rock Cutter", 2501).getInt();
        EggHatcherID = Config.getBlock("Egg Hatcher", 2502).getInt();
        LumberJackerID = Config.getBlock("Lumber Jacker", 2503).getInt();
        WashingMachineID = Config.getBlock("Washing Machine", 2504).getInt();
        ScarecrowID = Config.getBlock("Scarecrow", 2505).getInt();
       
        RecipeBone = Config.get("Recipes", "Bone", true);
        RecipeCyanDye = Config.get("Recipes", "Cyan Dye", true);
        RecipeIce = Config.get("Recipes", "Ice", true);
        RecipeGravel = Config.get("Recipes", "Gravel", true);
        RecipeObsidian = Config.get("Recipes", "Obsidian", true);
        RecipeMossyCobblestone = Config.get("Recipes", "Mossy Cobblestone", true);   
        RecipeFlint = Config.get("Recipes", "Flint", true);
        RecipeBomb = Config.get("Recipes", "Bomb", true);
        RecipeSaddle = Config.get("Recipes", "Saddle", true);
        RecipeWeb = Config.get("Recipes", "Cob Web", true);
        RecipeClay = Config.get("Recipes", "Clay", false);
        RecipeFilter = Config.get("Recipes", "Filter", true);
        RecipePaper = Config.get("Recipes", "Paper", true);
        RecipeString = Config.get("Recipes", "String", false);
        RecipeRockCutter = Config.get("Recipes", "Rock Cutter", true);
        RecipeRockGrinder = Config.get("Recipes", "Rock Grinder", true);
        RecipeEggHatcher = Config.get("Recipes", "Egg Hatcher", true);
        RecipeLumberJacker = Config.get("Recipes", "Lumber Jacker", true);
        RecipeWashingMachine = Config.get("Recipes", "Washing Machine", true);
       
        RecipeNetherBricks = Config.get("Smelting", "Nether Bricks", true);
        RecipeNetherBrick = Config.get("Smelting", "Nether Bricks", true);
        RecipeNetherStairs = Config.get("Smelting", "Nether Stairs", true);
        RecipeLeather = Config.get("Smelting", "Leather", false);

        OverideRockCutter = Config.get("Overide", "Rock Cutter", true);
        OverideRockGrinder = Config.get("Overide", "Rock Grinder", true);
        
        Config.save();	
   
        LogHandler.Log("Configuration File Loaded", 0);  
    }


    @Init
    public void load(FMLInitializationEvent Event)
    {
    	// Items
	
    	Filter = new ItemFilter(FilterID).setUnlocalizedName("Filter");
    	Bomb = new ItemBomb(BombID).setUnlocalizedName("Bomb");
    	
        
    	// Item Registry
    	
    	GameRegistry.registerItem(Filter, "Filter");
    	GameRegistry.registerItem(Bomb, "Bomb");
    	
    	
    	// Blocks
    	
    	RockGrinder = new BlockRockGrinder(RockGrinderID, Material.rock).setHardness(5F).setResistance(2.5F).setUnlocalizedName("Rock Grinder");
        RockCutter = new BlockRockCutter(RockCutterID, Material.rock).setHardness(5F).setResistance(2.5F).setUnlocalizedName("Rock Cutter");
        EggHatcher = new BlockEggHatcher(EggHatcherID, Material.rock).setHardness(5F).setResistance(2.5F).setUnlocalizedName("Egg Hatcher");
        LumberJacker = new BlockLumberJacker(LumberJackerID, Material.rock).setHardness(5F).setResistance(2.5F).setUnlocalizedName("Lumber Jacker");
        WashingMachine = new BlockWashingMachine(WashingMachineID, Material.rock).setHardness(5F).setResistance(2.5F).setUnlocalizedName("Washing Machine");
        
        
    	// Block Registry
    	
    	GameRegistry.registerBlock(RockGrinder, "Rock Grinder");
    	GameRegistry.registerBlock(RockCutter, "Rock Cutter");
    	GameRegistry.registerBlock(EggHatcher, "Egg Hatcher");
    	GameRegistry.registerBlock(LumberJacker, "Lumber Jacker");
    	GameRegistry.registerBlock(WashingMachine, "Washing Machine");
    	
        
    	// Network Registry
    	
    	NetworkRegistry.instance().registerGuiHandler(this, CommonProxy);
    	
    	
    	// Shapeless Recipe

    	if (RecipeBone.getBoolean(false) == true)
    	{
    		GameRegistry.addShapelessRecipe(new ItemStack(Item.bone, 2), new Object[] {new ItemStack(Item.chickenRaw)});
    	}
	
    	if (RecipeIce.getBoolean(false) == true)
    	{
    		GameRegistry.addShapelessRecipe(new ItemStack(Block.ice, 1), new Object[] {new ItemStack(Item.bucketWater),new ItemStack(Item.snowball)});
    	}
     
    	if (RecipeGravel.getBoolean(false) == true)
    	{
    		GameRegistry.addShapelessRecipe(new ItemStack(Block.gravel, 1), new Object[] {new ItemStack(Item.flint),new ItemStack(Block.sand)});
    	}
     
    	if (RecipeObsidian.getBoolean(false) == true)
    	{
    		GameRegistry.addShapelessRecipe(new ItemStack(Block.obsidian, 1), new Object[] {new ItemStack(Item.bucketWater),new ItemStack(Item.bucketLava)});
    	}
     
    	if (RecipeMossyCobblestone.getBoolean(false) == true)
    	{
    		GameRegistry.addShapelessRecipe(new ItemStack(Block.cobblestoneMossy, 1), new Object[] {new ItemStack(Block.cobblestone),new ItemStack(Block.vine)});
    	}

    	
    	// Smelting Recipe
     
    	if (RecipeNetherBricks.getBoolean(false) == true)
    	{
    		GameRegistry.addSmelting(Block.brick.blockID, new ItemStack(Block.netherBrick), 1.5F);	
    	}
     
    	if (RecipeNetherBrick.getBoolean(false) == true)
        {
            GameRegistry.addSmelting(Item.brick.itemID, new ItemStack(Item.netherrackBrick), 0.5F);   
        }
    	
    	if (RecipeNetherStairs.getBoolean(false) == true)
    	{
    		GameRegistry.addSmelting(Block.stairsBrick.blockID, new ItemStack(Block.stairsNetherBrick), 1.5F);		
    	}
     
    	if (RecipeLeather.getBoolean(false) == true)
    	{
    		GameRegistry.addSmelting(Item.rottenFlesh.itemID, new ItemStack(Item.leather), 0.5F);	
    	}
    
    	
    	// Smart Shaped Recipe
     
    	if (RecipeBomb.getBoolean(false) == true)
    	{
    		GameRegistry.addRecipe(new ItemStack(Bomb, 1), new Object[] {"S", "G", 'S', Item.silk, 'G', Item.gunpowder});
    	}
    	
    	if (RecipeRockCutter.getBoolean(false) == true)
        {
            GameRegistry.addRecipe(new ItemStack(RockCutter, 1), new Object[] {"CC", "CC", 'C', Block.cobblestone});
        }
     
    	
    	// Shaped Recipe
     
    	if (RecipeSaddle.getBoolean(false) == true)
    	{
    		GameRegistry.addRecipe(new ItemStack(Item.saddle), new Object[]
    		{
    			" L ", "L L", "I I", 'L', new ItemStack(Item.leather),'I', new ItemStack(Item.ingotIron)
    		});
    	}
     
    	if (RecipeWeb.getBoolean(false) == true)
    	{
    		GameRegistry.addRecipe(new ItemStack(Block.web, 1), new Object[]
    		{
    			"S S", " S ", "S S", 'S', new ItemStack(Item.silk),
    		});
    	}
     
    	if (RecipeClay.getBoolean(false) == true)
    	{
    		GameRegistry.addRecipe(new ItemStack(Item.clay, 8), new Object[]
    		{
    			"DBD", "SSS", "DBD", 'S', new ItemStack(Block.sand),'D', new ItemStack(Block.dirt),'B', new ItemStack(Item.bucketWater)
    		});
    	 
    		GameRegistry.addRecipe(new ItemStack(Item.clay, 8), new Object[]
    		{
    			"DSD", "BSB", "DSD", 'S', new ItemStack(Block.sand),'D', new ItemStack(Block.dirt),'B', new ItemStack(Item.bucketWater)
    		});
    	}
     
    	if (RecipeFilter.getBoolean(false) == true)
    	{
    		GameRegistry.addRecipe(new ItemStack(Filter), new Object[]
    	 	{
    			"   ", "P P", " W ", 'P', Block.planks, 'W', Block.cloth
    	 	});
     
    		GameRegistry.addRecipe(new ItemStack(Filter), new Object[]
    		{
    			"P P", " W ", "   ", 'P', Block.planks, 'W', Block.cloth
    		});
    	}
    	
    	if (RecipeRockGrinder.getBoolean(false) == true)
        {
            GameRegistry.addRecipe(new ItemStack(RockGrinder), new Object[]
            {
                "CCC", "CSC", "PSP", 'S', RockCutter, 'C', Block.cobblestone, 'P', Block.pistonBase
            });
        }
    	
    	if (RecipeEggHatcher.getBoolean(false) == true)
        {
            GameRegistry.addRecipe(new ItemStack(EggHatcher), new Object[]
            {
                "GGG", "CWC", "CCC", 'G', Block.glass, 'C', Block.cobblestone, 'W', Item.wheat
            });
        }
    	
    	if (RecipeLumberJacker.getBoolean(false) == true)
        {
            GameRegistry.addRecipe(new ItemStack(LumberJacker), new Object[]
            {
                "CAC", "CRC", "CCC", 'C', Block.cobblestone, 'R', Item.redstone, 'A', Item.axeIron
            });
        }
    	
    	if (RecipeWashingMachine.getBoolean(false) == true)
        {
            GameRegistry.addRecipe(new ItemStack(WashingMachine), new Object[]
            {
                "CCC", "CGC", "CCC", 'C', Block.cobblestone, 'G', Block.glass
            });
        }
    	
    	
    	// Machine Recipes
    	
    	RecipeManager.RegisterMachines();
  
    	
    	// Removing Vanilla Recipes
    	
    	if (OverideRockCutter.getBoolean(false) == true)
        {
    	    CommonProxy.RemoveRecipe(new ItemStack(Block.sandStone));
    	    CommonProxy.RemoveRecipe(new ItemStack(Block.stoneSingleSlab, 6, 0));
    	    CommonProxy.RemoveRecipe(new ItemStack(Block.stoneSingleSlab, 6, 1));
    	    CommonProxy.RemoveRecipe(new ItemStack(Block.stoneSingleSlab, 6, 2));
    	    CommonProxy.RemoveRecipe(new ItemStack(Block.stoneSingleSlab, 6, 3));
    	    CommonProxy.RemoveRecipe(new ItemStack(Block.stoneSingleSlab, 6, 4));
    	    CommonProxy.RemoveRecipe(new ItemStack(Block.stoneSingleSlab, 6, 5));
    	    CommonProxy.RemoveRecipe(new ItemStack(Block.stoneSingleSlab, 6, 6));
    	    CommonProxy.RemoveRecipe(new ItemStack(Block.stoneSingleSlab, 6, 7));
    	    CommonProxy.RemoveRecipe(new ItemStack(Block.stairsSandStone));
    	    CommonProxy.RemoveRecipe(new ItemStack(Block.brick));
    	    CommonProxy.RemoveRecipe(new ItemStack(Block.stairsBrick));
    	    CommonProxy.RemoveRecipe(new ItemStack(Block.stoneBrick));
    	    CommonProxy.RemoveRecipe(new ItemStack(Block.stairsStoneBrick));
    	    CommonProxy.RemoveRecipe(new ItemStack(Block.stairsCobblestone));
    	    CommonProxy.RemoveRecipe(new ItemStack(Block.blockNetherQuartz));
    	    CommonProxy.RemoveRecipe(new ItemStack(Block.netherBrick));
    	    CommonProxy.RemoveRecipe(new ItemStack(Block.stairsNetherBrick));
    	    CommonProxy.RemoveRecipe(new ItemStack(Block.stairsNetherQuartz));
        }
    	
    	
	    // Proxy's
	     
	    CommonProxy.LoadRenderers();
	    CommonProxy.KeyBinding();
	    CommonProxy.LoadSounds();
	    
	    
	    // Chest Looting
	 
	    ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(new WeightedRandomChestContent(new ItemStack(Filter), 1, 1, 50));
	    ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(Bomb), 1, 5, 25));
	   
	     
	    // Fuel Registry
	     
	    GameRegistry.registerFuelHandler(new FuelHandler());
	     
	    
	    // Entity Registry
	     
	    EntityRegistry.registerModEntity(EntityBomb.class, "Bomb", BombID, this, 950, 2, true);
	 
	     
	    // Tile Registry
	    
	    GameRegistry.registerTileEntity(TileEntityRockGrinder.class, "Rock Grinder");
	    GameRegistry.registerTileEntity(TileEntityEggHatcher.class, "Egg Hatcher");
	    GameRegistry.registerTileEntity(TileEntityLumberJacker.class, "Lumber Jacker");
	    GameRegistry.registerTileEntity(TileEntityWashingMachine.class, "Washing Machine");
	    
	    
	    // Language Registry
	     
	    LanguageRegistry.addName(Filter, "Filter");
	    LanguageRegistry.addName(Bomb, "Bomb");
	   
	    LanguageRegistry.addName(RockGrinder, "Rock Grinder");
	    LanguageRegistry.addName(RockCutter, "Rock Cutter");
	    LanguageRegistry.addName(EggHatcher, "Egg Hatcher");
	    LanguageRegistry.addName(LumberJacker, "Lumber Jacker");
	    LanguageRegistry.addName(WashingMachine, "Washing Machine");
    }
}