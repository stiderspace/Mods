package TreviModdingCrew.Utilities.Block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import TreviModdingCrew.Utilities.Common.Main;
import TreviModdingCrew.Utilities.Tile.TileEntityMobDetector;

public class BlockMobDetector extends BlockContainer
{
    // Declaration 
   
    public BlockMobDetector(int Par1, Material Material)
    {
        super(Par1, Material);
        setCreativeTab(Main.UtilitiesBlock);
    }

    
    // Opens The Gui On Right Click
    
    @Override
    public boolean onBlockActivated(World World, int Var1, int Var2, int Var3, EntityPlayer EntityPlayer, int Par1, float Par2, float Par3, float Par4)
    {
        EntityPlayer.openGui(Main.Instance, 2, World, Var1, Var2, Var3);
      
        return true;
    }

    
    // Lets A Redstone Wire Connect To The Block
    
    public boolean canConnectRedstone(IBlockAccess IBlockAccess, int Var1, int Var2, int Var3, int Var4)
    {
        return true;
    }
    
    
    // Creating A Tile
    
    @Override
    public TileEntity createNewTileEntity(World World)
    {
        return new TileEntityMobDetector();
    }   
}
