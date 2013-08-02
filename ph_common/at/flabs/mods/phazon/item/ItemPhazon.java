package at.flabs.mods.phazon.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemPhazon extends ItemBlock{

    public ItemPhazon(int par1) {
        super(par1);
    }

    public String getUnlocalizedName(ItemStack is)
    {
        return Block.blocksList[this.getBlockID()].getUnlocalizedName()+is.getItemDamage();
    }
}
