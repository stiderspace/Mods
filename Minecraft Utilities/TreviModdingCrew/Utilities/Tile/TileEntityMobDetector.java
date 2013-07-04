package TreviModdingCrew.Utilities.Tile;

import java.util.List;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityMobDetector extends TileEntity
{
    public static int MobID;
    
    public static String Amount = "";
    public static String Radius = "";
    
    public int Delay = 250;
     
    public TileEntityMobDetector()
    {
        
    }
    
    public void updateEntity() 
    { 
        if(Delay > 1)
        {
            Delay -= 1;
        }
            
        else
        {
            int Buffer = Integer.parseInt(Radius.toString());
            
            AxisAlignedBB Axis = AxisAlignedBB.getBoundingBox(xCoord - Buffer, yCoord, zCoord - Buffer, xCoord + Buffer, yCoord + 1, zCoord + Buffer);
            
            List Entities = worldObj.getEntitiesWithinAABB(EntityLiving.class, Axis);
            
            for(int Size = 0; Size < Entities.size(); Size++)
            {
                EntityLiving CurrentEntity = (EntityLiving) Entities.get(Size);
                
                if(CurrentEntity instanceof EntityPig)
                {
                    double xVel = 0;
                    double zVel = 0;
    
                    xVel = (CurrentEntity.posX - xCoord) / 5;
                    zVel = (CurrentEntity.posZ - zCoord) / 5;
                    
                    CurrentEntity.setVelocity(xVel, 0.1, zVel);
                }
                
                else if(CurrentEntity instanceof EntityCow)
                {
                   
                }
                
                else if(CurrentEntity instanceof EntityChicken)
                {
                    
                }
                
                
                else if(CurrentEntity instanceof EntitySheep)
                {
                                
                }
                
                Delay = 300;
            }
        }
    }
    
    
    // Reading From Tag Compound
    
    public void readFromNBT(NBTTagCompound NBTTagCompound)
    {
        MobID = NBTTagCompound.getInteger("MobID");
        Amount = NBTTagCompound.getString("Amount");
        Radius = NBTTagCompound.getString("Radius");
        
        super.readFromNBT(NBTTagCompound);
    }
    

    // Writing To Tag Compound
     
    public void writeToNBT(NBTTagCompound NBTTagCompound)
    {
        NBTTagCompound.setInteger("MobID", MobID);
        NBTTagCompound.setString("Amount", Amount);
        NBTTagCompound.setString("Radius", Radius);
        
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
