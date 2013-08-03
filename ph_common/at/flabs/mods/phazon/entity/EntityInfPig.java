package at.flabs.mods.phazon.entity;

import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import at.flabs.mods.phazon.PhazonMod;

public class EntityInfPig extends EntityInf {
    public EntityInfPig(World par1World) {
        super(par1World);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
        this.tasks.addTask(2, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(3, new EntityAIMoveThroughVillage(this, 1.0D, false));
        this.tasks.addTask(4, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(5, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        this.setSize(0.9F, 0.9F);
    }
    
    public EntityInfPig(EntityPig ep) {
        this(ep.worldObj);
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
            this.dropItem(PhazonMod.instance.phazonDrop.itemID, 1);
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
