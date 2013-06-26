package TreviModdingCrew.Utilities.Tile;

import java.util.List;

import net.minecraft.entity.passive.EntityPig;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityScarecrow extends TileEntity
{
    public void updateEntity() 
    {
        if(!worldObj.isRemote)
        {
            AxisAlignedBB Axis = AxisAlignedBB.getBoundingBox(xCoord - 5, yCoord - 5, zCoord - 5, xCoord + 5, yCoord + 5, zCoord + 5);
            
            List Pig = worldObj.getEntitiesWithinAABB(EntityPig.class, Axis);
           
            for(int Size = 0; Size < Pig.size(); Size++)
            {
                EntityPig EntityPig = (EntityPig) Pig.get(Size);
               
                if(Axis != null)
                {    
                    if(xCoord > xCoord)
                    {
                        xCoord -= 1;
                        
                        EntityPig.setPosition(xCoord - 0.05, yCoord, zCoord);
                    }
                    
                    else
                    {
                        xCoord += 1;
                        
                        EntityPig.setPosition(xCoord + 0.05, yCoord, zCoord);
                    }
                    
                    if(yCoord > yCoord)
                    {
                        yCoord -= 0.5;
                       
                        EntityPig.setPosition(xCoord, yCoord - 0.05, zCoord);  
                    }
                    
                    else
                    {
                        yCoord += 0.5;
                   
                        EntityPig.setPosition(xCoord, yCoord + 0.05, zCoord);
                    }
                }
            }
        }
    }
    
    
    // Reading From Tag Compound
    
    public void readFromNBT(NBTTagCompound NBTTagCompound)
    {   
        super.readFromNBT(NBTTagCompound);
    }
 
    
    // Writing To Tag Compound

    public void writeToNBT(NBTTagCompound NBTTagCompound)
    {       
        super.writeToNBT(NBTTagCompound);
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
        this.readFromNBT(Packet132TileEntityData.customParam1);
    }
}

