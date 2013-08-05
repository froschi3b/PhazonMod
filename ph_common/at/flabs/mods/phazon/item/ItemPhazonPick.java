package at.flabs.mods.phazon.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import at.flabs.mods.phazon.Vars;
import at.flabs.mods.phazon.common.Util;

public class ItemPhazonPick extends ItemPickaxe{

    public ItemPhazonPick(int id) {
        super(id, EnumToolMaterial.EMERALD);
        this.setCreativeTab(CreativeTabs.tabTools);
        this.func_111206_d(Vars.texdir + ":" + Vars.unlocalizedPhazonPick);
    }

    public void setDamage(ItemStack stack, int damage)
    {
        super.setDamage(stack, damage);
    }
    public boolean onBlockDestroyed(ItemStack is, World world, int x, int y, int z, int id, EntityLivingBase ep)
    {
        if(ep instanceof EntityPlayer){Util.removePhazon((EntityPlayer) ep, false);}
        return super.onBlockDestroyed(is, world, x, y, z, id, ep);
    }
}
