package TreviModdingCrew.Utilities.Tile;

import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityHatcher extends TileEntity
{
    // Declaration
    
	private int FirstLaunch;
	
    public boolean CanPutEggIn = false;
   
    public int TickToHatch = 0;
    
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
        Chicken.setPosition(Var1 + 0.5, Var2 + 1, Var3 + 0.5);
        World.spawnEntityInWorld(Chicken);
        Chicken.setGrowingAge(-24000);
        World.markBlockForUpdate(Var1, Var2, Var3);
    }
    
    
    // Reading From Tag Compound
    
    public void readFromNBT(NBTTagCompound NBTTagCompound)
    {
        CanPutEggIn  = NBTTagCompound.getBoolean("CanPutEggIn");
        TickToHatch  = NBTTagCompound.getInteger("TickToHatch");
       
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
       
        super.writeToNBT(NBTTagCompound);
    }
    
    @Override
	public Packet getDescriptionPacket()
    {
		NBTTagCompound tagCompound = new NBTTagCompound();
		writeToNBT(tagCompound);
		
		return new Packet132TileEntityData(xCoord, yCoord, zCoord, 1, tagCompound);
	}
	
    @Override
	public void onDataPacket(INetworkManager net, Packet132TileEntityData packet)
    {
		readFromNBT(packet.customParam1);
    }
}
