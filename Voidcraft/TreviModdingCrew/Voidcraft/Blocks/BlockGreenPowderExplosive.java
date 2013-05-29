package TreviModdingCrew.Voidcraft.Blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import TreviModdingCrew.Voidcraft.Common.Main;
import TreviModdingCrew.Voidcraft.Common.Reference;
import TreviModdingCrew.Voidcraft.Entity.EntityGreenPowderPrimed;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockGreenPowderExplosive extends Block
{
    // Declaration 
    
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
    
    
    // Triggering On Redstone Signal
    
    public void onBlockAdded(World World, int Par2, int Par3, int Par4)
    {
        super.onBlockAdded(World, Par2, Par3, Par4);

        if (World.isBlockIndirectlyGettingPowered(Par2, Par3, Par4))
        {
            onBlockDestroyedByPlayer(World, Par2, Par3, Par4, 1);
            World.setBlockToAir(Par2, Par3, Par4);
        }
    }

    
    // Checking If The Block Next To The Explosive Is Powered
    
    public void onNeighborBlockChange(World World, int Par2, int Par3, int Par4, int Par5)
    {
        if (World.isBlockIndirectlyGettingPowered(Par2, Par3, Par4))
        {
            onBlockDestroyedByPlayer(World, Par2, Par3, Par4, 1);
            World.setBlockToAir(Par2, Par3, Par4);
        }
    }

    
    // How Many It Should Drop On Break
    
    public int quantityDropped(Random Random)
    {
        return 1;
    }

    
    // If A Player Destroys It

    public void onBlockDestroyedByPlayer(World World, int Par2, int Par3, int Par4, int Par5)
    {
        func_94391_a(World, Par2, Par3, Par4, Par5, (EntityLiving)null);
    }

    public void func_94391_a(World World, int Par2, int Par3, int Par4, int Par5, EntityLiving EntityLiving)
    {
        if (!World.isRemote)
        {
            if ((Par5 & 1) == 1)
            {
                EntityGreenPowderPrimed EntityGreenPowderPrimed = new EntityGreenPowderPrimed(World, (double)((float)Par2 + 0.5F), (double)((float)Par3 + 0.5F), (double)((float)Par4 + 0.5F), EntityLiving);
                World.spawnEntityInWorld(EntityGreenPowderPrimed);
                World.playSoundAtEntity(EntityGreenPowderPrimed, "random.fuse", 1.0F, 1.0F);
            }
        }
    }

    
    // Letting It Light Up Using Flint & Steel
    
    public boolean onBlockActivated(World World, int Par2, int Par3, int Par4, EntityPlayer EntityPlayer, int Par6, float Par7, float Par8, float Par9)
    {
        if (EntityPlayer.getCurrentEquippedItem() != null && EntityPlayer.getCurrentEquippedItem().itemID == Item.flintAndSteel.itemID)
        {
            func_94391_a(World, Par2, Par3, Par4, 1, EntityPlayer);
            World.setBlockToAir(Par2, Par3, Par4);
            
            return true;
        }
        
        else
        {
            return super.onBlockActivated(World, Par2, Par3, Par4, EntityPlayer, Par6, Par7, Par8, Par9);
        }
    }

    public boolean canDropFromExplosion(Explosion Explosion)
    {
        return false;
    }
}
