package TreviModdingCrew.Utilities.Handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import TreviModdingCrew.Utilities.Recipes.RecipeRockCutter;

import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.world.World;

public class RockCutHandler 
{
    // Declaration
    
	private static final RockCutHandler Instance = new RockCutHandler();
	
	private List <IRecipe> Recipes = new ArrayList <IRecipe>();
	
	public static final RockCutHandler getInstance()
	{
		return Instance;
	}
	
	
	// Constructor
	
	private RockCutHandler()
	{
		Collections.sort(Recipes, new RecipeRockCutter(this));
	}
	
	
	// Makes A List
	
	public List <IRecipe> getRecipeList()
	{
		return Recipes;
	}
	
	
	// Adding A Shaped Recipe
	
	public void addRecipe(ItemStack ItemStack, Object ... inputArray)
	{
	     String Var3 = "";
	         
	     int Var4 = 0;
	     int Var5 = 0;
	     int Var6 = 0;
	     int Var9;
	         
	     if (inputArray[Var4] instanceof String[])
	     {
    	     String[] Var7 = (String[])((String[])inputArray[Var4++]);
    	     String[] Var8 = Var7;
    	     Var9 = Var7.length;
    	                 
    	     for (int var10 = 0; var10 < Var9; ++var10)
    	     {
        	     String Var11 = Var8[var10];
        	     ++Var6;
        	     Var5 = Var11.length();
        	     Var3 = Var3 + Var11;
    	     }
	     }
	         
	         
	     else  
	     {
	         while (inputArray[Var4] instanceof String)
	         {  
	             String Var13 = (String)inputArray[Var4++];
	             ++Var6;
	             Var5 = Var13.length();
	              Var3 = Var3 + Var13;
	         }
	    }
	         
	    HashMap <Character, ItemStack> Var14;
	         
	    for (Var14 = new HashMap <Character, ItemStack>(); Var4 < inputArray.length; Var4 += 2)  
	    {
	         Character Var16 = (Character)inputArray[Var4];
	         ItemStack Var17 = null;
	                 
	         if (inputArray[Var4 + 1] instanceof Item)
	         {
	             Var17 = new ItemStack((Item)inputArray[Var4 + 1]);
	         }
	                 
	         else if (inputArray[Var4 + 1] instanceof Block)
	         {
	             Var17 = new ItemStack((Block)inputArray[Var4 + 1], 1, -1);
	         }
	           
	         else if (inputArray[Var4 + 1] instanceof ItemStack)
	         {
	              Var17 = (ItemStack)inputArray[Var4 + 1];
	         }
	                
	         Var14.put(Var16, Var17);
	    }
	         
	    ItemStack[] Var15 = new ItemStack[Var5 * Var6];
	        
	    for (Var9 = 0; Var9 < Var5 * Var6; ++Var9)
	    {
	         char var18 = Var3.charAt(Var9);
	         if (Var14.containsKey(Character.valueOf(var18)))
	         {
	             Var15[Var9] = ((ItemStack)Var14.get(Character.valueOf(var18))).copy();
	         }
	         
	         else
	         {
	             Var15[Var9] = null;
	         }
	    }
	         
	    Recipes.add(new ShapedRecipes(Var5, Var6, Var15, ItemStack));
	}
	
	
	// Adding Shapeless Recipe
	
	void addShapelessRecipe(ItemStack ItemStack, Object ... inputArray)
	{
	    ArrayList <ItemStack> Var3 = new ArrayList <ItemStack>();
	    Object[] Var4 = inputArray;
	    int Var5 = inputArray.length;
	    for (int Var6 = 0; Var6 < Var5; ++Var6)
	    {
	        Object Var7 = Var4[Var6];

	        if (Var7 instanceof ItemStack)
	        {
	            Var3.add(((ItemStack)Var7).copy());
	        }
	                 
	        else if (Var7 instanceof Item)
	        {
	            Var3.add(new ItemStack((Item)Var7));
	        }
	                
	        else
	        {
	            if (!(Var7 instanceof Block))
	            {
	                LogHandler.Log("Invalid Recipe", 1);
	            }
	                         
	            Var3.add(new ItemStack((Block)Var7));
	                 
	        }
	    }
	         
	    Recipes.add(new ShapelessRecipes(ItemStack, Var3));
	}
	
	
	// Checking If Items In Table 
	
	public ItemStack findMatchingRecipe(InventoryCrafting InventoryCrafting, World World)
	{
	    int Var2 = 0;
	         
	    ItemStack Var3 = null;
	    ItemStack Var4 = null;
	         
	    for (int Var5 = 0; Var5 < InventoryCrafting.getSizeInventory(); ++Var5)
	    {
	         ItemStack Var6 = InventoryCrafting.getStackInSlot(Var5);
	         
	         if (Var6 != null)
             {
                 if (Var2 == 0)
                 {
                         Var3 = Var6;
                 }
                 if (Var2 == 1)
                 {
                         Var4 = Var6;
                 }
                
                 ++Var2;
             }
	    }
	         
	    if (Var2 == 2 && Var3.itemID == Var4.itemID && Var3.stackSize == 1 && Var4.stackSize == 1 && Item.itemsList[Var3.itemID].isDamageable())
        {
             Item Var10 = Item.itemsList[Var3.itemID];
             
             int Var12 = Var10.getMaxDamage() - Var3.getItemDamageForDisplay();
             int Var7 = Var10.getMaxDamage() - Var4.getItemDamageForDisplay();
             int Var8 = Var12 + Var7 + Var10.getMaxDamage() * 10 / 100;
             int Var9 = Var10.getMaxDamage() - Var8;
             
             if (Var9 < 0)
             {
                 Var9 = 0;
             }
             
             return new ItemStack(Var3.itemID, 1, Var9);
        }
	         
	    else
        {
             Iterator <IRecipe> Var11 = Recipes.iterator();
             IRecipe Var13;
            
             do
             {
                 if (!Var11.hasNext())
                 {
                         return null;
                 }
                 
                 Var13 = (IRecipe)Var11.next();
             }
             
             while (!Var13.matches(InventoryCrafting, World));
             {
                 
             }
             
             return Var13.getCraftingResult(InventoryCrafting);
        }
	}
}
