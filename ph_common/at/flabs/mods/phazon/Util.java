package at.flabs.mods.phazon;

import net.minecraft.entity.Entity; 
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
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
    public static boolean setEntityInfected(World world, int x, int y, int z, Entity entity) {
        if (!entity.getEntityData().hasKey(Vars.NBTNamePhazonMob)) {
            if (entity instanceof EntityPig) {
                entity.setDead();
                entity.getEntityData().setBoolean(Vars.NBTNamePhazonMob, true);
                
                EntityInfPig eip = new EntityInfPig((EntityPig) entity);
                world.spawnEntityInWorld(eip);
                world.spawnParticle("smoke", x+0.5, y+0.5, z+0.5, 0, 0.5, 0);
                return true;
            }
            if (entity instanceof EntityCow) {
                entity.setDead();
                entity.getEntityData().setBoolean(Vars.NBTNamePhazonMob, true);
                
                EntityInfCow eip = new EntityInfCow((EntityCow) entity);
                world.spawnEntityInWorld(eip);
                return true;
            }
            if (entity instanceof EntityChicken) {
                entity.setDead();
                entity.getEntityData().setBoolean(Vars.NBTNamePhazonMob, true);
                
                EntityInfChicken eip = new EntityInfChicken((EntityChicken) entity);
                world.spawnEntityInWorld(eip);
                return true;
            }
            if (entity instanceof EntityCreeper) {
                entity.setDead();
                entity.getEntityData().setBoolean(Vars.NBTNamePhazonMob, true);
                
                EntityInfCreeper eip = new EntityInfCreeper((EntityCreeper) entity);
                world.spawnEntityInWorld(eip);
                return true;
            }
            if (entity instanceof EntityZombie) {
                entity.setDead();
                entity.getEntityData().setBoolean(Vars.NBTNamePhazonMob, true);
                
                EntityInfZombie eip = new EntityInfZombie((EntityZombie) entity);
                world.spawnEntityInWorld(eip);
                return true;
            }
            if (entity instanceof EntitySkeleton) {
                entity.setDead();
                entity.getEntityData().setBoolean(Vars.NBTNamePhazonMob, true);
                
                EntityInfSkeleton eip = new EntityInfSkeleton((EntitySkeleton) entity);
                world.spawnEntityInWorld(eip);
                return true;
            }
            return false;
        }
        return true;
    }
    
    
    public static class DamageSourcePhazon extends DamageSource {
        
        protected DamageSourcePhazon() {
            super("phazon");
        }
        
    }
}
