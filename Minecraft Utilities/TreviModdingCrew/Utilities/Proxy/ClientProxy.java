package TreviModdingCrew.Utilities.Proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import TreviModdingCrew.Utilities.Common.Main;
import TreviModdingCrew.Utilities.Entity.EntityBomb;
import TreviModdingCrew.Utilities.Gui.GuiMobDetector;
import TreviModdingCrew.Utilities.Gui.GuiRockCutter;
import TreviModdingCrew.Utilities.Gui.GuiRockGrinder;
import TreviModdingCrew.Utilities.Handler.KeyBindHandler;
import TreviModdingCrew.Utilities.Handler.SoundHandler;
import TreviModdingCrew.Utilities.Renders.RenderBomb;
import TreviModdingCrew.Utilities.Renders.RenderEggHatcher;
import TreviModdingCrew.Utilities.Renders.RenderFilter;
import TreviModdingCrew.Utilities.Renders.RenderLumberJacker;
import TreviModdingCrew.Utilities.Renders.RenderWashingMachine;
import TreviModdingCrew.Utilities.Renders.ThrowableBomb;
import TreviModdingCrew.Utilities.Tile.TileEntityMobDetector;
import TreviModdingCrew.Utilities.Tile.TileEntityEggHatcher;
import TreviModdingCrew.Utilities.Tile.TileEntityLumberJacker;
import TreviModdingCrew.Utilities.Tile.TileEntityRockGrinder;
import TreviModdingCrew.Utilities.Tile.TileEntityWashingMachine;
import TreviModdingCrew.Utilities.Tile.TileRenderEggHatcher;
import TreviModdingCrew.Utilities.Tile.TileRenderLumberJacker;
import TreviModdingCrew.Utilities.Tile.TileRenderWashingMachine;
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
        MinecraftForgeClient.registerItemRenderer(Main.LumberJacker.blockID, new RenderLumberJacker());
        MinecraftForgeClient.registerItemRenderer(Main.EggHatcher.blockID, new RenderEggHatcher());
        MinecraftForgeClient.registerItemRenderer(Main.WashingMachine.blockID, new RenderWashingMachine());
        
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLumberJacker.class, new TileRenderLumberJacker());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEggHatcher.class, new TileRenderEggHatcher());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWashingMachine.class, new TileRenderWashingMachine());
        
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
	
	@Override
    public Object getClientGuiElement(int ID, EntityPlayer EntityPlayer, World World, int Var1, int Var2, int Var3)
    {
        TileEntity TileEntity = World.getBlockTileEntity(Var1, Var2, Var3);

        if(TileEntity != null)
        {
            switch(ID)
            {
                case 0: return new GuiRockGrinder(EntityPlayer.inventory, (TileEntityRockGrinder)TileEntity);
			
				case 2: return new GuiMobDetector(EntityPlayer, (TileEntityMobDetector)TileEntity);
                
                default: return null; 
            }
        }
        
        if(ID == 1)
        {
            return new GuiRockCutter(EntityPlayer.inventory, World, Var1, Var2, Var3);
        }
            
		return null;  
    }
}
