package TreviModdingCrew.Utilities.Gui;

import javax.swing.JOptionPane;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import TreviModdingCrew.Utilities.Container.ContainerRockCutter;

public class GuiRockCutter extends GuiContainer
{
    // Declaration
    
    public GuiRockCutter(InventoryPlayer InventoryPlayer, World World, int Var1, int Var2, int Var3)
    {
        super(new ContainerRockCutter(InventoryPlayer, World, Var1, Var2, Var3));
    }
    
    
    // Before The Gui Loads
    
    @Override
    public void initGui() 
    {
        super.initGui();
        
        buttonList.add(new GuiButton(1, guiLeft + 179, guiTop + 12, 71, 20, ("Close")));
    }
    
    
    // Does Something If You Press One Of The Buttons
    
    protected void actionPerformed(GuiButton GuiButton) 
    {
        if(GuiButton.id == 1)
        {
            mc.thePlayer.closeScreen();
        }
    }
    
    
    // Drawing Text On Gui
    
    protected void drawGuiContainerForegroundLayer(int Par1, int Par2)
    {
        String Name = ("Rock Cutter");
        
        fontRenderer.drawString(Name, xSize / 2 - fontRenderer.getStringWidth(Name) / 2, 6, 4210752);
        fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, (ySize - 96) + 2, 4210752);
    }
    
    
    // Drawing Background
    
    protected void drawGuiContainerBackgroundLayer(float Par1, int Par2, int Par3)
    {
        mc.renderEngine.bindTexture("/TreviModdingCrew/Utilities/Textures/Gui/Rock Cutter.png");

        GL11.glColor4f(1F, 1F, 1F, 1F);
        
        int Var4 = (width - xSize) / 2;
        int Var5 = (height - ySize) / 2;
        
        drawTexturedModalRect(Var4, Var5, 0, 0, xSize + 80, ySize);
    }
}
