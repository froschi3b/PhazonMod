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
        double x=ep.posX;//-100D + world.rand.nextInt(200);
        double z=ep.posX;//-100D + world.rand.nextInt(200);
        double y=ep.posY;//world.getHeightValue((int)x, (int)z);
        
        PhazonExplosion explosion = new PhazonExplosion(world, ep, x, y, z, 9);
        explosion.isSmoking = false;
        explosion.doExplosionA();
        explosion.doExplosionB(true);
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
