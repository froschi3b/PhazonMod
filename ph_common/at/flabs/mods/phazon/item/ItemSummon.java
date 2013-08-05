package at.flabs.mods.phazon.item;

import at.flabs.mods.phazon.Vars;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSummon extends Item {
    
    public ItemSummon(int id) {
        super(id);
        this.setCreativeTab(CreativeTabs.tabMisc);
        this.func_111206_d(Vars.texdir + ":" + Vars.unlocalizedSummon);
    }
    
    public ItemStack onEaten(ItemStack is, World world, EntityPlayer ep) {
        ep.getEntityData().setShort(Vars.NBTNamePhazonCorr,(short) 375);
        if (!world.isRemote) {
            double x = ep.posX -50D + world.rand.nextInt(100);
            double z = ep.posZ -50D + world.rand.nextInt(100);
            double y = world.getHeightValue((int)x, (int)z)-4;
            
            PhazonExplosion explosion = new PhazonExplosion(world, ep, x,y, z, 9);
            explosion.isSmoking = true;
            explosion.doExplosionA();
            explosion.doExplosionB(true);
        }
        is.stackSize--;
        return is;
    }
    
    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack par1ItemStack) {
        return 20;
    }
    
    public EnumAction getItemUseAction(ItemStack par1ItemStack) {
        return EnumAction.bow;
    }
    
    public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player) {
        player.setItemInUse(is, this.getMaxItemUseDuration(is));
        return is;
    }
}
