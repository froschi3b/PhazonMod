package at.flabs.mods.phazon.network;

import at.flabs.mods.phazon.PhazonMod;
import at.flabs.mods.phazon.Vars;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.NetLoginHandler;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet131MapData;
import net.minecraft.network.packet.Packet1Login;
import net.minecraft.server.MinecraftServer;
import cpw.mods.fml.common.network.IConnectionHandler;
import cpw.mods.fml.common.network.ITinyPacketHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class NetHandle implements ITinyPacketHandler,IConnectionHandler{

    @Override
    public void handle(NetHandler handler, Packet131MapData mapData) {
        if(mapData.uniqueID==0){
            short val=(short)( ((mapData.itemData[1]&0xFF)<<8) | (mapData.itemData[0]&0xFF) );
            handler.getPlayer().getEntityData().setShort(Vars.NBTNamePhazonLV, val);
        }
    }

    @Override
    public void playerLoggedIn(Player player, NetHandler netHandler, INetworkManager manager) {
        EntityPlayerMP ep=((EntityPlayerMP)player);
        short s=ep.getEntityData().getShort(Vars.NBTNamePhazonLV);
        Packet131MapData pckt=PacketDispatcher.getTinyPacket(PhazonMod.instance, (short) 0, toBytes(s));
        PacketDispatcher.sendPacketToPlayer(pckt, player);
    }

    private static byte[] toBytes(short s) {
        return new byte[]{(byte)(s & 0x00FF),(byte)((s & 0xFF00)>>8)};
    }
    @Override
    public String connectionReceived(NetLoginHandler netHandler, INetworkManager manager) {
        return null;
    }

    @Override
    public void connectionOpened(NetHandler netClientHandler, String server, int port, INetworkManager manager) {
        
    }

    @Override
    public void connectionOpened(NetHandler netClientHandler, MinecraftServer server, INetworkManager manager) {
        
    }

    @Override
    public void connectionClosed(INetworkManager manager) {
        
    }

    @Override
    public void clientLoggedIn(NetHandler clientHandler, INetworkManager manager, Packet1Login login) {
        
    }
    
}
