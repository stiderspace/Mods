package TreviModdingCrew.Voidcraft.Blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import TreviModdingCrew.Voidcraft.Common.Main;
import TreviModdingCrew.Voidcraft.Common.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;

public class BlockGreenPowderOre extends Block
{
    public BlockGreenPowderOre(int Par1, Material Material)
    {
        super(Par1, Material);
        setCreativeTab(Main.Voidcraft);
    }
    
    
    // Setting Textures
    
    private Icon[] IconBuffer = new Icon[1];
    
    @Override
    @SideOnly(Side.CLIENT)
    
    public void registerIcons(IconRegister IconRegister)
    {
        IconBuffer[0] = IconRegister.registerIcon(Reference.ModID + ":" + "GreenPowderOre");
    }
    
    @Override
    public Icon getIcon(int Par1, int Par2)
    {    
        return IconBuffer[0];
    }
    
    @Override
    public Icon getBlockTexture(IBlockAccess Par1, int Var1, int Var2, int Var3, int Par2)
    {
        return IconBuffer[0];
    }
    
    
    // Sets What You Get On Block Break
    
    public int idDropped(int par1, Random Random, int par3)
    {
        return  Item.coal.itemID;
    }
}
