package com.main.kmb.nature;

import java.awt.Graphics2D;
import java.util.Random;

import com.main.kmb.Assets.assets;
import com.main.kmb.Entity.Player;

public class Tree extends NatureObject{
	
	TreeType type;
	
	public Tree(int x, int y,TreeType type) {
		super(x, y);
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

	@Override
	public void render(Graphics2D g) {
		super.render(g);
	}
	
	@Override
	public void init() {
		switch(type){
			case OAK:
				nature_object_image = assets.getTree_oak();
			break;
		}
		
		super.init();
	}
	
	@Override
	public void tick(double deltaTime,Player p) {
		super.tick(deltaTime,p);
	}
	
	public enum TreeType{
		OAK
	}

}
