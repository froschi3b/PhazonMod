package at.flabs.mods.phazon;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class EventHandle {
    private static final ResourceLocation guiTex = new ResourceLocation("phazonmod","textures/gui/hud.png");
    @ForgeSubscribe
    public void onRenderHealthBar(RenderGameOverlayEvent.Post evt) {
        if (evt.type == RenderGameOverlayEvent.ElementType.HEALTH) {
            Minecraft.getMinecraft().renderEngine.func_110577_a(guiTex);
            
        }
    }
}
