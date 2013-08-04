package at.flabs.mods.phazon.entity;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityInfSkeletonold extends EntitySkeleton {
    
    public EntityInfSkeletonold(World world) {
        super(world);
        this.getEntityData().setBoolean("PHinf", true);
    }
    
    public EntityInfSkeletonold(EntitySkeleton ep) {
        this(ep.worldObj);
        this.setPosition(ep.posX, ep.posY, ep.posZ);
        ItemStack[] inv = ep.getLastActiveItems();
        for (int i = 0; i < inv.length; i++) {
            this.setCurrentItemOrArmor(i, inv[i]);
        }
        this.setSkeletonType(ep.getSkeletonType());
    }
    
    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEFINED;
    }
    
    /**
     * Attack the specified entity using a ranged attack.
     */
    public void attackEntityWithRangedAttack(EntityLivingBase par1EntityLivingBase, float par2) {
        EntityPlasma entityarrow = new EntityPlasma(this.worldObj, this, par1EntityLivingBase, 1.6F, (float) (14 - this.worldObj.difficultySetting * 4));
        int i = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, this.getHeldItem());
        int j = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, this.getHeldItem());
        entityarrow.setDamage((double) (par2 * 2.0F) + this.rand.nextGaussian() * 0.25D + (double) ((float) this.worldObj.difficultySetting * 0.11F));
        
        if (i > 0) {
            entityarrow.setDamage(entityarrow.getDamage() + (double) i * 0.5D + 0.5D);
        }
        
        if (j > 0) {
            entityarrow.setKnockbackStrength(j);
        }
        
        if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, this.getHeldItem()) > 0 || this.getSkeletonType() == 1) {
            entityarrow.setFire(100);
        }
        
        this.playSound("random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.worldObj.spawnEntityInWorld(entityarrow);
    }
}
