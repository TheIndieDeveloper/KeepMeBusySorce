package com.main.kmb.game;

import java.awt.Graphics2D;
import java.util.Random;

import com.main.kmb.Assets.assets;
import com.main.kmb.Entity.Player;
import com.main.kmb.gamestates.PlayingState;

public class b_health extends BonceObject{
	
	public b_health(int x, int y) {
		super(x, y);
		xpos = x;
		ypos = y;
		setImg(assets.getHealthPickup());
		isAlive = true;
		bonce = true;
	}

	@Override
	public void render(Graphics2D g) {
		super.render(g);
		
//		if(isAlive){
//			g.fillRect((int) xpos - PlayingState.xOffset, (int) ypos - PlayingState.yOffset, 32, 32);
//		}
	}
	
	@Override
	public void tick(double deltaTime) {
		super.tick(deltaTime);
	}
	

}
