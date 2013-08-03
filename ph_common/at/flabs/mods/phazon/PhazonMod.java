package at.flabs.mods.phazon;

import java.io.File;

import at.flabs.mods.phazon.block.BlockPhazon;
import at.flabs.mods.phazon.entity.EntityInfChicken;
import at.flabs.mods.phazon.entity.EntityInfCow;
import at.flabs.mods.phazon.entity.EntityInfPig;
import at.flabs.mods.phazon.item.ItemPhazon;
import at.flabs.mods.phazon.item.ItemPhazonCure;
import at.flabs.mods.phazon.network.NetHandle;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = Vars.modid, name = Vars.modname, version = Vars.version)
@NetworkMod(tinyPacketHandler=NetHandle.class)
public class PhazonMod {
    @Instance
    public static PhazonMod instance;
    @SidedProxy(clientSide = "at.flabs.mods.phazon.client.ProxyClient", serverSide = "at.flabs.mods.phazon.ProxyCommon")
    public static ProxyCommon proxy;
    public Block phazonBlock;
    public Item phazonDrop;
    
    @EventHandler
    public void perInit(FMLPreInitializationEvent evz) {
        Configuration config = new Configuration(new File(evz.getModConfigurationDirectory(), "PhazonMod.cfg"));
        int phazonBlockId = 0;
        int phazonCureId = 0;
        try {
            config.load();
            phazonBlockId = config.getBlock("Phazon", 1011).getInt();
            phazonCureId = config.getItem("PhazonDrop", 10110).getInt();
            Vars.datawatcherid = config.get("general", "dataWatcherId", 24).getInt();
        } catch (Exception e) {
            
        } finally {
            config.save();
        }
        phazonBlock = new BlockPhazon(phazonBlockId).setHardness(1f).setTickRandomly(true).setUnlocalizedName(Vars.unlocalizedPhazonBlock).setCreativeTab(CreativeTabs.tabBlock);
        
        phazonDrop = new ItemPhazonCure(phazonCureId).setUnlocalizedName(Vars.unlocalizedPhazonDrop);
        
        GameRegistry.registerBlock(phazonBlock, ItemPhazon.class,Vars.unlocalizedPhazonBlock);
        
        MinecraftForge.EVENT_BUS.register(new EventHandle());
        
        LanguageRegistry.instance().loadLocalization(Vars.en_US, "en_US", false);
        
        int ifcid = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(EntityInfCow.class, "phcow", ifcid);
        EntityRegistry.registerModEntity(EntityInfCow.class, "phcow", ifcid, this, 50, 1, false);
        int ifpid = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(EntityInfPig.class, "phpig", ifpid);
        EntityRegistry.registerModEntity(EntityInfPig.class, "phpig", ifpid, this, 50, 1, false);
        int ifchid = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(EntityInfChicken.class, "phchick", ifchid);
        EntityRegistry.registerModEntity(EntityInfChicken.class, "phchick", ifchid, this, 50, 1, false);
    }
    
    @EventHandler
    public void init(FMLInitializationEvent evz) {
        
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent evz) {
        proxy.registerRendering();
    }
}
