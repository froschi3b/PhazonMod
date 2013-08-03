package at.flabs.mods.phazon.entity;

import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityInfSkeleton extends EntitySkeleton {

    public EntityInfSkeleton(World world) {
        super(world);
        this.getEntityData().setBoolean("PHinf", true);
    }
    public EntityInfSkeleton(EntitySkeleton ep){
        this(ep.worldObj);
        this.setPosition(ep.posX, ep.posY, ep.posZ);
        ItemStack[] inv=ep.getLastActiveItems();
        for (int i=0;i<inv.length;i++){
            this.setCurrentItemOrArmor(i, inv[i]);
        }
        this.setSkeletonType(ep.getSkeletonType());
    }
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEFINED;
    }
    
}
