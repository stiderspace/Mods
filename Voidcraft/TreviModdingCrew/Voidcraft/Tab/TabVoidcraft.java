package TreviModdingCrew.Voidcraft.Tab;

import TreviModdingCrew.Voidcraft.Common.Main;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TabVoidcraft extends CreativeTabs
{
    // Declaration
    
    public TabVoidcraft(int Par1, String Label)
    {
        super(Label);
    }
    
    
    // Setting Texture 
    
    @SideOnly(Side.CLIENT)
    public ItemStack getIconItemStack()
    {
        return new ItemStack(Main.GreenPowderExplosive);
    }
    
    
    // Setting Name
    
    public String getTranslatedTabLabel()
    {
        return "Voidcraft";
    }
}
