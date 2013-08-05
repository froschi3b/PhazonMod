package at.flabs.mods.phazon.item;

import net.minecraft.block.Block;
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
    public boolean onBlockDestroyed(ItemStack is, World world, int id, int x, int y, int z, EntityLivingBase ep)
    {
        if(ep instanceof EntityPlayer && this.canHarvestBlock(Block.blocksList[id]) && Util.hasEnoughPhazon((EntityPlayer) ep, true)){Util.removePhazon((EntityPlayer) ep, true);}
        return super.onBlockDestroyed(is, world, id, x, y, z, ep);
    }
}
