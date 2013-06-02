package TreviModdingCrew.Utilities.Tile;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import TreviModdingCrew.Utilities.Model.ModelEggHatcher;

public class TileRenderEggHatcher extends TileEntitySpecialRenderer
{
    private ModelEggHatcher EggHatcher = new ModelEggHatcher();
    
    @Override
    public void renderTileEntityAt(TileEntity TileEntity, double Par1, double Par2, double Par3, float Tick)
    {
        EggHatcher.Render((TileEntityEggHatcher)TileEntity, Par1, Par2, Par3);
    }
}
