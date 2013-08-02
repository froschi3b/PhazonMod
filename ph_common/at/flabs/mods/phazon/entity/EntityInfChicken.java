package at.flabs.mods.phazon.entity;

import at.flabs.mods.phazon.PhazonMod;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityInfChicken extends EntityInf {

    public float field_70888_h;
    public float field_70886_e;
    public float field_70889_i = 1.0F;
    public float destPos;
    public float field_70884_g;
    
    public EntityInfChicken(World par1World) {
        super(par1World);
        this.setSize(0.3F, 0.7F);
    }
    
    public EntityInfChicken(EntityChicken ep) {
        this(ep.worldObj);
        this.setPosition(ep.posX, ep.posY, ep.posZ);
        this.setRotation(ep.rotationYaw, ep.rotationPitch);
    }
    
    public void onLivingUpdate() {
        super.onLivingUpdate();
        this.field_70888_h = this.field_70886_e;
        this.field_70884_g = this.destPos;
        this.destPos = (float)((double)this.destPos + (double)(this.onGround ? -1 : 4) * 0.3D);

        if (this.destPos < 0.0F)
        {
            this.destPos = 0.0F;
        }

        if (this.destPos > 1.0F)
        {
            this.destPos = 1.0F;
        }

        if (!this.onGround && this.field_70889_i < 1.0F)
        {
            this.field_70889_i = 1.0F;
        }

        this.field_70889_i = (float)((double)this.field_70889_i * 0.9D);

        if (!this.onGround && this.motionY < 0.0D) {
            this.motionY *= 0.6D;
        }
        this.field_70886_e += this.field_70889_i * 2.0F;
    }
    
    /**
     * Called when the mob is falling. Calculates and applies fall damage.
     */
    protected void fall(float par1) {}
    
    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound() {
        return "mob.chicken.say";
    }
    
    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound() {
        return "mob.chicken.hurt";
    }
    
    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound() {
        return "mob.chicken.hurt";
    }
    
    /**
     * Plays step sound at given x, y, z for the entity
     */
    protected void playStepSound(int par1, int par2, int par3, int par4) {
        this.playSound("mob.chicken.step", 0.15F, 1.0F);
    }
    
    /**
     * Returns the item ID for the item the mob drops on death.
     */
    protected int getDropItemId() {
        return Item.feather.itemID;
    }
    
    /**
     * Drop 0-2 items of this living's type. @param par1 - Whether this entity
     * has recently been hit by a player. @param par2 - Level of Looting used to
     * kill this mob.
     */
    protected void dropFewItems(boolean par1, int par2) {
        int j = this.rand.nextInt(3) + this.rand.nextInt(1 + par2);
        
        for (int k = 0; k < j; ++k) {
            this.dropItem(Item.feather.itemID, 1);
        }
        
        if (this.isBurning()) {
            this.dropItem(Item.chickenCooked.itemID, 1);
        } else {
            this.dropItem(Item.chickenRaw.itemID, 1);
        }
        this.dropItem(PhazonMod.instance.phazonDrop.itemID, 1);
    }
    
}
