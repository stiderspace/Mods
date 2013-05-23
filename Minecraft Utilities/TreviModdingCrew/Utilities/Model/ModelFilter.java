package TreviModdingCrew.Utilities.Model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelFilter extends ModelBase
{
	// Declarations
	
    public ModelRenderer Handle;
    public ModelRenderer Circle1;
    public ModelRenderer Circle2;
    public ModelRenderer Circle3;
    public ModelRenderer Circle4;
    public ModelRenderer Circle5;
    public ModelRenderer Circle6;
    public ModelRenderer Circle7;
    public ModelRenderer Filter2;
    public ModelRenderer Filter1;
    public ModelRenderer Circle8;
    public ModelRenderer Filter3;
    public ModelRenderer Filter4;
    public ModelRenderer Bottom1;

    
    // Registering The Model Parts
    
    public ModelFilter()
    {
    	textureWidth = 256;
    	textureHeight = 256;
    
    	Handle = new ModelRenderer(this, 25, 0);
    	Handle.addBox(0F, 0F, 0F, 5, 1, 1);
    	Handle.setRotationPoint(-1F, 0F, 0F);
    	Handle.setTextureSize(256, 256);
    	Handle.mirror = true;
    	setRotation(Handle, 0F, 0F, -0.2974289F);
    	
    	Circle1 = new ModelRenderer(this, 50, 0);
    	Circle1.addBox(-1F, 0F, -1F, 1, 1, 3);
    	Circle1.setRotationPoint(0F, 0F, 0F);
    	Circle1.setTextureSize(256, 256);
    	Circle1.mirror = true;
    	setRotation(Circle1, 0F, 0F, 0F);
    	
    	Circle2 = new ModelRenderer(this, 75, 0);
    	Circle2.addBox(-2F, 0F, 0F, 1, 1, 1);
    	Circle2.setRotationPoint(0F, 0F, 2F);
    	Circle2.setTextureSize(256, 256);
    	Circle2.mirror = true;
    	setRotation(Circle2, 0F, 0F, 0F);
    	
    	Circle3 = new ModelRenderer(this, 100, 0);
    	Circle3.addBox(0F, 0F, 0F, 1, 1, 1);
    	Circle3.setRotationPoint(-2F, 0F, -2F);
    	Circle3.setTextureSize(256, 256);
    	Circle3.mirror = true;
    	setRotation(Circle3, 0F, 0F, 0F);
    	
    	Circle4 = new ModelRenderer(this, 125, 0);
    	Circle4.addBox(0F, 0F, 0F, 3, 1, 1);
    	Circle4.setRotationPoint(-5F, 0F, 3F);
    	Circle4.setTextureSize(256, 256);
    	Circle4.mirror = true;
    	setRotation(Circle4, 0F, 0F, 0F);
    	
    	Circle5 = new ModelRenderer(this, 150, 0);
    	Circle5.addBox(0F, 0F, -3F, 3, 1, 1);
    	Circle5.setRotationPoint(-5F, 0F, 0F);
    	Circle5.setTextureSize(256, 256);
    	Circle5.mirror = true;
    	setRotation(Circle5, 0F, 0F, 0F);
    	
    	Circle6 = new ModelRenderer(this, 175, 0);
    	Circle6.addBox(0F, 0F, 2F, 1, 1, 1);
    	Circle6.setRotationPoint(-6F, 0F, 0F);
    	Circle6.setTextureSize(256, 256);
    	Circle6.mirror = true;
    	setRotation(Circle6, 0F, 0F, 0F);
    	
    	Circle7 = new ModelRenderer(this, 200, 0);
    	Circle7.addBox(0F, 0F, 0F, 1, 1, 1);
    	Circle7.setRotationPoint(-6F, 0F, -2F);
    	Circle7.setTextureSize(256, 256);
    	Circle7.mirror = true;
    	setRotation(Circle7, 0F, 0F, 0F);
    	
    	Circle8 = new ModelRenderer(this, 200, 3);
    	Circle8.addBox(0F, 0F, 0F, 1, 1, 3);
    	Circle8.setRotationPoint(-7F, 0F, -1F);
    	Circle8.setTextureSize(256, 256);
    	Circle8.mirror = true;
    	setRotation(Circle8, 0F, 0F, 0F);
    	
    	Filter2 = new ModelRenderer(this, 50, 25);
    	Filter2.addBox(0F, 0F, 0F, 3, 1, 1);
    	Filter2.setRotationPoint(-5F, 1F, 2F);
    	Filter2.setTextureSize(256, 256);
    	Filter2.mirror = true;
    	setRotation(Filter2, 0F, 0F, 0F);
    	
    	Filter1 = new ModelRenderer(this, 25, 25);
    	Filter1.addBox(0F, 0F, 0F, 3, 1, 1);
    	Filter1.setRotationPoint(-5F, 1F, -2F);
    	Filter1.setTextureSize(256, 256);
    	Filter1.mirror = true;
    	setRotation(Filter1, 0F, 0F, 0F);
    	
    	Filter3 = new ModelRenderer(this, 75, 25);
    	Filter3.addBox(0F, 0F, 0F, 1, 1, 3);
    	Filter3.setRotationPoint(-6F, 1F, -1F);
    	Filter3.setTextureSize(256, 256);
    	Filter3.mirror = true;
    	setRotation(Filter3, 0F, 0F, 0F);
    	
    	Filter4 = new ModelRenderer(this, 100, 25);
    	Filter4.addBox(0F, 0F, 0F, 1, 1, 3);
    	Filter4.setRotationPoint(-2F, 1F, -1F);
    	Filter4.setTextureSize(256, 256);
    	Filter4.mirror = true;
    	setRotation(Filter4, 0F, 0F, 0F);
    	
    	Bottom1 = new ModelRenderer(this, 125, 25);
    	Bottom1.addBox(0F, 0F, 0F, 3, 1, 3);
    	Bottom1.setRotationPoint(-5F, 2F, -1F);
    	Bottom1.setTextureSize(256, 256);
    	Bottom1.mirror = true;
    	setRotation(Bottom1, 0F, 0F, 0F);
    }
  
    
    // Makes It Possible To Render It
    
    public void render(Entity Entity, float F, float F1, float F2, float F3, float F4, float F5)
    {
    	 super.render(Entity, F, F1, F2, F3, F4, F5);
    	 setRotationAngles(F, F1, F2, F3, F4, F5, Entity);
	 	 Handle.render(F5);
	 	 Circle1.render(F5);
	 	 Circle2.render(F5);
	 	 Circle3.render(F5);
	 	 Circle4.render(F5);
	 	 Circle5.render(F5);
	 	 Circle6.render(F5);
	 	 Circle7.render(F5);
	 	 Filter2.render(F5);
	 	 Filter1.render(F5);
	     Circle8.render(F5);
	  	 Filter3.render(F5);
	     Filter4.render(F5);
	     Bottom1.render(F5);
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
