package at.flabs.mods.phazon.common;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import cpw.mods.fml.common.network.PacketDispatcher;
import net.minecraft.entity.Entity; 
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet131MapData;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import at.flabs.mods.phazon.PhazonMod;
import at.flabs.mods.phazon.Vars;
import at.flabs.mods.phazon.entity.EntityInfChicken;
import at.flabs.mods.phazon.entity.EntityInfCow;
import at.flabs.mods.phazon.entity.EntityInfCreeper;
import at.flabs.mods.phazon.entity.EntityInfPig;
import at.flabs.mods.phazon.entity.EntityInfSkeleton;
import at.flabs.mods.phazon.entity.EntityInfZombie;

public class Util {
    public static DamageSource phazon = new DamageSourcePhazon();

    public static byte[] toBytes(short s) {
        return new byte[] { (byte) (s & 0x00FF), (byte) ((s & 0xFF00) >> 8) };
    }
    public static void sendPat(int x,int y,int z, int did){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(baos);
        try {
            out.writeInt(x);
            out.writeInt(y);
            out.writeInt(z);
            Packet131MapData pckt=PacketDispatcher.getTinyPacket(PhazonMod.instance, (short) 1, baos.toByteArray());
            PacketDispatcher.sendPacketToAllAround(x, y, z, 64, did, pckt);
        } catch (IOException e) {
        }
    }
    public static boolean setEntityInfected(World world, int x, int y, int z, Entity entity) {
        if (!entity.getEntityData().hasKey(Vars.NBTNamePhazonMob)) {
            if (entity instanceof EntityPig) {
                entity.setDead();
                entity.getEntityData().setBoolean(Vars.NBTNamePhazonMob, true);
                
                EntityInfPig eip = new EntityInfPig((EntityPig) entity);
                world.spawnEntityInWorld(eip);
                sendPat(x,y,z,world.provider.dimensionId);
                return true;
            }
            if (entity instanceof EntityCow) {
                entity.setDead();
                entity.getEntityData().setBoolean(Vars.NBTNamePhazonMob, true);
                
                EntityInfCow eip = new EntityInfCow((EntityCow) entity);
                world.spawnEntityInWorld(eip);
                sendPat(x,y,z,world.provider.dimensionId);
                return true;
            }
            if (entity instanceof EntityChicken) {
                entity.setDead();
                entity.getEntityData().setBoolean(Vars.NBTNamePhazonMob, true);
                
                EntityInfChicken eip = new EntityInfChicken((EntityChicken) entity);
                world.spawnEntityInWorld(eip);
                sendPat(x,y,z,world.provider.dimensionId);
                return true;
            }
            if (entity instanceof EntityCreeper) {
                entity.setDead();
                entity.getEntityData().setBoolean(Vars.NBTNamePhazonMob, true);
                
                EntityInfCreeper eip = new EntityInfCreeper((EntityCreeper) entity);
                world.spawnEntityInWorld(eip);
                sendPat(x,y,z,world.provider.dimensionId);
                return true;
            }
            if (entity instanceof EntityZombie) {
                entity.setDead();
                entity.getEntityData().setBoolean(Vars.NBTNamePhazonMob, true);
                
                EntityInfZombie eip = new EntityInfZombie((EntityZombie) entity);
                world.spawnEntityInWorld(eip);
                sendPat(x,y,z,world.provider.dimensionId);
                return true;
            }
            if (entity instanceof EntitySkeleton) {
                entity.setDead();
                entity.getEntityData().setBoolean(Vars.NBTNamePhazonMob, true);
                
                EntityInfSkeleton eip = new EntityInfSkeleton((EntitySkeleton) entity);
                world.spawnEntityInWorld(eip);
                sendPat(x,y,z,world.provider.dimensionId);
                return true;
            }
            return false;
        }
        return true;
    }
    
    public static boolean hasEnoughPhazon(EntityPlayer player, boolean infinity){
        return player.getEntityData().getShort(Vars.NBTNamePhazonLV)>amount(infinity);
    }
    public static void removePhazon(EntityPlayer player, boolean infinity){
        short s = player.getEntityData().getShort(Vars.NBTNamePhazonLV);
        s -= amount(infinity);
        player.getEntityData().setShort(Vars.NBTNamePhazonLV,s);
        

        short t = player.getEntityData().getShort(Vars.NBTNamePhazonCorr);
        t ++;
        if(t>375){
            t=375;
        }
        player.getEntityData().setShort(Vars.NBTNamePhazonCorr,t);
    }
    private static int amount(boolean inf){
        return inf ? 25 :50;
    }
    
    public static class DamageSourcePhazon extends DamageSource {
        
        protected DamageSourcePhazon() {
            super("phazon");
        }
        
    }
}
