package TreviModdingCrew.Utilities.Items;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import TreviModdingCrew.Utilities.Common.Main;
import TreviModdingCrew.Utilities.Common.Reference;
import TreviModdingCrew.Utilities.Entity.EntityBomb;
import TreviModdingCrew.Utilities.Handler.KeyBindHandler;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBomb extends Item
{
	// Declarations 
	
	public ItemBomb(int Par1)
	{
		super(Par1);
		setMaxStackSize(16);
		setCreativeTab(Main.UtilitiesItem);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister IconRegister)
    {
        itemIcon = IconRegister.registerIcon(Reference.ModID + ":" + "Bomb");
    }
	
	// Let Something Happen On Right Click
	
	@Override
    public ItemStack onItemRightClick(ItemStack ItemStack, World World, EntityPlayer EntityPlayer)
	{	
		if (!World.isRemote)
		{
			World.spawnEntityInWorld(new EntityBomb(World, EntityPlayer));
			World.playSoundAtEntity(EntityPlayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
			EntityPlayer.inventory.consumeInventoryItem(Main.Bomb.itemID);
		}
		
		return ItemStack;
	}
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack ItemStack, EntityPlayer EntityPlayer, List List, boolean Par1)
    {
        if(KeyBindHandler.MoreKeyDown == true)
        {
            List.clear();
            
            List.add("Bomb");
            List.add("Right click to throw and");
            List.add("blow away some bad guys!");
        }
    }
}
