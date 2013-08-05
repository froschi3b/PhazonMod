package at.flabs.mods.phazon.item;

import at.flabs.mods.phazon.Vars;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemPhazonDrop extends Item {
    
    public ItemPhazonDrop(int par1) {
        super(par1);
        this.setCreativeTab(CreativeTabs.tabMisc);
        this.func_111206_d(Vars.texdir + ":" + Vars.unlocalizedPhazonDrop);
    }
}
