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

    private GuiTextField TextFieldAmount;
    private GuiTextField TextFieldRadius;
    
    public GuiMobDetector(EntityPlayer EntityPlayer, TileEntityMobDetector TileEntityMobDetector)
    {
        new GuiButton(0, 0, 0, 0, 0, "");
        Tile = TileEntityMobDetector;
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
        
        TextFieldAmount = new GuiTextField(fontRenderer, posX + 169, posY + 58, 32, 15);
        TextFieldRadius = new GuiTextField(fontRenderer, posX + 169, posY + 80, 32, 15);
        
        TextFieldAmount.setFocused(false);
        TextFieldAmount.setMaxStringLength(3);
        
        TextFieldRadius.setFocused(false);
        TextFieldRadius.setMaxStringLength(2);
        
    }
    
    public void keyTyped(char Par1, int Par2)
    {
        super.keyTyped(Par1, Par2);
        
        TextFieldAmount.textboxKeyTyped(Par1, Par2);
        TextFieldRadius.textboxKeyTyped(Par1, Par2);
   }
    
    
    public void mouseClicked(int Par1, int Par2, int Par3)
    {
        super.mouseClicked(Par1, Par2, Par3);
       
        TextFieldAmount.mouseClicked(Par1, Par2, Par3);
        TextFieldRadius.mouseClicked(Par1, Par2, Par3);
    }
        
    public void updateScreen()
    {
        Tile.Amount = TextFieldAmount.getText();
        Tile.Radius = TextFieldRadius.getText();
    }
    
    public void actionPerformed(GuiButton GuiButton)
    {
        if(GuiButton.id == 1)
        {
            mc.thePlayer.closeScreen();
        }
        
        if(GuiButton.id == 2)
        {
            if(Tile.MobID < 2)
            {
                Tile.MobID++;
                
                GuiButton Decrease = (GuiButton)buttonList.get(2);
                Decrease.enabled = true;
                
                if(Tile.MobID == 2)
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
        
        TextFieldAmount.setText(Tile.Amount);
        TextFieldAmount.drawTextBox();

        TextFieldRadius.setText(Tile.Radius);
        TextFieldRadius.drawTextBox();
        
        fontRenderer.drawString("Mob Detector", posX + 55, posY + 6, 4210752);
        fontRenderer.drawString("Amount: ", posX + 90, posY + 62, 4210752);
        fontRenderer.drawString("Radius: ", posX + 90, posY + 83, 4210752);
        
        super.drawScreen(Var1, Var2, Var3);
    } 
}
