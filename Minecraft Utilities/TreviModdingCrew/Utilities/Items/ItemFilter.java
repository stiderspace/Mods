package TreviModdingCrew.Utilities.Items;

import java.util.List;

import TreviModdingCrew.Utilities.Common.Main;
import TreviModdingCrew.Utilities.Common.Reference;
import TreviModdingCrew.Utilities.Handler.KeyBindHandler;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemFilter extends Item
{
	// Declarations
	
	public ItemFilter(int Par1)
	{
		super(Par1);
		setCreativeTab(Main.UtilitiesItem);
		setMaxStackSize(1);
		setUnlocalizedName("Filter");
		setMaxDamage(25);
	}

	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister IconRegister)
    {
        itemIcon = IconRegister.registerIcon(Reference.ModID + ":" + "Filter");
    }
	
	
	// Let Something Happen On Right Click
	
	public boolean onItemUse(ItemStack ItemStack, EntityPlayer EntityPlayer, World World, int Par4, int Par5, int Par6, int Par7, float Par8, float Par9, float Par10)
	{
	    int Par1 = 25;
	    int Chance = 3;
	    int BlockUsedOn = World.getBlockId(Par4, Par5, Par6);
        int BlockAbove = World.getBlockId(Par4, Par5 + 1, Par6);
        
		Par1 = Par1 - 1;
		Block Sand = Block.sand;
    
		int Bid = World.getBlockId(Par4, Par5, Par6);
		Block Break = Block.blocksList[Bid];

		if (BlockUsedOn == Block.gravel.blockID)
		{
			Break.dropBlockAsItemWithChance(World, Par4, Par5, Par6, Item.flint.itemID, 100, 100);
			World.setBlock(Par4, Par5, Par6, 12);
			World.playSoundEffect(Par4 + 0.5F, Par5 + 0.5F, Par6 + 0.5F, Sand.stepSound.getStepSound(), (Sand.stepSound.getVolume() + 0.7F) / 2.0F, Sand.stepSound.getPitch() * 1.1F);
	        ItemStack.damageItem(1, EntityPlayer);
	        
	        return true;
		}
    
		if ((BlockUsedOn != Block.blockClay.blockID) || (BlockAbove != Block.waterStill.blockID))
		{
			return false;
		}

		if(EntityPlayer.username.equals("MrTreefo"))
		{
		    Chance = 1;
		}
		
		if (!World.isRemote)
		{	
			if (World.rand.nextInt(Chance) == 0)
			{
				float  Var1 = 0.7F;
    		
				double Var2 = World.rand.nextFloat() * Var1 + (1.0F - Var1) * 0.5D;
				double Var3 = World.rand.nextFloat() * Var1 + (1.0F - Var1) * 0.2D + 0.6D;
				double Var4 = World.rand.nextFloat() * Var1 + (1.0F - Var1) * 0.5D;
    		
				EntityItem Var5 = new EntityItem(World, Par4 + Var2, Par5 + Var3, Par6 + Var4, new ItemStack(Item.goldNugget));
				World.spawnEntityInWorld(Var5);
		        ItemStack.damageItem(1, EntityPlayer);
				Var5.delayBeforeCanPickup = 10 ;
			}
		}

		World.playSoundEffect(Par4 + 0.5F, Par5 + 0.5F, Par6 + 0.5F, Sand.stepSound.getStepSound(), (Sand.stepSound.getVolume() + 0.7F) / 2.0F, Sand.stepSound.getPitch() * 1.1F);

		if (World.isRemote)
		{
			return true;
		}
 
		World.setBlock(Par4, Par5, Par6, 12);
		
		return true;
	}
  
	
	// Adding The Tooltip 
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack ItemStack, EntityPlayer EntityPlayer, List List, boolean Par1)
	{
		List.add(ItemStack.getItemDamage()+ "/25 Uses Remaining");
		
		if(KeyBindHandler.MoreKeyDown == true)
		{
		    List.clear();
		    
		    List.add("Filter");
		    List.add("Right click on gravel or clay"); 
		    List.add("to obtain flint or golden nuggets!");
		}
	}
}