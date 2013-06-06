package TreviModdingCrew.Utilities.Tile;

import java.util.Iterator;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

import TreviModdingCrew.Utilities.Block.BlockTrashCan;

public class TileEntityTrashCan extends TileEntity implements IInventory
{
    private ItemStack[] ChestContents = new ItemStack[36];

    public boolean AdjacentChestChecked = false;

    public TileEntityTrashCan AdjacentChestZNeg;
    public TileEntityTrashCan AdjacentChestXPos;
    public TileEntityTrashCan AdjacentChestXNeg;
    public TileEntityTrashCan AdjacentChestZPosition;

    public float LidAngle;
    public float PrevLidAngle;

    public int NumUsingPlayers;
    public int TicksSinceSync;
    public int CheckTime = -1;
   
    private String InventoryName;

    public int getSizeInventory()
    {
        return 27;
    }

    public ItemStack getStackInSlot(int Par1)
    {
        return ChestContents[Par1];
    }

    public ItemStack decrStackSize(int Par1, int Par2)
    {
        if (ChestContents[Par1] != null)
        {
            ItemStack ItemStack;

            if (ChestContents[Par1].stackSize <= Par2)
            {
                ItemStack = ChestContents[Par1];
                ChestContents[Par1] = null;
                onInventoryChanged();
               
                return ItemStack;
            }
            
            else
            {
                ItemStack = ChestContents[Par1].splitStack(Par2);

                if (ChestContents[Par1].stackSize == 0)
                {
                    ChestContents[Par1] = null;
                }

                onInventoryChanged();
               
                return ItemStack;
            }
        }
       
        else
        {
            return null;
        }
    }

    public ItemStack getStackInSlotOnClosing(int Par1)
    {
        if (ChestContents[Par1] != null)
        {
            ItemStack ItemStack = ChestContents[Par1];
            ChestContents[Par1] = null;
            
            return ItemStack;
        }
        
        else
        {
            return null;
        }
    }

    public void setInventorySlotContents(int Par1, ItemStack ItemStack)
    {
        ChestContents[Par1] = ItemStack;

        if (ItemStack != null && ItemStack.stackSize > getInventoryStackLimit())
        {
            ItemStack.stackSize = getInventoryStackLimit();
        }

        onInventoryChanged();
    }

    public String getInvName()
    {
        return isInvNameLocalized() ? InventoryName : "Trash Can";
    }

    public boolean isInvNameLocalized()
    {
        return InventoryName != null && InventoryName.length() > 0;
    }

    public void func_94043_a(String Par1Str)
    {
        InventoryName = Par1Str;
    }
 
    public void readFromNBT(NBTTagCompound NBTTagCompound)
    {
        super.readFromNBT(NBTTagCompound);
        
        NBTTagList nbttaglist = NBTTagCompound.getTagList("Item");
        ChestContents = new ItemStack[getSizeInventory()];

        if (NBTTagCompound.hasKey("Trash Can"))
        {
            InventoryName = NBTTagCompound.getString("Trash Can");
        }

        for (int Var1 = 0; Var1 < nbttaglist.tagCount(); ++Var1)
        {
            NBTTagCompound Tag = (NBTTagCompound)nbttaglist.tagAt(Var1);
            
            int Var2 = Tag.getByte("Slot") & 255;

            if (Var2 >= 0 && Var2 < ChestContents.length)
            {
                ChestContents[Var2] = ItemStack.loadItemStackFromNBT(Tag);
            }
        }
    }

    public void writeToNBT(NBTTagCompound Par1NBTTagCompound)
    {
        super.writeToNBT(Par1NBTTagCompound);
        NBTTagList nbttaglist = new NBTTagList();

        for (int Var1 = 0; Var1 < ChestContents.length; ++Var1)
        {
            if (ChestContents[Var1] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)Var1);
                ChestContents[Var1].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        Par1NBTTagCompound.setTag("Item", nbttaglist);

        if (isInvNameLocalized())
        {
            Par1NBTTagCompound.setString("Trash Can", InventoryName);
        }
    }

