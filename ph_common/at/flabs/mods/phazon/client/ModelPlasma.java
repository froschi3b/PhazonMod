package at.flabs.mods.phazon.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPlasma extends ModelBase {
    ModelRenderer box;
    
    public ModelPlasma() {
        textureWidth = 64;
        textureHeight = 32;
        box = new ModelRenderer(this, 0, 0);
        box.addBox(-2F, -2F, -2F, 4, 4, 4);
        box.setRotationPoint(0F, 0F, 0F);
        box.setTextureSize(64, 32);
        box.setTextureOffset(0, 0);
        box.mirror = true;
        setRotation(box, 0F, 0F, 0F);
    }
    
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        box.render(f5);
    }
    
    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
