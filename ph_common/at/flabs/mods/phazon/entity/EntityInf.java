package at.flabs.mods.phazon.entity;

import at.flabs.mods.phazon.Vars;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.world.World;

public abstract class EntityInf extends EntityMob {
    
    public EntityInf(World par1World) {
        super(par1World);
        this.getEntityData().setBoolean(Vars.NBTNamePhazonMob, true);
    }
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(40.0D);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.23000000417232513D*1.5D);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(6.0D);
    }
    /**
     * Returns true if the newer Entity AI code should be run
     */
    protected boolean isAIEnabled()
    {
        return true;
    }
}
