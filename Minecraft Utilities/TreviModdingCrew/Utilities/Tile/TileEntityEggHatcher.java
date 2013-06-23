package TreviModdingCrew.Utilities.Tile;

import TreviModdingCrew.Utilities.Block.BlockEggHatcher;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityEggHatcher extends TileEntity
{
    // Declaration
    
	private int FirstLaunch;
	
    public boolean CanPutEggIn = false;
   
    public int TickToHatch = 0;
    public int Buffer = 0;
    
    public void updateEntity() 
    {
        if(!worldObj.isRemote)
        {
            if(TickToHatch <= 0)
            {
                if(CanPutEggIn == false)
                {
                    SpawnChicken(worldObj, xCoord, yCoord, zCoord);
                    CanPutEggIn = true;
                }
            }
            
            else
            {
                TickToHatch--;
            }
        } 
    }
    
    public static void SpawnChicken(World World, int Var1, int Var2, int Var3)
    {
        EntityChicken Chicken = new EntityChicken(World);
        Chicken.setPosition(Var1 + BlockEggHatcher.PosX, Var2 + BlockEggHatcher.PosY, Var3 + BlockEggHatcher.PosZ);
        World.spawnEntityInWorld(Chicken);
        Chicken.setGrowingAge(-24000);
        World.markBlockForUpdate(Var1, Var2, Var3);
    }
    
    
    // Reading From Tag Compound
    
    public void readFromNBT(NBTTagCompound NBTTagCompound)
    {
        CanPutEggIn  = NBTTagCompound.getBoolean("CanPutEggIn");
        TickToHatch  = NBTTagCompound.getInteger("TickToHatch");
        Buffer = NBTTagCompound.getInteger("Buffer");
       
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
        NBTTagCompound.setBoolean("CanPutEggIn", CanPutEggIn); 
        NBTTagCompound.setInteger("TickToHatch", TickToHatch);
        NBTTagCompound.setInteger("Buffer", Buffer);
       
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
