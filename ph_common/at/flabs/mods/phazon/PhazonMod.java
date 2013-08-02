package at.flabs.mods.phazon;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = Vars.modid, name = Vars.modname, version = Vars.version)
@NetworkMod
public class PhazonMod {
    @Instance
    public static PhazonMod instance;
    public Block phazonBlock;
    
    @EventHandler
    public void perInit(FMLPreInitializationEvent evz) {
        Configuration config = new Configuration(new File(evz.getModConfigurationDirectory(), "PhazonMod.cfg"));
        try{
            config.load();
            
        }catch(Exception e){
            
        }finally{
            config.save();
        }
    }
    
    @EventHandler
    public void init(FMLInitializationEvent evz) {
        
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent evz) {
        
    }
}
