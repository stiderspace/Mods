package TreviModdingCrew.Utilities.Tab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

import TreviModdingCrew.Utilities.Common.Main;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TabUtilitiesBlock extends CreativeTabs
{
    // Declaration
    
    public TabUtilitiesBlock(int Par1, String Label)
    {
        super(Label);
    }
    
    
    // Setting Texture 
    
    @SideOnly(Side.CLIENT)
    public ItemStack getIconItemStack()
    {
        return new ItemStack(Main.RockCutter);
    }
    
    
    // Setting Name
    
    public String getTranslatedTabLabel()
    {
        return "Utilities Blocks";
    }
}
