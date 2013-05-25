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
import TreviModdingCrew.Utilities.Entity.EntityBomb;
import TreviModdingCrew.Utilities.Handler.FuelHandler;
import TreviModdingCrew.Utilities.Handler.LogHandler;
import TreviModdingCrew.Utilities.Handler.SoundHandler;
import TreviModdingCrew.Utilities.Items.ItemBomb;
import TreviModdingCrew.Utilities.Items.ItemFilter;
import TreviModdingCrew.Utilities.Items.ItemMagnet;
import TreviModdingCrew.Utilities.Items.ItemTreetap;
import TreviModdingCrew.Utilities.Manager.RecipeManager;
import TreviModdingCrew.Utilities.Packet.PacketManager;
import TreviModdingCrew.Utilities.Proxy.ClientProxy;
import TreviModdingCrew.Utilities.Proxy.CommonProxy;
import TreviModdingCrew.Utilities.Tab.TabUtilitiesBlock;
import TreviModdingCrew.Utilities.Tab.TabUtilitiesItem;
import TreviModdingCrew.Utilities.Tile.TileEntityHatcher;
import TreviModdingCrew.Utilities.Tile.TileEntityLumberJacker;
import TreviModdingCrew.Utilities.Tile.TileEntityRockGrinder;

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

@Mod(modid=Reference.ModID, name=Reference.ModName, version=Reference.Version)
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
	
	
	// Declarations

	public static Item Filter;
	public static Item Bomb;
	public static Item Magnet;
	public static Item Treetap;
	
	public static Block RockGrinder;
	public static Block RockCutter;
	public static Block EggHatcher;
	public static Block LumberJacker;
	
	public static CreativeTabs UtilitiesBlock = new TabUtilitiesBlock(CreativeTabs.getNextID(), "Utilities Blocks");
	public static CreativeTabs UtilitiesItem = new TabUtilitiesItem(CreativeTabs.getNextID(), "Utilities Items");
	
	public int FilterID;
	public int BombID;
	public int MagnetID;
	public int TreetapID;
	
	public int RockGrinderID;
	public int RockCutterID;
	public int EggHatcherID;
	public int LumberJackerID;
    
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
	public Property RecipeTreetap;
	public Property RecipeLumberJacker;
	
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
        MagnetID = Config.getItem("Magnet", 5502).getInt();
        TreetapID = Config.getItem("Treetap", 5503).getInt();
       
        RockGrinderID = Config.getBlock("Rock Grinder", 2500).getInt();
        RockCutterID = Config.getBlock("Rock Cutter", 2501).getInt();
        EggHatcherID = Config.getBlock("Egg Hatcher", 2502).getInt();
        LumberJackerID = Config.getBlock("Lumber Jacker", 2503).getInt();
        
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
        RecipeTreetap = Config.get("Recipes", "Treetap", true);
        RecipeLumberJacker = Config.get("Recipes", "Lumber Jacker", true);
        
        RecipeNetherBricks = Config.get("Smelting", "Nether Bricks", true);
        RecipeNetherBrick = Config.get("Smelting", "Nether Bricks", true);
        RecipeNetherStairs = Config.get("Smelting", "Nether Stairs", true);
        RecipeLeather = Config.get("Smelting", "Leather", false);

        OverideRockCutter = Config.get("Overide", "Rock Cutter", true);
        OverideRockGrinder = Config.get("Overide", "Rock Grinder", true);
        
        Config.save();	
   
        LogHandler.Log("Configuration File Loaded");  
        
        
        // Loading Sound Files
        
        MinecraftForge.EVENT_BUS.register(new SoundHandler());
    }


    @Init
    public void load(FMLInitializationEvent Event)
    {
    	// Items
	
    	Filter = new ItemFilter(FilterID).setUnlocalizedName("Filter");
    	Bomb = new ItemBomb(BombID).setUnlocalizedName("Bomb");
    	Magnet = new ItemMagnet(MagnetID).setUnlocalizedName("Magnet");
    	Treetap = new ItemTreetap(TreetapID).setUnlocalizedName("Treetap");
    	
        
    	// Item Registry
    	
    	GameRegistry.registerItem(Filter, "Filter");
    	GameRegistry.registerItem(Bomb, "Bomb");
    	GameRegistry.registerItem(Magnet, "Magnet");
    	GameRegistry.registerItem(Treetap, "Treetap");
    	
    	
    	// Blocks
    	
    	RockGrinder = new BlockRockGrinder(RockGrinderID, Material.rock).setHardness(5F).setResistance(2.5F).setUnlocalizedName("Rock Grinder");
        RockCutter = new BlockRockCutter(RockCutterID, Material.rock).setHardness(5F).setResistance(2.5F).setUnlocalizedName("Rock Cutter");
        EggHatcher = new BlockEggHatcher(EggHatcherID, Material.rock).setHardness(5F).setResistance(2.5F).setUnlocalizedName("Egg Hatcher");
        LumberJacker = new BlockLumberJacker(LumberJackerID, Material.rock).setHardness(5F).setResistance(2.5F).setUnlocalizedName("Egg Hatcher");
        
    	
    	// Block Registry
    	
    	GameRegistry.registerBlock(RockGrinder, "Rock Grinder");
    	GameRegistry.registerBlock(RockCutter, "Rock Cutter");
    	GameRegistry.registerBlock(EggHatcher, "Egg Hatcher");
    	GameRegistry.registerBlock(LumberJacker, "Lumber Jacker");
        
    	
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
    		GameRegistry.addSmelting(Block.brick.blockID,
    			 new ItemStack(Block.netherBrick), 1.5F);
    	}
     
    	if (RecipeNetherBrick.getBoolean(false) == true)
        {
            GameRegistry.addSmelting(Item.brick.itemID,
                 new ItemStack(Item.netherrackBrick), 0.5F);
        }
    	
    	if (RecipeNetherStairs.getBoolean(false) == true)
    	{
    		GameRegistry.addSmelting(Block.stairsBrick.blockID,
    				new ItemStack(Block.stairsNetherBrick), 1.5F);
    	}
     
    	if (RecipeLeather.getBoolean(false) == true)
    	{
    		GameRegistry.addSmelting(Item.rottenFlesh.itemID,
    			 new ItemStack(Item.leather), 0.5F);
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
                "CCC", "CGC", "CWC", 'G', Block.glass, 'C', Block.cobblestone, 'W', Item.wheat
            });
        }
    	
    	if (RecipeTreetap.getBoolean(false) == true)
        {
            GameRegistry.addRecipe(new ItemStack(Treetap), new Object[]
            {
                " I ", "WWW", "W  ", 'I', Item.ingotIron, 'W', Block.planks
            });
        }
    	
    	if (RecipeLumberJacker.getBoolean(false) == true)
        {
            GameRegistry.addRecipe(new ItemStack(LumberJacker), new Object[]
            {
                "CIC", "CRC", "CCC", 'C', Block.cobblestone, 'R', Item.redstone, 'I', Item.ingotIron
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
	    
	    
	    // Chest Looting
	 
	    ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(new WeightedRandomChestContent(new ItemStack(Filter), 1, 1, 50));
	    ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(Bomb), 1, 5, 25));
	    ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(Magnet), 1, 2, 25));
        
	     
	    // Fuel Registry
	     
	    GameRegistry.registerFuelHandler(new FuelHandler());
	     
	    
	    // Entity Registry
	     
	    EntityRegistry.registerModEntity(EntityBomb.class, "Bomb", BombID, this, 950, 2, true);
	 
	     
	    // Tile Registry
	    
	    GameRegistry.registerTileEntity(TileEntityRockGrinder.class, "Rock Grinder");
	    GameRegistry.registerTileEntity(TileEntityHatcher.class, "Egg Hatcher");
	    GameRegistry.registerTileEntity(TileEntityLumberJacker.class, "Lumber Jacker");

	    
	    // Language Registry
	     
	    LanguageRegistry.addName(Filter, "Filter");
	    LanguageRegistry.addName(Bomb, "Bomb");
	    LanguageRegistry.addName(Magnet, "Magnet");
	    LanguageRegistry.addName(Treetap, "Treetap");
	     
	    LanguageRegistry.addName(RockGrinder, "Rock Grinder");
	    LanguageRegistry.addName(RockCutter, "Rock Cutter");
	    LanguageRegistry.addName(EggHatcher, "Egg Hatcher");
	    LanguageRegistry.addName(LumberJacker, "Lumber Jacker");
    }
}