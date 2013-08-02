package at.flabs.mods.phazon;

import java.io.File;

import at.flabs.mods.phazon.block.BlockPhazon;
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
        int phazonBlockId = 0;
        try {
            config.load();
            phazonBlockId = config.getBlock("Phazon", 1011).getInt();
        } catch (Exception e) {
            
        } finally {
            config.save();
        }
        phazonBlock = new BlockPhazon(phazonBlockId).setHardness(1f).setTickRandomly(true).setUnlocalizedName(Vars.unlocalizedPhazonBlock);
        
    }
    
    @EventHandler
    public void init(FMLInitializationEvent evz) {
        
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent evz) {
        
    }
}
