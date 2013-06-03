package TreviModdingCrew.Utilities.Block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import TreviModdingCrew.Utilities.Common.Main;
import TreviModdingCrew.Utilities.Common.Reference;
import TreviModdingCrew.Utilities.Tile.TileEntityRockGrinder;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockRockGrinder extends BlockContainer
{
    // Declarations 
    
    private boolean KeepInventory = false;
    private Random ItemDrop;

    public BlockRockGrinder(int Par1, Material Par2)
	{
		super(Par1, Par2);
		ItemDrop = new Random();
		setUnlocalizedName("Rock Grinder");
		setHardness(5F);
		setCreativeTab(Main.UtilitiesBlock);
	}

    
    // Setting Textures
    
    private Icon[] IconBuffer = new Icon[4];
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister IconRegister)
    {
        IconBuffer[0] = IconRegister.registerIcon(Reference.ModID + ":" + "RockCutterBottom");
        IconBuffer[1] = IconRegister.registerIcon(Reference.ModID + ":" + "RockCutterTop");
        IconBuffer[2] = IconRegister.registerIcon(Reference.ModID + ":" + "MachineSide");
        IconBuffer[3] = IconRegister.registerIcon(Reference.ModID + ":" + "RockGrinderFront");
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
        
        if(Par1 == 4)
        {
            return IconBuffer[3];
        }
          
        return IconBuffer[2];
    }
    
    
    // Lets A Redstone Wire Connect To The Block
    
    public boolean canConnectRedstone(IBlockAccess iba, int i, int j, int k, int dir)
    {
        return true;
    }
    
    
    // Creating The Tile
    
	@Override
	public TileEntity createNewTileEntity(World World)
	{
		return new TileEntityRockGrinder();
	}
	

	// Register The Information When Placed Down
	
    public void onBlockAdded(World World, int Par1, int Par2, int Par3)
    {
        super.onBlockAdded(World, Par1, Par2, Par3);
        World.markBlockForUpdate(Par1, Par2, Par3);
    }
    
   
    // Opens The Gui On Right Click
    
    @Override
    public boolean onBlockActivated(World World, int Var1, int Var2, int Var3, EntityPlayer EntityPlayer, int Par1, float Par2, float Par3, float Par4) 
    {
        int BlockAbove = World.getBlockId(Var1, Var2 + 1, Var3);
        int BlockRight = World.getBlockId(Var1, Var2, Var3 + 1);
        int BlockLeft = World.getBlockId(Var1 + 1, Var2, Var3);
        int BlockBack = World.getBlockId(Var1, Var2, Var3 - 1);
        
        if (BlockAbove == Block.hopperBlock.blockID && (BlockLeft == Block.lever.blockID || BlockRight == Block.lever.blockID || BlockBack == Block.lever.blockID))
        {
            EntityPlayer.openGui(Main.Instance, 0, World, Var1, Var2, Var3);
            
            return true;
        }
        
    	return false;
    }		
 
    
    // If Block Breaks Drop Items Inside
    
    public void breakBlock(World World, int Par2, int Par3, int Par4, int Par5, int Par6)
    {
        if (!KeepInventory)
        {
            TileEntityRockGrinder Tile = (TileEntityRockGrinder)World.getBlockTileEntity(Par2, Par3, Par4);

            if (Tile != null)
            {
                for (int Var8 = 0; Var8 < Tile.getSizeInventory(); ++Var8)
                {
                    ItemStack ItemStack = Tile.getStackInSlot(Var8);

                    if (ItemStack != null)
                    {
                        float Var10 = this.ItemDrop.nextFloat() * 0.8F + 0.1F;
                        float Var11 = this.ItemDrop.nextFloat() * 0.8F + 0.1F;
                        float Var12 = this.ItemDrop.nextFloat() * 0.8F + 0.1F;

                        while (ItemStack.stackSize > 0)
                        {
                            int Var13 = this.ItemDrop.nextInt(21) + 10;

                            if (Var13 > ItemStack.stackSize)
                            {
                                Var13 = ItemStack.stackSize;
                            }

                            ItemStack.stackSize -= Var13;
                            
                            EntityItem Var14 = new EntityItem(World, (double)((float)Par2 + Var10), (double)((float)Par3 + Var11), (double)((float)Par4 + Var12), new ItemStack(ItemStack.itemID, Var13, ItemStack.getItemDamage()));

                            if (ItemStack.hasTagCompound())
                            {
                                Var14.getEntityItem().setTagCompound((NBTTagCompound)ItemStack.getTagCompound().copy());
                            }

                            float Var15 = 0.05F;

                            Var14.motionX = (double)((float)this.ItemDrop.nextGaussian() * Var15);
                            Var14.motionY = (double)((float)this.ItemDrop.nextGaussian() * Var15 + 0.2F);
                            Var14.motionZ = (double)((float)this.ItemDrop.nextGaussian() * Var15);
                            
                            World.spawnEntityInWorld(Var14);
                        }
                    }
                }
            }
        }

        super.breakBlock(World, Par2, Par3, Par4, Par5, Par6);
    }
}

