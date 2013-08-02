package at.flabs.mods.phazon.client;

import at.flabs.mods.phazon.ProxyCommon;
import at.flabs.mods.phazon.entity.*;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class ProxyClient extends ProxyCommon{

    public void registerRendering() {
        RenderingRegistry.registerEntityRenderingHandler(EntityInfCow.class, new RenderInf.Cow(0.7f));
        RenderingRegistry.registerEntityRenderingHandler(EntityInfPig.class, new RenderInf.Pig(0.7f));
        }
}