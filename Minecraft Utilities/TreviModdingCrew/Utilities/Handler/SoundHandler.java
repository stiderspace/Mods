package TreviModdingCrew.Utilities.Handler;

import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SoundHandler
{
    @SideOnly(Side.CLIENT)
    @ForgeSubscribe
    public void onSound(SoundLoadEvent SoundLoadEvent)
    {
        try 
        {
            LogHandler.Log("Sound Files Loaded", 0);
        } 
        
        catch (Exception Exception)
        {
            LogHandler.Log("Could Not Load Sound Files", 1);
        }
    }
}
