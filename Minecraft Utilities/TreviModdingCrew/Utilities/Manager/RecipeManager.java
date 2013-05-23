package TreviModdingCrew.Utilities.Manager;

import TreviModdingCrew.Utilities.Common.Main;
import TreviModdingCrew.Utilities.Handler.RockCutHandler;
import TreviModdingCrew.Utilities.Recipes.RecipeRockGrinder;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;


// Configurating The Recipes

public class RecipeManager 
{
    public static void RegisterMachines()
    {
        if(Main.OverideRockGrinder.getBoolean(false) == true)
        {
            RecipeRockGrinder.Grinding().addSmelting(Block.stone.blockID, new ItemStack(Block.cobblestone), 0.5F);
            RecipeRockGrinder.Grinding().addSmelting(Block.ice.blockID, new ItemStack(Item.snowball, 4), 0.5F);
            RecipeRockGrinder.Grinding().addSmelting(Block.glass.blockID, new ItemStack(Block.sand), 0.5F);
            RecipeRockGrinder.Grinding().addSmelting(Block.cobblestone.blockID, new ItemStack(Block.sand), 0.5F);
            RecipeRockGrinder.Grinding().addSmelting(Block.blockNetherQuartz.blockID, new ItemStack(Item.netherQuartz, 4), 0.5F);
            RecipeRockGrinder.Grinding().addSmelting(Block.sandStone.blockID, new ItemStack(Block.sand, 4), 0.5F);
            
            RecipeRockGrinder.Grinding().addSmelting(Item.bone.itemID, new ItemStack(Item.dyePowder, 6, 15), 0.5F);
            RecipeRockGrinder.Grinding().addSmelting(Item.blazeRod.itemID, new ItemStack(Item.blazePowder, 4), 0.5F);
        }
        
        if (Main.OverideRockCutter.getBoolean(false) == true)
        {
            RockCutHandler.getInstance().addRecipe(new ItemStack(Block.sandStone, 4, 0), new Object[]
            {
                "SS", "SS", 'S', Block.sand
            });
            
            RockCutHandler.getInstance().addRecipe(new ItemStack(Block.stoneSingleSlab, 6, 1), new Object[]
            {
                "SSS", 'S', Block.sandStone
            });
        
            RockCutHandler.getInstance().addRecipe(new ItemStack(Block.stairsSandStone, 4, 0), new Object[]
            {
                "S  ", "SS ", "SSS", 'S', Block.sandStone
            });
        
            RockCutHandler.getInstance().addRecipe(new ItemStack(Block.brick, 1, 0), new Object[]
            {
                "BB", "BB", 'B', Item.brick
            });
        
            RockCutHandler.getInstance().addRecipe(new ItemStack(Block.stoneSingleSlab, 6, 4), new Object[]
            {
                "SSS", 'S', Block.brick
            });
        
            RockCutHandler.getInstance().addRecipe(new ItemStack(Block.stairsBrick, 4, 0), new Object[]
            {
                "B  ", "BB ", "BBB", 'B', Block.brick
            });
        
            RockCutHandler.getInstance().addRecipe(new ItemStack(Block.stoneBrick, 4, 0), new Object[]
            {
                "SS", "SS", 'S', Block.stone
            });
        
            RockCutHandler.getInstance().addRecipe(new ItemStack(Block.stoneSingleSlab, 6, 5), new Object[]
            {
                "SSS", 'S', Block.stoneBrick
            });
        
            RockCutHandler.getInstance().addRecipe(new ItemStack(Block.stairsStoneBrick, 4, 0), new Object[]
            {
                "S  ", "SS ", "SSS", 'S', Block.stoneBrick
            });
        
            RockCutHandler.getInstance().addRecipe(new ItemStack(Block.stairsCobblestone, 4, 0), new Object[]
            {
                "C  ", "CC ", "CCC", 'C', Block.cobblestone
            });
        
            RockCutHandler.getInstance().addRecipe(new ItemStack(Block.stoneSingleSlab, 6, 3), new Object[]
            {
                "CCC", 'C', Block.cobblestone
            });
            
            RockCutHandler.getInstance().addRecipe(new ItemStack(Block.blockNetherQuartz, 1), new Object[]
            {
                "QQ", "QQ", 'Q', Item.netherQuartz
            });
           
            RockCutHandler.getInstance().addRecipe(new ItemStack(Block.stoneSingleSlab, 6, 7), new Object[]
            {
                "QQQ", 'Q', Block.blockNetherQuartz
            });
            
            RockCutHandler.getInstance().addRecipe(new ItemStack(Block.stairsNetherQuartz, 4), new Object[]
            {
                "Q  ", "QQ ", "QQQ", 'Q', Block.blockNetherQuartz
            });
         
            RockCutHandler.getInstance().addRecipe(new ItemStack(Block.netherBrick, 1), new Object[]
            {
                "BB", "BB", 'B', Item.netherrackBrick
            });
            
            RockCutHandler.getInstance().addRecipe(new ItemStack(Block.stoneSingleSlab, 6, 6), new Object[]
            {
                "NNN", 'N', Block.netherBrick
            });
            
            RockCutHandler.getInstance().addRecipe(new ItemStack(Block.stairsNetherBrick, 4), new Object[]
            {
                "N  ", "NN ", "NNN", 'N', Block.netherBrick
            });
            
            RockCutHandler.getInstance().addRecipe(new ItemStack(Block.stoneSingleSlab, 6, 0), new Object[]
            {
                "SSS", 'S', Block.stone
            });    
        }
    }
}
