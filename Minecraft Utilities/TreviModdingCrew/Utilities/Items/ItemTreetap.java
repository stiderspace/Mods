package TreviModdingCrew.Utilities.Items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import TreviModdingCrew.Utilities.Common.Main;
import TreviModdingCrew.Utilities.Handler.KeyBindHandler;

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
    
    
    // Let Something Happen On Right Click
    
    public boolean onItemUse(ItemStack ItemStack, EntityPlayer EntityPlayer, World World, int Par4, int Par5, int Par6, int Par7, float Par8, float Par9, float Par10)
    {
        return false;
    }


    // Adding The Tooltip 
    
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack ItemStack, EntityPlayer EntityPlayer, List List, boolean Par1)
    {
        List.add(ItemStack.getItemDamage()+ "/50 Uses Remaining");
        
        if(KeyBindHandler.MoreKeyDown == true)
        {
            List.clear();
            
            List.add("Treetap");
            List.add("Right click on a tree to obtain water."); 
            List.add("How bigger the tree how bigger the chance");
            List.add("to obtain water in your bottle or bucket.");
        }
    }
}
