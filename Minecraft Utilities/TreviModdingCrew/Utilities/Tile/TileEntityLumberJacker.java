package TreviModdingCrew.Utilities.Tile;

import TreviModdingCrew.Utilities.Block.BlockLumberJacker;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

public class TileEntityLumberJacker extends TileEntity
{
    public void updateEntity()
    {
        if (!worldObj.isRemote)
        {
            if(worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord))
            {
                BlockLumberJacker.CutWood(worldObj, xCoord, yCoord, zCoord);
            }
        }
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
