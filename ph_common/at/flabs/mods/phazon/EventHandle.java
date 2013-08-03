package at.flabs.mods.phazon;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class EventHandle {
    private static final ResourceLocation guiTex = new ResourceLocation(Vars.texdir,Vars.hud);
    @ForgeSubscribe
    public void onRenderHealthBar(RenderGameOverlayEvent.Post evt) {
        if (evt.type == RenderGameOverlayEvent.ElementType.HEALTH) {
            Minecraft.getMinecraft().renderEngine.func_110577_a(guiTex);
            int wid=Minecraft.getMinecraft().currentScreen.width/2;
            int hid=Minecraft.getMinecraft().currentScreen.height/2; 
            Minecraft.getMinecraft().currentScreen.drawTexturedModalRect(0, 0, 0, 0, 128, 16);
        }
    }
}
