package at.flabs.mods.phazon;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class EventHandle {
    private static final ResourceLocation guiTex = new ResourceLocation(Vars.texdir,Vars.hud);
    private ScaledResolution res;
    private boolean renderedBossHealth;
    @ForgeSubscribe
    public void onRenderHealthBar(RenderGameOverlayEvent.Post evt) {
        if (evt.type == RenderGameOverlayEvent.ElementType.HEALTH) {
            Minecraft mc =Minecraft.getMinecraft();
            res = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
            int width=res.getScaledWidth();
            //int height=res.getScaledHeight();
            
            int y = 0;
            if(renderedBossHealth){
                y=20;
            }
            short s=mc.thePlayer.getEntityData().getShort(Vars.NBTNamePhazonLV);
            int wid=(int) (s/500d*128d);
            
            mc.renderEngine.func_110577_a(guiTex);
            mc.ingameGUI.drawTexturedModalRect(width/2-64, y, 0, 16, wid, 16);
            mc.ingameGUI.drawTexturedModalRect(width/2-64, y, 0, 0, 128, 16);
            mc.func_110434_K().func_110577_a(Gui.field_110324_m);
            renderedBossHealth=false;
        }else if(evt.type == RenderGameOverlayEvent.ElementType.BOSSHEALTH){
            if(BossStatus.bossName != null && BossStatus.statusBarLength > 0){
                renderedBossHealth=true;
            }
        }
    }
    @ForgeSubscribe
    public void onDeath(LivingDeathEvent evt){
        if(evt.entityLiving instanceof EntityPlayer){
            if (evt.source.damageType.startsWith("phazon")){
                //TODO spawn superzombie
            }
        }
    }
}
