package at.flabs.mods.phazon.entity;

import net.minecraft.entity.passive.EntityCow;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import at.flabs.mods.phazon.PhazonMod;

public class EntityInfCow extends EntityInf {
    
    public EntityInfCow(World par1World) {
        super(par1World);
        this.setSize(0.9F, 1.3F);
    }
    
    public EntityInfCow(EntityCow ep) {
        this(ep.worldObj);
        this.setPosition(ep.posX, ep.posY, ep.posZ);
        this.setRotation(ep.rotationYaw, ep.rotationPitch);
    }
    /**
     * Returns the item ID for the item the mob drops on death.
     */
    protected int getDropItemId()
    {
        return Item.leather.itemID;
    }

    /**
     * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param
     * par2 - Level of Looting used to kill this mob.
     */
    protected void dropFewItems(boolean par1, int par2)
    {
        int j = this.rand.nextInt(3) + this.rand.nextInt(1 + par2);
        int k;

        for (k = 0; k < j; ++k)
        {
            this.dropItem(Item.leather.itemID, 1);
        }

        j = this.rand.nextInt(3) + 1 + this.rand.nextInt(1 + par2);

        for (k = 0; k < j; ++k)
        {
            if (this.isBurning())
            {
                this.dropItem(Item.beefCooked.itemID, 1);
            }
            else
            {
                this.dropItem(Item.beefRaw.itemID, 1);
            }
        }
        this.dropItem(PhazonMod.instance.phazonDrop.itemID, 1);
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound()
    {
        return "mob.cow.say";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound()
    {
        return "mob.cow.hurt";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound()
    {
        return "mob.cow.hurt";
    }

    protected float getSoundVolume()
    {
        return 0.4F;
    }
    /**
     * Plays step sound at given x, y, z for the entity
     */
    protected void playStepSound(int par1, int par2, int par3, int par4)
    {
        this.playSound("mob.cow.step", 0.15F, 1.0F);
    }
}
