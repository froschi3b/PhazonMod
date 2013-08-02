package at.flabs.mods.phazon.client;

import net.minecraft.client.model.ModelPig;
import at.flabs.mods.phazon.ProxyCommon;
import at.flabs.mods.phazon.entity.EntityInfPig;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ProxyClient extends ProxyCommon{

    public void registerRendering() {
        RenderingRegistry.registerEntityRenderingHandler(EntityInfPig.class, new RenderInf(new ModelPig(),0.7f));
    }
}