    public int getInventoryStackLimit()
    {
        return 64;
    }

    public boolean isUseableByPlayer(EntityPlayer Par1EntityPlayer)
    {
        return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) != this ? false : Par1EntityPlayer.getDistanceSq((double)xCoord + 0.5D, (double)yCoord + 0.5D, (double)zCoord + 0.5D) <= 64.0D;
    }

    public void updateContainingBlockInfo()
    {
        super.updateContainingBlockInfo();
        AdjacentChestChecked = false;
    }

    private void func_90009_a(TileEntityTrashCan Par1TileEntityChest, int Par2)
    {
        if (Par1TileEntityChest.isInvalid())
        {
            AdjacentChestChecked = false;
        }
        
        else if (AdjacentChestChecked)
        {
            switch (Par2)
            {
                case 0: if (AdjacentChestZPosition != Par1TileEntityChest)
                        {
                            AdjacentChestChecked = false;
                        }
                break;       

                case 1: if (AdjacentChestXNeg != Par1TileEntityChest)
                        {
                            AdjacentChestChecked = false;
                        }
                break;
               
                case 2: if (AdjacentChestZNeg != Par1TileEntityChest)
                        {
                            AdjacentChestChecked = false;
                        }
                break;
                
                case 3: if (AdjacentChestXPos != Par1TileEntityChest)
                        {
                            AdjacentChestChecked = false;
                        }
                break;
                
                }
            }
        }

    public void checkForAdjacentChests()
    {
        if (!AdjacentChestChecked)
        {
            AdjacentChestChecked = true;
            AdjacentChestZNeg = null;
            AdjacentChestXPos = null;
            AdjacentChestXNeg = null;
            AdjacentChestZPosition = null;

            if (func_94044_a(xCoord - 1, yCoord, zCoord))
            {
                AdjacentChestXNeg = (TileEntityTrashCan)worldObj.getBlockTileEntity(xCoord - 1, yCoord, zCoord);
            }

            if (func_94044_a(xCoord + 1, yCoord, zCoord))
            {
                AdjacentChestXPos = (TileEntityTrashCan)worldObj.getBlockTileEntity(xCoord + 1, yCoord, zCoord);
            }

            if (func_94044_a(xCoord, yCoord, zCoord - 1))
            {
                AdjacentChestZNeg = (TileEntityTrashCan)worldObj.getBlockTileEntity(xCoord, yCoord, zCoord - 1);
            }

            if (func_94044_a(xCoord, yCoord, zCoord + 1))
            {
                AdjacentChestZPosition = (TileEntityTrashCan)worldObj.getBlockTileEntity(xCoord, yCoord, zCoord + 1);
            }

            if (AdjacentChestZNeg != null)
            {
                AdjacentChestZNeg.func_90009_a(this, 0);
            }

            if (AdjacentChestZPosition != null)
            {
                AdjacentChestZPosition.func_90009_a(this, 2);
            }

            if (AdjacentChestXPos != null)
            {
                AdjacentChestXPos.func_90009_a(this, 1);
            }

            if (AdjacentChestXNeg != null)
            {
                AdjacentChestXNeg.func_90009_a(this, 3);
            }
        }
    }

    private boolean func_94044_a(int Par1, int Par2, int Par3)
    {
        Block Var1 = Block.blocksList[worldObj.getBlockId(Par1, Par2, Par3)];
        return Var1 != null && Var1 instanceof BlockTrashCan ? ((BlockTrashCan)Var1).Value == CheckBlockTime() : false;
    }

    
    public void updateEntity()
    {
        super.updateEntity();
        
        checkForAdjacentChests();
        ++TicksSinceSync;
        
        float Var1;

        if (!worldObj.isRemote)
        {
            if(worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord))
            {  
                for(int i=0; i<27; i++)
                {
                    setInventorySlotContents(i, null);
                }
            }
        }
        
        if (!worldObj.isRemote && NumUsingPlayers != 0 && (TicksSinceSync + xCoord + yCoord + zCoord) % 200 == 0)
        {
            NumUsingPlayers = 0;
            Var1 = 5.0F;
            List list = worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getAABBPool().getAABB((double)((float)xCoord - Var1), (double)((float)yCoord - Var1), (double)((float)zCoord - Var1), (double)((float)(xCoord + 1) + Var1), (double)((float)(yCoord + 1) + Var1), (double)((float)(zCoord + 1) + Var1)));
            Iterator iterator = list.iterator();

            while (iterator.hasNext())
            {
                EntityPlayer entityplayer = (EntityPlayer)iterator.next();

                if (entityplayer.openContainer instanceof ContainerChest)
                {
                    IInventory iinventory = ((ContainerChest)entityplayer.openContainer).getLowerChestInventory();

                    if (iinventory == this || iinventory instanceof InventoryLargeChest && ((InventoryLargeChest)iinventory).isPartOfLargeChest(this))
                    {
                        ++NumUsingPlayers;
                    }
                }
            }
        }

        PrevLidAngle = LidAngle;
        Var1 = 0.1F;
        double Par1;

        if (NumUsingPlayers > 0 && LidAngle == 0.0F && AdjacentChestZNeg == null && AdjacentChestXNeg == null)
        {
            double d1 = (double)xCoord + 0.5D;
            Par1 = (double)zCoord + 0.5D;

            if (AdjacentChestZPosition != null)
            {
                Par1 += 0.5D;
            }

            if (AdjacentChestXPos != null)
            {
                d1 += 0.5D;
            } 
        }

        if (NumUsingPlayers == 0 && LidAngle > 0.0F || NumUsingPlayers > 0 && LidAngle < 1.0F)
        {
            float f1 = LidAngle;

            if (NumUsingPlayers > 0)
            {
                LidAngle += Var1;
            }
            else
            {
                LidAngle -= Var1;
            }

            if (LidAngle > 1.0F)
            {
                LidAngle = 1.0F;
            }

            float Par2 = 0.5F;

            if (LidAngle < Par2 && f1 >= Par2 && AdjacentChestZNeg == null && AdjacentChestXNeg == null)
            {
                Par1 = (double)xCoord + 0.5D;
                
                double Par3 = (double)zCoord + 0.5D;

                if (AdjacentChestZPosition != null)
                {
                    Par3 += 0.5D;
                }

                if (AdjacentChestXPos != null)
                {
                    Par1 += 0.5D;
                }  
            }

            if (LidAngle < 0.0F)
            {
                LidAngle = 0.0F;
            }
        }
    }

    public boolean receiveClientEvent(int Par1, int Par2)
    {
        if (Par1 == 1)
        {
            NumUsingPlayers = Par2;
            return true;
        }
        
        else
        {
            return super.receiveClientEvent(Par1, Par2);
        }
    }

    public void openChest()
    {
        if (NumUsingPlayers < 0)
        {
            NumUsingPlayers = 0;
        }

        ++NumUsingPlayers;
        worldObj.addBlockEvent(xCoord, yCoord, zCoord, getBlockType().blockID, 1, NumUsingPlayers);
        worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord, zCoord, getBlockType().blockID);
        worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord - 1, zCoord, getBlockType().blockID);
    }

    public void closeChest()
    {
        if (getBlockType() != null && getBlockType() instanceof BlockTrashCan)
        {
            --NumUsingPlayers;
            worldObj.addBlockEvent(xCoord, yCoord, zCoord, getBlockType().blockID, 1, NumUsingPlayers);
            worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord, zCoord, getBlockType().blockID);
            worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord - 1, zCoord, getBlockType().blockID);
        }
    }

    public boolean isStackValidForSlot(int Par1, ItemStack ItemStack)
    {
        return true;
    }

    public void invalidate()
    {
        super.invalidate();
       
        updateContainingBlockInfo();
        checkForAdjacentChests();
    }

    public int CheckBlockTime()
    {
        if (CheckTime == -1)
        {
            if (worldObj == null || !(getBlockType() instanceof BlockTrashCan))
            {
                return 0;
            }

            CheckTime = ((BlockTrashCan)getBlockType()).Value;
        }

        return CheckTime;
    }
}