package TreviModdingCrew.Utilities.Renders;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import TreviModdingCrew.Utilities.Model.ModelMagnet;

public class RenderMagnet implements IItemRenderer 
{
    // Declarations
    
    protected ModelMagnet Magnet;
    
    public RenderMagnet()
    {
        Magnet = new ModelMagnet();
    }

    
    // Selecting The Render Type
    
    @Override
    public boolean handleRenderType(ItemStack ItemStack, ItemRenderType ItemRenderType) 
    {
        switch(ItemRenderType)
        {
            case EQUIPPED: return true;
            
            default: return false;
        }
    }

    
    //  The Boolean For If It Should Render Type
    
    @Override
    public boolean shouldUseRenderHelper(ItemRenderType ItemRenderType, ItemStack ItemStack, ItemRendererHelper ItemRendererHelper) 
    {
        return false;
    }

    
    // Rendering The Item In Your Hand
    
    @Override
    public void renderItem(ItemRenderType ItemRenderType, ItemStack ItemStack, Object... Data) 
    {
        switch(ItemRenderType)
        {
            case EQUIPPED: 
            {
                GL11.glPushMatrix();
            
                Minecraft.getMinecraft().renderEngine.bindTexture("/TreviModdingCrew/Utilities/Textures/Model/Magnet.png");
                
                float Scale = 0.95F;
                
                GL11.glRotatef(107F, 1F, 0F, 0F);
                GL11.glRotatef(12F, 0F, 1F, 0F);
                GL11.glRotatef(-15F, 0F, 0F, 1F);
                
                boolean IsFirstPerson = false;
                
                if (Data[1] != null && Data[1] instanceof EntityPlayer)
                {
                    if(!((EntityPlayer)Data[1] == Minecraft.getMinecraft().renderViewEntity && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && !((Minecraft.getMinecraft().currentScreen instanceof GuiInventory || Minecraft.getMinecraft().currentScreen instanceof GuiContainerCreative) && RenderManager.instance.playerViewY == 180.0F)))
                    {
                        GL11.glTranslatef(0.165F, 0.1F, -0.75F);
                    }
                    
                    else
                    {
                        Scale = 0.90F;
                        
                        IsFirstPerson = true;
                        
                        GL11.glRotatef(-40F, 1F, 0F, 0F);
                        GL11.glRotatef(0F, 0F, 1F, 0F);
                        GL11.glRotatef(-85F, 0F, 0F, 1F);
                        GL11.glTranslatef(-0.5F, 0.55F, -0.95F);
                    }
                }
                
                else
                {
                    GL11.glTranslatef(-0.4F, -0.05F, 0.3F);
                }
                
                GL11.glTranslatef(0.05F, 0.06F, 0.77F);
                GL11.glScalef(Scale, Scale, Scale);
            
                Magnet.render((Entity)Data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
                
                GL11.glPopMatrix();
            }
            
            break;
            
            default:
            break;
        }
    }
}
