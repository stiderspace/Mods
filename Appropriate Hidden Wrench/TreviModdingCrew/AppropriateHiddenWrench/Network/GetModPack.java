package TreviModdingCrew.AppropriateHiddenWrench.Network;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.net.URLConnection;

import TreviModdingCrew.AppropriateHiddenWrench.Handler.LogHandler;

public class GetModPack
{
    public static String getData(String Path)
    {
        try
        {
            URL Adress = new URL(Path);
            URLConnection Connection = Adress.openConnection();
         
            Connection.setConnectTimeout(1000);
            
            InputStream Stream = Adress.openConnection().getInputStream();
            Reader Reader = new BufferedReader(new InputStreamReader(Stream, "UTF-8"));
         
            int Var1;
            
            Writer Writer = new StringWriter();
            
            char[] Buffer = new char[1024];
            
            while ((Var1 = Reader.read(Buffer)) != -1)
            {
                Writer.write(Buffer, 0, Var1);
            }
            
            return Writer.toString(); 
        }
        
        catch(Exception Exception)
        {
            LogHandler.Log("Could Not Download Mod Pack", 1);
        }
        
        return "";
    }
}
