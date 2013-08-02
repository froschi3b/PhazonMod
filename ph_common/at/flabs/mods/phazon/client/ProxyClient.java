package at.flabs.mods.phazon.client;

import at.flabs.mods.phazon.ProxyCommon;
import at.flabs.mods.phazon.entity.*;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class ProxyClient extends ProxyCommon{

    public void registerRendering() {
        RenderingRegistry.registerEntityRenderingHandler(EntityInfPig.class, new RenderInfPig(0.7f));
        RenderingRegistry.registerEntityRenderingHandler(EntityInfCow.class, new RenderInfCow(0.7f)); 
        }
}
