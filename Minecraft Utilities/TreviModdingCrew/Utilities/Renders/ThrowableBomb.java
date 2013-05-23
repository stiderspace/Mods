package TreviModdingCrew.Utilities.Renders;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.potion.PotionHelper;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ThrowableBomb extends Render
{
	// Declarations
	
	private int ItemIconIndex;
	private String TextureFile;

	public ThrowableBomb(int Par1, String Par2)
	{
		this.ItemIconIndex = Par1;
		this.TextureFile = Par2;
	}

  
  // Setting The Render For The Icon
	
  public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
  {
	  GL11.glPushMatrix();
	  GL11.glTranslatef((float)par2, (float)par4, (float)par6);
	  GL11.glEnable(32826);
	  GL11.glScalef(0.5F, 0.5F, 0.5F);
	  loadTexture(this.TextureFile);
	  Tessellator var10 = Tessellator.instance;

	  if (this.ItemIconIndex == 154)
	  {
		  int var11 = PotionHelper.func_77915_a(((EntityPotion)par1Entity).getPotionDamage(), false);
		  
		  float var12 = (var11 >> 16 & 0xFF) / 255.0F;
		  float var13 = (var11 >> 8 & 0xFF) / 255.0F;
		  float var14 = (var11 & 0xFF) / 255.0F;
    	
		  GL11.glColor3f(var12, var13, var14);
		  GL11.glPushMatrix();
		  func_77026_a(var10, 141);
		  GL11.glPopMatrix();
		  GL11.glColor3f(1.0F, 1.0F, 1.0F);
	  }	

    	func_77026_a(var10, this.ItemIconIndex);
    	GL11.glDisable(32826);
    	GL11.glPopMatrix();
  }

  private void func_77026_a(Tessellator Tessellator, int Par2)
  {
	  float var3 = (Par2 % 16 * 16 + 0) / 256.0F;
	  float var4 = (Par2 % 16 * 16 + 16) / 256.0F;
	  float var5 = (Par2 / 16 * 16 + 0) / 256.0F;
	  float var6 = (Par2 / 16 * 16 + 16) / 256.0F;
	  float var7 = 1.0F;
	  float var8 = 0.5F;
	  float var9 = 0.25F;
	  
	  GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
	  GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
	  
	  Tessellator.startDrawingQuads();
	  Tessellator.setNormal(0.0F, 1.0F, 0.0F);
	  Tessellator.addVertexWithUV(0.0F - var8, 0.0F - var9, 0.0D, var3, var6);
	  Tessellator.addVertexWithUV(var7 - var8, 0.0F - var9, 0.0D, var4, var6);
	  Tessellator.addVertexWithUV(var7 - var8, var7 - var9, 0.0D, var4, var5);
	  Tessellator.addVertexWithUV(0.0F - var8, var7 - var9, 0.0D, var3, var5);
	  Tessellator.draw();
  }
}