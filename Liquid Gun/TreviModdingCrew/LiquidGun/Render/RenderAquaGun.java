package TreviModdingCrew.LiquidGun.Render;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import TreviModdingCrew.LiquidGun.Model.ModelGun;

public class RenderAquaGun implements IItemRenderer
{
    public ModelGun Model = new ModelGun();
    
    @Override
    public boolean handleRenderType(ItemStack ItemStack, ItemRenderType ItemRenderType)
    {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType ItemRenderType, ItemStack ItemStack, ItemRendererHelper ItemRendererHelper)
    {
        return ItemRenderType != ItemRenderType.EQUIPPED;
    }

    @Override
    public void renderItem(ItemRenderType ItemRenderType, ItemStack ItemStack, Object... data)
    {
        GL11.glPushMatrix();
        {
            if(ItemRenderType != ItemRenderType.EQUIPPED && ItemRenderType != ItemRenderType.FIRST_PERSON_MAP) 
            {
                GL11.glScalef(0.6F, 0.6F, 0.6F);
            }
            
            if(ItemRenderType == ItemRenderType.EQUIPPED)
            {
                GL11.glRotatef(90F, 0F, 1F, 0F);
                GL11.glTranslatef(0F, 0F, 0.4F);
                GL11.glRotatef(15F, -1F, 0F, 0F);
                GL11.glRotatef(16F, 0F, 0F, 1F);
                GL11.glRotatef(10F, 0F, 1F, 0F);
                GL11.glTranslatef(0F, 0.35F, 0F);
                GL11.glTranslatef(0.1F, 0F, 0F);
                
                if(ModLoader.getMinecraftInstance().thePlayer.inventory.getCurrentItem() == ItemStack && ModLoader.getMinecraftInstance().gameSettings.thirdPersonView == 0)
                {
                    GL11.glRotatef(45f, 0f, 1f, 0f);
                    
                    if(ModLoader.getMinecraftInstance().thePlayer.getItemInUseCount()> 0)
                    {
                        GL11.glRotatef(45F, 0F, 1F, 0F);
                        GL11.glRotatef(30F, 0F, 0F, -1F);
                    }
                }
            }
            
            if(ItemRenderType == ItemRenderType.FIRST_PERSON_MAP)
            {
                GL11.glRotatef(90f, 0f, 1f, 0f);
            }
            
            if(ItemRenderType == ItemRenderType.ENTITY)
            {
                GL11.glTranslatef(0.0F, 0.25F, 0.0F);
            }
            
            GL11.glRotatef(180F, 1F, 0F, 0F);
            
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, ModLoader.getMinecraftInstance().renderEngine.getTexture("/mods/AquaGun/textures/models/Gun.png"));
            
            if(ItemRenderType == ItemRenderType.ENTITY) 
            { 
                Model.render((Entity) data[1], 0, 0, 0, 0, 0, 0.0625F, ItemStack); 
            }
            
            else
            {
                Model.render(null, 0, 0, 0, 0, 0, 0.0625F, ItemStack); 
            }
        }
        
        GL11.glPopMatrix();
    }
}