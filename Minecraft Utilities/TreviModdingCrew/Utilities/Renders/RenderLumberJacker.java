package TreviModdingCrew.Utilities.Renders;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import TreviModdingCrew.Utilities.Model.ModelLumberJacker;

import cpw.mods.fml.client.FMLClientHandler;

public class RenderLumberJacker implements IItemRenderer
{
    private ModelLumberJacker Model;
    
    public RenderLumberJacker()
    {
        Model = new ModelLumberJacker();
    }
    
    @Override
    public boolean handleRenderType(ItemStack ItemStack, ItemRenderType ItemRenderType)
    {
        return true;
    }
    
    @Override
    public boolean shouldUseRenderHelper(ItemRenderType ItemRenderType, ItemStack ItemStack, ItemRendererHelper ItemRendererHelper)
    {
        return true;
    }
    
    @Override
    public void renderItem(ItemRenderType ItemRenderType, ItemStack ItemStack, Object... data)
    {
        switch(ItemRenderType)
        {
            case ENTITY:
            {
                Render(0f, 0f, 0f, 0.5f);
                return;
            }
            
            case EQUIPPED:
            {
                Render(0.5F, 0F, 0.5F, 0.5F);
                return;
            }
             
            case INVENTORY:
            {
                Render(0f, -0.5f, 0f, 0.5f);
                return;
            }
            
            default: return;
        }
    }
    
    private void Render(float Var1, float Var2, float Var3, float Scale)
    { 
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);
        
        GL11.glTranslatef(Var1,  Var2,  Var3);
        GL11.glScalef(Scale, Scale, Scale);
        GL11.glRotatef(0F, 0F, 1F, 0F);
        
        FMLClientHandler.instance().getClient().renderEngine.bindTexture("/TreviModdingCrew/Utilities/Textures/Model/Lumber Jacker.png");
        
        Model.render();
        
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
}