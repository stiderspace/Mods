package TreviModdingCrew.Utilities.Block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import TreviModdingCrew.Utilities.Common.Main;
import TreviModdingCrew.Utilities.Common.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockRockCutter extends Block
{
    // Declaration
    
    public BlockRockCutter(int Par1, Material Rock, boolean Thing)
    {
        super(Par1, Rock);
        setUnlocalizedName("Rock Cutter");
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
        IconBuffer[2] = IconRegister.registerIcon(Reference.ModID + ":" + "RockCutterSide");
        IconBuffer[3] = IconRegister.registerIcon(Reference.ModID + ":" + "MachineSide");
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
            return IconBuffer[3];
        }
        
        if(Par1 == 3)
        {
            return IconBuffer[3];
        }
        
        if(Par1 == 4)
        {
            return IconBuffer[2];
        }
        
        if(Par1 == 5)
        {
            return IconBuffer[3];
        }
            
        return IconBuffer[1];
    }
    
    
    // Opens The Gui On Right Click
    
    @Override
    public boolean onBlockActivated(World World, int Var1, int Var2, int Var3, EntityPlayer EntityPlayer, int Par1, float Par2, float Par3, float Par4)
    {
        EntityPlayer.openGui(Main.Instance, 1, World, Var1, Var2, Var3);
      
        return true;
    }
}
