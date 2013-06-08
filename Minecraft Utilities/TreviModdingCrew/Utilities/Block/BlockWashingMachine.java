package TreviModdingCrew.Utilities.Block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import TreviModdingCrew.Utilities.Common.Main;
import TreviModdingCrew.Utilities.Common.Reference;
import TreviModdingCrew.Utilities.Tile.TileEntityWashingMachine;

public class BlockWashingMachine extends BlockContainer
{
    // Declaration
    
    public BlockWashingMachine(int Par1, Material Rock)
    {
        super(Par1, Rock);
        setUnlocalizedName("Egg Hatcher");
        setHardness(5F);
        setCreativeTab(Main.UtilitiesBlock);
    }
    
    // Custom Block Render
    
    @Override
    public boolean hasTileEntity(int Metadata)
    {
        return true;
    }
    
    
    // Let Something Happen On Right Click
    
    @Override
    public boolean onBlockActivated(World World, int Var1, int Var2, int Var3, EntityPlayer EntityPlayer, int Par1, float Par2, float Par3, float Par4)
    {
        if(EntityPlayer.getHeldItem() == null)
        {
            return false;
        }
        
        if(EntityPlayer.getHeldItem().itemID == Block.cloth.blockID)
        {
            TileEntityWashingMachine Tile = (TileEntityWashingMachine) World.getBlockTileEntity(Var1, Var2, Var3);
            
            Random Var4 = new Random();
            
            if(Tile.CanPutWoolIn == false)
            {
                return false;
            }
            
            if (Tile.CanPutWoolIn == true)
            {
                if(!World.isRemote)
                {
                    EntityPlayer.inventory.consumeInventoryItem(Block.cloth.blockID);
                   
                    Tile.CanPutWoolIn = false;
                    Tile.TickToWash = 50 + Var4.nextInt(100);
                    World.markBlockForUpdate(Var1, Var2, Var3);
                }
            } 
            
            return true;
        }
        
        return false;
    }
    
    
    // Creating The Tile
    
    @Override
    public TileEntity createNewTileEntity(World World)
    {
        TileEntityWashingMachine Tile = new TileEntityWashingMachine();
        
        Tile.TickToWash = 0;
        Tile.CanPutWoolIn = true;
        
        return Tile;
    }
    
    
    // Lets Something Happen If The Block Breaks
    
    public void breakBlock(World World, int Par2, int Par3, int Par4, int Par5, int Par6)
    {
        TileEntityWashingMachine Tile = (TileEntityWashingMachine) World.getBlockTileEntity(Par2, Par3, Par4);
        
        if(Tile.TickToWash > 0)
        {
            EntityItem Var5 = new EntityItem(World, Par2 + 0, Par3 + 0.5, Par4 + 0, new ItemStack(Item.silk, 0 + World.rand.nextInt(2)));
            World.spawnEntityInWorld(Var5);
        }
        
        super.breakBlock(World, Par2, Par3, Par4, Par5, Par6);
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
    
    
    // Lets A Redstone Wire Connect To The Block
    
    public boolean canConnectRedstone(IBlockAccess IBlockAccess, int Var1, int Var2, int Var3, int Var4)
    {
        return true;
    }
}