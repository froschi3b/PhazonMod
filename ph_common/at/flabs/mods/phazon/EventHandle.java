package at.flabs.mods.phazon;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class EventHandle {
    private static final ResourceLocation guiTex = new ResourceLocation(Vars.texdir,Vars.hud);
    private ScaledResolution res;
    @ForgeSubscribe
    public void onRenderHealthBar(RenderGameOverlayEvent.Pre evt) {
        if (evt.type == RenderGameOverlayEvent.ElementType.HEALTH) {
            Minecraft mc =Minecraft.getMinecraft();
            res = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
            int width=res.getScaledWidth();
            int height=res.getScaledHeight();

            mc.renderEngine.func_110577_a(guiTex);
            mc.ingameGUI.drawTexturedModalRect(0, 0, 0, 0, 128, 16);
        }
    }
}
