package TreviModdingCrew.Utilities.Tile;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import TreviModdingCrew.Utilities.Model.ModelTrashCan;

public class TileRenderTrashCan extends TileEntitySpecialRenderer
{
    private ModelTrashCan Model = new ModelTrashCan();
    
    @Override
    public void renderTileEntityAt(TileEntity TileEntity, double Par1, double Par2, double Par3, float Tick)
    {
        Model.Render((TileEntityTrashCan)TileEntity, Par1, Par2, Par3);
    }
}
