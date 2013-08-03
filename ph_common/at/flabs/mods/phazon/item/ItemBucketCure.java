package at.flabs.mods.phazon.item;

import java.util.List;

import at.flabs.mods.phazon.Vars;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemBucketCure extends Item {

    public ItemBucketCure(int id) {
        super(id);
        this.setMaxStackSize(1);
        this.setCreativeTab(CreativeTabs.tabMisc);
    }


    public ItemStack onEaten(ItemStack is, World world, EntityPlayer ep)
    {
        if (!ep.capabilities.isCreativeMode)
        {
            --is.stackSize;
        }

        if (!world.isRemote)
        {
            short prev=ep.getEntityData().getShort(Vars.NBTNamePhazonLV);
            prev-=100;
            if(prev<0){
                prev=0;
            }
            ep.getEntityData().setShort(Vars.NBTNamePhazonLV, prev);
        }

        return is.stackSize <= 0 ? new ItemStack(Item.bucketEmpty) : is;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void addInformation(ItemStack is, EntityPlayer ep, List l, boolean debug) {
        l.add(StatCollector.translateToLocal("item.phazonCure.sub"));
    }

    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack is)
    {
        return 32;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack is)
    {
        return EnumAction.drink;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer ep)
    {
        ep.setItemInUse(is, this.getMaxItemUseDuration(is));
        return is;
    }
}
