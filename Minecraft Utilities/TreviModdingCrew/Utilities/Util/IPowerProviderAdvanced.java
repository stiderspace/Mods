package TreviModdingCrew.Utilities.Util;

import buildcraft.api.power.IPowerProvider;

public abstract interface IPowerProviderAdvanced extends IPowerProvider
{
      public abstract void addEnergy(float Par1);
      public abstract void subtractEnergy(float Par2);
      public abstract void setEnergyStored(float Par3);
      public abstract void roundEnergyStored();

      public abstract int powerRequest();
}