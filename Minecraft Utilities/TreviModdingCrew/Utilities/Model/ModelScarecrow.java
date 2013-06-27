package TreviModdingCrew.Utilities.Model;

import org.lwjgl.opengl.GL11;

import TreviModdingCrew.Utilities.Tile.TileEntityLumberJacker;
import TreviModdingCrew.Utilities.Tile.TileEntityScarecrow;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.client.model.ModelBase;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

@SideOnly(Side.CLIENT)
public class ModelScarecrow extends ModelBase
{
    // Declaration
    
    private IModelCustom Scarecrow;
    
    
    // Constructor
    
    public ModelScarecrow()
    {
        Scarecrow = AdvancedModelLoader.loadModel("/TreviModdingCrew/Utilities/Libery/Scarecrow.obj");
    }
    
    
    // Main Render Method
    
    public void Render()
    {
        Scarecrow.renderAll();
    }

    
    // Secondary Render Method
    
    public void Render(TileEntityScarecrow TileEntityScarecrow, double Var1, double Var2, double Var3)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)Var1 + 0.5f, (float)Var2 + 0f, (float)Var3 + 0.5f);
        GL11.glRotatef(270F, 0F, 1F, 0F);
        GL11.glScalef(0.5f, 0.5f, 0.5f);
     
        FMLClientHandler.instance().getClient().renderEngine.bindTexture("/TreviModdingCrew/Utilities/Textures/Model/Scarecrow.png");
     
        Render();
     
        GL11.glPopMatrix();
    } 
}