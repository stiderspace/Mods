package TreviModdingCrew.Voidcraft.Items;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;

import TreviModdingCrew.Voidcraft.Common.Main;
import TreviModdingCrew.Voidcraft.Common.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockGreenPowderExplosive extends Block
{
    public BlockGreenPowderExplosive(int Par1, Material Material)
    {
        super(Par1, Material);
        setCreativeTab(Main.Voidcraft);
    }
    
    // Setting Textures
    
    private Icon[] IconBuffer = new Icon[3];
    
    @Override
    @SideOnly(Side.CLIENT)
    
    public void registerIcons(IconRegister IconRegister)
    {
        IconBuffer[0] = IconRegister.registerIcon(Reference.ModID + ":" + "GreenPowderExplosiveBottom");
        IconBuffer[1] = IconRegister.registerIcon(Reference.ModID + ":" + "GreenPowderExplosiveSide");
        IconBuffer[2] = IconRegister.registerIcon(Reference.ModID + ":" + "GreenPowderExplosiveTop");
    }
    
    @Override
    public Icon getIcon(int Par1, int Par2)
    {
        if(Par1 == 0)
        {
            return IconBuffer[0];
        }
        
        if(Par1 == 2)
        {
            return IconBuffer[1];
        }
        
        if(Par1 == 3)
        {
            return IconBuffer[1];
        }
        
        if(Par1 == 4)
        {
            return IconBuffer[1];
        }
        
        if(Par1 == 5)
        {
            return IconBuffer[1];
        }
            
        return IconBuffer[2];
    }
    
    @Override
    public Icon getBlockTexture(IBlockAccess Par1, int Var1, int Var2, int Var3, int Par2)
    {
        if(Par2 == 0)
        {
            return IconBuffer[0];
        }
        
        if(Par2 == 2)
        {
            return IconBuffer[1];
        }
        
        if(Par2 == 3)
        {
            return IconBuffer[1];
        }
        
        if(Par2 == 4)
        {
            return IconBuffer[1];
        }
        
        if(Par2 == 5)
        {
            return IconBuffer[1];
        }
            
        return IconBuffer[2];
    }
}
