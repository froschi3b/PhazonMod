package at.flabs.mods.phazon.item;

import at.flabs.mods.phazon.Util;
import at.flabs.mods.phazon.Vars;
import at.flabs.mods.phazon.entity.EntityPlasma;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class ItemPhazonCanon extends Item {
    @SideOnly(Side.CLIENT)
    private Icon[] iconArray;
    
    public ItemPhazonCanon(int par1) {
        super(par1);
        this.maxStackSize = 1;
        this.setMaxDamage(384);
        this.setCreativeTab(CreativeTabs.tabCombat);
        this.func_111206_d(Vars.texdir + ":" + Vars.unlocalizedPhazonCanon);
    }
    
    public ItemStack onEaten(ItemStack is, World world, EntityPlayer ep) {
        int j = this.getMaxItemUseDuration(is);
        
        boolean flag = EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, is) > 0;
        
        if (ep.capabilities.isCreativeMode || Util.hasEnoughPhazon(ep, flag)) {
            float f = (float) j;
            f = (f * f + f * 2.0F) / 3.0F;
            
            if (f > 1.0F) {
                f = 1.0F;
            }
            
            EntityPlasma entityplasma = new EntityPlasma(world, ep, f * 2.0F);
            
            if (f == 1.0F) {
                entityplasma.setIsCritical(true);
            }
            
            int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, is);
            
            if (k > 0) {
                entityplasma.setDamage(entityplasma.getDamage() + (double) k * 0.5D + 0.5D);
            }
            
            int l = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, is);
            
            if (l > 0) {
                entityplasma.setKnockbackStrength(l);
            }
            
            if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, is) > 0) {
                entityplasma.setFire(100);
            }
            
            is.damageItem(1, ep);
            world.playSoundAtEntity(ep, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
            
            if (!ep.capabilities.isCreativeMode) {
                ep.inventory.consumeInventoryItem(Item.arrow.itemID);
            }
            
            if (!world.isRemote) {
                world.spawnEntityInWorld(entityplasma);
            }
        }
        return is;
    }
    
    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack par1ItemStack) {
        return 20;
    }
    
    /**
     * returns the action that specifies what animation to play when the items
     * is being used
     */
    public EnumAction getItemUseAction(ItemStack par1ItemStack) {
        return EnumAction.bow;
    }
    
    /**
     * Called whenever this item is equipped and the right mouse button is
     * pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player) {
        boolean flag = EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, is) > 0;
        if (player.capabilities.isCreativeMode || Util.hasEnoughPhazon(player, flag)) {
            player.setItemInUse(is, this.getMaxItemUseDuration(is));
        }
        
        return is;
    }
    
    /**
     * Return the enchantability factor of the item, most of the time is based
     * on material.
     */
    public int getItemEnchantability() {
        return 1;
    }
    
}
