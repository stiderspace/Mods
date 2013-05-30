package TreviModdingCrew.Utilities.Renders;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import TreviModdingCrew.Utilities.Model.ModelLumberJacker;
import TreviModdingCrew.Utilities.Tile.TileEntityLumberJacker;

public class TileRenderLumberJacker extends TileEntitySpecialRenderer
{
    private ModelLumberJacker Model = new ModelLumberJacker();
    
    @Override
    public void renderTileEntityAt(TileEntity TileEntity, double Par1, double Par2, double Par3, float Tick)
    {
        Model.render((TileEntityLumberJacker)TileEntity, Par1, Par2, Par3);
    }
}
