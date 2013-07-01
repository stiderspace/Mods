package TreviModdingCrew.Utilities.Tile;

import buildcraft.api.power.IPowerProvider;
import buildcraft.api.power.IPowerReceptor;
import TreviModdingCrew.Utilities.Block.BlockEggHatcher;
import TreviModdingCrew.Utilities.Util.PowerProviderAdvanced;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class TileEntityEggHatcher extends TileEntity implements IPowerReceptor
{
    // Declaration
    
	private int FirstLaunch;
	
    public boolean CanPutEggIn = false;
   
    public int TickToHatch = 0;
    public int Buffer = 0;
    
    public PowerProviderAdvanced MyProvider;
    
    
    // Tile Entity Configuration
    
    public TileEntityEggHatcher()
    {
        MyProvider = new PowerProviderAdvanced();
        MyProvider.configure(100, 8000);
    }
    
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
                if(MyProvider.getEnergyStored() >= 1)
                {
                    TickToHatch -= 2;
                    
                    MyProvider.subtractEnergy(2.0F);
                    
                    System.out.println("Hatcher 1:  " + " Energy: " + MyProvider.getEnergyStored() + "MJ   TickToHatch: " + TickToHatch);
                }
                
                else
                { 
                    TickToHatch -= 1;
                    System.out.println("Hatcher 2:  " + " Energy: " + MyProvider.getEnergyStored() + "MJ   TickToHatch: " + TickToHatch);
                }
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
        
        MyProvider.setEnergyStored(NBTTagCompound.getFloat("EnergyStored"));
        
       
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
        
        NBTTagCompound.setFloat("EnergyStored", MyProvider.getEnergyStored());
        
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

    
    // Let A Machine Request For Power

    @Override
    public int powerRequest(ForgeDirection ForgeDirection)
    {
        if(MyProvider.getEnergyStored() >= MyProvider.getMaxEnergyStored())
        {
            return 0;
        }
       
        return (int)(MyProvider.getMaxEnergyStored() - MyProvider.getEnergyStored());
    }

    @Override
    public void setPowerProvider(IPowerProvider IPowerProvider)
    {
        
    }
}