package at.flabs.mods.phazon.entity;

import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.world.World;

public abstract class EntityInf extends EntityZombie{

    public EntityInf(World par1World) {
        super(par1World);
    }
    
    public abstract String getEntityName();
}
