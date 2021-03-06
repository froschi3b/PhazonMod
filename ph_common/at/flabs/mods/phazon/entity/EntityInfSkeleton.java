package at.flabs.mods.phazon.entity;

import java.util.Calendar;

import at.flabs.mods.phazon.PhazonMod;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class EntityInfSkeleton extends EntityMob implements IRangedAttackMob {
    private EntityAIArrowAttack aiArrowAttack = new EntityAIArrowAttack(this, 1.0D, 20, 60, 15.0F);
    
    public EntityInfSkeleton(World par1World) {
        super(par1World);
        this.getEntityData().setBoolean("PHinf", true);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(3, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        
        if (par1World != null && !par1World.isRemote) {
            this.tasks.addTask(4, this.aiArrowAttack);
        }
    }
    
    public EntityInfSkeleton(EntitySkeleton ep) {
        this(ep.worldObj);
        this.setPosition(ep.posX, ep.posY, ep.posZ);
        ItemStack[] inv = ep.getLastActiveItems();
        if (inv[0] != null) {
            if (inv[0].getItem() == Item.bow) {
                inv[0].itemID = PhazonMod.instance.phazonCanon.itemID;
            }
        }
        for (int i = 0; i < inv.length; i++) {
            this.setCurrentItemOrArmor(i, inv[i]);
        }
        this.setSkeletonType(ep.getSkeletonType());
    }
    
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25D);
    }
    
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(13, new Byte((byte) 0));
    }
    
    /**
     * Returns true if the newer Entity AI code should be run
     */
    public boolean isAIEnabled() {
        return true;
    }
    
    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound() {
        return "mob.skeleton.say";
    }
    
    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound() {
        return "mob.skeleton.hurt";
    }
    
    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound() {
        return "mob.skeleton.death";
    }
    
    /**
     * Plays step sound at given x, y, z for the entity
     */
    protected void playStepSound(int par1, int par2, int par3, int par4) {
        this.playSound("mob.skeleton.step", 0.15F, 1.0F);
    }
    
    public boolean attackEntityAsMob(Entity par1Entity) {
        if (super.attackEntityAsMob(par1Entity)) {
            if (this.getSkeletonType() == 1 && par1Entity instanceof EntityLivingBase) {
                ((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.wither.id, 200));
            }
            
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Called frequently so the entity can update its state every tick as
     * required. For example, zombies and skeletons use this to react to
     * sunlight and start to burn.
     */
    public void onLivingUpdate() {
        if (this.worldObj.isRemote && this.getSkeletonType() == 1) {
            this.setSize(0.72F, 2.34F);
        }
        
        super.onLivingUpdate();
    }
    
    /**
     * Handles updating while being ridden by an entity
     */
    public void updateRidden() {
        super.updateRidden();
        
        if (this.ridingEntity instanceof EntityCreature) {
            EntityCreature entitycreature = (EntityCreature) this.ridingEntity;
            this.renderYawOffset = entitycreature.renderYawOffset;
        }
    }
    
    /**
     * Returns the item ID for the item the mob drops on death.
     */
    protected int getDropItemId() {
        return Item.arrow.itemID;
    }
    
    /**
     * Drop 0-2 items of this living's type. @param par1 - Whether this entity
     * has recently been hit by a player. @param par2 - Level of Looting used to
     * kill this mob.
     */
    protected void dropFewItems(boolean par1, int par2) {
        int j;
        int k;
        
        if (this.getSkeletonType() == 1) {
            j = this.rand.nextInt(3 + par2) - 1;
            
            for (k = 0; k < j; ++k) {
                this.dropItem(Item.coal.itemID, 1);
            }
        } else {
            j = this.rand.nextInt(3 + par2);
            
            for (k = 0; k < j; ++k) {
                this.dropItem(Item.arrow.itemID, 1);
            }
        }
        
        j = this.rand.nextInt(3 + par2);
        
        for (k = 0; k < j; ++k) {
            this.dropItem(Item.bone.itemID, 1);
        }
        this.dropItem(PhazonMod.instance.phazonDrop.itemID, 1);
    }
    
    protected void dropRareDrop(int par1) {
        if (this.getSkeletonType() == 1) {
            this.entityDropItem(new ItemStack(Item.skull.itemID, 1, 1), 0.0F);
        }
    }
    
    /**
     * Makes entity wear random armor based on difficulty
     */
    protected void addRandomArmor() {
        super.addRandomArmor();
        this.setCurrentItemOrArmor(0, new ItemStack(PhazonMod.instance.phazonCanon));
    }
    
    public EntityLivingData func_110161_a(EntityLivingData par1EntityLivingData) {
        par1EntityLivingData = super.func_110161_a(par1EntityLivingData);
        
        this.tasks.addTask(4, this.aiArrowAttack);
        this.addRandomArmor();
        this.enchantEquipment();
        
        this.setCanPickUpLoot(this.rand.nextFloat() < 0.55F * this.worldObj.func_110746_b(this.posX, this.posY, this.posZ));
        
        if (this.getCurrentItemOrArmor(4) == null) {
            Calendar calendar = this.worldObj.getCurrentDate();
            
            if (calendar.get(2) + 1 == 10 && calendar.get(5) == 31 && this.rand.nextFloat() < 0.25F) {
                this.setCurrentItemOrArmor(4, new ItemStack(this.rand.nextFloat() < 0.1F ? Block.pumpkinLantern : Block.pumpkin));
                this.equipmentDropChances[4] = 0.0F;
            }
        }
        
        return par1EntityLivingData;
    }
    
    /**
     * Attack the specified entity using a ranged attack.
     */
    public void attackEntityWithRangedAttack(EntityLivingBase par1EntityLivingBase, float par2) {
        EntityPlasma entityarrow = new EntityPlasma(this.worldObj, this, par1EntityLivingBase, 1.6F, (float) (14 - this.worldObj.difficultySetting * 4));
        int i = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, this.getHeldItem());
        int j = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, this.getHeldItem());
        entityarrow.setDamage((double) (par2 * 6.0F) + this.rand.nextGaussian() + (double) ((float) this.worldObj.difficultySetting * 0.22F));
        
        if (i > 0) {
            entityarrow.setDamage(entityarrow.getDamage() + (double) i + 1.5D);
        }
        
        if (j > 0) {
            entityarrow.setKnockbackStrength(j);
        }
        
        if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, this.getHeldItem()) > 0 || this.getSkeletonType() == 1) {
            entityarrow.setFire(100);
        }
        
        this.playSound("random.explode", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.worldObj.spawnEntityInWorld(entityarrow);
    }
    
    /**
     * Return this skeleton's type.
     */
    public int getSkeletonType() {
        return this.dataWatcher.getWatchableObjectByte(13);
    }
    
    /**
     * Set this skeleton's type.
     */
    public void setSkeletonType(int par1) {
        this.dataWatcher.updateObject(13, Byte.valueOf((byte) par1));
        this.isImmuneToFire = par1 == 1;
        
        if (par1 == 1) {
            this.setSize(0.72F, 2.34F);
        } else {
            this.setSize(0.6F, 1.8F);
        }
    }
    
    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound);
        
        if (par1NBTTagCompound.hasKey("SkeletonType")) {
            byte b0 = par1NBTTagCompound.getByte("SkeletonType");
            this.setSkeletonType(b0);
        }

        this.tasks.addTask(4, this.aiArrowAttack);
    }
    
    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setByte("SkeletonType", (byte) this.getSkeletonType());
    }
    
    /**
     * Sets the held item, or an armor slot. Slot 0 is held item. Slot 1-4 is
     * armor. Params: Item, slot
     */
    public void setCurrentItemOrArmor(int par1, ItemStack par2ItemStack) {
        super.setCurrentItemOrArmor(par1, par2ItemStack);
        
        if (!this.worldObj.isRemote && par1 == 0) {
            this.tasks.addTask(4, this.aiArrowAttack);
        }
    }
    
    /**
     * Returns the Y Offset of this entity.
     */
    public double getYOffset() {
        return super.getYOffset() - 0.5D;
    }
}
