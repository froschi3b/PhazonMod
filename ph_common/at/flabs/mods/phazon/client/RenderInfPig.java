package at.flabs.mods.phazon.client;

import at.flabs.mods.phazon.Vars;
import net.minecraft.client.model.ModelPig;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderInfPig extends RenderLiving{

    public RenderInfPig(float f) {
        super(new ModelPig(), f);
    }
    
    private static final ResourceLocation tex = new ResourceLocation(Vars.texdir, "textures/entity/infpig.png");
    
    @Override
    protected ResourceLocation func_110775_a(Entity entity) {
        return tex;
    }
}
