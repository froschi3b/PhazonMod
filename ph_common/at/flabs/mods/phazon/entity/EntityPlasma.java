package at.flabs.mods.phazon.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.world.World;

public class EntityPlasma extends EntityArrow {

    public EntityPlasma(World par1World, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase, float par4, float par5) {
        super(par1World, par2EntityLivingBase, par3EntityLivingBase, par4, par5);
    }
    public EntityPlasma(World par1World) {
        super(par1World);
    }
    
}
