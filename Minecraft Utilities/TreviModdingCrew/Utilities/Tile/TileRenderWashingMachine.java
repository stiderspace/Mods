package TreviModdingCrew.Utilities.Tile;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import TreviModdingCrew.Utilities.Model.ModelWashingMachine;
import TreviModdingCrew.Utilities.Tile.TileEntityWashingMachine;

public class TileRenderWashingMachine extends TileEntitySpecialRenderer
{
    private ModelWashingMachine Model = new ModelWashingMachine();
    
    @Override
    public void renderTileEntityAt(TileEntity TileEntity, double Par1, double Par2, double Par3, float Tick)
    {
        Model.Render((TileEntityWashingMachine)TileEntity, Par1, Par2, Par3);
    }
}
