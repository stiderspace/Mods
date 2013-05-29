package TreviModdingCrew.LiquidGun.Render;

import net.minecraft.block.Block;
import net.minecraft.client.particle.EntitySmokeFX;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.src.ModLoader;
import net.minecraftforge.liquids.IBlockLiquid;

import TreviModdingCrew.LiquidGun.Entity.EntityLiquidBullet;

public class RenderLiquidBullet extends Render
{
    @Override
    public void doRender(Entity Entity, double Par1, double Par2, double Par3, float Par4, float Par5)
    {
        if(Entity instanceof EntityLiquidBullet)
        {
            try
            {
                int Par6 = ((EntityLiquidBullet)Entity).getLiquidItemID();
                byte[] Par7 = ((IBlockLiquid)Block.blocksList[Par6]).getLiquidRGB();
               
                EntitySmokeFX EntitySmokeFX = new EntitySmokeFX(Entity.worldObj, Par1, Par2, Par3, 0, 0, 0);
                ModLoader.getMinecraftInstance().effectRenderer.addEffect(EntitySmokeFX);
            }
            
            catch(Exception Exception)
            {
               
            }
        }
    }
}