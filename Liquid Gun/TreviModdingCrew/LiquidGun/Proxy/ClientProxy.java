package TreviModdingCrew.LiquidGun.Proxy;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

import TreviModdingCrew.LiquidGun.Common.Main;
import TreviModdingCrew.LiquidGun.Entity.EntityLiquidBullet;
import TreviModdingCrew.LiquidGun.Render.RenderLiquidBullet;
import TreviModdingCrew.LiquidGun.Render.RenderLiquidGun;

public class ClientProxy extends CommonProxy
{
    public IItemRenderer ItemRenderLiquidGun = new RenderLiquidGun();
  
    @Override
    public void LoadRenderers()
    {
        super.LoadRenderers();
        
        MinecraftForgeClient.registerItemRenderer(Main.LiquidGun.itemID, ItemRenderLiquidGun);
        
        RenderingRegistry.registerEntityRenderingHandler(EntityLiquidBullet.class, new RenderLiquidBullet(0, "/TreviModdingCrew/LiquidGun/Textures/Bullet.png"));
    }
}
