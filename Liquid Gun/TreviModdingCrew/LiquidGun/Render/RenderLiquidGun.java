package TreviModdingCrew.LiquidGun.Render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import TreviModdingCrew.LiquidGun.Model.ModelLiquidGun;

public class RenderLiquidGun implements IItemRenderer
{
    public ModelLiquidGun Model = new ModelLiquidGun();
    
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
    public void renderItem(ItemRenderType ItemRenderType, ItemStack ItemStack, Object... Data)
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
                GL11.glRotatef(14F, -1F, 0F, 0F);
                GL11.glRotatef(14F, 0F, 0F, 1F);
                GL11.glRotatef(10F, 0F, 1F, 0F);
                GL11.glTranslatef(0F, 0.40F, 0F);
                GL11.glTranslatef(0.1F, 0F, 0.05F);
                
                if(ModLoader.getMinecraftInstance().thePlayer.inventory.getCurrentItem() == ItemStack && ModLoader.getMinecraftInstance().gameSettings.thirdPersonView == 0)
                {
                    GL11.glRotatef(45f, 0f, 1f, 0f);
                    
                    if(ModLoader.getMinecraftInstance().thePlayer.getItemInUseCount()> 0)
                    {
                        GL11.glRotatef(45F, 0F, 1F, 0F);
                        GL11.glRotatef(30F, 0F, 0F, -1F);
                    }
                }
                
                if(((EntityPlayer)Data[1] == Minecraft.getMinecraft().renderViewEntity && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && !((Minecraft.getMinecraft().currentScreen instanceof GuiInventory || Minecraft.getMinecraft().currentScreen instanceof GuiContainerCreative) && RenderManager.instance.playerViewY == 180.0F)))
                {
                    GL11.glTranslatef(0.9F, 0.9F, -0.75F);
                }
            }
            
            if(ItemRenderType == ItemRenderType.EQUIPPED_FIRST_PERSON)
            {
                GL11.glTranslatef(0F, 1.90F, 1F);
                GL11.glRotatef(225f, 0f, 1f, 0f);
            }
            
            if(ItemRenderType == ItemRenderType.INVENTORY)
            {
                GL11.glScalef(1.5F, 1.5F, 1.5F);
            }
            
            if(ItemRenderType == ItemRenderType.ENTITY)
            {
                GL11.glTranslatef(0.0F, 0.25F, 0.0F);
            }
            
            GL11.glRotatef(180F, 1F, 0F, 0F);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, ModLoader.getMinecraftInstance().renderEngine.getTexture("/mods/LiquidGun/textures/models/Gun.png"));
            
            if(ItemRenderType == ItemRenderType.ENTITY) 
            { 
                Model.render((Entity) Data[1], 0, 0, 0, 0, 0, 0.0625F, ItemStack); 
            }
            
            else
            {
                Model.render(null, 0, 0, 0, 0, 0, 0.0625F, ItemStack); 
            }
        }
        
        GL11.glPopMatrix();
    }
}