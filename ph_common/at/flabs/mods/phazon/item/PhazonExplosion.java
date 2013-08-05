package at.flabs.mods.phazon.item;

import java.util.Iterator;
import java.util.Random;

import at.flabs.mods.phazon.PhazonMod;
import net.minecraft.entity.Entity;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class PhazonExplosion extends Explosion {
    private World worldObj;
    private Random explosionRNG = new Random(System.nanoTime());

    public PhazonExplosion(World world, Entity e, double x, double y, double z, float strength) {
        super(world, e, x, y, z, strength);
        worldObj=world;
        this.isFlaming=true;
    }

    public void doExplosionB(boolean par1)
    {
        super.doExplosionB(par1);

        if (this.isFlaming)
        {
            @SuppressWarnings("rawtypes")
            Iterator iterator = this.affectedBlockPositions.iterator();

            while (iterator.hasNext())
            {
                ChunkPosition chunkposition = (ChunkPosition)iterator.next();
                int i = chunkposition.x;
                int j = chunkposition.y;
                int k = chunkposition.z;
                //int i1 = this.worldObj.getBlockId(i, j, k);

                if(this.explosionRNG.nextInt(3) == 0)
                {
                    this.worldObj.setBlock(i, j, k, PhazonMod.instance.phazonBlock.blockID);
                }
            }
        }
    }
}
