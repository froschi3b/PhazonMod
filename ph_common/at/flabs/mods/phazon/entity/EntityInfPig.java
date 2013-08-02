package at.flabs.mods.phazon.entity;

import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.world.World;

public class EntityInfPig extends EntityZombie {
    
    public EntityInfPig(World par1World) {
        super(par1World);
        tasks.taskEntries.remove(5);
        tasks.taskEntries.remove(3);
        tasks.taskEntries.remove(1);
    }
}
