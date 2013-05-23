package TreviModdingCrew.Utilities.Container;
 
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import TreviModdingCrew.Utilities.Recipes.RecipeRockGrinder;
import TreviModdingCrew.Utilities.Tile.TileEntityRockGrinder;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerRockGrinder extends Container
{
    // Declaration 
    
    private TileEntityRockGrinder RockGrinder;
    
    private int LastCookTime;
    private int LastBurnTime;
    private int LastItemBurnTime;
 
    
    // Adding The Slots
    
    public ContainerRockGrinder(InventoryPlayer InventoryPlayer, TileEntityRockGrinder TileEntityRockGrinder)
    {
        LastCookTime = 0;
        LastBurnTime = 0;
        LastItemBurnTime = 0;
        
        RockGrinder = TileEntityRockGrinder;
        
        addSlotToContainer(new Slot(TileEntityRockGrinder, 0, 56, 17));
        addSlotToContainer(new Slot(TileEntityRockGrinder, 1, 56, 53));
        addSlotToContainer(new SlotFurnace(InventoryPlayer.player, TileEntityRockGrinder, 2, 116, 35));
        
        int Var1;

        for (Var1 = 0; Var1 < 3; ++Var1)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(InventoryPlayer, j + Var1 * 9 + 9, 8 + j * 18, 84 + Var1 * 18));
            }
        }

        for (Var1 = 0; Var1 < 9; ++Var1)
        {
            this.addSlotToContainer(new Slot(InventoryPlayer, Var1, 8 + Var1 * 18, 142));
        }
    }
 
         
    // On Crafting Matrix
    
    public void addCraftingToCrafters(ICrafting ICrafting)
    {
        super.addCraftingToCrafters(ICrafting);
        ICrafting.sendProgressBarUpdate(this, 0, RockGrinder.CookTime);
        ICrafting.sendProgressBarUpdate(this, 1, RockGrinder.BurnTime);
        ICrafting.sendProgressBarUpdate(this, 2, RockGrinder.ItemBurnTime);
    }


    // Checks The Changes In Container
    
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int Var1 = 0; Var1 <  crafters.size(); ++Var1)
        {
            ICrafting icrafting = (ICrafting) crafters.get(Var1);

            if (LastCookTime != RockGrinder.CookTime)
            {
                icrafting.sendProgressBarUpdate(this, 0, RockGrinder.CookTime);
            }

            if (LastBurnTime != RockGrinder.BurnTime)
            {
                icrafting.sendProgressBarUpdate(this, 1, RockGrinder.BurnTime);
            }

            if ( LastItemBurnTime != RockGrinder.ItemBurnTime)
            {
                icrafting.sendProgressBarUpdate(this, 2, RockGrinder.ItemBurnTime);
            }
        }

        LastCookTime = RockGrinder.CookTime;
        LastBurnTime = RockGrinder.BurnTime;
        LastItemBurnTime = RockGrinder.ItemBurnTime;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int Par1, int Par2)
    {
        if (Par1 == 0)
        {
            RockGrinder.CookTime = Par2;
        }

        if (Par1 == 1)
        {
            RockGrinder.BurnTime = Par2;
        }

        if (Par1 == 2)
        {
            RockGrinder.ItemBurnTime = Par2;
        }
    }

    public boolean canInteractWith(EntityPlayer EntityPlayer)
    {
        return RockGrinder.isUseableByPlayer(EntityPlayer);
    }
 
    // Activation Shift Clicking 
    
    @Override
    public ItemStack transferStackInSlot(EntityPlayer EntityPlayer, int Slotnumber)
    {
        ItemStack Items = null;
        Slot Slot = (Slot)inventorySlots.get(Slotnumber);
 
        if (Slot != null && Slot.getHasStack())
        {
            ItemStack ItemStack = Slot.getStack();
            
            Items = ItemStack.copy();
 
            if (Slotnumber == 2)
            {
                if (!mergeItemStack(ItemStack, 3, 39, true))
                {
                    return null;
                }
 
                Slot.onSlotChange(ItemStack, Items);
            }
            
            else if (Slotnumber == 1 || Slotnumber == 0)
            {
                if (!mergeItemStack(ItemStack, 3, 39, false))
                {
                    return null;
                }
            }
            
            else if (RecipeRockGrinder.Grinding().getGrindingResult(ItemStack.getItem().itemID) != null)
            {
                if (!mergeItemStack(ItemStack, 0, 1, false))
                {
                    return null;
                }
            }
            
            else if (TileEntityRockGrinder.isItemFuel(ItemStack))
            {
                if (!mergeItemStack(ItemStack, 1, 2, false))
                {
                    return null;
                }
            }
            
            else if (Slotnumber >= 3 && Slotnumber < 30)
            {
                if (!mergeItemStack(ItemStack, 30, 39, false))
                {
                    return null;
                }
            }
            
            else if (Slotnumber >= 30 && Slotnumber < 39 && !mergeItemStack(ItemStack, 3, 30, false))
            {
                return null;
            }
 
            if (ItemStack.stackSize == 0)
            {
                Slot.putStack(null);
            }
            
            else
            {
                Slot.onSlotChanged();
            }
 
            if (ItemStack.stackSize == Items.stackSize)
            {
                return null;
            }
            
            Slot.onPickupFromSlot(EntityPlayer, Items);
        }
 
        return Items;
    }
}