package TreviModdingCrew.Utilities.Gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import TreviModdingCrew.Utilities.Container.ContainerRockGrinder;
import TreviModdingCrew.Utilities.Tile.TileEntityRockGrinder;
import TreviModdingCrew.Utilities.Util.MathHelper;

public class GuiRockGrinder extends GuiContainer
{
    // Declaration
    
    private int xMouse = 0;
    private int yMouse = 0;
    
    private TileEntityRockGrinder Tile;
    
    public GuiRockGrinder(InventoryPlayer InventoryPlayer, TileEntityRockGrinder TileEntityRockGrinder)
    {
        super(new ContainerRockGrinder(InventoryPlayer, TileEntityRockGrinder));
        Tile = TileEntityRockGrinder;
    }
 
    
    // Before The Gui Loads
    
    @Override
    public void initGui() 
    {
        super.initGui();
        
        buttonList.add(new GuiButton(1, guiLeft + 180, guiTop + 5, 100, 20, ("Increase Speed")));
        buttonList.add(new GuiButton(2, guiLeft + 180, guiTop + 30, 100, 20, ("Decrease Speed")));
        buttonList.add(new GuiButton(3, guiLeft + 180, guiTop + 140, 100, 20, ("Close Grinder")));
       
        GuiButton Button;
        
        if(Tile.Speed == 5)
        {
            Button = (GuiButton)buttonList.get(0);
            Button.enabled = false;
        }
        
        else if(Tile.Speed == 1)
        {
            Button = (GuiButton)buttonList.get(1);
            Button.enabled = false;
        }
    }
    
    
    // Does Something If You Press One Of The Buttons
    
    protected void actionPerformed(GuiButton GuiButton) 
    {
        if(GuiButton.id == 1)
        {
            if(Tile.Speed < 5)
            {
                Tile.Speed++;
                
                GuiButton Decrease = (GuiButton)buttonList.get(1);
                Decrease.enabled = true;
                
                if(Tile.Speed == 5)
                {
                    GuiButton Button = (GuiButton)buttonList.get(0);
                    Button.enabled = false;
                }
                
                else
                {
                    GuiButton Button = (GuiButton)buttonList.get(0);
                    Button.enabled = true;
                }
            }
        }
        
        if(GuiButton.id == 2)
        {
            if(Tile.Speed > 1)
            {
                Tile.Speed--;
                
                GuiButton Increase = (GuiButton)buttonList.get(0);
                Increase.enabled = true;
                
                if(Tile.Speed == 1)
                {
                    GuiButton Button = (GuiButton)buttonList.get(1);
                    Button.enabled = false;
                }
                
                else
                {
                    GuiButton Button = (GuiButton)buttonList.get(1);
                    Button.enabled = true;
                }
            }
        }
        
        if(GuiButton.id == 3)
        {
            mc.thePlayer.closeScreen();
        }
    }
    
    
    // Drawing Text On Gui
    
    protected void drawGuiContainerForegroundLayer(int Par1, int Par2)
    {
        String Name = "Rock Grinder";
        String SpeedValue = "Speed: " + TileEntityRockGrinder.Speed;
        
        fontRenderer.drawString(Name, xSize / 2 - fontRenderer.getStringWidth(Name) / 2, 6, 4210752);
        fontRenderer.drawString(SpeedValue, xSize / 2 + 60 - fontRenderer.getStringWidth(SpeedValue) / 2, 72, 4210752);
        fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, (ySize - 96) + 2, 4210752);
        
        drawMouse();
    }
    
    
    // Getting The Mouse Cordinates Depending On The Screensize 
    
    @Override
    public void handleMouseInput()
    {
        int xPos = Mouse.getEventX() * width / mc.displayWidth;
        int yPos = height - Mouse.getEventY() * height / mc.displayHeight - 1;
    
        xMouse = (xPos - guiLeft);
        yMouse = (yPos - guiTop);
    
        super.handleMouseInput();
    }
 

    // Drawing Background
    
    protected void drawGuiContainerBackgroundLayer(float Par1, int Par2, int Par3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture("/TreviModdingCrew/Utilities/Textures/Gui/Rock Grinder.png");
        
        int Var1 = (width - xSize) / 2;
        int Var2 = (height - ySize) / 2;
        int Var3;
        
        drawTexturedModalRect(Var1, Var2, 0, 0, xSize, ySize);

        if (Tile.MyProvider.getEnergyStored() > 0)
        {
            int showUntil = 0;
            
            for(int i=1; i<53; i++)
            {
                if(Tile.MyProvider.getEnergyStored() >= i * 153.846)
                {
                    showUntil = i;
                }
            }
            
            drawTexturedModalRect(Var1 + 8, Var2 + 69 - showUntil, 176, 84 - showUntil, 13, showUntil + 31);
        }
        
        if (Tile.isBurning())
        {
            Var3 = Tile.getBurnTimeRemainingScaled(12);
            drawTexturedModalRect(Var1 + 57, Var2 + 36 + 12 - Var3, 176, 12 - Var3, 14, Var3 + 2);
        }

        Var3 = Tile.getCookProgressScaled(24);
        drawTexturedModalRect(Var1 + 79, Var2 + 34, 176, 14, Var3 + 1, 16);
    }
    
    
    // Drawing The Tooltip On The Screen When The Mouse Hovers Over The Right Spot
    
    private void drawMouse()
    {
        if(xMouse >= 8 && yMouse >= 16)
        {
            if(xMouse <= 21 && yMouse <= 69)
            {
                drawCreativeTabHoveringText("Energy: " + MathHelper.round(Tile.MyProvider.getEnergyStored()) + "/" + Tile.MyProvider.getMaxEnergyStored(), xMouse, yMouse);
            }
            
            return;
        }
    }
}