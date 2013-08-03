package at.flabs.mods.phazon.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelChicken;
import net.minecraft.client.model.ModelCow;
import net.minecraft.client.model.ModelPig;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import at.flabs.mods.phazon.Vars;
import at.flabs.mods.phazon.entity.EntityInfChicken;

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
    public static class Chicken extends RenderInf {
        public Chicken(float f) {
            super(new ModelChicken(), f, "chicken");
        }
        protected float handleRotationFloat(EntityLivingBase par1EntityLivingBase, float par2)
        {
            return this.getWingRotation((EntityInfChicken)par1EntityLivingBase, par2);
        }
        protected float getWingRotation(EntityInfChicken ec, float f)
        {
            float f1 = ec.field_70888_h + (ec.field_70886_e - ec.field_70888_h) * f;
            float f2 = ec.field_70884_g + (ec.destPos - ec.field_70884_g) * f;
            return (MathHelper.sin(f1) + 1.0F) * f2;
        }
    }
    public static class Zombie extends RenderInf {
        public Zombie(float f) {
            super(new ModelZombie(), f, "zombie");
        }
    }
}
