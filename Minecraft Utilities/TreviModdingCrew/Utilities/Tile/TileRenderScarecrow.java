package TreviModdingCrew.Utilities.Tile;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import TreviModdingCrew.Utilities.Model.ModelEggHatcher;
import TreviModdingCrew.Utilities.Model.ModelScarecrow;

public class TileRenderScarecrow extends TileEntitySpecialRenderer
{
    private ModelScarecrow Scarecrow = new ModelScarecrow();
    
    @Override
    public void renderTileEntityAt(TileEntity TileEntity, double Par1, double Par2, double Par3, float Tick)
    {
        Scarecrow.Render((TileEntityScarecrow)TileEntity, Par1, Par2, Par3);
    }
}
