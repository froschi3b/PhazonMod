package at.flabs.mods.phazon.client;

import at.flabs.mods.phazon.Vars;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderPlasma extends Render {
    private static ResourceLocation tex = new ResourceLocation(Vars.texdir, "textures/entity/plasma.png");
    
    @Override
    public void doRender(Entity entity, double d0, double d1, double d2, float f, float f1) {
        
    }
    
    @Override
    protected ResourceLocation func_110775_a(Entity entity) {
        return tex;
    }
    
}
