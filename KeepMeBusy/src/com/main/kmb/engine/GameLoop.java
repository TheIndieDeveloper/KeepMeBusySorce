package com.main.kmb.engine;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.awt.image.Raster;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JPanel;

import com.main.kmb.Assets.Mouse_KeyManager;
import com.main.kmb.Assets.assets;
import com.main.kmb.Entity.Korax;
import com.main.kmb.Entity.Player;
import com.main.kmb.gamestate.GameStateManager;
import com.main.kmb.gamestates.Block;
import com.main.kmb.gamestates.PlayingState;
import com.main.kmb.gfx.Light;
import com.main.kmb.gfx.Particle;
import com.main.kmb.gfx.SpriteSheet;
import com.main.kmb.gfx.Weather;
import com.main.kmb.gfx.load;
import com.main.kmb.main.frame;

public class GameLoop extends JPanel implements Runnable {

	//TODO WELCOME! TO THIS SORCE
	
	private Thread thread;
	public static boolean running;
	
	private int frames = 0;
	private String fps = "";

	public static Graphics2D g;
	BufferedImage img;
	
	GameStateManager gsm = new GameStateManager();
	public static Mouse_KeyManager game = new Mouse_KeyManager();
	
	public static CopyOnWriteArrayList<Particle> parts = new CopyOnWriteArrayList<Particle>();
	public static CopyOnWriteArrayList<Weather> weather = new CopyOnWriteArrayList<Weather>();
	
	public GameLoop() {
		setPreferredSize(new Dimension(frame.WIDTH,frame.HEIGHT));
		setFocusable(false);
		requestFocus();

	}
	
	@Override/*This Starts Anything when class is Runner?!*/
	public void addNotify() {
		super.addNotify();
		if(thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}
	
	@Override
	public void run() {
		init();

		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D / 60D;

		long lastTimer = System.currentTimeMillis();
		//double deltaTime = 0;
		
		while(running){
			long now = System.nanoTime(); //LAST TIME LOOP
			long updateLength = now - lastTime;
		    lastTime = now;
		    double deltaTime = updateLength / ((double)nsPerTick);

		    
		    if(!game.isPaused()){
			    tick(deltaTime);
				render();
		    }
		    renderPauseScreen(deltaTime);
			clear();
			deltaTime -= 1;
			//System.out.println("DELTA TIME: "+deltaTime + " "+fps);
			
			frames++;
			
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;
				fps = "FPS: " +frames;
				frames = 0;
			}
		}
	}

	int angle = 0;
	
	private void renderPauseScreen(double deltaTime) {
		if(game.isPaused()){
			
			g.drawImage(assets.getOut_off_focus_GUI(), 1280 / 2 - 32*20 / 2, 720 / 2 - 32* 10 / 2, 32*20,32*10,null);

//			if(Korax.walkidle.sprite != null){
//				g.drawImage(Korax.walkidle.sprite, 355,(int) 300, 64*2, 64*2, null);
//				g.drawImage(Korax.walkidle.sprite, 800,(int) 300, 64*2, 64*2, null);
//				Korax.walkidle.update(System.currentTimeMillis());
//			}
			

			
			g.setFont(new Font("Back to 1982",32,48));
			g.setColor(Color.WHITE);
			g.drawString("PAUSED!", 510, 320);
			
			g.setFont(new Font("Terminator Two",32,15));
			
			if(angle != 1){
				angle+=1;
			}
			if(angle >= 1){
				angle = 0;
			}
			
			parts.add(new Particle(625, 465, (int) -1, 2, 5, (float) .8, Color.red, angle));
			parts.add(new Particle(655, 465, (int)  2, 2, 5, (float) .8, Color.red, angle));

			
			for(Particle part : parts){
				part.update(deltaTime);
			}
			
			for(Particle part : parts){
				part.render(g);
			}

		}
	}
	public load bi;
	
	
	private void init() {
		running = true;
		img = new BufferedImage(frame.WIDTH, frame.HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) img.getGraphics();
        
		bi = new load();
		
		BufferedImage spritesheet = null;
		try 
		{
			System.out.println("LOADING SPRITESHEET!");
			spritesheet = bi.LoadImage("SpriteSheet.png");
			System.out.println("LOADED SPRITESHEET!");
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}		
		ArrayList<BufferedImage> sprites = new ArrayList<BufferedImage>();
		SpriteSheet ss = new SpriteSheet(spritesheet);	
		
	}
	
	//LIGHTS

	/*
	 * 
	 * fix so lightning can be scaled up and down!
	 * 
	 * fix lightning so you can add when ever you want!
	 */


	private void tick(double deltaTime) {

		gsm.tick(deltaTime);
		
		for(Particle part : parts){
			part.update(deltaTime);
		}
		
		for(Weather Weath : weather){
			Weath.update(deltaTime);
		}
		
		game.tick(deltaTime);
	}

	public void render() {
		
		g.clearRect(0, 0, frame.WIDTH, frame.HEIGHT);
		g.setFont(new Font("Terminator Two",32,15));
		
		gsm.render(g);	
		
		for(Particle part : parts){
			part.render(g);
		}
		
		for(Weather Weath : weather){
			Weath.render(g);
		}
	}
	
	private void clear() {
		
		Graphics g2 = getGraphics();
		if(img != null){
			g2.drawImage(img, 0, 0, null);
		}
		g2.dispose();
	}
	
	public void setLight(int x, int y,int[] array,int width, int value){
	    array[width*y+x] = value;
	}

	

}
