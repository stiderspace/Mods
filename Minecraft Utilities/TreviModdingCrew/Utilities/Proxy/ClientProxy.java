package TreviModdingCrew.Utilities.Proxy;

import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import TreviModdingCrew.Utilities.Common.Main;
import TreviModdingCrew.Utilities.Entity.EntityBomb;
import TreviModdingCrew.Utilities.Handler.KeyBindHandler;
import TreviModdingCrew.Utilities.Renders.RenderBomb;
import TreviModdingCrew.Utilities.Renders.RenderFilter;
import TreviModdingCrew.Utilities.Renders.RenderMagnet;
import TreviModdingCrew.Utilities.Renders.ThrowableBomb;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ClientProxy extends CommonProxy 
{
	// The Method For The Renders
	
    @SideOnly(Side.CLIENT)
	@Override
	public void LoadRenderers()
	{
        MinecraftForgeClient.registerItemRenderer(Main.Filter.itemID, (IItemRenderer) new RenderFilter());
        MinecraftForgeClient.registerItemRenderer(Main.Bomb.itemID, (IItemRenderer) new RenderBomb());
        MinecraftForgeClient.registerItemRenderer(Main.Magnet.itemID, (IItemRenderer) new RenderMagnet());
        
		RenderingRegistry.registerEntityRenderingHandler(EntityBomb.class, new ThrowableBomb(0, "/TreviModdingCrew/Utilities/Textures/Render/Throwable.png"));   
		
		super.LoadRenderers();
	}
    
    public void KeyBinding()
    {
        KeyBindingRegistry.registerKeyBinding(new KeyBindHandler());
        
        super.KeyBinding();
    }
}
