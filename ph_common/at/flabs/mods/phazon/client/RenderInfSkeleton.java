package at.flabs.mods.phazon.client;

import at.flabs.mods.phazon.Vars;
import at.flabs.mods.phazon.entity.EntityInfSkeleton;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderInfSkeleton extends RenderBiped {
    private static final ResourceLocation tex = new ResourceLocation(Vars.texdir, "textures/entity/infskeleton.png");
    
    public RenderInfSkeleton() {
        super(new ModelInfSkeleton(), 0.5F);
    }
    public RenderInfSkeleton(ModelBiped model) {
        super(model, 0.5F);
    }
    
    protected void preRenderCallback(EntityInfSkeleton par1EntitySkeleton, float par2) {
        if (par1EntitySkeleton.getSkeletonType() == 1) {
            GL11.glScalef(1.2F, 1.2F, 1.2F);
        }
    }
    
    protected void func_82422_c() {
        GL11.glTranslatef(0.09375F, 0.1875F, 0.0F);
    }
    
    protected ResourceLocation func_110856_a(EntityLiving par1EntityLiving) {
        return tex;
    }
    
    /**
     * Allows the render to do any OpenGL state modifications necessary before
     * the model is rendered. Args: entityLiving, partialTickTime
     */
    protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2) {
        this.preRenderCallback((EntityInfSkeleton) par1EntityLivingBase, par2);
    }
    
    protected ResourceLocation func_110775_a(Entity par1Entity) {
        return tex;
    }
}
