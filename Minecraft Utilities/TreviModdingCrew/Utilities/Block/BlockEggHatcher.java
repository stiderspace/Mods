package TreviModdingCrew.Utilities.Block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import TreviModdingCrew.Utilities.Common.Main;
import TreviModdingCrew.Utilities.Common.Reference;
import TreviModdingCrew.Utilities.Tile.TileEntityEggHatcher;

public class BlockEggHatcher extends BlockContainer
{
    // Declaration
    
    public BlockEggHatcher(int Par1, Material Rock)
    {
        super(Par1, Rock);
        setUnlocalizedName("Egg Hatcher");
        setHardness(5F);
        setCreativeTab(Main.UtilitiesBlock);
    }
    
    
    // Setting Textures
    
    private Icon[] IconBuffer = new Icon[3];
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister IconRegister)
    {
        IconBuffer[0] = IconRegister.registerIcon(Reference.ModID + ":" + "Rock Cutter Bottom");
        IconBuffer[1] = IconRegister.registerIcon(Reference.ModID + ":" + "Glass");
        IconBuffer[2] = IconRegister.registerIcon(Reference.ModID + ":" + "Machine Side");
    }
    
    @Override
    public Icon getIcon(int Par1, int Par2)
    {
        if(Par1 == 0)
        {
            return IconBuffer[0];
        }
        
        if(Par1 == 1)
        {
            return IconBuffer[1];
        }
        
        return IconBuffer[2];
    }
    
    
    // Custom Block Render
    
    @Override
    public boolean hasTileEntity(int Metadata)
    {
        return true;
    }
    
    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }
    
    @Override
    public int getRenderType()
    {
        return -1;
    }
    
    
    // Let Something Happen On Right Click
    
    @Override
    public boolean onBlockActivated(World World, int Var1, int Var2, int Var3, EntityPlayer EntityPlayer, int Par1, float Par2, float Par3, float Par4)
    {
    	if(EntityPlayer.getHeldItem() == null)
    	{
    		return false;
    	}
    	
    	if(EntityPlayer.getHeldItem().itemID == Item.egg.itemID)
        {
            TileEntityEggHatcher Tile = (TileEntityEggHatcher) World.getBlockTileEntity(Var1, Var2, Var3);
            
            Random Var4 = new Random();
            
            if(Tile.CanPutEggIn == false)
            {
            	if(!World.isRemote)
            	{
            		EntityPlayer.inventory.consumeInventoryItem(Item.egg.itemID);
	                
            		EntityItem Var5 = new EntityItem(World, Var1 + 0.5, Var2 + 1, Var3 + 0.5, new ItemStack(Item.egg));
	                World.spawnEntityInWorld(Var5);
            	}
            }
            
            if (Tile.CanPutEggIn == true)
            {
                if(!World.isRemote)
                {
                    EntityPlayer.inventory.consumeInventoryItem(Item.egg.itemID);
                   
                    Tile.CanPutEggIn = false;
                    Tile.TickToHatch = 6000 + Var4.nextInt(4000);
                    World.markBlockForUpdate(Var1, Var2, Var3);
                }
            } 
            
            return true;
        }
    	
    	if(EntityPlayer.getHeldItem().itemID == Item.wheat.itemID)
    	{
    	    TileEntityEggHatcher Tile = (TileEntityEggHatcher) World.getBlockTileEntity(Var1, Var2, Var3);
    	    
    	    if(Tile.TickToHatch > 0)
    	    {
        	    Tile.TickToHatch = Tile.TickToHatch - 2000;
        	    EntityPlayer.inventory.consumeInventoryItem(Item.wheat.itemID);
        	    
        	    World.spawnParticle("heart", Var1 + 0.5, Var2 + 1.25, Var3 + 0.5, 0.0D, 0.0D, 0.0D);
        	    
        	    return true;
    	    }
        	  
    	    return false;
    	}
    	
        return false;
    }
    
    
    // Creating The Tile
    
    @Override
    public TileEntity createNewTileEntity(World World)
    {
        TileEntityEggHatcher Tile = new TileEntityEggHatcher();
        
        Tile.TickToHatch = 0;
        Tile.CanPutEggIn = true;
        
        return Tile;
    }
    
    
    // Lets Something Happen If The Block Breaks
    
    public void breakBlock(World World, int Par2, int Par3, int Par4, int Par5, int Par6)
    {
    	TileEntityEggHatcher Tile = (TileEntityEggHatcher) World.getBlockTileEntity(Par2, Par3, Par4);
        
    	if(Tile.TickToHatch > 0)
        {
            EntityItem Var5 = new EntityItem(World, Par2 + 0, Par3 + 0.5, Par4 + 0, new ItemStack(Item.egg));
            World.spawnEntityInWorld(Var5);
        }
        
        super.breakBlock(World, Par2, Par3, Par4, Par5, Par6);
    }
}