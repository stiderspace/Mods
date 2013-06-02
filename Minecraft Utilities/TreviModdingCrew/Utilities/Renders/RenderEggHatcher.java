package TreviModdingCrew.Utilities.Renders;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import TreviModdingCrew.Utilities.Model.ModelEggHatcher;

import cpw.mods.fml.client.FMLClientHandler;

public class RenderEggHatcher implements IItemRenderer
{
    private ModelEggHatcher EggHatcher;
    
    public RenderEggHatcher()
    {
        EggHatcher = new ModelEggHatcher();
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
                Render(0f, 0f, 0f, 0.5f, ItemRenderType);
                return;
            }
            
            case EQUIPPED:
            {
                Render(0.5F, 0F, 0.5F, 0.5F, ItemRenderType);
                return;
            }
            
            case EQUIPPED_FIRST_PERSON: 
            {
                Render(0.5F, 0.0F, 0.5F, 0.5F, ItemRenderType);
                return;
            }
             
            case INVENTORY:
            {
                Render(0f, -0.5f, 0f, 0.5f, ItemRenderType);
                return;
            }
            
            default: return;
        }
    }
    
    private void Render(float Var1, float Var2, float Var3, float Scale, ItemRenderType ItemRenderType)
    { 
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);
        
        GL11.glTranslatef(Var1,  Var2,  Var3);
        GL11.glScalef(Scale, Scale, Scale);
        GL11.glRotatef(0F, 0F, 1F, 0F);
        
        if(ItemRenderType == ItemRenderType.EQUIPPED_FIRST_PERSON)
        {
            GL11.glRotatef(-90F, 0F, 1F, 0F);
        }
        
        if(ItemRenderType == ItemRenderType.EQUIPPED)
        {
            GL11.glRotatef(180F, 0F, 1F, 0F);
        }
        
        FMLClientHandler.instance().getClient().renderEngine.bindTexture("/TreviModdingCrew/Utilities/Textures/Model/Egg Hatcher.png");
        
        EggHatcher.Render();
        
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
}