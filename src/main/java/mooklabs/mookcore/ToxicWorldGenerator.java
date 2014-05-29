package mooklabs.mookcore;

import java.util.Random;

import mooklabs.nausicaamod.Main;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

/**
 * Creates some worldgen in
 * 
 * @author mooklabs
 */
public class ToxicWorldGenerator implements IWorldGenerator {

	int[][][] house = { { {} } };/*// first layer { { 1, 1, 1 }, { 1, 1, 1 }, { 1,1,1 }, }, // second layer { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 2, 3 }, }, // third layer { { 1, 1,
								 * 1 }, { 1, 1, 1 }, { 1, 2, 3 }, }, }; */

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

		switch (world.provider.dimensionId) {
		case Main.dimensionId:
			generateSurface(world, random, chunkX * 16, chunkZ * 16);

		default:
		}
	}

	private void generateSurface(World world, Random random, int x, int z) {
		int x1 = random.nextInt(16) + x;
		int z1 = random.nextInt(16) + z;// makes it more random
		for (int y = 60; y < 65; y++)
			if (world.getBlock(x, y, z) == Main.poisonGrass && random.nextInt(5) == 0) {
				for (int i = -1; i <= 1; i++)
					for (int j = -1; j <= 1; j++)
						for (int k = -1; k <= 1; k++)
							world.setBlock(x1 + i, y + j, z1 + k, Main.quicksand);
			}
		if (random.nextInt(1) == 0) {// its by chunck, and i want about one per few cchunck
			// System.out.println("making log tower@" + x1 + ", " +z1);
			for (int i = 0; i < 25; i++)
				world.setBlock(x1, i + 33, z1, Main.petrifiedLog);
			for (int i = 0; i < 3; i++)
				for (int j = -1; j < 2; j++)
					for (int k = -1; k < 2; k++)
						world.setBlock(j + x1, i + 33, k + z1, Main.petrifiedLog);
		}
		for(int times=0; times<random.nextInt(3)+1; times++){
		x1 = random.nextInt(16) + x;//get different randoms
		z1 = random.nextInt(16) + z;
		if (random.nextInt(1) == 0)
		for (int i = 0; i < 3; i++)
			for (int j = -1; j < 2; j++)
				for (int k = -1; k < 2; k++)
					world.setBlock(j + x1, i + 33, k + z1, Main.petrifiedLog);
		}
		/*if (random.nextInt(10) == 0) for (int xh = 0; xh < house.length; xh++) 
		 * for (int yh = 0; yh < house[0].length; yh++) 
		 * for (int zh = 0; zh < house[0][0].length; zh++) {
		 * world.setBlock(x1 + xh, 5 + yh, z1 + zh, house[xh][yh][zh]); 
		 * System.out.println(xh + " " + yh + " " + zh); } */
		// this.addOreSpawn(Main.fuelBlock,world,random,x,z,16,16,2+random.nextInt(3),1,15,60);//x,z,16,16,vein size,times per chunck, min y, max
	}

	/**
	 * Adds an Ore Spawn to Minecraft. Simply register all Ores to spawn with this method in your Generation method in your IWorldGeneration extending Class
	 * 
	 * @param The Block to spawn
	 * @param The World to spawn in
	 * @param A Random object for retrieving random positions within the world to spawn the Block
	 * @param An int for passing the X-Coordinate for the Generation method
	 * @param An int for passing the Z-Coordinate for the Generation method
	 * @param An int for setting the maximum X-Coordinate values for spawning on the X-Axis on a Per-Chunk basis
	 * @param An int for setting the maximum Z-Coordinate values for spawning on the Z-Axis on a Per-Chunk basis
	 * @param An int for setting the maximum size of a vein
	 * @param An int for the Number of chances available for the Block to spawn per-chunk
	 * @param An int for the minimum Y-Coordinate height at which this block may spawn
	 * @param An int for the maximum Y-Coordinate height at which this block may spawn
	 **/
	public void addOreSpawn(Block block, World world, Random random, int blockXPos, int blockZPos, int maxX, int maxZ, int maxVeinSize, int chancesToSpawn, int minY, int maxY) {
		int maxPossY = minY + (maxY - 1);
		assert maxY > minY : "The maximum Y must be greater than the Minimum Y";
		assert maxX > 0 && maxX <= 16 : "addOreSpawn: The Maximum X must be greater than 0 and less than 16";
		assert minY > 0 : "addOreSpawn: The Minimum Y must be greater than 0";
		assert maxY < 256 && maxY > 0 : "addOreSpawn: The Maximum Y must be less than 256 but greater than 0";
		assert maxZ > 0 && maxZ <= 16 : "addOreSpawn: The Maximum Z must be greater than 0 and less than 16";

		int diffBtwnMinMaxY = maxY - minY;
		for (int x = 0; x < chancesToSpawn; x++) {
			int posX = blockXPos + random.nextInt(maxX);
			int posY = minY + random.nextInt(diffBtwnMinMaxY);
			int posZ = blockZPos + random.nextInt(maxZ);
			(new WorldGenMinable(block, maxVeinSize)).generate(world, random, posX, posY, posZ);
		}
	}
}