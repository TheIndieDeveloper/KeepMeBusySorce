package com.main.kmb.gfx;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class SpriteSheet {

	public static BufferedImage SpriteSheet;
	
	public SpriteSheet(BufferedImage img) {
		this.SpriteSheet = img;
	}
	
	public static BufferedImage getTile(int xTile, int yTile , int width, int height){
		BufferedImage sprite = SpriteSheet.getSubimage(xTile, yTile, width, height);
		return sprite;
	}

}
