package TreviModdingCrew.Utilities.Tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.ForgeDummyContainer;
import net.minecraftforge.common.ISidedInventory;

import TreviModdingCrew.Utilities.Recipes.RecipeRockGrinder;
import TreviModdingCrew.Utilities.Util.PowerProviderAdvanced;

import buildcraft.api.power.IPowerProvider;
import buildcraft.api.power.IPowerReceptor;

public class TileEntityRockGrinder extends TileEntity implements ISidedInventory, IPowerReceptor
{   
    // Declaration
    
    private ItemStack ItemStacks[];
   
    public int BurnTime;
    public int ItemBurnTime;
    public int CookTime;
    
    public static int Speed;
    
    private boolean IsActive;
    
    private float OldPower;
    
    public PowerProviderAdvanced MyProvider;
    
    
    // Tile Entity Configuration
    
    public TileEntityRockGrinder()
    {
        ItemStacks = new ItemStack[3];
        BurnTime = 0;
        ItemBurnTime = 0;
        CookTime = 0;
        Speed = 1;
        
        MyProvider = new PowerProviderAdvanced();
        MyProvider.configure(100, 8000);
    }
    
  
    // Getting Inventory Size
    
    public int getSizeInventory()
    {
        return ItemStacks.length;
    }
 

    // Getting Info Of What Is Inside Furnace
    
    public ItemStack getStackInSlot(int Par1)
    {
        return ItemStacks[Par1];
    }


    // Decreasing The Stacksize
    
    public ItemStack decrStackSize(int Par1, int Par2)
    {
        if (ItemStacks[Par1] != null)
        {
            if (ItemStacks[Par1].stackSize <= Par2)
            {
                ItemStack Items = ItemStacks[Par1];
                ItemStacks[Par1] = null;
                
                return Items;
            }
 
            ItemStack Itemstack = ItemStacks[Par1].splitStack(Par2);
 
            if (ItemStacks[Par1].stackSize == 0)
            {
                ItemStacks[Par1] = null;
            }
 
            return Itemstack;
        }
        
        else
        {
            return null;
        }
    }
 
    
    // Saving The Tile Entity
    
    public ItemStack getStackInSlotOnClosing(int Par1)
    {
        if (ItemStacks[Par1] != null)
        {
            ItemStack Itemstack = ItemStacks[Par1];
            ItemStacks[Par1] = null;
           
            return Itemstack;
        }
        
        else
        {
            return null;
        }
    }
 
    
    // Getting Stacksize 
    
    public void setInventorySlotContents(int Par1, ItemStack ItemStack)
    {
        ItemStacks[Par1] = ItemStack;
 
        if (ItemStack != null && ItemStack.stackSize > getInventoryStackLimit())
        {
            ItemStack.stackSize = getInventoryStackLimit();
        }
    }
 
    
    // Getting Container Name
    
    public String getInvName()
    {
        return "container.RockGrinder";
    }
    
    
    // Updating The Block
    
    private void updateBlock()
    {
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }
 

    // Reading From Tag Compound
    
    public void readFromNBT(NBTTagCompound NBTTagCompound)
    {           
        NBTTagList nbttaglist = NBTTagCompound.getTagList("Items");
        ItemStacks = new ItemStack[getSizeInventory()];
 
        for (int Var1 = 0; Var1 < nbttaglist.tagCount(); Var1++)
        {
            NBTTagCompound nbttagcompound = (NBTTagCompound)nbttaglist.tagAt(Var1);
            
            byte Var2 = nbttagcompound.getByte("Slot");
 
            if (Var2 >= 0 && Var2 < ItemStacks.length)
            {
                ItemStacks[Var2] = ItemStack.loadItemStackFromNBT(nbttagcompound);
            }
        }
    
        BurnTime = NBTTagCompound.getShort("BurnTime");
        CookTime = NBTTagCompound.getShort("CookTime");
        Speed = NBTTagCompound.getInteger("Speed"); 
        ItemBurnTime = getItemBurnTime(ItemStacks[1]);
        
        MyProvider.setEnergyStored(NBTTagCompound.getFloat("EnergyStored"));
        
        super.readFromNBT(NBTTagCompound);
    }
 
    
    // Writing To Tag Compound

