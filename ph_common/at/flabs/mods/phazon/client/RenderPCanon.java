package at.flabs.mods.phazon.client;

import org.lwjgl.opengl.GL11;

import at.flabs.mods.phazon.PhazonMod;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public class RenderPCanon implements IItemRenderer {
    
    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return ItemRenderType.EQUIPPED.equals(type);
    }
    
    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return ItemRenderType.EQUIPPED.equals(type) && item.itemID==PhazonMod.instance.phazonCanon.itemID;
    }
    
    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        f2 = 0.625F;
        GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
        GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
        GL11.glScalef(f2, -f2, f2);
        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
    }
    
}
