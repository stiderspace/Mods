package TreviModdingCrew.Utilities.Handler;

import TreviModdingCrew.Utilities.Common.Reference;

public class LogHandler
{
    // Used To Print Out Stuff In The Console
    
    public static void Log(String Message, int Level)
    {
        if(Level == 0)
        {
            System.out.println(Reference.ModID + ": " + Message);
        }
        
        if(Level == 1)
        {
            System.out.println(Reference.ModID + ": " + Message);
        }
        
        if(Level == 2)
        {
            System.out.println(Reference.ModID + ": " + Message);
        }
    }   
}
