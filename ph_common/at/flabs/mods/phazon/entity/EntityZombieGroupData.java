package at.flabs.mods.phazon.entity;

import net.minecraft.entity.EntityLivingData;

class EntityZombieGroupData implements EntityLivingData
{
    public boolean field_142048_a;
    public boolean field_142046_b;

    final EntityInfZombie field_142047_c;

    private EntityZombieGroupData(EntityInfZombie par1EntityZombie, boolean par2, boolean par3)
    {
        this.field_142047_c = par1EntityZombie;
        this.field_142048_a = false;
        this.field_142046_b = false;
        this.field_142048_a = par2;
        this.field_142046_b = par3;
    }

    EntityZombieGroupData(EntityInfZombie par1EntityZombie, boolean par2, boolean par3, EntityInfZombie par4EntityZombieINNER1)
    {
        this(par1EntityZombie, par2, par3);
    }
}