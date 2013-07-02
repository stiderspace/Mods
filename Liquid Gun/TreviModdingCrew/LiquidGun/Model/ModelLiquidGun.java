package TreviModdingCrew.LiquidGun.Model;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.IBlockLiquid;
import net.minecraft.src.ModLoader;
import net.minecraftforge.liquids.LiquidStack;
import net.minecraft.client.renderer.Tessellator;

public class ModelLiquidGun extends ModelBase
{
    ModelRenderer Pice0; 

    public ModelLiquidGun()
    {
        textureWidth = 68;
        textureHeight = 68;
        
        setTextureOffset("Pice0.Part0", 0, 22);
        setTextureOffset("Pice0.Part1", 0, 17);
        setTextureOffset("Pice0.Part2", 0, 12); 
        setTextureOffset("Pice0.Part3", 0, 27);
        setTextureOffset("Pice0.Part4", 0, 0);
        setTextureOffset("Pice0.Part5", 56, 0);
        setTextureOffset("Pice0.Part6", 0, 32);
        setTextureOffset("Pice0.Part7", 24, 0);

        Pice0 = new ModelRenderer(this, "Pice0");
        Pice0.setRotationPoint(0F, 0F, 0F);
        Pice0.mirror = false;
        Pice0.addBox("Part0", -15F, -3F, -2F, 30, 1, 4);
        Pice0.addBox("Part1", -15F, -2F, 2F, 30, 4, 1);
        Pice0.addBox("Part2", -15F, -2F, -3F, 30, 4, 1);
        Pice0.addBox("Part3", -15F, 2F, -2F, 30, 1, 4);
        Pice0.addBox("Part4", -3F, -3F, -3F, 6, 6, 6);
        Pice0.addBox("Part5", -1F, 3F, -1F, 2, 5, 2);
        Pice0.addBox("Part6", 14F, -3F, -3F, 1, 6, 6);
        Pice0.addBox("Part7", -2F, -9F, -2F, 4, 6, 4);
    }

    public void render(Entity Entity, float F, float F1, float F2, float F3, float F4, float F5, ItemStack ItemStack)
    {
        LiquidStack LiquidStacks;
        
        try
        {
            LiquidStacks = LiquidStack.loadLiquidStackFromNBT(ItemStack.getTagCompound().getCompoundTag("LiquidData"));
        }
        
        catch(Exception Exception)
        {
            LiquidStacks = null;
        }
        
        super.render(Entity, F, F1, F2, F3, F4, F5);
        setRotationAngles(F, F1, F2, F3, F4, F5, Entity);
        Pice0.render(F5);
        
        if(LiquidStacks != null && LiquidStacks.itemID != 0 && LiquidStacks.amount > 0)
        {
            String Texture = "";
            
            if(Block.blocksList[LiquidStacks.itemID] instanceof IBlockLiquid)
            {
                Texture = ((IBlockLiquid)Block.blocksList[LiquidStacks.itemID]).getLiquidBlockTextureFile();
            }
            
            else Texture = "/terrain.png";
            
            ModLoader.getMinecraftInstance().renderEngine.bindTexture(Texture);

            Tessellator Tess = Tessellator.instance;

            LiquidStacks.getRenderingIcon().getInterpolatedU(0);
            
            double Par1 = LiquidStacks.getRenderingIcon().getInterpolatedU(0);
            double Par2 = LiquidStacks.getRenderingIcon().getInterpolatedV(0 + (((LiquidStacks.amount * -1) + 1000) / 62.5d));
            double Par3 = LiquidStacks.getRenderingIcon().getInterpolatedU(16);
            double Par4 = LiquidStacks.getRenderingIcon().getInterpolatedV(16);
            double Par5 = LiquidStacks.amount / 200F;

            GL11.glPushMatrix();
            GL11.glScalef(1f/16f, 1f/16f, 1f/16f); 
            GL11.glDisable(GL11.GL_LIGHTING);
          
            Tess.startDrawingQuads(); 
            {
                Tess.addVertexWithUV(-1.5, -3, -1.5, Par3, Par2);
                Tess.addVertexWithUV(1.5, -3, -1.5, Par1, Par2);
                Tess.addVertexWithUV(1.5, -3 - Par5, -1.5, Par1, Par4);
                Tess.addVertexWithUV(-1.5, -3 - Par5, -1.5, Par3, Par4);

                Tess.addVertexWithUV(-1.5, -3 - Par5, 1.5, Par1, Par4);
                Tess.addVertexWithUV(-1.5, -3, 1.5, Par1, Par2);
                Tess.addVertexWithUV(-1.5, -3, -1.5, Par3, Par2);
                Tess.addVertexWithUV(-1.5, -3 - Par5, -1.5, Par3, Par4);

                Tess.addVertexWithUV(1.5, -3 - Par5, 1.5, Par3, Par4);
                Tess.addVertexWithUV(1.5, -3, 1.5, Par3, Par2);
                Tess.addVertexWithUV(-1.5, -3, 1.5, Par1, Par2);
                Tess.addVertexWithUV(-1.5, -3 - Par5, 1.5, Par1, Par4);

                Tess.addVertexWithUV(1.5, -3, -1.5, Par1, Par2);
                Tess.addVertexWithUV(1.5, -3, 1.5, Par3, Par2);
                Tess.addVertexWithUV(1.5, -3 - Par5, 1.5, Par3, Par4);
                Tess.addVertexWithUV(1.5, -3 - Par5, -1.5, Par1, Par4);

                Tess.addVertexWithUV(1.5, -3 - Par5, 1.5, Par3, Par4);
                Tess.addVertexWithUV(-1.5, -3 - Par5, 1.5, Par3, Par2);
                Tess.addVertexWithUV(-1.5, -3 - Par5, -1.5, Par1, Par2);
                Tess.addVertexWithUV(1.5, -3 - Par5, -1.5, Par1, Par4);
            }
            
            Tess.draw();
            
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glPopMatrix();
        }
    }

    public void setRotationAngles(float F, float F1, float F2, float F3, float F4, float F5, Entity Entity)
    {
        super.setRotationAngles(F, F1, F2, F3, F4, F5, Entity);
    }
}
