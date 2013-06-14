package TreviModdingCrew.Utilities.Tile;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityWashingMachine extends TileEntity
{
    // Declaration
    
    private int FirstLaunch;
    
    public boolean CanPutWoolIn = false;
   
    public int TickToWash;
    
    public void updateEntity() 
    {
        if(!worldObj.isRemote)
        {
            if(TickToWash <= 0)
            {
                if(CanPutWoolIn == false)
                {
                    SpawnWool(worldObj, xCoord, yCoord, zCoord);
                    worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                    CanPutWoolIn = true;
                }
            }
            
            else
            {
                if(worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord))
                {
                    TickToWash--;
                }
            }
        } 
    }
    
    public static void SpawnWool(World World, int Var1, int Var2, int Var3)
    {
        EntityItem Var4 = new EntityItem(World, Var1, Var2 + 0.5, Var3 + 0.5, new ItemStack(Block.cloth, 1, 0));
        World.spawnEntityInWorld(Var4);
    }
    
    
    // Reading From Tag Compound
    
    public void readFromNBT(NBTTagCompound NBTTagCompound)
    {
        CanPutWoolIn  = NBTTagCompound.getBoolean("CanPutWoolIn");
        TickToWash  = NBTTagCompound.getInteger("TickToWash");
       
        super.readFromNBT(NBTTagCompound);
        
        if(FirstLaunch == 1)
        {
            worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        }
        
        FirstLaunch = 1;
    }
    
    
    // Writing To Tag Compound
     
    public void writeToNBT(NBTTagCompound NBTTagCompound)
    {
        NBTTagCompound.setBoolean("CanPutWoolIn", CanPutWoolIn); 
        NBTTagCompound.setInteger("TickToWash", TickToWash);
       
        super.writeToNBT(NBTTagCompound);
    }
    
    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound Tag = new NBTTagCompound();
        writeToNBT(Tag);
        
        return new Packet132TileEntityData(xCoord, yCoord, zCoord, 1, Tag);
    }
    
    @Override
    public void onDataPacket(INetworkManager INetworkManager, Packet132TileEntityData Packet132TileEntityData)
    {
        readFromNBT(Packet132TileEntityData.customParam1);
    }
}