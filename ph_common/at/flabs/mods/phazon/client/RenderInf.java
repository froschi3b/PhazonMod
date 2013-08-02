package at.flabs.mods.phazon.client;

import at.flabs.mods.phazon.Vars;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderInf extends RenderLiving{
    private static final ResourceLocation pigtex = new ResourceLocation(Vars.texdir,"textures/entity/infpig.png");

    public RenderInf(ModelBase par1ModelBase, float f) {
        super(par1ModelBase, f);
    }

    @Override
    protected ResourceLocation func_110775_a(Entity entity) {
        return pigtex;
    }
    
}
