package TreviModdingCrew.Utilities.Util;

import net.minecraftforge.common.ForgeDirection;

import buildcraft.api.power.IPowerReceptor;
import buildcraft.api.power.PowerProvider;

public class PowerProviderAdvanced extends PowerProvider
implements IPowerProviderAdvanced, IPowerProviderAdvancedLoss
{
    public float EnergyRequest = 1.0F;
    
    public PowerProviderAdvanced()
    {
        EnergyRequest = (1.0F / 50);
    
        powerLoss = 0;
        powerLossRegularity = 72000;
    
        configure(0, 0);
    }
    
    public PowerProviderAdvanced(int loss)
    {
        EnergyRequest = (1.0F / 50);
    
        powerLoss = 0;
        powerLossRegularity = 72000;
    
        configure(0, 0);
    }
    
    public void configure(int MaxEnergyReceived, int MaxStoredEnergy)
    {
        super.configure(0, 2, MaxEnergyReceived, 0, MaxStoredEnergy);
    }
    
    public boolean update(IPowerReceptor IPowerReceptor)
    {
        return false;
    }
    
    public void receiveEnergy(float Quantity, ForgeDirection ForgeDirection)
    {
        energyStored += Quantity;
    
        if(energyStored > maxEnergyStored)
        {
            energyStored = maxEnergyStored;
        }
    }
    
    public void addEnergy(float Quantity)
    {
        energyStored += Quantity;
    
        if (energyStored > maxEnergyStored)
        {
            energyStored = maxEnergyStored;
        }
    }
    
    public void subtractEnergy(float Quantity)
    {
        energyStored -= Quantity;
        
        if (energyStored < 0.0F)
        {
            energyStored = 0.0F;
        }
    }
    
    public void setEnergyStored(float Quantity)
    {
        energyStored = Quantity;
          
        
        if(energyStored > maxEnergyStored)
        {
            energyStored = maxEnergyStored;
        }
          
        else if(energyStored < 0.0F)
        {
            energyStored = 0.0F;
        }
    }
    
    public void roundEnergyStored()
    {
        energyStored = MathHelper.round(energyStored);
    }
    
    public int powerRequest()
    {
        if(energyStored == maxEnergyStored) 
        {
            return 0;
        }
        
      return MathHelper.ceil(MathHelper.minFloat(maxEnergyReceived, (maxEnergyStored - energyStored) * EnergyRequest));
    }
    
    public void addEnergyWithoutLoss(float Quantity)
    {
        energyStored += Quantity;
    }
    
    public void receiveEnergyWithoutLoss(float Quantity, ForgeDirection from)
    {
        energyStored += Quantity;
    
        if(energyStored > maxEnergyStored)
        {
            energyStored = maxEnergyStored;
        }
    }
}