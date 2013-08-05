package at.flabs.mods.phazon;

import java.io.File;

import at.flabs.mods.phazon.common.BlockPhazon;
import at.flabs.mods.phazon.common.EventHandle;
import at.flabs.mods.phazon.common.NetHandle;
import at.flabs.mods.phazon.common.ProxyCommon;
import at.flabs.mods.phazon.common.TickHandle;
import at.flabs.mods.phazon.entity.EntityInfChicken;
import at.flabs.mods.phazon.entity.EntityInfCow;
import at.flabs.mods.phazon.entity.EntityInfCreeper;
import at.flabs.mods.phazon.entity.EntityInfPig;
import at.flabs.mods.phazon.entity.EntityInfSkeleton;
import at.flabs.mods.phazon.entity.EntityInfZombie;
import at.flabs.mods.phazon.entity.EntityPlasma;
import at.flabs.mods.phazon.item.ItemBucketCure;
import at.flabs.mods.phazon.item.ItemPhazon;
import at.flabs.mods.phazon.item.ItemPhazonCanon;
import at.flabs.mods.phazon.item.ItemPhazonDrop;
import at.flabs.mods.phazon.item.ItemPhazonPick;
import at.flabs.mods.phazon.item.ItemSummon;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = Vars.modid, name = Vars.modname, version = Vars.version)
@NetworkMod(tinyPacketHandler = NetHandle.class, connectionHandler = NetHandle.class)
public class PhazonMod {
    @Instance
    public static PhazonMod instance;
    @SidedProxy(clientSide = "at.flabs.mods.phazon.client.ProxyClient", serverSide = "at.flabs.mods.phazon.ProxyCommon")
    public static ProxyCommon proxy;
    public Block phazonBlock;
    public Item phazonDrop,phazonCure,summon,phazonPick;
    public ItemPhazonCanon phazonCanon;
    
    @EventHandler
    public void perInit(FMLPreInitializationEvent evz) {
        Configuration config = new Configuration(new File(evz.getModConfigurationDirectory(), "PhazonMod.cfg"));
        int phazonBlockId = 0;
        int phazonDropId = 0;
        int phazonCureId = 0;
        int phazonCanonId = 0;
        int summonId = 0;
        int phazonPickId = 0;
        try {
            config.load();
            phazonBlockId = config.getBlock("Phazon", 1011).getInt();
            phazonDropId = config.getItem("PhazonDrop", 10110).getInt();
            phazonCureId = config.getItem("PhazonCure", 10111).getInt();
            phazonCanonId = config.getItem("PhazonCanon", 10112).getInt();
            summonId = config.getItem("Summon", 10113).getInt();
            phazonPickId = config.getItem("PhazonPick", 10114).getInt();
        } catch (Exception e) {
            
        } finally {
            config.save();
        }
        phazonBlock = new BlockPhazon(phazonBlockId).setHardness(1f).setTickRandomly(true).setUnlocalizedName(Vars.unlocalizedPhazonBlock).setCreativeTab(CreativeTabs.tabBlock);
        
        phazonDrop = new ItemPhazonDrop(phazonDropId).setUnlocalizedName(Vars.unlocalizedPhazonDrop);
        phazonCure = new ItemBucketCure(phazonCureId).setUnlocalizedName(Vars.unlocalizedPhazonCure);
        phazonCanon = (ItemPhazonCanon) new ItemPhazonCanon(phazonCanonId).setUnlocalizedName(Vars.unlocalizedPhazonCanon);
        summon = new ItemSummon(summonId).setUnlocalizedName(Vars.unlocalizedSummon);
        phazonPick = new ItemPhazonPick(phazonPickId).setUnlocalizedName(Vars.unlocalizedPhazonPick);
        
        GameRegistry.registerBlock(phazonBlock, ItemPhazon.class, Vars.unlocalizedPhazonBlock);

        TickRegistry.registerTickHandler(new TickHandle(), Side.SERVER);
        TickRegistry.registerTickHandler(new TickHandle(), Side.CLIENT);
        MinecraftForge.EVENT_BUS.register(new EventHandle());
        
        LanguageRegistry.instance().loadLocalization(Vars.en_US, "en_US", false);

        GameRegistry.addRecipe(new ItemStack(phazonCure), "ddd","dbd","ddd",'d',phazonDrop,'b',Item.bucketEmpty);
        GameRegistry.addRecipe(new ItemStack(phazonCanon), "ada","dbd","dcd",'d',phazonDrop,'b',Item.bow,'c',phazonCure);
        GameRegistry.addRecipe(new ItemStack(phazonPick),"ppp","dsd","asa",'d',Item.ingotGold,'p',phazonDrop,'s',Item.stick);
        
        GameRegistry.addRecipe(new ItemStack(summon), "ada","drd",'d',Item.diamond,'r',Item.redstone);
        
        int ifcid = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(EntityInfCow.class, "phcow", ifcid);
        EntityRegistry.registerModEntity(EntityInfCow.class, "phcow", ifcid, this, 50, 1, false);
        int ifpid = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(EntityInfPig.class, "phpig", ifpid);
        EntityRegistry.registerModEntity(EntityInfPig.class, "phpig", ifpid, this, 50, 1, false);
        int ifchid = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(EntityInfChicken.class, "phchick", ifchid);
        EntityRegistry.registerModEntity(EntityInfChicken.class, "phchick", ifchid, this, 50, 1, false);
        int ifcrid = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(EntityInfCreeper.class, "phcre", ifcrid);
        EntityRegistry.registerModEntity(EntityInfCreeper.class, "phcre", ifcrid, this, 50, 1, false);
        int ifzoid = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(EntityInfZombie.class, "phzom", ifzoid);
        EntityRegistry.registerModEntity(EntityInfZombie.class, "phzom", ifzoid, this, 50, 1, false);
        int ifskid = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(EntityInfSkeleton.class, "phske", ifskid);
        EntityRegistry.registerModEntity(EntityInfSkeleton.class, "phske", ifskid, this, 50, 1, false);
        int plasmid = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(EntityPlasma.class, "plas", plasmid);
        EntityRegistry.registerModEntity(EntityPlasma.class, "plas", plasmid, this, 64, 1, false);
    }
    
    @EventHandler
    public void init(FMLInitializationEvent evz) {
        //Testing Commiting
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent evz) {
        proxy.registerRendering();
    }
}
