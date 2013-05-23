package TreviModdingCrew.Utilities.Proxy;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import TreviModdingCrew.Utilities.Container.ContainerRockCutter;
import TreviModdingCrew.Utilities.Container.ContainerRockGrinder;
import TreviModdingCrew.Utilities.Gui.GuiRockCutter;
import TreviModdingCrew.Utilities.Gui.GuiRockGrinder;
import TreviModdingCrew.Utilities.Tile.TileEntityRockGrinder;

import cpw.mods.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler 
{
    // Loading The Render For Throwable Items
        
    public void LoadRenderers()
    {

    }
        
        
    // The Method For The Gui Server Element
        
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer EntityPlayer, World World, int Var1, int Var2, int Var3)
    {
        TileEntity TileEntity = World.getBlockTileEntity(Var1, Var2, Var3);

        if(TileEntity != null)
        {
            switch(ID)
            {
                case 0: return new ContainerRockGrinder(EntityPlayer.inventory, (TileEntityRockGrinder)TileEntity);
                    
                default: return null;
            }
        }
            
        if(ID == 1)
        {
            return new ContainerRockCutter(EntityPlayer.inventory, World, Var1, Var2, Var3);
        }
            
        return TileEntity;  
        
    }
      
    
    // The Method For The Gui Client Element
        
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer EntityPlayer, World World, int Var1, int Var2, int Var3)
    {
        TileEntity TileEntity = World.getBlockTileEntity(Var1, Var2, Var3);

        if(TileEntity != null)
        {
            switch(ID)
            {
                case 0: return new GuiRockGrinder(EntityPlayer.inventory, (TileEntityRockGrinder)TileEntity);
                
                default: return null; 
            }
        }
            
        if(ID == 1)
        {
            return new GuiRockCutter(EntityPlayer.inventory, World, Var1, Var2, Var3);
        }
        
        return TileEntity;  
    }
        
        
    // Removing Recipes
        
    public void RemoveRecipe(ItemStack ItemStack)
    {
        ItemStack RecipeResult = null;
        ArrayList Recipes = (ArrayList) CraftingManager.getInstance().getRecipeList();
            
        for (int Scan = 0; Scan < Recipes.size(); Scan++)
        {
            IRecipe tmpRecipe = (IRecipe) Recipes.get(Scan);
            RecipeResult = tmpRecipe.getRecipeOutput();
               
            if (RecipeResult != null) 
            {
                if (RecipeResult.itemID == ItemStack.itemID && RecipeResult.getItemDamage() == ItemStack.getItemDamage())
                {
                    Recipes.remove(Scan);
                    Scan--;
                }
            }
        }
    }


    public void KeyBinding()
    {
       
    }
}

