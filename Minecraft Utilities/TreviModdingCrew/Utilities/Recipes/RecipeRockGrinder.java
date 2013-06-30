package TreviModdingCrew.Utilities.Recipes;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.ItemStack;

public class RecipeRockGrinder 
{
    // Declarations
    
    private static final RecipeRockGrinder RockGrinderBase = new RecipeRockGrinder();
 
    public static Map <Integer, ItemStack> RockGrinderList = new HashMap <Integer, ItemStack>();
    public static Map <Integer, Float> RockGrinderExperience = new HashMap <Integer, Float>();
 

    //Used To Call Smelting Recipes
    
    public static final RecipeRockGrinder Grinding()
    {
        return RockGrinderBase;
    }
 
    
    //Recipes Go Here
    
    private RecipeRockGrinder()
    {
     
    }
 
    
    //Doing Something With The Recipes
   
    public void addGrinding(int Par1, ItemStack ItemStack, float Experience)
    {
        RockGrinderList.put(Integer.valueOf(Par1), ItemStack);
        RockGrinderExperience.put(Integer.valueOf(ItemStack.itemID), Float.valueOf(Experience));
    }
 

    // Returning A Item Or Block
    
    public ItemStack getGrindingResult(int Par1)
    {
        return (ItemStack)RockGrinderList.get(Integer.valueOf(Par1));
    }
 
    
    // Making The Recipe List
    
    public Map <Integer, ItemStack> getGrindingList()
    {
        return RockGrinderList;
    }
    
    
    // Getting Experience From Smelting
    
    public float getExperience(int Par1)
    {
        return this.RockGrinderExperience.containsKey(Integer.valueOf(Par1)) ? ((Float)this.RockGrinderExperience.get(Integer.valueOf(Par1))).floatValue() : 0.0F;
    }   
}
