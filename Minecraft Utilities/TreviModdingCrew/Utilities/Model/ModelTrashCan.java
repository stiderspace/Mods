package TreviModdingCrew.Utilities.Model;

import org.lwjgl.opengl.GL11;

import TreviModdingCrew.Utilities.Tile.TileEntityTrashCan;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.client.model.ModelBase;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

@SideOnly(Side.CLIENT)
public class ModelTrashCan extends ModelBase
{
    // Declaration
    
    private IModelCustom TrashCan;
    
    
    // Constructor
    
    public ModelTrashCan()
    {
        TrashCan = AdvancedModelLoader.loadModel("/TreviModdingCrew/Utilities/Libery/Trash Can.obj");
    }
    
    
    // Main Render Method
    
    public void Render()
    {
        TrashCan.renderAll();
    }

    
    // Secondary Render Method
    
    public void Render(TileEntityTrashCan TileEntityTrashCan, double Var1, double Var2, double Var3)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)Var1 + 0.5f, (float)Var2 + 0f, (float)Var3 + 0.5f);
        GL11.glRotatef(270F, 0F, 1F, 0F);
        GL11.glScalef(0.5f, 0.5f, 0.5f);
     
        FMLClientHandler.instance().getClient().renderEngine.bindTexture("/TreviModdingCrew/Utilities/Textures/Model/Trash Can.png");
     
        Render();
     
        GL11.glPopMatrix();
    } 
}