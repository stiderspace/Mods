package TreviModdingCrew.Utilities.Model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMagnet extends ModelBase
{
    // Declarations
    
    public ModelRenderer Handle;
    public ModelRenderer Curve1;
    public ModelRenderer Curve2;
    public ModelRenderer Pole1;
    public ModelRenderer Pole2;
  
    
    // Registering The Model Parts
    
    public ModelMagnet()
    {
        textureWidth = 256;
        textureHeight = 256;
    
        Handle = new ModelRenderer(this, 0, 0);
        Handle.addBox(0F, 0F, 0F, 7, 3, 3);
        Handle.setRotationPoint(0F, 0F, 0F);
        Handle.setTextureSize(256, 256);
        Handle.mirror = true;
        setRotation(Handle, 0F, 0F, 0F);
      
        Curve1 = new ModelRenderer(this, 25, 0);
        Curve1.addBox(0F, 0F, 0F, 3, 3, 3);
        Curve1.setRotationPoint(0F, 0F, 0F);
        Curve1.setTextureSize(256, 256);
        Curve1.mirror = true;
        setRotation(Curve1, 0F, 0F, 0.9294653F);
      
        Curve2 = new ModelRenderer(this, 50, 0);
        Curve2.addBox(0F, 0F, 0F, 3, 3, 3);
        Curve2.setRotationPoint(7F, 0F, 0F);
        Curve2.setTextureSize(256, 256);
        Curve2.mirror = true;
        setRotation(Curve2, 0F, 0F, 0.6457718F);
      
        Pole1 = new ModelRenderer(this, 75, 0);
        Pole1.addBox(0F, 0F, 0F, 3, 11, 3);
        Pole1.setRotationPoint(-2.4F, 1.8F, 0F);
        Pole1.setTextureSize(256, 256);
        Pole1.mirror = true;
        setRotation(Pole1, 0F, 0F, 0F);
          
        Pole2 = new ModelRenderer(this, 100, 0);
        Pole2.addBox(0F, 0F, 0F, 3, 11, 3);
        Pole2.setRotationPoint(6.4F, 1.8F, 0F);
        Pole2.setTextureSize(256, 256);
        Pole2.mirror = true;
        setRotation(Pole2, 0F, 0F, 0F);
    }
 
    
    // Makes It Possible To Render It
    
    public void render(Entity Entity, float F, float F1, float F2, float F3, float F4, float F5)
    {
        super.render(Entity, F, F1, F2, F3, F4, F5);
        setRotationAngles(F, F1, F2, F3, F4, F5, Entity);
        Handle.render(F5);
        Curve1.render(F5);
        Curve2.render(F5);
        Pole1.render(F5);
        Pole2.render(F5);
    }
 
    
    // Makes It Possible To Set Rotations
    
    private void setRotation(ModelRenderer ModelRenderer, float F, float F1, float F2)
    {
        ModelRenderer.rotateAngleX = F;
        ModelRenderer.rotateAngleY = F1;
        ModelRenderer.rotateAngleZ = F2;
    }
    
 
    // Setting The Rotation Angles
    
    public void setRotationAngles(float F, float F1, float F2, float F3, float F4, float F5, Entity Entity)
    {
        super.setRotationAngles(F, F1, F2, F3, F4, F5, Entity);
    }
}