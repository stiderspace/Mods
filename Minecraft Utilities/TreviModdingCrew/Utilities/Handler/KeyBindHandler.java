package TreviModdingCrew.Utilities.Handler;

import java.util.EnumSet;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;

public class KeyBindHandler extends KeyHandler
{
    // Declaration
    
    private EnumSet <TickType> Type = EnumSet.of(TickType.CLIENT);
    
    static KeyBinding More = new KeyBinding("More Information", Keyboard.KEY_M);
    
    public static boolean MoreKeyDown = false;
    
    
    // Constructor
    
    public KeyBindHandler()
    {
        super(new KeyBinding[]{More}, new boolean[]{false, false});
    }
    
    
    // Sets A Button Description
    
    @Override
    public String getLabel() 
    {
        return "More Information";
    }

    
    // Checks If A Key Is Held Down And Returns True
    
    @Override
    public void keyDown(EnumSet <TickType> Types, KeyBinding KeyBinding, boolean TickEnd, boolean IsRepeat)
    {
        if(KeyBinding == More)
        {
            MoreKeyDown = true;
        }
    }

    
    // Checks If A Key Is Up And Returns False
    
    @Override
    public void keyUp(EnumSet <TickType> Types, KeyBinding KeyBinding, boolean TickEnd)
    {
        if(KeyBinding == More)
        {
            MoreKeyDown = false;
        }  
    }

    
    // Makes It A Enum Tick Type
    
    @Override
    public EnumSet <TickType> ticks() 
    {
        return Type;
    }
}
