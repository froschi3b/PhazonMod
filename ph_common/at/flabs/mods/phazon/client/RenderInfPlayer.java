package at.flabs.mods.phazon.client;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import at.flabs.mods.phazon.Vars;

public class RenderInfPlayer extends RenderInfSkeleton {
    private static final ResourceLocation tex = new ResourceLocation(Vars.texdir, "textures/entity/infplayer.png");
    
    public RenderInfPlayer(){
        super(new ModelBiped());
    }
    protected ResourceLocation func_110775_a(Entity par1Entity) {
        return tex;
    }
    
    protected ResourceLocation func_110856_a(EntityLiving par1EntityLiving) {
        return tex;
    }
    
}
