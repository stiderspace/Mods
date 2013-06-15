package TreviModdingCrew.LiquidGun.Proxy;

import net.minecraft.client.renderer.entity.Render;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

import TreviModdingCrew.LiquidGun.Common.Main;
import TreviModdingCrew.LiquidGun.Render.RenderLiquidGun;
import TreviModdingCrew.LiquidGun.Render.RenderLiquidBullet;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{
    public IItemRenderer ItemRenderLiquidGun = new RenderLiquidGun();
    public Render RenderLiquidBullet = new RenderLiquidBullet();
    
    @Override
    public void LoadRenderers()
    {
        super.LoadRenderers();
        
        MinecraftForgeClient.registerItemRenderer(Main.LiquidGun.itemID, ItemRenderLiquidGun);
        RenderingRegistry.registerEntityRenderingHandler(TreviModdingCrew.LiquidGun.Entity.EntityLiquidBullet.class, RenderLiquidBullet);
    }
}
