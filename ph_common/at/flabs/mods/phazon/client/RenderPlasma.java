package at.flabs.mods.phazon.client;

import org.lwjgl.opengl.GL11;

import at.flabs.mods.phazon.Vars;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderPlasma extends Render {
    private static ResourceLocation tex = new ResourceLocation(Vars.texdir, "textures/entity/plasma.png");
    
    private static ModelBase model=new ModelPlasma();
    
    @Override
    public void doRender(Entity entity, double x, double y, double z, float f, float f1) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)x, (float)y, (float)z);
        model.render(entity, 0f, 0f, 0f, 0f, 0f, 0f);
        GL11.glPopMatrix();
    }
    
    @Override
    protected ResourceLocation func_110775_a(Entity entity) {
        return tex;
    }
    
}
