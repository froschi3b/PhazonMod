package at.flabs.mods.phazon.block;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet131MapData;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import at.flabs.mods.phazon.PhazonMod;
import at.flabs.mods.phazon.Util;
import at.flabs.mods.phazon.Vars;

public class BlockPhazon extends Block {
    Icon blockRed;
    
    public BlockPhazon(int par1) {
        super(par1, Material.rock);
    }
    
    public void registerIcons(IconRegister icr) {
        this.blockIcon = icr.registerIcon(Vars.texdir + ":phazon");
        this.blockRed = icr.registerIcon(Vars.texdir + ":phazonRed");
    }
    
    public Icon getIcon(int side, int meta) {
        if (meta == 1) {
            return this.blockRed;
        }
        return this.blockIcon;
    }
    
    public boolean isBlockNormalCube(World world, int x, int y, int z) {
        return false;
    }
    
    public void updateTick(World world, int x, int y, int z, Random random) {
        if (!world.isRemote) {
            int meta = world.getBlockMetadata(x, y, z);
            for (int a = 0; a < (meta + 1); a++) {
                int i = x + random.nextInt(3) - 1;
                int j = y + random.nextInt(3) - 1;
                int k = z + random.nextInt(3) - 1;
                
                if (world.getBlockId(i, j, k) == Block.stone.blockID) {
                    world.setBlock(i, j, k, this.blockID, world.getBlockMetadata(x, y, z), 3);
                    
                }
            }
        }
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void getSubBlocks(int id, CreativeTabs c, List l) {
        l.add(new ItemStack(id, 1, 0));
        l.add(new ItemStack(id, 1, 1));
    }
    
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World w, int x, int y, int z) {
        float f = 0.0625F;
        return AxisAlignedBB.getAABBPool().getAABB((double) ((float) x + f), (double) y, (double) ((float) z + f), (double) ((float) (x + 1) - f), (double) ((float) (y + 1) - f), (double) ((float) (z + 1) - f));
    }
    
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        if (entity instanceof EntityLivingBase) {
            short b = (short) (entity.getEntityData().getShort(Vars.NBTNamePhazonLV) + 1);
            if (b > 500) {
                b = 500;
                if (!world.isRemote) Util.setEntityInfected(world, x, y, z, entity);
            }
            if (!world.isRemote && b == 500 && Util.recieveDamage((EntityLivingBase) entity)) {
                ((EntityLivingBase) entity).attackEntityFrom(Util.phazon, 10f);
                
            }
            if (entity instanceof EntityPlayerMP) {
                Packet131MapData pckt = PacketDispatcher.getTinyPacket(PhazonMod.instance, (short) 0, toBytes(b));
                PacketDispatcher.sendPacketToPlayer(pckt, (Player) entity);
            }
            entity.getEntityData().setShort(Vars.NBTNamePhazonLV, b);
        }
    }
    
    private static byte[] toBytes(short s) {
        return new byte[] { (byte) (s & 0x00FF), (byte) ((s & 0xFF00) >> 8) };
    }
}
