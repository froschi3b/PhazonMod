package at.flabs.mods.phazon.item;

import at.flabs.mods.phazon.PhazonMod;
import at.flabs.mods.phazon.Vars;
import net.minecraft.item.ItemMultiTextureTile;

public class ItemPhazon extends ItemMultiTextureTile{

    public ItemPhazon(int par1) {
        super(par1, PhazonMod.instance.phazonBlock, new String[]{Vars.unlocalizedPhazonBlock+0,Vars.unlocalizedPhazonBlock+1});
    }
}
