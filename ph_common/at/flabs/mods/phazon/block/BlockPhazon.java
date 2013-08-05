package at.flabs.mods.phazon.block;

import java.util.Random;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.packet.Packet131MapData;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import at.flabs.mods.phazon.PhazonMod;
import at.flabs.mods.phazon.Util;
import at.flabs.mods.phazon.Vars;

public class BlockPhazon extends Block {
    
    public BlockPhazon(int par1) {
        super(par1, Material.rock);
    }
    
    public void registerIcons(IconRegister icr) {
        this.blockIcon = icr.registerIcon(Vars.texdir + ":phazon");
    }
    
    public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side) {
        return true;
    }
    public boolean isBlockNormalCube(World world, int x, int y, int z)
    {
        return false;
    }
    
    public void updateTick(World world, int x, int y, int z, Random random) {
        if (!world.isRemote) {
            for (int a = 0; a < (2); a++) {
                int i = x + random.nextInt(3) - 1;
                int j = y + random.nextInt(3) - 1;
                int k = z + random.nextInt(3) - 1;
                if (eat(world.getBlockId(i, j, k))) {
                    world.setBlock(i, j, k, this.blockID, world.getBlockMetadata(x, y, z), 3);
                    
                }
            }
        }
    }
    
    public static int[] eatlist = { Block.grass.blockID, Block.dirt.blockID, Block.sand.blockID, Block.wood.blockID, Block.leaves.blockID };
    
    public static boolean eat(int id) {
        for (int i : eatlist) {
            if (id == i) {
                return true;
            }
        }
        return false;
    }
    
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World w, int x, int y, int z) {
        float f = 0.0625F;
        return AxisAlignedBB.getAABBPool().getAABB((double) ((float) x + f), (double) y, (double) ((float) z + f), (double) ((float) (x + 1) - f), (double) ((float) (y + 1) - f), (double) ((float) (z + 1) - f));
    }
    
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        if (!world.isRemote && entity instanceof EntityLivingBase) {
            short old = entity.getEntityData().getShort(Vars.NBTNamePhazonLV);
            short b = (short) (old + 2);
            if (b > 500) {
                b = 500;
                if (!Util.setEntityInfected(world, x, y, z, entity)) {
                    ((EntityLivingBase) entity).attackEntityFrom(Util.phazon, 10f);
                }
            }
            if (entity instanceof EntityPlayerMP) {
                if ((b >= 400 && old < 400) || (b >= 450 && old < 450)) {
                    ChatMessageComponent cmc = new ChatMessageComponent();
                    cmc.func_111072_b("[Warning] Phazon Levels High");
                    ((EntityPlayerMP) entity).sendChatToPlayer(cmc);
                }
                if (b != old) {
                    Packet131MapData pckt = PacketDispatcher.getTinyPacket(PhazonMod.instance, (short) 0, Util.toBytes(b));
                    PacketDispatcher.sendPacketToPlayer(pckt, (Player) entity);
                }
            }
            entity.getEntityData().setShort(Vars.NBTNamePhazonLV, b);
        }
    }
    
}
