package TreviModdingCrew.Utilities.Items;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import TreviModdingCrew.Utilities.Common.Main;
import TreviModdingCrew.Utilities.Common.Reference;
import TreviModdingCrew.Utilities.Handler.KeyBindHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMagnet extends Item
{
    // Declarations 
    
    public ItemMagnet(int Par1)
    {
        super(Par1);
        setMaxStackSize(1);
        setCreativeTab(Main.UtilitiesItem);
        setMaxDamage(300);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister IconRegister)
    {
        itemIcon = IconRegister.registerIcon(Reference.ModID + ":" + "Magnet");
    }
    
    
    // Let Something Happen On Right Click
    
    @Override
    public ItemStack onItemRightClick(ItemStack ItemStack, World World, EntityPlayer EntityPlayer)
    {
        if(World.isRemote)
        {
            return ItemStack;
        }
        
        AxisAlignedBB Axis = AxisAlignedBB.getBoundingBox(EntityPlayer.posX - 10, EntityPlayer.posY - 5, EntityPlayer.posZ - 10, EntityPlayer.posX + 10, EntityPlayer.posY + 5, EntityPlayer.posZ + 10);
        List Items = World.getEntitiesWithinAABB(EntityItem.class, Axis);
        
        for(int Size = 0; Size < Items.size(); Size++)
        {
            EntityItem Item = (EntityItem) Items.get(Size);
            
            if(Item != null)
            {
                EntityPlayer.setItemInUse(ItemStack, this.getMaxItemUseDuration(ItemStack));
                
                if(EntityPlayer.posX > EntityPlayer.posX)
                {
                    EntityPlayer.posX -= 1;
                    Item.setPosition(EntityPlayer.posX - 0.25, EntityPlayer.posY, EntityPlayer.posZ);
                }
                
                else
                {
                    EntityPlayer.posX += 1;
                    Item.setPosition(EntityPlayer.posX + 0.25, EntityPlayer.posY, EntityPlayer.posZ);
                }
                
                if(EntityPlayer.posY > EntityPlayer.posY)
                {
                    EntityPlayer.posY -= 0.5;
                   
                    Item.setPosition(EntityPlayer.posX, EntityPlayer.posY - 0.25, EntityPlayer.posZ);
                }
                
                else
                {
                    EntityPlayer.posY += 0.5;
               
                    Item.setPosition(EntityPlayer.posX, EntityPlayer.posY + 0.25, EntityPlayer.posZ);
                }
                
                if(EntityPlayer.posZ > EntityPlayer.posZ)
                {
                    EntityPlayer.posZ -= 1;
                    
                    Item.setPosition(EntityPlayer.posX, EntityPlayer.posY, EntityPlayer.posZ - 0.25);
                }
                
                else
                {
                    EntityPlayer.posZ += 1;
                    
                    Item.setPosition(EntityPlayer.posX, EntityPlayer.posY, EntityPlayer.posZ + 0.25);
                }
            
                ItemStack.damageItem(1, EntityPlayer);
            } 
        }
        
        return ItemStack;
    }
 
    public EnumAction getItemUseAction(ItemStack ItemStack)
    {
        return EnumAction.bow;
    }
    
    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 72000;
    }
    
    
    // Adding The Tooltip 
    
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack ItemStack, EntityPlayer EntityPlayer, List List, boolean Par1)
    {
        List.add(ItemStack.getItemDamage()+ "/300 Uses Remaining");
        
        if(KeyBindHandler.MoreKeyDown == true)
        {
            List.clear();
            
            List.add("Magnet");
            List.add("Sucks items towards you. Be carefull"); 
            List.add("items can hurt you! Also this might drag");
            List.add("you to a magnetic field if you hold it");
            List.add("too long...");
        }
    }   
}
