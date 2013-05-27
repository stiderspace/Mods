package TreviModdingCrew.Utilities.Util;

import net.minecraftforge.common.ForgeDirection;

public abstract interface IPowerProviderAdvancedLoss extends IPowerProviderAdvanced
{
      public abstract void addEnergyWithoutLoss(float Par4);
      public abstract void receiveEnergyWithoutLoss(float Par5, ForgeDirection ForgeDirection);
}