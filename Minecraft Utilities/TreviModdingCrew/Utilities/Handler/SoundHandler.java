package TreviModdingCrew.Utilities.Handler;

import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class SoundHandler
{
    @ForgeSubscribe
    public void onSound(SoundLoadEvent SoundLoadEvent)
    {
        try 
        {
            LogHandler.Log("Sound Files Loaded");
        } 
        
        catch (Exception Exception)
        {
            LogHandler.Log("Could Not Load Sound Files");
        }
    }
}
