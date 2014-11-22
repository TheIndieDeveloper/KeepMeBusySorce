package com.main.kmb.gamestate;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public abstract class GameState {
	
	public GameStateManager gsm;
	
	public GameState(GameStateManager gsm) {
		this.gsm = gsm;	
	}
	
	public abstract void tick(double deltaTime);
	public abstract void render(Graphics2D g);

}
