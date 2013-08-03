package at.flabs.mods.phazon.entity;

import net.minecraft.world.World;

public class EntityInfCreeper extends EntityInf {

    /**
     * Time when this creeper was last in an active state (Messed up code here, probably causes creeper animation to go
     * weird)
     */
    private int lastActiveTime;

    /**
     * The amount of time since the creeper was close enough to the player to ignite
     */
    private int timeSinceIgnited;
    private int fuseTime = 30;

    /** Explosion radius for this creeper. */
    private int explosionRadius = 3;
    
    public EntityInfCreeper(World world) {
        super(world);
    }
    
}
