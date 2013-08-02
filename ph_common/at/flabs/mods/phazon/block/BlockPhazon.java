package at.flabs.mods.phazon.block;

import java.util.Random;

import at.flabs.mods.phazon.Vars;
import at.flabs.mods.phazon.entity.EntityInfPig;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockPhazon extends Block {
    
    public BlockPhazon(int par1) {
        super(par1, Material.rock);
    }
    
    public void registerIcons(IconRegister icr) {
        this.blockIcon = icr.registerIcon(Vars.texdir + ":phazon");
    }
    
    public void updateTick(World world, int x, int y, int z, Random random) {
        if (!world.isRemote) {
            for (int l = 0; l < 2; ++l) {
                int i = x + random.nextInt(5) - 3;
                int j = y + random.nextInt(5) - 3;
                int k = z + random.nextInt(5) - 3;
                
                if (world.getBlockId(i, j, k) == Block.stone.blockID) {
                    world.setBlock(i, j, k, this.blockID, world.getBlockMetadata(x, y, z), 3);
                }
            }
        }
    }
    
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World w, int x, int y, int z) {
        float f = 0.0625F;
        return AxisAlignedBB.getAABBPool().getAABB((double) ((float) x + f), (double) y, (double) ((float) z + f), (double) ((float) (x + 1) - f), (double) ((float) (y + 1) - f), (double) ((float) (z + 1) - f));
    }
    
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        if (!world.isRemote) {
            byte b = (byte) (entity.getEntityData().getByte(Vars.NBTNamePhazonLV) + 1);
            if (b > 100) {
                b = 100;
                setEntityInfected(world, x, y, z, entity);
            }
            System.out.println(b);
            entity.getEntityData().setByte(Vars.NBTNamePhazonLV, b);
        }
    }
    
    public void setEntityInfected(World world, int x, int y, int z, Entity entity) {
        if (!entity.getEntityData().hasKey(Vars.NBTNamePhazonMob)) {
            if (entity instanceof EntityPig) {
                entity.setDead();
                
                EntityInfPig eip = new EntityInfPig((EntityPig) entity);
                world.spawnEntityInWorld(eip);
            }
        }
    }
    
}
