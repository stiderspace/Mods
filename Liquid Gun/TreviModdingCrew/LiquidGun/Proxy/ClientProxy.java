package TreviModdingCrew.LiquidGun.Proxy;

import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

import TreviModdingCrew.LiquidGun.Common.Main;
import TreviModdingCrew.LiquidGun.Render.RenderLiquidGun;

public class ClientProxy extends CommonProxy
{
    public IItemRenderer ItemRenderLiquidGun = new RenderLiquidGun();
  
    @Override
    public void LoadRenderers()
    {
        super.LoadRenderers();
        
        MinecraftForgeClient.registerItemRenderer(Main.LiquidGun.itemID, ItemRenderLiquidGun);
    }
}
