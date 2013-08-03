package at.flabs.mods.phazon.entity;

import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityInfZombie extends EntityZombie{

    public EntityInfZombie(World world) {
        super(world);
    }
    public EntityInfZombie(EntityZombie ep){
        this(ep.worldObj);
        this.setPosition(ep.posX, ep.posY, ep.posZ);
        ItemStack[] inv=ep.getLastActiveItems();
        for (int i=0;i<inv.length;i++){
            this.setCurrentItemOrArmor(i, inv[i]);
        }
    }
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEFINED;
    }
    
}
