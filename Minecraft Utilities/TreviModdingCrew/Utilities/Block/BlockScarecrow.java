package TreviModdingCrew.Utilities.Block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import TreviModdingCrew.Utilities.Common.Main;
import TreviModdingCrew.Utilities.Tile.TileEntityScarecrow;

public class BlockScarecrow extends BlockContainer
{
    // Declaration
    
    public BlockScarecrow(int Par1, Material Material)
    {
        super(Par1, Material);
        setCreativeTab(Main.UtilitiesBlock);
    }
    
    
    // Custom Block Render
    
    @Override
    public boolean hasTileEntity(int Metadata)
    {
        return true;
    }
    
    // Creating The Tile
    
    @Override
    public TileEntity createNewTileEntity(World World)
    {
        TileEntityScarecrow Tile = new TileEntityScarecrow();
        
        return Tile;
    }
    
    
    // Lets Something Happen If The Block Breaks
    
    public void breakBlock(World World, int Par2, int Par3, int Par4, int Par5, int Par6)
    {
        super.breakBlock(World, Par2, Par3, Par4, Par5, Par6);
    }
}
