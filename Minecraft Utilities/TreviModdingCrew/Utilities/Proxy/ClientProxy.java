package TreviModdingCrew.Utilities.Proxy;

import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

import TreviModdingCrew.Utilities.Common.Main;
import TreviModdingCrew.Utilities.Entity.EntityBomb;
import TreviModdingCrew.Utilities.Handler.KeyBindHandler;
import TreviModdingCrew.Utilities.Handler.SoundHandler;
import TreviModdingCrew.Utilities.Renders.RenderBomb;
import TreviModdingCrew.Utilities.Renders.RenderFilter;
import TreviModdingCrew.Utilities.Renders.RenderLumberJacker;
import TreviModdingCrew.Utilities.Renders.RenderMagnet;
import TreviModdingCrew.Utilities.Renders.ThrowableBomb;
import TreviModdingCrew.Utilities.Renders.TileRenderLumberJacker;
import TreviModdingCrew.Utilities.Tile.TileEntityLumberJacker;

import cpw.mods.fml.client.registry.ClientRegistry;
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
        MinecraftForgeClient.registerItemRenderer(Main.LumberJacker.blockID, new RenderLumberJacker());
        
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLumberJacker.class, new TileRenderLumberJacker());
        
		RenderingRegistry.registerEntityRenderingHandler(EntityBomb.class, new ThrowableBomb(0, "/TreviModdingCrew/Utilities/Textures/Render/Throwable.png"));   
		
		super.LoadRenderers();
	}
    
    public void KeyBinding()
    {
        KeyBindingRegistry.registerKeyBinding(new KeyBindHandler());
        
        super.KeyBinding();
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void LoadSounds()
    {
        MinecraftForge.EVENT_BUS.register(new SoundHandler()); 
    }
}
