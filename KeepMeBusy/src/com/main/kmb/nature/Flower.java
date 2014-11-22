package com.main.kmb.nature;

import java.awt.Graphics2D;
import java.util.Random;

import com.main.kmb.Assets.assets;
import com.main.kmb.Entity.Player;

public class Flower extends NatureObject{
	
	FlowerType type;
	
	public Flower(int x, int y,FlowerType type) {
		super(x, y);
		setWidth(32);
		setHeight(32);
		this.type = type;
		xpos = x;
		ypos = y;
		int max = 5500;
		int maxy = 5800;
		Random ran = new Random();
		int xx = ran.nextInt(max);
		int yy = ran.nextInt(maxy);
		teleport(xx, yy);
	}

	public Flower(int x, int y) {
		super(x, y);
		xpos = x;
		ypos = y;
	}

	@Override
	public void render(Graphics2D g) {
		super.render(g);
	}
	
	@Override
	public void init() {
		switch(type){
			case RED:
				nature_object_image = assets.getRed_flower();
			break;
			case WHITE:
				nature_object_image = assets.getWhite_flower();
			break;
		}
		
		super.init();
	}
	
	@Override
	public void tick(double deltaTime,Player p) {
		super.tick(deltaTime,p);
	}
	
	public enum FlowerType{
		RED,WHITE
	}

}
