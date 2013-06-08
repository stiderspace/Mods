package TreviModdingCrew.Utilities.Block;

import java.util.Iterator;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import TreviModdingCrew.Utilities.Common.Main;
import TreviModdingCrew.Utilities.Common.Reference;
import TreviModdingCrew.Utilities.Tile.TileEntityTrashCan;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import static net.minecraftforge.common.ForgeDirection.DOWN;

public class BlockTrashCan extends BlockContainer
{
    // Decelaration
    
    public final Random random = new Random();
    public final int Value;

    public BlockTrashCan(int Par1, int Par2)
    {
        super(Par1, Material.rock);
        setCreativeTab(Main.UtilitiesBlock);
        Value = Par2;
    }

  
    // When The Player Places It Down
    
    public void onBlockAdded(World World, int Par2, int Par3, int Par4)
    {
        super.onBlockAdded(World, Par2, Par3, Par4);
        
        unifyAdjacentChests(World, Par2, Par3, Par4);
        
        int Var1 = World.getBlockId(Par2, Par3, Par4 - 1);
        int Var2 = World.getBlockId(Par2, Par3, Par4 + 1);
        int Var3 = World.getBlockId(Par2 - 1, Par3, Par4);
        int Var4 = World.getBlockId(Par2 + 1, Par3, Par4);

        if (Var1 == blockID)
        {
            unifyAdjacentChests(World, Par2, Par3, Par4 - 1);
        }

        if (Var2 == blockID)
        {
            unifyAdjacentChests(World, Par2, Par3, Par4 + 1);
        }

        if (Var3 == blockID)
        {
            unifyAdjacentChests(World, Par2 - 1, Par3, Par4);
        }

        if (Var4 == blockID)
        {
            unifyAdjacentChests(World, Par2 + 1, Par3, Par4);
        }
    }

    
    // Checks Who Placed The Block
    
