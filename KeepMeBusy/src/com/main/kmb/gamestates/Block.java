package com.main.kmb.gamestates;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ImageObserver;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

import com.main.kmb.Assets.assets;
import com.main.kmb.Entity.Player;
import com.main.kmb.gamestates.Block.BlockType;
import com.main.kmb.main.frame;

public class Block extends Rectangle{
	
	public static final int blockSize = 32;
	
	BlockType type;
	BufferedImage img;
	boolean solid;
	boolean isChoosed;
	
	public Block(int x, int y, BlockType type, boolean solid) {
		this.type = type;
		this.solid = solid;
		setBounds(x , y , blockSize, blockSize);
	}
	
	public int getTile(int[][] map,int x, int y)
	{
		if(x < 0 || y < 0 || x >= width || y>= height)
			return 1;
		return map[x][y];
	}
	
	public boolean isChoosed() {
		return isChoosed;
	}
	
	public void setChoosed(boolean isChoosed) {
		this.isChoosed = isChoosed;
	}
	
	public void render(Graphics2D g){
		
		g.drawImage(img, x - (int) PlayingState.xOffset, y - (int)PlayingState.yOffset, blockSize,blockSize,null);
		
//		if(solid){
//			g.setColor(Color.RED);
//		}else{
//			g.setColor(Color.GREEN);
//		}
		
		//g.drawRect(x - (int) PlayingState.xOffset, y - (int)PlayingState.yOffset, blockSize,blockSize);
		
		g.setColor(Color.WHITE);
		switch(type){
			case WALL:
				img = assets.getWallTexture();
			break;
			case STONE_GROUND:
				img = assets.getStone_GroundTexture();
			break;
			case GRASS:
				img = assets.getGrass();
			break;
			case MOSSYSTONE_GROUND:
				img = assets.getMossyStone_GroundTexture();
			break;
			case WOOD_FLOOR:
				img = assets.getWoodFloor_Texture();
			break;
			case GROUND_ROAD_STONE:
				img = assets.getGroundRoadStone_Texture();
			break;
			case BRICKS:
				img = assets.getBricks_Texture();
			break;
		}
	}
	
	public void tick(double deltaTime){
		setBounds(x, y , blockSize, blockSize);
	}
	
	public enum BlockType{
		WALL, 
		STONE_GROUND, 
		GRASS, 
		MOSSYSTONE_GROUND, 
		WOOD_FLOOR,
		GROUND_ROAD_STONE,
		BRICKS
	}

	public BlockType getBlockType() {
		return type;
	}

	public boolean isSolid() {
		return solid;
	}
	
	public void setBlockType(BlockType blocktype) {
		this.type = blocktype;
	}

}
