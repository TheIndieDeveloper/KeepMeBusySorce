package com.main.kmb.gamestates;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.main.kmb.gamestate.GameState;
import com.main.kmb.gamestate.GameStateManager;

public class DeadState extends GameState {

	public DeadState(GameStateManager gsm) {
		super(gsm);
		System.out.println("DEAD STATE!");
	}

	@Override
	public void tick(double deltaTime) {

	}


	@Override
	public void render(Graphics2D g) {
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 1));
		g.setColor(Color.WHITE);
		g.setFont(new Font("Terminator Two",20, 64));
		g.setColor(Color.RED);
		g.drawString("DEAD", 200, 200);
	}

}
