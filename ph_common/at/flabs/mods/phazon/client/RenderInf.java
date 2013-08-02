package at.flabs.mods.phazon.client;

import at.flabs.mods.phazon.Vars;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelCow;
import net.minecraft.client.model.ModelPig;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderInf extends RenderLiving {
    private ResourceLocation tex;
    
    public RenderInf(ModelBase par1ModelBase, float f, String texture) {
        super(par1ModelBase, f);
        tex = new ResourceLocation(Vars.texdir, "textures/entity/inf" + texture + ".png");
    }
    
    @Override
    protected ResourceLocation func_110775_a(Entity entity) {
        return tex;
    }
    
    public static class Cow extends RenderInf {
        public Cow(float f) {
            super(new ModelCow(), f, "cow");
        }
    }
    
    public static class Pig extends RenderInf {
        public Pig(float f) {
            super(new ModelPig(), f, "pig");
        }
    }
    
}
