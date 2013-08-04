package at.flabs.mods.phazon.network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Random;

import at.flabs.mods.phazon.PhazonMod;
import at.flabs.mods.phazon.Util;
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
        }else if(mapData.uniqueID==1){
            for(int i=0;i<50;i++){
                DataInputStream in = new DataInputStream(new ByteArrayInputStream(mapData.itemData));
                
                Random r = new Random(System.nanoTime());
                try {
                    handler.getPlayer().worldObj.spawnParticle("smoke", in.readInt()+r.nextDouble(), in.readInt()+r.nextDouble(), in.readInt()+r.nextDouble(), 0, 0.5, 0);
                } catch (IOException e) {
                }
            }
        }
    }

    @Override
    public void playerLoggedIn(Player player, NetHandler netHandler, INetworkManager manager) {
        EntityPlayerMP ep=((EntityPlayerMP)player);
        short s=ep.getEntityData().getShort(Vars.NBTNamePhazonLV);
        Packet131MapData pckt=PacketDispatcher.getTinyPacket(PhazonMod.instance, (short) 0, Util.toBytes(s));
        PacketDispatcher.sendPacketToPlayer(pckt, player);
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
