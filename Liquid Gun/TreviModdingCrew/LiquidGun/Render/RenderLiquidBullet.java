package TreviModdingCrew.LiquidGun.Render;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.potion.PotionHelper;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderLiquidBullet extends Render
{
    // Declarations
    
    private int ItemIconIndex;
    private String TextureFile;

    public RenderLiquidBullet(int Par1, String Par2)
    {
        this.ItemIconIndex = Par1;
        this.TextureFile = Par2;
    }

  
  // Setting The Render For The Icon
    
  public void doRender(Entity Entity, double Par2, double Par4, double Par6, float Par8, float Par9)
  {
      GL11.glPushMatrix();
      GL11.glTranslatef((float)Par2, (float)Par4, (float)Par6);
      GL11.glEnable(32826);
      GL11.glScalef(0.5F, 0.5F, 0.5F);
      loadTexture(this.TextureFile);
      Tessellator Var10 = Tessellator.instance;

      if (this.ItemIconIndex == 154)
      {
          int Var11 = PotionHelper.func_77915_a(((EntityPotion)Entity).getPotionDamage(), false);
          
          float Var12 = (Var11 >> 16 & 0xFF) / 255.0F;
          float Var13 = (Var11 >> 8 & 0xFF) / 255.0F;
          float Var14 = (Var11 & 0xFF) / 255.0F;
        
          GL11.glColor3f(Var12, Var13, Var14);
          GL11.glPushMatrix();
          func_77026_a(Var10, 141);
          GL11.glPopMatrix();
          GL11.glColor3f(1.0F, 1.0F, 1.0F);
      } 

        func_77026_a(Var10, this.ItemIconIndex);
        GL11.glDisable(32826);
        GL11.glPopMatrix();
  }

  private void func_77026_a(Tessellator Tessellator, int Par2)
  {
      float Var3 = (Par2 % 16 * 16 + 0) / 256.0F;
      float Var4 = (Par2 % 16 * 16 + 16) / 256.0F;
      float Var5 = (Par2 / 16 * 16 + 0) / 256.0F;
      float Var6 = (Par2 / 16 * 16 + 16) / 256.0F;
      float Var7 = 1.0F;
      float Var8 = 0.5F;
      float Var9 = 0.25F;
      
      GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
      
      Tessellator.startDrawingQuads();
      Tessellator.setNormal(0.0F, 1.0F, 0.0F);
      Tessellator.addVertexWithUV(0.0F - Var8, 0.0F - Var9, 0.0D, Var3, Var6);
      Tessellator.addVertexWithUV(Var7 - Var8, 0.0F - Var9, 0.0D, Var4, Var6);
      Tessellator.addVertexWithUV(Var7 - Var8, Var7 - Var9, 0.0D, Var4, Var5);
      Tessellator.addVertexWithUV(0.0F - Var8, Var7 - Var9, 0.0D, Var3, Var5);
      Tessellator.draw();
  }
}