package TreviModdingCrew.Utilities.Block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import TreviModdingCrew.Utilities.Common.Main;
import TreviModdingCrew.Utilities.Common.Reference;
import TreviModdingCrew.Utilities.Tile.TileEntityScarecrow;

public class BlockScarecrow extends BlockContainer
{
    // Declaration
    
    public BlockScarecrow(int Par1, Material Material)
    {
        super(Par1, Material);
        setCreativeTab(Main.UtilitiesBlock);
    }
    
    
    // Setting Textures
    
    private Icon[] IconBuffer = new Icon[1];
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister IconRegister)
    {
        IconBuffer[0] = IconRegister.registerIcon(Reference.ModID + ":" + "Cobblestone");
    }
    
    @Override
    public Icon getIcon(int Par1, int Par2)
    {
        return IconBuffer[0];
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
