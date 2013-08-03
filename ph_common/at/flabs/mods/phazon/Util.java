package at.flabs.mods.phazon;

import net.minecraft.entity.Entity; 
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import at.flabs.mods.phazon.entity.EntityInfChicken;
import at.flabs.mods.phazon.entity.EntityInfCow;
import at.flabs.mods.phazon.entity.EntityInfCreeper;
import at.flabs.mods.phazon.entity.EntityInfPig;

public class Util {
    public static DamageSource phazon = new DamageSourcePhazon();

    public static byte[] toBytes(short s) {
        return new byte[] { (byte) (s & 0x00FF), (byte) ((s & 0xFF00) >> 8) };
    }
    public static boolean setEntityInfected(World world, int x, int y, int z, Entity entity) {
        if (!entity.getEntityData().hasKey(Vars.NBTNamePhazonMob)) {
            if (entity instanceof EntityPig) {
                entity.setDead();
                
                EntityInfPig eip = new EntityInfPig((EntityPig) entity);
                world.spawnEntityInWorld(eip);
                return true;
            }
            if (entity instanceof EntityCow) {
                entity.setDead();
                
                EntityInfCow eip = new EntityInfCow((EntityCow) entity);
                world.spawnEntityInWorld(eip);
                return true;
            }
            if (entity instanceof EntityChicken) {
                entity.setDead();
                
                EntityInfChicken eip = new EntityInfChicken((EntityChicken) entity);
                world.spawnEntityInWorld(eip);
                return true;
            }
            if (entity instanceof EntityCreeper) {
                entity.setDead();
                
                EntityInfCreeper eip = new EntityInfCreeper((EntityCreeper) entity);
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
