package TreviModdingCrew.Utilities.Handler;

import java.util.EnumSet;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;

public class KeyBindHandler extends KeyHandler
{
    private EnumSet <TickType> Type = EnumSet.of(TickType.CLIENT);
    
    static KeyBinding More = new KeyBinding("More Information", Keyboard.KEY_M);
    
    public static boolean MoreKeyDown = false;
    
    public KeyBindHandler()
    {
        super(new KeyBinding[]{More}, new boolean[]{false, false});
    }
    
    @Override
    public String getLabel() 
    {
        return "More Information";
    }

    @Override
    public void keyDown(EnumSet <TickType> Types, KeyBinding KeyBinding, boolean TickEnd, boolean IsRepeat)
    {
        if(KeyBinding == More)
        {
            MoreKeyDown = true;
        }
    }

    @Override
    public void keyUp(EnumSet <TickType> Types, KeyBinding KeyBinding, boolean TickEnd)
    {
        if(KeyBinding == More)
        {
            MoreKeyDown = false;
        }  
    }

    @Override
    public EnumSet<TickType> ticks() 
    {
        return Type;
    }
}
