package at.flabs.mods.phazon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.world.World;
import at.flabs.mods.phazon.entity.EntityInfPig;

public class Util {
    
    public static void setEntityInfected(World world, int x, int y, int z, Entity entity) {
        if (!entity.getEntityData().hasKey(Vars.NBTNamePhazonMob)) {
            if (entity instanceof EntityPig) {
                entity.setDead();
                
                EntityInfPig eip = new EntityInfPig((EntityPig) entity);
                world.spawnEntityInWorld(eip);
            }
        }
    }
}
