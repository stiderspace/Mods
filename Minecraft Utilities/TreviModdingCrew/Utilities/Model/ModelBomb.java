package TreviModdingCrew.Utilities.Model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBomb extends ModelBase
{
   // Declarations
	
   public ModelRenderer Round1;
   public ModelRenderer Round2;
   public ModelRenderer Round3;
   public ModelRenderer Round4;
   public ModelRenderer Defuser1;
  
   
   // Registering The Model Parts
   
  public ModelBomb()
  {
	  textureWidth = 256;
	  textureHeight = 256;
    
      Round1 = new ModelRenderer(this, 0, 0);
      Round1.addBox(0F, 0F, 0F, 3, 1, 3);
      Round1.setRotationPoint(0F, -4F, 0F);
      Round1.setTextureSize(256, 256);
      Round1.mirror = true;
      setRotation(Round1, 0F, 0F, 0F);
      
      Round2 = new ModelRenderer(this, 25, 0);
      Round2.addBox(0F, 0F, 0F, 5, 3, 3);
      Round2.setRotationPoint(-1F, -3F, 0F);
      Round2.setTextureSize(256, 256);
      Round2.mirror = true;
      setRotation(Round2, 0F, 0F, 0F);
      
      Round3 = new ModelRenderer(this, 50, 0);
      Round3.addBox(0F, 0F, 0F, 3, 3, 5);
      Round3.setRotationPoint(0F, -3F, -1F);
      Round3.setTextureSize(256, 256);
      Round3.mirror = true;
      setRotation(Round3, 0F, 0F, 0F);
      
      Round4 = new ModelRenderer(this, 100, 0);
      Round4.addBox(0F, 0F, 0F, 3, 1, 3);
      Round4.setRotationPoint(0F, 0F, 0F);
      Round4.setTextureSize(256, 256);
      Round4.mirror = true;
      setRotation(Round4, 0F, 0F, 0F);
      
      Defuser1 = new ModelRenderer(this, 75, 0);
      Defuser1.addBox(0F, 0F, 0F, 1, 1, 1);
      Defuser1.setRotationPoint(1F, -5F, 1F);
      Defuser1.setTextureSize(256, 256);
      Defuser1.mirror = true;
      setRotation(Defuser1, 0F, 0F, 0F);
  }

  
  // Makes It Possible To Render It
  
  public void render(Entity Entity, float F, float F1, float F2, float F3, float F4, float F5)
  {
	  super.render(Entity, F, F1, F2, F3, F4, F5);
      setRotationAngles(F, F1, F2, F3, F4, F5, Entity);
      Round1.render(F5);
      Round2.render(F5);
      Round3.render(F5);
      Round4.render(F5);
      Defuser1.render(F5);
  }

  
  // Makes It Possible To Set Rotations
  
  private void setRotation(ModelRenderer ModelRenderer, float Var1, float Var2, float Var3)
  {
	  ModelRenderer.rotateAngleX = Var1;
	  ModelRenderer.rotateAngleY = Var2;
	  ModelRenderer.rotateAngleZ = Var3;
  }
  
  
  // Setting The Rotation Angles
  
  public void setRotationAngles(float F, float F1, float F2, float F3, float F4, float F5, Entity Entity)
  {
	  super.setRotationAngles(F, F1, F2, F3, F4, F5, Entity);
  }
}
