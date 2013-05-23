package TreviModdingCrew.Utilities.Items;

import net.minecraft.item.Item;

import TreviModdingCrew.Utilities.Common.Main;

public class ItemTreetap extends Item
{
    // Declaration
    
    public ItemTreetap(int Par1)
    {
        super(Par1);
        
        setCreativeTab(Main.UtilitiesItem);
        setMaxStackSize(1);
        setMaxDamage(50);
    }
}
