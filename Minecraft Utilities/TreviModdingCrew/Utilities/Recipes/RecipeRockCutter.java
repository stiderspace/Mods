package TreviModdingCrew.Utilities.Recipes;

import java.util.Comparator;

import TreviModdingCrew.Utilities.Handler.RockCutHandler;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;

public class RecipeRockCutter implements Comparator
{
    // Declaration
    
	final RockCutHandler HandlerRockCutter;
	
	public RecipeRockCutter(RockCutHandler Crafting)
	{
		HandlerRockCutter = Crafting;
	}
	
	public int CompareRecipes(IRecipe IRecipe1, IRecipe IRecipe2)
	{
		return IRecipe1 instanceof ShapelessRecipes && IRecipe2 instanceof ShapedRecipes ? 1 : (IRecipe2 instanceof ShapelessRecipes && IRecipe1 instanceof ShapedRecipes ? -1 : (IRecipe2.getRecipeSize() < IRecipe1.getRecipeSize() ? -1 : (IRecipe2.getRecipeSize() > IRecipe1.getRecipeSize() ? 1 : 0)));
	}
	
	public int compare(Object Object1, Object Object2)
	{
		return CompareRecipes((IRecipe)Object1, (IRecipe)Object2);
	}
}
