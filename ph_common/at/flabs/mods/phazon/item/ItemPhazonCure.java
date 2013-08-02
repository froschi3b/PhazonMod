package at.flabs.mods.phazon.item;

import at.flabs.mods.phazon.entity.EntityInfPig;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemPhazonCure extends Item{

    public ItemPhazonCure(int par1) {
        super(par1);
        this.setCreativeTab(CreativeTabs.tabAllSearch);
    }

    public boolean onItemUse(ItemStack is, EntityPlayer ep, World w, int x, int y, int z, int side, float xo, float yo, float zo)
    {
        if(!w.isRemote){
            EntityInfPig eip=new EntityInfPig(w);
            eip.setPosition(x+0.5, y+1.5, z+0.5);
            w.spawnEntityInWorld(eip);
        }
        return true;
    }
}
