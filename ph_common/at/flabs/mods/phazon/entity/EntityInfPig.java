package at.flabs.mods.phazon.entity;

import at.flabs.mods.phazon.PhazonMod;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityInfPig extends EntityInf {
    
    public EntityInfPig(World par1World) {
        super(par1World);
        this.setSize(0.9F, 0.9F);
    }
    
    public EntityInfPig(EntityPig ep) {
        super(ep.worldObj);
        this.setSize(0.9F, 0.9F);
        this.setPosition(ep.posX, ep.posY, ep.posZ);
        this.setRotation(ep.rotationYaw, ep.rotationPitch);
    }
    
    @Override
    protected void dropFewItems(boolean par1, int par2) {
        int j = this.getDropItemId();
        
        if (j > 0) {
            int k = this.rand.nextInt(3);
            
            if (par2 > 0) {
                k += this.rand.nextInt(par2 + 1);
            }
            
            for (int l = 0; l < k; ++l) {
                this.dropItem(j, 1);
            }
            this.dropItem(PhazonMod.instance.phazonCure.itemID, 1);
        }
    }
    
    @Override
    protected int getDropItemId() {
        return this.isBurning() ? Item.porkCooked.itemID : Item.porkRaw.itemID;
    }
    
    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound() {
        return "mob.pig.say";
    }
    
    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound() {
        return "mob.pig.say";
    }
    
    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound() {
        return "mob.pig.death";
    }
    
    /**
     * Plays step sound at given x, y, z for the entity
     */
    protected void playStepSound(int par1, int par2, int par3, int par4) {
        this.playSound("mob.pig.step", 0.15F, 1.0F);
    }
}
