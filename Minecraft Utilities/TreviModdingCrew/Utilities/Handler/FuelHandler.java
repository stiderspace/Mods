package TreviModdingCrew.Utilities.Handler;

import java.util.Random;

import TreviModdingCrew.Utilities.Common.Main;

import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.IFuelHandler;

public class FuelHandler implements IFuelHandler 
{
    // Declaration 
    
    Random Var1 = new Random();
    
    
    // Setting The Burn Value
    
    @Override
    public int getBurnTime(ItemStack ItemStack) 
    {
        if(ItemStack.itemID == Main.Filter.itemID)
        {
            return Var1.nextInt(350);
        } 
        
        else return 0;
    }
}