    public void writeToNBT(NBTTagCompound NBTTagCompound)
    {  
        NBTTagCompound.setShort("BurnTime", (short)BurnTime);
        NBTTagCompound.setShort("CookTime", (short)CookTime);
        NBTTagCompound.setInteger("Speed", (int)Speed);
        
        NBTTagCompound.setFloat("EnergyStored", MyProvider.getEnergyStored());
        
        NBTTagList NBTTagList = new NBTTagList();
 
        for (int Var1 = 0; Var1 < ItemStacks.length; Var1++)
        {
            if (ItemStacks[Var1] != null)
            {
                NBTTagCompound Tag = new NBTTagCompound();
                Tag.setByte("Slot", (byte)Var1);
                
                ItemStacks[Var1].writeToNBT(Tag);
                
                NBTTagList.appendTag(Tag);
            }
        }
 
        NBTTagCompound.setTag("Items", NBTTagList);
        
        super.writeToNBT(NBTTagCompound);
    }
 
    
    // Stacksize Limit
    
    public int getInventoryStackLimit()
    {
        return 64;
    }
 
    
    // Furnace Speed
    
    public int getCookProgressScaled(int Par1)
    {
        return (CookTime * Par1) / 200;
    }
 
    public int getBurnTimeRemainingScaled(int Par1)
    {
        if (ItemBurnTime == 0)
        {
            ItemBurnTime = 200;
        }
 
        return (BurnTime * Par1) / ItemBurnTime;
    }
 

    // Checking If Furnace Burns
    
    public boolean isBurning()
    {
        return BurnTime > 0;
    }
 

    // Updating Entity
    
    public void updateEntity()
    {
        boolean Var1 = BurnTime > 0;
        boolean Var2 = false;
        
        if(MyProvider.getEnergyStored() > 0)
        {
            if(worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord))
            {
                if (canGrind())
                {
                    MyProvider.subtractEnergy(Speed);
                    CookTime = CookTime + Speed;
    
                    if (CookTime >= 200)
                    {
                        CookTime = 0;
                        grindItem();
                        Var2 = true;
                    }
                }
            }
        }
        
