package TreviModdingCrew.Utilities.Container;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import TreviModdingCrew.Utilities.Common.Main;
import TreviModdingCrew.Utilities.Handler.RockCutHandler;

public class ContainerRockCutter extends Container 
{
    private World worldObject;
    
    private int Par1;
    private int Par2;
    private int Par3;
    
    public InventoryCrafting CraftMatrix = new InventoryCrafting(this, 3, 3);
    public IInventory CraftResult = new InventoryCraftResult();
    
    public ContainerRockCutter(InventoryPlayer InventoryPlayer, World World, int Var1, int Var2, int Var3)
    {
        
        worldObject = World;
        
        Par1 = Var1;
        Par2 = Var2;
        Par3 = Var3;
        
        addSlotToContainer(new SlotCrafting(InventoryPlayer.player, CraftMatrix, CraftResult, 0, 124, 35));
       
        int Var6;
        int Var7;
        
        for(Var6 = 0; Var6 <3; ++Var6)
        {
            for(Var7 = 0; Var7 <3; ++Var7)
            {
                addSlotToContainer(new Slot(CraftMatrix, Var7 + Var6 * 3, 30 + Var7 * 18, 17 + Var6 * 18));
            }
        }
        
        for (Var6 = 0; Var6 < 3; ++Var6)
        {
            for (Var7 = 0; Var7 < 9; ++Var7)
            {
                addSlotToContainer(new Slot(InventoryPlayer, Var7 + Var6 * 9 + 9, 8 + Var7 * 18, 84 + Var6 * 18));
            }
        }
        
        for (Var6 = 0; Var6 < 9; ++Var6)
        {
            addSlotToContainer(new Slot(InventoryPlayer, Var6, 8 + Var6 * 18, 142));
        }
        
        onCraftMatrixChanged(CraftMatrix);
    }
    
    @Override
    public boolean canInteractWith(EntityPlayer EntityPlayer)
    {
        return worldObject.getBlockId(Par1, Par2, Par3) != Main.RockCutter.blockID ? false : EntityPlayer.getDistanceSq((double)Par1 + 0.5D, (double)Par2 + 0.5D, (double)Par3 + 0.5D) <= 64.0D;
    }

    public void onCraftMatrixChanged(IInventory IInventory)
    {
        ItemStack Stacks = RockCutHandler.getInstance().findMatchingRecipe(CraftMatrix, worldObject);
        CraftResult.setInventorySlotContents(0, ItemStack.areItemStacksEqual(Stacks, new ItemStack(Block.planks, 2, 0)) ? null : Stacks);
    }
    
    public void onCraftGuiClosed(EntityPlayer EntityPlayer)
    {
        super.onCraftGuiClosed(EntityPlayer);
            
        if(!worldObject.isRemote)
        {
            for (int Var2 = 0; Var2 < 9; ++Var2)
            {
                ItemStack Var3 = CraftMatrix.getStackInSlotOnClosing(Var2);
                if (Var3 != null)
                {
                    EntityPlayer.dropPlayerItem(Var3);
                }
            }
        }
    }
    
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int Par2)
    {
        ItemStack Var3 = null;
        Slot Var4 = (Slot)this.inventorySlots.get(Par2);

        if (Var4 != null && Var4.getHasStack())
        {
            ItemStack Var5 = Var4.getStack();
            Var3 = Var5.copy();

            if (Par2 == 0)
            {
                if (!mergeItemStack(Var5, 10, 46, true))
                {
                    return null;
                }

                Var4.onSlotChange(Var5, Var3);
            }
            
            else if (Par2 >= 10 && Par2 < 37)
            {   
                if (!mergeItemStack(Var5, 37, 46, false))
                {
                    return null;
                }
            }
            
            else if (Par2 >= 37 && Par2 < 46)
            {
                if (!this.mergeItemStack(Var5, 10, 37, false))
                {
                    return null;
                }
            }
            
            else if (!mergeItemStack(Var5, 10, 46, false))
            {
                return null;
            }

            if (Var5.stackSize == 0)
            {
                Var4.putStack((ItemStack)null);
            }
            
            else
            {
                Var4.onSlotChanged();
            }

            if (Var5.stackSize == Var3.stackSize)
            {
                return null;
            }

            Var4.onPickupFromSlot(par1EntityPlayer, Var5);
            
        }
             
        return Var3;
    }
}
