package at.flabs.mods.phazon.client;

import at.flabs.mods.phazon.entity.EntityInfSkeleton;
import net.minecraft.client.model.ModelSkeleton;
import net.minecraft.entity.EntityLivingBase;

public class ModelInfSkeleton extends ModelSkeleton{
    public ModelInfSkeleton(){
        super();
    }
    public void setLivingAnimations(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4)
    {
        this.aimedBow = ((EntityInfSkeleton)par1EntityLivingBase).getSkeletonType() == 1;
    }
    
}