        else
        {
            if (BurnTime > 0)
            {
                BurnTime = BurnTime - Speed;
                
                if(BurnTime < 0)
                {
                    if(ItemStacks[1] != null)
                    {
                        BurnTime = getItemBurnTime(ItemStacks[1]);
                        ItemStacks[1].stackSize--;
                    }
                } 
            }
    
            if(worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord) || CookTime > 0)
            {
                if (BurnTime == 0 && canGrind())
                {
                    if (ItemStacks[1] != null)
                    {
                        ItemBurnTime = BurnTime = getItemBurnTime(ItemStacks[1]);
        
                        if (BurnTime > 0)
                        {
                            Var2 = true;
    
                            --ItemStacks[1].stackSize;
    
                            if (ItemStacks[1].stackSize == 0)
                            {
                                Item Var3 = ItemStacks[1].getItem().getContainerItem();
                                ItemStacks[1] = Var3 == null ? null : new ItemStack(Var3);
                            }
                        }
                    }
                }
    
                if (isBurning() && canGrind())
                {
                    CookTime = CookTime + Speed;
    
                    if (CookTime >= 200)
                    {
                        CookTime = 0;
                        grindItem();
                        Var2 = true;
                    }
                }
                
                if (Var1 != BurnTime > 0)
                {
                    Var2 = true;
                    validate();
                }
            }
        }
        
        IsActive = isBurning();
       
        if (Var2)
        {
            onInventoryChanged();
            updateBlock();
        }
        
        if(OldPower != MyProvider.getEnergyStored())
        { 
            updateBlock();
        }
        
        OldPower = MyProvider.getEnergyStored();
        
        if(ItemStacks[0] == null)
        {
            CookTime = 0;
        }
    }


    // Can Grinder Grind?
    
    private boolean canGrind()
    {
        if (ItemStacks[0] == null)
        {
            return false;
        }
 
        ItemStack ItemStack = RecipeRockGrinder.Grinding().getGrindingResult(ItemStacks[0].getItem().itemID);
 
        if (ItemStack == null)
        {
            return false;
        }
 
        if (ItemStacks[2] == null)
        {
            return true;
        }
 
        if (!ItemStacks[2].isItemEqual(ItemStack))
        {
            return false;
        }
 
        if (ItemStacks[2].stackSize < getInventoryStackLimit() && ItemStacks[2].stackSize < ItemStacks[2].getMaxStackSize())
        {
            return true;
        }
 
        return ItemStacks[2].stackSize < ItemStack.getMaxStackSize();
    }
 
    
    // Converting Your Material
    
    public void grindItem()
    {
        if (canGrind())
        {
            ItemStack Var1 = RecipeRockGrinder.Grinding().getGrindingResult(ItemStacks[0].getItem().itemID);

            if (ItemStacks[2] == null)
            {
                ItemStacks[2] = Var1.copy();
            }
            
            else if (ItemStacks[2].itemID == Var1.itemID)
            {
               ItemStacks[2].stackSize += Var1.stackSize;
            }
            
            ItemStacks[0].stackSize--;
            
            if (ItemStacks[0].stackSize == 0)
            {
                Item Var2 = ItemStacks[0].getItem().getContainerItem();
                ItemStacks[0] = Var2 == null ? null : new ItemStack(Var2);
                System.out.println("ItemStacks[0].stackSize equals 0");
            }
        }  
    }
 
    
    // Checking If Item Is Fuel
    
    public static boolean isItemFuel(ItemStack ItemStack)
    {
        return getItemBurnTime(ItemStack) > 0;
    }
    

    // Calculating The Amount Of Ticks That The Furnace Will Supply Heat
    
    public static int getItemBurnTime(ItemStack ItemStack)
    {
        if (ItemStack == null)
        {
            return 0;
        }
 
        int Fuel = ItemStack.getItem().itemID;
        
        if (Fuel == Item.coal.itemID)
        {
            return 1250;
        }
 
        else
        {
            return 0;
        }
    }


    // Making It Usable By Players
   
    public boolean isUseableByPlayer(EntityPlayer EntityPlayer)
    {
        if (worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) != this)
        {
            return false;
        }
 
        return EntityPlayer.getDistanceSq((double)xCoord + 0.5D, (double)yCoord + 0.5D, (double)zCoord + 0.5D) <= 64D;
    }
 
    
    // Lets Something Happen On Opening The Gui
    
    public void openChest()
    {
   
    }
 
    
    // Lets Something Happen On Exiting The Gui
    
    public void closeChest()
    {
        
    }

    
    // Setting The Value For Furnace Activiy
    
    public boolean isActive() 
    {
        return IsActive;
    }

    
    // Checking If Inventory Name Is Localized 
    
    @Override
    public boolean isInvNameLocalized() 
    {
        return true;
    }

    
    // Checking If Item Fits In Slot
    
    @Override
    public boolean isStackValidForSlot(int Var1, ItemStack ItemStack) 
    {
        return Var1 == 2 ? false : (Var1 == 1 ? isItemFuel(ItemStack) : true);
    }

    
    // Setting Sided Inventory Directions
    
    @Override
    public int getStartInventorySide(ForgeDirection Side)
    {
        if (ForgeDummyContainer.legacyFurnaceSides)
        {
            if (Side == ForgeDirection.DOWN) return 1;
            if (Side == ForgeDirection.UP) return 0;
            if (Side == ForgeDirection.SOUTH) return 2;
            
            return 2;
        }
        
        else
        {
            if (Side == ForgeDirection.DOWN) return 1;
            if (Side == ForgeDirection.UP) return 0;
            if (Side == ForgeDirection.SOUTH) return 2;
            
            return 1;
        }
    }
    
    
    // Getting The Size Of The Inventory

    @Override
    public int getSizeInventorySide(ForgeDirection Side)
    {
        return 1;
    }
    
    
    // Adding Data To The Packet
    
    @Override
    public Packet getDescriptionPacket() 
    {
        NBTTagCompound Tag = new NBTTagCompound();
        writeToNBT(Tag);
        
        return new Packet132TileEntityData(xCoord, yCoord, zCoord, 1, Tag);
    }
    
    
    // Sending The Packet
    
    public void onDataPacket(INetworkManager INetworkManager, Packet132TileEntityData Packet132TileEntityData)
    {
        readFromNBT(Packet132TileEntityData.customParam1);
    }


    // Setting What Provides Power
    
    @Override
    public void setPowerProvider(IPowerProvider IPowerProvider)
    {
        
    }

    
    // Getting The Provider Data

    @Override
    public IPowerProvider getPowerProvider()
    {
        return MyProvider;
    }


    // Doing Something With Power
    
    @Override
    public void doWork()
    {
        
    }

    
    // Let A Machine Request Ror Power

    @Override
    public int powerRequest(ForgeDirection ForgeDirection)
    {
        if(MyProvider.getEnergyStored() >= MyProvider.getMaxEnergyStored())
        {
            return 0;
        }
        
        return (int)(MyProvider.getMaxEnergyStored() - MyProvider.getEnergyStored());
    }
}