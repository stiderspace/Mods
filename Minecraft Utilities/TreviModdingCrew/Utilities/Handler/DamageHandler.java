package TreviModdingCrew.Utilities.Handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.ICraftingHandler;

public class DamageHandler implements ICraftingHandler
{
	// Let Something Stay In Crafting Table
	
	@Override
	public void onCrafting(EntityPlayer EntityPlayer, ItemStack ItemStack, IInventory IInventory) 
	{
	    for(int I=0; I < IInventory.getSizeInventory(); I++)
	    {         
	    	if(IInventory.getStackInSlot(I) != null)
	    	{
	    		ItemStack T = IInventory.getStackInSlot(I);
	    		
	    		if(T.getItem() != null && T.getItem() == Item.shears)
	    		{
	    			ItemStack S = new ItemStack(Item.shears, 2, (T.getItemDamage() + 5));
	    			IInventory.setInventorySlotContents(I, S);
	    		}
	    	}  
	    }
	}

	
	// Doing Custom Stuff With Furnace
	
	@Override
	public void onSmelting(EntityPlayer EntityPlayer, ItemStack ItemStack) 
	{	
	}
}
