package at.flabs.mods.phazon;

import java.util.EnumSet;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class TickHandle implements ITickHandler{

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {
        if(type.contains(TickType.PLAYER)){
            EntityPlayer player=(EntityPlayer) tickData[0];
            if(player.getEntityData().getShort(Vars.NBTNamePhazonCorr)==1000){
                short s = player.getEntityData().getShort(Vars.NBTNamePhazonLV);
                s++;
                player.getEntityData().setShort(Vars.NBTNamePhazonLV,s);
            }
        }
    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData) {}

    @Override
    public EnumSet<TickType> ticks() {
        return EnumSet.of(TickType.PLAYER);
    }

    @Override
    public String getLabel() {
        return "PhazonMod TickHandler";
    }
    
}
