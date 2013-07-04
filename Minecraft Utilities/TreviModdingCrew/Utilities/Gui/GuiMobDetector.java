package TreviModdingCrew.Utilities.Gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.player.EntityPlayer;

import org.lwjgl.opengl.GL11;

import TreviModdingCrew.Utilities.Tile.TileEntityMobDetector;

public class GuiMobDetector extends GuiScreen
{
    // Declaration 
    
    private int xMouse = 0;
    private int yMouse = 0;
    
    private TileEntityMobDetector Tile;

    private GuiTextField TextField;
   
    public GuiMobDetector(EntityPlayer EntityPlayer, TileEntityMobDetector TileEntityAnimalRadar)
    {
        new GuiButton(0, 0, 0, 0, 0, "");
        Tile = TileEntityAnimalRadar;
    }
    
    public final int xSizeOfTexture = 256;
    public final int ySizeOfTexture = 165;
    
    public void initGui()
    {
        buttonList.clear();

        int posX = (width - xSizeOfTexture) / 2;
        int posY = (height - ySizeOfTexture) / 2;
    
        buttonList.add(new GuiButton(1, posX + 219, posY + 12, 71, 20, "Close"));
        buttonList.add(new GuiButton(2, posX + 113, posY + 18, 50, 20, "Next"));
        buttonList.add(new GuiButton(3, posX + 48, posY + 18, 60, 20, "Previous"));
        
        GuiButton Button;
        
        if(Tile.MobID == 3)
        {
            Button = (GuiButton)buttonList.get(1);
            Button.enabled = false;
        }
        
        else if(Tile.MobID == 0)
        {
            Button = (GuiButton)buttonList.get(2);
            Button.enabled = false;
        }
        
        TextField = new GuiTextField(fontRenderer, posX + 169, posY + 58, 32, 15);
        
        TextField.setFocused(true);
        TextField.setMaxStringLength(4);
    }
    
    public void keyTyped(char Par1, int Par2)
    {
        super.keyTyped(Par1, Par2);
        
        TextField.textboxKeyTyped(Par1, Par2);
   }
    
    
    public void mouseClicked(int Par1, int Par2, int Par3)
    {
        super.mouseClicked(Par1, Par2, Par3);
       
        TextField.mouseClicked(Par1, Par2, Par3);
    }
        
    public void updateScreen()
    {
        Tile.Amount = TextField.getText();
    }
    
    public void actionPerformed(GuiButton GuiButton)
    {
        if(GuiButton.id == 1)
        {
            mc.thePlayer.closeScreen();
        }
        
        if(GuiButton.id == 2)
        {
            if(Tile.MobID < 3)
            {
                Tile.MobID++;
                
                GuiButton Decrease = (GuiButton)buttonList.get(2);
                Decrease.enabled = true;
                
                if(Tile.MobID == 3)
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
            if(Tile.MobID > 0)
            {
                Tile.MobID--;
                
                GuiButton Increase = (GuiButton)buttonList.get(1);
                Increase.enabled = true;
                
                if(Tile.MobID == 0)
                {
                    GuiButton Button = (GuiButton)buttonList.get(2);
                    Button.enabled = false;
                }
                
                else
                {
                    GuiButton Button = (GuiButton)buttonList.get(2);
                    Button.enabled = true;
                }
            }
        }
    }
    
    public boolean doesGuiPauseGame()
    {
        return false;
    }
    
    
    @Override
    public void drawScreen(int Var1, int Var2, float Var3)
    {
        drawDefaultBackground();
        
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
       
        mc.renderEngine.bindTexture("/TreviModdingCrew/Utilities/Textures/Gui/Mob Detector.png");
        
        int posX = (width - xSizeOfTexture) / 2 + 40;
        int posY = (height - ySizeOfTexture) / 2;

        drawTexturedModalRect(posX, posY, 0, 0, xSizeOfTexture, ySizeOfTexture);
        
        
        if(Tile.MobID == 0)
        {
            drawTexturedModalRect(posX + 129, posY + 19, 0, 166, 32, 32);
        }
        
        if(Tile.MobID == 1)
        {
            drawTexturedModalRect(posX + 129, posY + 19, 32, 166, 32, 32);
        }
        
        if(Tile.MobID == 2)
        {
            drawTexturedModalRect(posX + 129, posY + 19, 64, 166, 32, 32);
        }
        
        TextField.setText(Tile.Amount);
        TextField.drawTextBox();
        
        fontRenderer.drawString("Mob Detector", posX + 55, posY + 6, 4210752);
        fontRenderer.drawString("Radius: ", posX + 90, posY + 62, 4210752);
        
        super.drawScreen(Var1, Var2, Var3);
    } 
}
