package at.flabs.mods.phazon.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
            ep.curePotionEffects(is);
        }

        return is.stackSize <= 0 ? new ItemStack(Item.bucketEmpty) : is;
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
