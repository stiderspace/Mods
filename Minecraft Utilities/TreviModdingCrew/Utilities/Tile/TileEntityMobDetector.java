package TreviModdingCrew.Utilities.Tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

public class TileEntityMobDetector extends TileEntity
{
    public static int MobID;
    public static String Amount;
    
    public TileEntityMobDetector()
    {
       
    }
    
    public void updateEntity() 
    {
        System.out.println(Amount);
    }
    
    
    // Reading From Tag Compound
    
    public void readFromNBT(NBTTagCompound NBTTagCompound)
    {
        MobID = NBTTagCompound.getInteger("MobID");
        Amount = NBTTagCompound.getString("Amount");
        
        super.readFromNBT(NBTTagCompound);
    }
    

    // Writing To Tag Compound
     
    public void writeToNBT(NBTTagCompound NBTTagCompound)
    {
        NBTTagCompound.setInteger("MobID", MobID);
        NBTTagCompound.setString("Amount", Amount);
        
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
