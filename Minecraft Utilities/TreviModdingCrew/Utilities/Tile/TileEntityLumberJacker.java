package TreviModdingCrew.Utilities.Tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

import TreviModdingCrew.Utilities.Block.BlockLumberJacker;

public class TileEntityLumberJacker extends TileEntity
{
    public boolean HasCutted = false;
    
    public TileEntityLumberJacker()
    {
        HasCutted = false; 
    }
    
    public void updateEntity()
    {
        if (!worldObj.isRemote)
        {
            if(worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord))
            {
                if(HasCutted == false)
                {
                    BlockLumberJacker.CutWood(worldObj, xCoord, yCoord, zCoord);
                    
                    HasCutted = true;
                }
            }
            
            if(!worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord))
            {
                HasCutted = false;
            }
        }
    }
    
    
    // Reading From Tag Compound
    
    public void readFromNBT(NBTTagCompound NBTTagCompound)
    {   
        HasCutted = NBTTagCompound.getBoolean("HasCutted");
      
        super.readFromNBT(NBTTagCompound);
    }
 
    
    // Writing To Tag Compound

    public void writeToNBT(NBTTagCompound NBTTagCompound)
    {       
        NBTTagCompound.setBoolean("HasCutted", (boolean)HasCutted);
       
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
