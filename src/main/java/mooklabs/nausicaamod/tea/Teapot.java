package mooklabs.nausicaamod.tea;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;

/**
 * teapots are important for tea right?
 * 
 * here's number 2
 * @author emilynewman
 *
 */

public class Teapot extends ItemBlock implements LiquidTea {

	public Teapot() {
		super();
		setBlockName("teapot");
		setTextureName(Main.itemfold + ":teapot");
		setMaxStackSize(1);
	}
	
	
	
	

}
