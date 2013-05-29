package TreviModdingCrew.LiquidGun.Packet;

import net.minecraft.network.INetworkManager;

import com.google.common.io.ByteArrayDataInput;

public interface PacketReciever 
{
    // Handling The Packet Data
    
	public void handlePacketData(INetworkManager INetworkManager, String Channel, ByteArrayDataInput DataStream);
}
