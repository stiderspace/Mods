package TreviModdingCrew.Utilities.Packet;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import TreviModdingCrew.Utilities.Handler.LogHandler;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class PacketManager implements IPacketHandler 
{
    // If There Is Data To Be Send
    
	@Override
	public void onPacketData(INetworkManager INetworkManager, Packet250CustomPayload Packet, Player Player)
	{
		try
        {
			ByteArrayDataInput Data = ByteStreams.newDataInput(Packet.data);
			DataInputStream DataInput = new DataInputStream((InputStream) Data);
			
			int Var1 = Data.readInt();
			int Var2 = Data.readInt();
			int Var3 = Data.readInt();

			World World = ((EntityPlayer)Player).worldObj;

			if(World != null)
			{
				TileEntity TileEntity = World.getBlockTileEntity(Var1, Var2, Var3);

				if(TileEntity != null)
				{
					if(TileEntity instanceof PacketReciever)
					{
						((PacketReciever)TileEntity).handlePacketData(INetworkManager, Packet.channel, Data);
						TileEntity.readFromNBT(Packet.readNBTTagCompound(DataInput));
					}
				}
			}
        }
		
        catch(Exception Exception)
        {
            LogHandler.Log("Could Not Sent Tag Compound Packet", 1);
        }
	}
	
	
	// Trying To Sent Tile Packet
	
	public static void sendTileEntityPacket(TileEntity TileEntity, String Name, Object... SenderData)
	{
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		DataOutputStream data = new DataOutputStream(bytes);
		
		try
		{
			data.writeInt(TileEntity.xCoord);
			data.writeInt(TileEntity.yCoord);
			data.writeInt(TileEntity.zCoord);
			
			sendPacketToClient(Name, bytes, data, SenderData);
		}
	
		catch(IOException IOException)
		{
		    LogHandler.Log("Could Not Sent Tile Entity Data", 1);
		}
	}
	
	
	// Trying To Send Packet To Client
	
	public static void sendPacketToClient(String Channel, ByteArrayOutputStream ByteArrayOutputStream, DataOutputStream Data, Object... senderData)
	{	
	    try
	    {
	        for(Object dataValue : senderData)
	        {
	            if(dataValue instanceof Integer)
	            {
	                Data.writeInt((Integer)dataValue);
	            }
	            else if(dataValue instanceof Float)
	            {
	                Data.writeFloat((Float)dataValue);
	            }
	            else if(dataValue instanceof Double)
			    {
	                Data.writeDouble((Double)dataValue);
			    }
	            else if(dataValue instanceof Byte)
			    {
	                Data.writeByte((Byte)dataValue);
			    }
	            else if(dataValue instanceof Boolean)
	            {
	                Data.writeBoolean((Boolean)dataValue);
	            }
	            else if(dataValue instanceof String)
	            {
	                Data.writeUTF((String)dataValue);
	            }
	            else if(dataValue instanceof Short)
	            {
	                Data.writeShort((Short)dataValue);
	            }
	            else if(dataValue instanceof Long)
	            {
	                Data.writeLong((Long)dataValue);
	            }		
	        }
	        
	        Packet250CustomPayload Packet = new Packet250CustomPayload();
	      
	        Packet.channel = Channel;
	        Packet.data = ByteArrayOutputStream.toByteArray();
	        Packet.length = Packet.data.length;
	        Packet.isChunkDataPacket = true;
		
	        FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().sendPacketToAllPlayers(Packet);
		
	        LogHandler.Log("Packet Successfully Sent To Client", 0);
		
	    }
	    
	    catch(IOException IOException)
	    {
	        LogHandler.Log("Failed To Sent Packet To Client", 1);
	    }
	}
	
	
	// Trying To Send Packet To Server
    
	public static void sendPacketToServer(String Channel, ByteArrayOutputStream ByteArrayOutputStream, DataOutputStream Data, Object... SenderData)
	{
	    try
	    {
	        for(Object dataValue : SenderData)
	        {
	            if(dataValue instanceof Integer)
	            {
	                Data.writeInt((Integer)dataValue);
	            }
	            else if(dataValue instanceof Float)
	            {
	                Data.writeFloat((Float)dataValue);
	            }
	            else if(dataValue instanceof Double)
	            {
	                Data.writeDouble((Double)dataValue);
	            }
	            else if(dataValue instanceof Byte)
	            {
	                Data.writeByte((Byte)dataValue);
	            }
	            else if(dataValue instanceof Boolean)
	            {
	                Data.writeBoolean((Boolean)dataValue);
	            }
	            else if(dataValue instanceof String)
	            {
	                Data.writeUTF((String)dataValue);
	            }
	            else if(dataValue instanceof Short)
	            {
	                Data.writeShort((Short)dataValue);
	            }
	            else if(dataValue instanceof Long)
	            {
	                Data.writeLong((Long)dataValue);
	            }
	        }
	            
	            Packet250CustomPayload Packet = new Packet250CustomPayload();
	            
	            Packet.channel = Channel;
	            Packet.data = ByteArrayOutputStream.toByteArray();
	            Packet.length = Packet.data.length;
	            Packet.isChunkDataPacket = true;
	           
	            PacketDispatcher.sendPacketToServer(Packet);
	            
	            LogHandler.Log("Packet Successfully Sent To Server", 0);
	            
        }
	    
        catch(IOException IOException)
        {
            LogHandler.Log("Failed To Sent Packet To Server", 1);
        }
	}
}