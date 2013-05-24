package TreviModdingCrew.Utilities.Items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import TreviModdingCrew.Utilities.Common.Main;
import TreviModdingCrew.Utilities.Handler.ItemSwapHandler;
import TreviModdingCrew.Utilities.Handler.KeyBindHandler;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

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
  
    public boolean ThisIsPersonal = false;
   
    public int Outcome = 0;
    public int Chance = 3;

    
    // Let Something Happen On Right Click
    
    public boolean onItemUse(ItemStack ItemStack, EntityPlayer EntityPlayer, World World, int Par4, int Par5, int Par6, int Par7, float Par8, float Par9, float Par10)
    {
        ItemSwapHandler ItemSwapHandler = new ItemSwapHandler();
        
        if(World.getBlockId(Par4, Par5, Par6) == Block.wood.blockID)
        {
            if(EntityPlayer.inventory.hasItem(Item.glassBottle.itemID))
            {
                Outcome = 0;
            }
            
            if(EntityPlayer.inventory.hasItem(Item.bucketEmpty.itemID))
            {
                Outcome = 1;
            }
            
            if(!EntityPlayer.inventory.hasItem(Item.bucketEmpty.itemID) || (!EntityPlayer.inventory.hasItem(Item.glassBottle.itemID)))
            {
                Outcome = 2;
            }
            
            if (World.rand.nextInt(Chance) == 0)  
            {
                switch(Outcome)
                {
                    case 0: ItemSwapHandler.SwapItem(Item.glassBottle, new ItemStack(Item.potion.itemID, 1, 0), EntityPlayer);  
                    break;
                    
                    case 1: ItemSwapHandler.SwapItem(Item.bucketEmpty, new ItemStack(Item.bucketWater), EntityPlayer);
                    break;
                }
            }
            
            if(Outcome == 2)
            {
                Personalization(EntityPlayer, World);
            }
            
            ItemStack.damageItem(1, EntityPlayer);
            
            return true;
        } 
        
        return false;
    }
    
    
    // Personalization
    
    public void Personalization(EntityPlayer EntityPlayer, World World)
    {
        if(EntityPlayer.username.equals("MrTreefo"))
        {
            if(!World.isRemote)
            {
                FMLClientHandler.instance().getClient().ingameGUI.getChatGUI().printChatMessage("You dont have a bucket good sir!");
            }
            
            ThisIsPersonal = true;
        }
        
        if(EntityPlayer.username.equals("tiena63"))
        {
            if(!World.isRemote)
            {
                FMLClientHandler.instance().getClient().ingameGUI.getChatGUI().printChatMessage("You dont have a bucket sweathart!");
            }
            
            ThisIsPersonal = true;
        }
        
        if(EntityPlayer.username.equals("ABC_Lockwood"))
        {
            if(!World.isRemote)
            {
                FMLClientHandler.instance().getClient().ingameGUI.getChatGUI().printChatMessage("You dont have a bucket you stupid idiot!");
            }
            
            ThisIsPersonal = true;
        }
        
        if(EntityPlayer.username.equals("ChipUK"))
        {
            FMLClientHandler.instance().getClient().ingameGUI.getChatGUI().printChatMessage("");
            ThisIsPersonal = true;
            
            if(!World.isRemote)
            {
                FMLClientHandler.instance().getClient().ingameGUI.getChatGUI().printChatMessage("Chip, give your bucket an inventory!");
            }
            
            ThisIsPersonal = true;
        }
        
        if(EntityPlayer.username.equals("l0rry"))
        {
            if(!World.isRemote)
            {
                FMLClientHandler.instance().getClient().ingameGUI.getChatGUI().printChatMessage("Derp you forgot your bucket, Go and get one!");
            }
            
            ThisIsPersonal = true;
        }
        
        if(EntityPlayer.username.equals("SleepingWolf20"))
        {
            if(!World.isRemote)
            {
                FMLClientHandler.instance().getClient().ingameGUI.getChatGUI().printChatMessage("Get a bucket or I will kill you!");
            }
            
            ThisIsPersonal = true;
        }
        
        if(EntityPlayer.username.equals("manmaed"))
        {
            if(!World.isRemote)
            {
                FMLClientHandler.instance().getClient().ingameGUI.getChatGUI().printChatMessage("Go command Direwolf20 to get a bucket!");
            }
            
            ThisIsPersonal = true;
        }
        
        if(EntityPlayer.username.equals("Metalen"))
        {
            if(!World.isRemote)
            {
                FMLClientHandler.instance().getClient().ingameGUI.getChatGUI().printChatMessage("Go in creative mode and spawn yourself a bucket!");
            }
            
            ThisIsPersonal = true;
        }
        
        if(EntityPlayer.username.equals("Lordmau5"))
        {
            if(!World.isRemote)
            {
                FMLClientHandler.instance().getClient().ingameGUI.getChatGUI().printChatMessage("You did a amazing job on liquid energy, no empty the bucket so you can put water in!");
            }
            
            ThisIsPersonal = true;
        }
        
        if(ThisIsPersonal == false)
        {
            EntityPlayer.addChatMessage("You dont have something to put water in!");
        }
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
