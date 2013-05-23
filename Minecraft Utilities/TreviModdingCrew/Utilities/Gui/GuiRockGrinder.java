package TreviModdingCrew.Utilities.Gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import TreviModdingCrew.Utilities.Container.ContainerRockGrinder;
import TreviModdingCrew.Utilities.Tile.TileEntityRockGrinder;

public class GuiRockGrinder extends GuiContainer
{
    // Declaration
    
	private TileEntityRockGrinder Inventory;
	public static int MoreFuel;
	
    public GuiRockGrinder(InventoryPlayer InventoryPlayer, TileEntityRockGrinder TileEntityRockSmasher)
    {
        super(new ContainerRockGrinder(InventoryPlayer, TileEntityRockSmasher));
        Inventory = TileEntityRockSmasher; 
    }
 
    
    // Before The Gui Loads
    
    @Override
    public void initGui() 
    {
        super.initGui();
        
        buttonList.add(new GuiButton(1, width / 2 + 3, height / 4 + 160, StatCollector.translateToLocal("Increase Speed")));
        buttonList.add(new GuiButton(2, width / 2 - 203, height / 4 + 160, StatCollector.translateToLocal("Decrease Speed")));
    }
    
    protected void actionPerformed(GuiButton GuiButton) 
    {
        switch(GuiButton.id) 
        {
            case 1: if(Inventory.Speed < 5)
                    {
            			Inventory.Speed++;
            			
                    }
            break;
            
            case 2: if(Inventory.Speed > 1)
                    {
            			Inventory.Speed--;	
            			
                    }
            break;
        }
    }
    
    
    // Drawing Text On Gui
    
    protected void drawGuiContainerForegroundLayer(int Par1, int Par2)
    {
        String Name = ("Rock Grinder");
        String SpeedValue = "Speed: " + Inventory.Speed;
        
        fontRenderer.drawString(Name, xSize / 2 - fontRenderer.getStringWidth(Name) / 2, 6, 4210752);
        fontRenderer.drawString(SpeedValue, xSize / 2 + 60 - fontRenderer.getStringWidth(SpeedValue) / 2, 72, 4210752);
        fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, (ySize - 96) + 2, 4210752);
    }
 

    // Drawing Background
    
    protected void drawGuiContainerBackgroundLayer(float Par1, int Par2, int Par3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture("/TreviModdingCrew/Utilities/Textures/Gui/Rock Grinder.png");
        
        int Var1 = (width - xSize) / 2;
        int Var2 = (height - ySize) / 2;
        int Var3;
        
        drawTexturedModalRect(Var1, Var2, 0, 0, this.xSize, this.ySize);

        if (Inventory.isBurning())
        {
            Var3 = Inventory.getBurnTimeRemainingScaled(12);
                   drawTexturedModalRect(Var1 + 56, Var2 + 36 + 12 - Var3, 176, 12 - Var3, 14, Var3 + 2);
        }

        Var3 = Inventory.getCookProgressScaled(24);
               drawTexturedModalRect(Var1 + 79, Var2 + 34, 176, 14, Var3 + 1, 16);
    }
}