package TreviModdingCrew.Utilities.Block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import TreviModdingCrew.Utilities.Common.Main;
import TreviModdingCrew.Utilities.Tile.TileEntityLumberJacker;

public class BlockLumberJacker extends BlockContainer
{
    // Declaration
    
    public BlockLumberJacker(int Par1, Material Material)
    {
        super(Par1, Material);
        setCreativeTab(Main.UtilitiesBlock);
        setUnlocalizedName("Lumber Jacker");
    }
    
    @Override
    public TileEntity createNewTileEntity(World World)
    {
        return new TileEntityLumberJacker();
    }
    
    
    // Register The Information When Placed Down
    
    public void onBlockAdded(World World, int Par1, int Par2, int Par3)
    {
        super.onBlockAdded(World, Par1, Par2, Par3);
        World.markBlockForUpdate(Par1, Par2, Par3);
    }
    
    public static boolean HasCutted = false;
    
    public static void CutWood(World World, int Par1, int Par2, int Par3)
    {
        if(World.getBlockId(Par1, Par2 + 1, Par3) == Block.wood.blockID)
        {
            World.setBlock(Par1, Par2 + 1, Par3, 0);
            
            Random Number = new Random();
            
            EntityItem Replacement = new EntityItem(World, Par1, Par2 + 1.4, Par3, new ItemStack(Block.planks, 4 + Number.nextInt(3)));
            World.spawnEntityInWorld(Replacement);
        }
        
        if(World.getBlockId(Par1, Par2 + 1, Par3) == Block.planks.blockID)
        {
            World.setBlock(Par1, Par2 + 1, Par3, 0);
            
            Random Number = new Random();
            EntityItem Replacement = new EntityItem(World, Par1, Par2 + 1.4, Par3, new ItemStack(Item.stick, 4 + Number.nextInt(3)));
            World.spawnEntityInWorld(Replacement);
        }
        
        if(World.getBlockId(Par1, Par2 + 1, Par3) == Block.bookShelf.blockID)
        {
            World.setBlock(Par1, Par2 + 1, Par3, 0);
            
            Random Number = new Random();
            
            EntityItem Replacement = new EntityItem(World, Par1, Par2 + 1.4, Par3, new ItemStack(Block.planks, 4 + Number.nextInt(3)));
            World.spawnEntityInWorld(Replacement);
            
            EntityItem Second = new EntityItem(World, Par1, Par2 + 1.4, Par3, new ItemStack(Item.book, 1 + Number.nextInt(2)));
            World.spawnEntityInWorld(Second);
        }
        
        if(World.getBlockId(Par1, Par2 + 1, Par3) == Block.workbench.blockID)
        {
            World.setBlock(Par1, Par2 + 1, Par3, 0);
            
            Random Number = new Random();
            
            EntityItem Replacement = new EntityItem(World, Par1, Par2 + 1.4, Par3, new ItemStack(Block.planks, 1 + Number.nextInt(4)));
            World.spawnEntityInWorld(Replacement);
        }
        
        if(World.getBlockId(Par1, Par2 + 1, Par3) == Block.jukebox.blockID)
        {
            World.setBlock(Par1, Par2 + 1, Par3, 0);
            
            Random Number = new Random();
            
            EntityItem Replacement = new EntityItem(World, Par1, Par2 + 1.4, Par3, new ItemStack(Block.planks, 1 + Number.nextInt(9)));
            World.spawnEntityInWorld(Replacement);
            
            EntityItem Second = new EntityItem(World, Par1, Par2 + 1.4, Par3, new ItemStack(Item.diamond, 1));
            World.spawnEntityInWorld(Second);
        }
        
        if(World.getBlockId(Par1, Par2 + 1, Par3) == Block.music.blockID)
        {
            World.setBlock(Par1, Par2 + 1, Par3, 0);
            
            Random Number = new Random();
            
            EntityItem Replacement = new EntityItem(World, Par1, Par2 + 1.4, Par3, new ItemStack(Block.planks, 1 + Number.nextInt(9)));
            World.spawnEntityInWorld(Replacement);
            
            EntityItem Second = new EntityItem(World, Par1, Par2 + 1.4, Par3, new ItemStack(Item.redstone, 1));
            World.spawnEntityInWorld(Second);
        }
        
        if(World.getBlockId(Par1, Par2 + 1, Par3) == Block.chest.blockID)
        {
            World.setBlock(Par1, Par2 + 1, Par3, 0);
            
            Random Number = new Random();
            
            EntityItem Replacement = new EntityItem(World, Par1, Par2 + 1.4, Par3, new ItemStack(Block.planks, 1 + Number.nextInt(9)));
            World.spawnEntityInWorld(Replacement);
        }
        
        if(World.getBlockId(Par1, Par2 + 1, Par3) == Block.pressurePlatePlanks.blockID)
        {
            World.setBlock(Par1, Par2 + 1, Par3, 0);
            
            Random Number = new Random();
            
            EntityItem Replacement = new EntityItem(World, Par1, Par2 + 1.4, Par3, new ItemStack(Block.planks, 1 + Number.nextInt(2)));
            World.spawnEntityInWorld(Replacement);
        }
        
        if(World.getBlockId(Par1, Par2 + 1, Par3) == Block.doorWood.blockID)
        {
            World.setBlock(Par1, Par2 + 1, Par3, 0);
            
            Random Number = new Random();
            
            EntityItem Replacement = new EntityItem(World, Par1, Par2 + 1.4, Par3, new ItemStack(Block.planks, 1 + Number.nextInt(6)));
            World.spawnEntityInWorld(Replacement);
        }
        
        if(World.getBlockId(Par1, Par2 + 1, Par3) == Block.fence.blockID)
        {
            World.setBlock(Par1, Par2 + 1, Par3, 0);
            
            Random Number = new Random();
            
            EntityItem Replacement = new EntityItem(World, Par1, Par2 + 1.4, Par3, new ItemStack(Item.stick, 1 + Number.nextInt(6)));
            World.spawnEntityInWorld(Replacement);
        }
        
        if(World.getBlockId(Par1, Par2 + 1, Par3) == Block.fenceGate.blockID)
        {
            World.setBlock(Par1, Par2 + 1, Par3, 0);
            
            Random Number = new Random();
            
            EntityItem Replacement = new EntityItem(World, Par1, Par2 + 1.4, Par3, new ItemStack(Block.planks, 1 + Number.nextInt(2)));
            World.spawnEntityInWorld(Replacement);
            
            EntityItem Second = new EntityItem(World, Par1, Par2 + 1.4, Par3, new ItemStack(Item.stick, 1 + Number.nextInt(4)));
            World.spawnEntityInWorld(Second);
        }
        
        if(World.getBlockId(Par1, Par2 + 1, Par3) == Block.ladder.blockID)
        {
            World.setBlock(Par1, Par2 + 1, Par3, 0);
            
            Random Number = new Random();
            
            EntityItem Replacement = new EntityItem(World, Par1, Par2 + 1.4, Par3, new ItemStack(Item.stick, 1 + Number.nextInt(7)));
            World.spawnEntityInWorld(Replacement);
        }
        
        if(World.getBlockId(Par1, Par2 + 1, Par3) == Block.trapdoor.blockID)
        {
            World.setBlock(Par1, Par2 + 1, Par3, 0);
            
            Random Number = new Random();
            
            EntityItem Replacement = new EntityItem(World, Par1, Par2 + 1.4, Par3, new ItemStack(Block.planks, 1 + Number.nextInt(3)));
            World.spawnEntityInWorld(Replacement);
        }
        
        if(World.getBlockId(Par1, Par2 + 1, Par3) == Block.signPost.blockID)
        {
            World.setBlock(Par1, Par2 + 1, Par3, 0);
            
            Random Number = new Random();
            
            EntityItem Replacement = new EntityItem(World, Par1, Par2 + 1.4, Par3, new ItemStack(Block.planks, 1 + Number.nextInt(6)));
            World.spawnEntityInWorld(Replacement);
            
            EntityItem Second = new EntityItem(World, Par1, Par2 + 1.4, Par3, new ItemStack(Item.stick, 1));
            World.spawnEntityInWorld(Second);
        }
    }
}
