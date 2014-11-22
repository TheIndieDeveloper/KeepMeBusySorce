package com.main.kmb.gamestate;

import java.awt.Font;
import java.awt.Graphics2D;
import java.util.Stack;

import com.main.kmb.gamestates.PlayingState;

public class GameStateManager{
	
	public static Stack<GameState> states;

	public GameStateManager() {
		states = new Stack<GameState>();
		states.push(new PlayingState(this));
	}

	public void tick(double deltaTime) {
		states.peek().tick(deltaTime);
	}
	
	public void render(Graphics2D g) {
		states.peek().render(g);
	}
	
}