    public void onBlockPlacedBy(World World, int Par2, int Par3, int Par4, EntityLiving EntityLiving, ItemStack ItemStack)
    {
        int Var1 = World.getBlockId(Par2, Par3, Par4 - 1);
        int Var2 = World.getBlockId(Par2, Par3, Par4 + 1);
        int Var3 = World.getBlockId(Par2 - 1, Par3, Par4);
        int Var4 = World.getBlockId(Par2 + 1, Par3, Par4);
        
        byte Var5 = 0;
       
        int Var6 = MathHelper.floor_double((double)(EntityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (Var6 == 0)
        {
            Var5 = 2;
        }

        if (Var6 == 1)
        {
            Var5 = 5;
        }

        if (Var6 == 2)
        {
            Var5 = 3;
        }

        if (Var6 == 3)
        {
            Var5 = 4;
        }

        if (Var1 != blockID && Var2 != blockID && Var3 != blockID && Var4 != blockID)
        {
            World.setBlockMetadataWithNotify(Par2, Par3, Par4, Var5, 3);
        }
        
        else
        {
            if ((Var1 == blockID || Var2 == blockID) && (Var5 == 4 || Var5 == 5))
            {
                if (Var1 == blockID)
                {
                    World.setBlockMetadataWithNotify(Par2, Par3, Par4 - 1, Var5, 3);
                }
                
                else
                {
                    World.setBlockMetadataWithNotify(Par2, Par3, Par4 + 1, Var5, 3);
                }

                World.setBlockMetadataWithNotify(Par2, Par3, Par4, Var5, 3);
            }

            if ((Var3 == blockID || Var4 == blockID) && (Var5 == 2 || Var5 == 3))
            {
                if (Var3 == blockID)
                {
                    World.setBlockMetadataWithNotify(Par2 - 1, Par3, Par4, Var5, 3);
                }
                
                else
                {
                    World.setBlockMetadataWithNotify(Par2 + 1, Par3, Par4, Var5, 3);
                }

                World.setBlockMetadataWithNotify(Par2, Par3, Par4, Var5, 3);
            }
        }

        if (ItemStack.hasDisplayName())
        {
            ((TileEntityTrashCan)World.getBlockTileEntity(Par2, Par3, Par4)).func_94043_a(ItemStack.getDisplayName());
        }
    }

    
    // Combines The Chests Together
    
    public void unifyAdjacentChests(World World, int Par2, int Par3, int Par4)
    {
        if (!World.isRemote)
        {
            int Var1 = World.getBlockId(Par2, Par3, Par4 - 1);
            int Var2 = World.getBlockId(Par2, Par3, Par4 + 1);
            int Var3 = World.getBlockId(Par2 - 1, Par3, Par4);
            int Var4 = World.getBlockId(Par2 + 1, Par3, Par4);
            
            int Var5;
            int Var6;
            
            boolean Flag;
            
            byte Var7;
            
            int Var8;

            if (Var2 != blockID && Var2 != blockID)
            {
                if (Var3 != blockID && Var4 != blockID)
                {
                    Var7 = 3;

                    if (Block.opaqueCubeLookup[Var2] && !Block.opaqueCubeLookup[Var2])
                    {
                        Var7 = 3;
                    }

                    if (Block.opaqueCubeLookup[Var2] && !Block.opaqueCubeLookup[Var2])
                    {
                        Var7 = 2;
                    }

                    if (Block.opaqueCubeLookup[Var3] && !Block.opaqueCubeLookup[Var4])
                    {
                        Var7 = 5;
                    }

                    if (Block.opaqueCubeLookup[Var4] && !Block.opaqueCubeLookup[Var3])
                    {
                        Var7 = 4;
                    }
                }
                
                else
                {
                    Var5 = World.getBlockId(Var3 == blockID ? Par2 - 1 : Par2 + 1, Par3, Par4 - 1);
                    Var6 = World.getBlockId(Var3 == blockID ? Par2 - 1 : Par2 + 1, Par3, Par4 + 1);
                    Var7 = 3;
                    
                    Flag = true;

                    if (Var3 == blockID)
                    {
                        Var8 = World.getBlockMetadata(Par2 - 1, Par3, Par4);
                    }
                   
                    else
                    {
                        Var8 = World.getBlockMetadata(Par2 + 1, Par3, Par4);
                    }

                    if (Var8 == 2)
                    {
                        Var7 = 2;
                    }

                    if ((Block.opaqueCubeLookup[Var2] || Block.opaqueCubeLookup[Var5]) && !Block.opaqueCubeLookup[Var2] && !Block.opaqueCubeLookup[Var6])
                    {
                        Var7 = 3;
                    }

                    if ((Block.opaqueCubeLookup[Var2] || Block.opaqueCubeLookup[Var6]) && !Block.opaqueCubeLookup[Var2] && !Block.opaqueCubeLookup[Var5])
                    {
                        Var7 = 2;
                    }
                }
            }
            
            else
            {
                Var5 = World.getBlockId(Par2 - 1, Par3, Var2 == blockID ? Par4 - 1 : Par4 + 1);
                Var6 = World.getBlockId(Par2 + 1, Par3, Var2 == blockID ? Par4 - 1 : Par4 + 1);
                Var7 = 5;
                
                Flag = true;

                if (Var2 == blockID)
                {
                    Var8 = World.getBlockMetadata(Par2, Par3, Par4 - 1);
                }
                
                else
                {
                    Var8 = World.getBlockMetadata(Par2, Par3, Par4 + 1);
                }

                if (Var8 == 4)
                {
                    Var7 = 4;
                }

                if ((Block.opaqueCubeLookup[Var3] || Block.opaqueCubeLookup[Var5]) && !Block.opaqueCubeLookup[Var4] && !Block.opaqueCubeLookup[Var6])
                {
                    Var7 = 5;
                }

                if ((Block.opaqueCubeLookup[Var4] || Block.opaqueCubeLookup[Var6]) && !Block.opaqueCubeLookup[Var3] && !Block.opaqueCubeLookup[Var5])
                {
                    Var7 = 4;
                }
            }

            World.setBlockMetadataWithNotify(Par2, Par3, Par4, Var7, 3);
        }
    }

    
    // Sets It If You Can Place The Block
    
    public boolean canPlaceBlockAt(World World, int Par2, int Par3, int Par4)
    {
        int Var1 = 0;

        if (World.getBlockId(Par2 - 1, Par3, Par4) == blockID)
        {
            ++Var1;
        }

        if (World.getBlockId(Par2 + 1, Par3, Par4) == blockID)
        {
            ++Var1;
        }

        if (World.getBlockId(Par2, Par3, Par4 - 1) == blockID)
        {
            ++Var1;
        }

        if (World.getBlockId(Par2, Par3, Par4 + 1) == blockID)
        {
            ++Var1;
        }

        return Var1 > 1 ? false : (isThereANeighborChest(World, Par2 - 1, Par3, Par4) ? false : (isThereANeighborChest(World, Par2 + 1, Par3, Par4) ? false : (isThereANeighborChest(World, Par2, Par3, Par4 - 1) ? false : !isThereANeighborChest(World, Par2, Par3, Par4 + 1))));
    }

    
    // Checks If There Is A Chest On The Side
    
    private boolean isThereANeighborChest(World World, int Par2, int Par3, int Par4)
    {
        return World.getBlockId(Par2, Par3, Par4) != blockID ? false : (World.getBlockId(Par2 - 1, Par3, Par4) == blockID ? true : (World.getBlockId(Par2 + 1, Par3, Par4) == blockID ? true : (World.getBlockId(Par2, Par3, Par4 - 1) == blockID ? true : World.getBlockId(Par2, Par3, Par4 + 1) == blockID)));
    }

    
    // Checks If Block On Sides Updates
    
    public void onNeighborBlockChange(World World, int Par2, int Par3, int Par4, int Par5)
    {
        super.onNeighborBlockChange(World, Par2, Par3, Par4, Par5);
        
        TileEntityTrashCan TileEntityTrashCan = (TileEntityTrashCan)World.getBlockTileEntity(Par2, Par3, Par4);

        if (TileEntityTrashCan != null)
        {
            TileEntityTrashCan.updateContainingBlockInfo();
        }
    }
    
    
    // Lets Something Happen If The Block Breaks
    
    public void breakBlock(World World, int Par2, int Par3, int Par4, int Par5, int Par6)
    {
        TileEntityTrashCan TileEntityTrashCan = (TileEntityTrashCan)World.getBlockTileEntity(Par2, Par3, Par4);

        if (TileEntityTrashCan != null)
        {
            for (int Var1 = 0; Var1 < TileEntityTrashCan.getSizeInventory(); ++Var1)
            {
                ItemStack Items = TileEntityTrashCan.getStackInSlot(Var1);

                if (Items != null)
                {
                    float Var2 = random.nextFloat() * 0.8F + 0.1F;
                    float Var3 = random.nextFloat() * 0.8F + 0.1F;
                    
                    EntityItem EntityItem;

                    for (float Var4 = random.nextFloat() * 0.8F + 0.1F; Items.stackSize > 0; World.spawnEntityInWorld(EntityItem))
                    {
                        int Var5 = random.nextInt(21) + 10;

                        if (Var5 > Items.stackSize)
                        {
                            Var5 = Items.stackSize;
                        }

                        Items.stackSize -= Var5;
                       
                        EntityItem = new EntityItem(World, (double)((float)Par2 + Var2), (double)((float)Par3 + Var3), (double)((float)Par4 + Var4), new ItemStack(Items.itemID, Var5, Items.getItemDamage()));
                        
                        float Var6 = 0.05F;
                       
                        EntityItem.motionX = (double)((float)random.nextGaussian() * Var6);
                        EntityItem.motionY = (double)((float)random.nextGaussian() * Var6 + 0.2F);
                        EntityItem.motionZ = (double)((float)random.nextGaussian() * Var6);

                        if (Items.hasTagCompound())
                        {
                            EntityItem.getEntityItem().setTagCompound((NBTTagCompound)Items.getTagCompound().copy());
                        }
                    }
                }
            }

            World.func_96440_m(Par2, Par3, Par4, Par5);
        }

        super.breakBlock(World, Par2, Par3, Par4, Par5, Par6);
    }

    
    // Let Something Happen On Right Click
    
    public boolean onBlockActivated(World World, int Par2, int Par3, int Par4, EntityPlayer EntityPlayer, int Par6, float Par7, float Par8, float Par9)
    {
        if (World.isRemote)
        {
            return true;
        }
        
        else
        {
            IInventory IInventory = func_94442_h_(World, Par2, Par3, Par4);

            if (IInventory != null)
            {
                EntityPlayer.displayGUIChest(IInventory);
            }

            return true;
        }
    }

    
    // Changes Container String If There Are 2 Trash Cans Next To Each Other
    
    public IInventory func_94442_h_(World World, int Par2, int Par3, int Par4)
    {
        Object Object = (TileEntityTrashCan)World.getBlockTileEntity(Par2, Par3, Par4);

        if (Object == null)
        {
            return null;
        }
        
        else if (World.isBlockSolidOnSide(Par2, Par3 + 1, Par4, DOWN))
        {
            return null;
        }
        
        else if (isOcelotBlockingChest(World, Par2, Par3, Par4))
        {
            return null;
        }
        
        else if (World.getBlockId(Par2 - 1, Par3, Par4) == blockID && (World.isBlockSolidOnSide(Par2 - 1, Par3 + 1, Par4, DOWN) || isOcelotBlockingChest(World, Par2 - 1, Par3, Par4)))
        {
            return null;
        }
        
        else if (World.getBlockId(Par2 + 1, Par3, Par4) == blockID && (World.isBlockSolidOnSide(Par2 + 1, Par3 + 1, Par4, DOWN) || isOcelotBlockingChest(World, Par2 + 1, Par3, Par4)))
        {
            return null;
        }
        
        else if (World.getBlockId(Par2, Par3, Par4 - 1) == blockID && (World.isBlockSolidOnSide(Par2, Par3 + 1, Par4 - 1, DOWN) || isOcelotBlockingChest(World, Par2, Par3, Par4 - 1)))
        {
            return null;
        }
        
        else if (World.getBlockId(Par2, Par3, Par4 + 1) == blockID && (World.isBlockSolidOnSide(Par2, Par3 + 1, Par4 + 1, DOWN) || isOcelotBlockingChest(World, Par2, Par3, Par4 + 1)))
        {
            return null;
        }
        
        else
        {
            if (World.getBlockId(Par2 - 1, Par3, Par4) == blockID)
            {
                Object = new InventoryLargeChest("Dump Container", (TileEntityTrashCan)World.getBlockTileEntity(Par2 - 1, Par3, Par4), (IInventory)Object);
            }

            if (World.getBlockId(Par2 + 1, Par3, Par4) == blockID)
            {
                Object = new InventoryLargeChest("Dump Container", (IInventory)Object, (TileEntityTrashCan)World.getBlockTileEntity(Par2 + 1, Par3, Par4));
            }

            if (World.getBlockId(Par2, Par3, Par4 - 1) == blockID)
            {
                Object = new InventoryLargeChest("Dump Container", (TileEntityTrashCan)World.getBlockTileEntity(Par2, Par3, Par4 - 1), (IInventory)Object);
            }

            if (World.getBlockId(Par2, Par3, Par4 + 1) == blockID)
            {
                Object = new InventoryLargeChest("Dump Container", (IInventory)Object, (TileEntityTrashCan)World.getBlockTileEntity(Par2, Par3, Par4 + 1));
            }

            return (IInventory)Object;
        }
    }
    
    // Creating The Tile
    
    public TileEntity createNewTileEntity(World World)
    {
        TileEntityTrashCan TileEntityTrashCan = new TileEntityTrashCan();
        
        return TileEntityTrashCan;
    }

   
    // Checks If A Ocelot Is Blocking The Chest
    
    public static boolean isOcelotBlockingChest(World World, int Par1, int Par2, int Par3)
    {
        Iterator Iterator = World.getEntitiesWithinAABB(EntityOcelot.class, AxisAlignedBB.getAABBPool().getAABB((double)Par1, (double)(Par2 + 1), (double)Par3, (double)(Par1 + 1), (double)(Par2 + 2), (double)(Par3 + 1))).iterator();
        
        EntityOcelot Entityocelot1;

        do
        {
            if (!Iterator.hasNext())
            {
                return false;
            }

            EntityOcelot EntityOcelot2 = (EntityOcelot)Iterator.next();
            Entityocelot1 = (EntityOcelot)EntityOcelot2;
        }
        
        while (!Entityocelot1.isSitting());
        {
            return true;
        }
    }


    // Lets A Redstone Wire Connect To The Block
    
    public boolean canConnectRedstone(IBlockAccess IBlockAccess, int Var1, int Var2, int Var3, int Var4)
    {
        return true;
    }
    
    
    // Setting Textures
    
    private Icon[] IconBuffer = new Icon[1];
    
    @Override
    @SideOnly(Side.CLIENT)
    
    public void registerIcons(IconRegister IconRegister)
    {
        IconBuffer[0] = IconRegister.registerIcon(Reference.ModID + ":" + "RockCutterBottom");
    }
    
    @Override
    public Icon getIcon(int Par1, int Par2)
    {
        return IconBuffer[0];
    }
}
