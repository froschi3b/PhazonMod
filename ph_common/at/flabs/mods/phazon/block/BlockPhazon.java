package at.flabs.mods.phazon.block;

import java.util.Random;

import at.flabs.mods.phazon.Vars;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.world.World;

public class BlockPhazon extends Block {
    
    public BlockPhazon(int par1) {
        super(par1, Material.rock);
    }
    
    public void registerIcons(IconRegister icr) {
        this.blockIcon = icr.registerIcon(Vars.texdir+":phazon");
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
}
