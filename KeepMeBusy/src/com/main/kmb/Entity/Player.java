package com.main.kmb.Entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import com.main.kmb.Assets.Mouse_KeyManager;
import com.main.kmb.Assets.assets;
import com.main.kmb.engine.GameLoop;
import com.main.kmb.game.b_health;
import com.main.kmb.gamestates.Block;
import com.main.kmb.gamestates.Block.BlockType;
import com.main.kmb.gamestates.PlayingState;
import com.main.kmb.gfx.Animator;
import com.main.kmb.gfx.Light;
import com.main.kmb.gfx.Particle;
import com.main.kmb.gfx.SpriteSheet;
import com.main.kmb.gfx.load;
import com.main.kmb.main.frame;

public class Player extends Entity{

	public Player() {
		super();
		setPos(frame.WIDTH / 2 - getWidth() / 2, frame.HEIGHT / 2 - getHeight() / 2);
	}
	
	@Override
	public void init() {
		super.init();
		
		right.add(SpriteSheet.getTile(0, 64, 16, 16));
		right.add(SpriteSheet.getTile(16, 64, 16, 16));
		right.add(SpriteSheet.getTile(32, 64, 16, 16));
		right.add(SpriteSheet.getTile(48, 64, 16, 16));
		
		walkright = new Animator(right);
		walkright.setSpeed(getAnimationSpeed());
		walkright.play();
		
		rightdown.add(SpriteSheet.getTile(32, 64+32, 16, 16));
		rightdown.add(SpriteSheet.getTile(48, 64+32, 16, 16));
		
		walkrightdown = new Animator(rightdown);
		walkrightdown.setSpeed(getAnimationSpeed());
		walkrightdown.play();
		
		left.add(SpriteSheet.getTile(0, 64+16, 16, 16));
		left.add(SpriteSheet.getTile(16, 64+16, 16, 16));
		left.add(SpriteSheet.getTile(32, 64+16, 16, 16));
		left.add(SpriteSheet.getTile(48, 64+16, 16, 16));
		
		walkleft = new Animator(left);
		walkleft.setSpeed(getAnimationSpeed());
		walkleft.play();
		
		leftdown.add(SpriteSheet.getTile(0, 64+32, 16, 16));
		leftdown.add(SpriteSheet.getTile(16, 64+32, 16, 16));
		
		walkleftdown = new Animator(leftdown);
		walkleftdown.setSpeed(getAnimationSpeed());
		walkleftdown.play();
		
		up.add(SpriteSheet.getTile(0, 48, 16, 16));
		up.add(SpriteSheet.getTile(16, 48, 16, 16));
		
		walkup = new Animator(up);
		walkup.setSpeed(getAnimationSpeed());
		walkup.play();
		
		down.add(SpriteSheet.getTile(0, 32, 16, 16));
		down.add(SpriteSheet.getTile(16, 32, 16, 16));
		
		walkdown = new Animator(down);
		walkdown.setSpeed(getAnimationSpeed());
		walkdown.play();
		
		idle.add(SpriteSheet.getTile(32, 48, 16, 16));
		idle.add(SpriteSheet.getTile(48, 48, 16, 16));
		
		walkidle = new Animator(idle);
		walkidle.setSpeed(getAnimationSpeed());
		walkidle.play();
	}

	public Animator walkidle;
	ArrayList<BufferedImage> idle = new ArrayList<BufferedImage>();
	
	public Animator walkright;
	ArrayList<BufferedImage> right = new ArrayList<BufferedImage>();
	
	public Animator walkrightdown;
	ArrayList<BufferedImage> rightdown = new ArrayList<BufferedImage>();
	
	public Animator walkleft;
	ArrayList<BufferedImage> left = new ArrayList<BufferedImage>();
	
	public Animator walkleftdown;
	ArrayList<BufferedImage> leftdown = new ArrayList<BufferedImage>();
	
	public Animator walkup;
	ArrayList<BufferedImage> up = new ArrayList<BufferedImage>();
	
	public Animator walkdown;
	ArrayList<BufferedImage> down = new ArrayList<BufferedImage>();
	
	@Override
	public void tick(double deltaTime) {
		super.tick(deltaTime); 
	}
	
	//setBounds(frame.WIDTH / 2 - width / 2, frame.HEIGHT / 2 - height / 2,width,height);
	
	public static int AnimationState = 0;
	boolean firstSpawn = true;
	
	@Override
	public void render(Graphics2D g) {
		super.render(g);

		//g.drawRect(frame.WIDTH / 2 - width / 2 - PlayingState.xOffset, frame.HEIGHT / 2 - height / 2 - PlayingState.yOffset,width,height);
//		g.drawRect((int)xpos,(int)ypos,width,height);
//		g.drawRect(200,200,width,height);
//		g.drawRect(PlayerRect.x,PlayerRect.y,32,32);
		
		if(isAnimate()){
			
			if(firstSpawn){
				g.drawImage(walkdown.sprite, (int) getXpos() - 16 ,(int) getYpos() - 26 , 64, 64, null);
				walkdown.update(System.currentTimeMillis());
				firstSpawn = false;
			}
			
			walkright.setSpeed(getAnimationSpeed());
			walkdown.setSpeed(getAnimationSpeed());
			walkup.setSpeed(getAnimationSpeed());
			walkleft.setSpeed(getAnimationSpeed());
		
			if(isDown()){
				AnimationState = 0;
			}
			if(isUp()){
				AnimationState = 1;
			}
			if(isRight()){
				AnimationState = 2;
			}
			if(isLeft()){
				AnimationState = 3;
			}
			
			if(AnimationState == 0){
				g.drawImage(walkdown.sprite, (int) getXpos() - 16 ,(int) getYpos() - 26 , 64, 64, null);
				if(isDown()){
					walkdown.update(System.currentTimeMillis());
				}
			}else if(AnimationState == 1){
				g.drawImage(walkup.sprite, (int) getXpos() - 16  ,(int) getYpos() - 26 , 64, 64, null);
				if(isUp()){
					walkup.update(System.currentTimeMillis());
				}
			}else if(AnimationState == 2){
				g.drawImage(walkright.sprite, (int) getXpos() - 16  ,(int) getYpos() - 26 , 64, 64, null);
				if(isRight()){
					walkright.update(System.currentTimeMillis());
				}
			}else if(AnimationState == 3){
				g.drawImage(walkleft.sprite, (int) getXpos() - 16  ,(int) getYpos() - 26 , 64, 64, null);
				if(isLeft()){
					walkleft.update(System.currentTimeMillis());
				}
			}
			if(isRunning()){
				if(getStats().getCurrStamina() > 2){
					if(isRight() || isLeft() || isUp() || isDown()){
						GameLoop.parts.add(new Particle((int)getXpos() + 8, (int)getYpos() + 32 , 0, 0, 10, .3f, Color.GRAY, 0, true));
						GameLoop.parts.add(new Particle((int)getXpos() + 32 - 8, (int)getYpos() + 32 , 0, 0, 10, .3f, Color.GRAY, 0, true));
					}
				}
			}
			fixDamaged(g);
		}
	//	FixMouseStuff(g);
		//g.drawString("health: "+format.format(getStats().getCurrHealth())+"/"+format.format(getStats().getMaxHealth()), 200, 500);
		//g.drawString("health: "+(int)getStats().getCurrHealth()+"/"+(int)getStats().getMaxHealth(), 200, 500);
	}
	
	//TODO
	/*
	 * fix mouse click change
	 * fix the village!
	 */
	
	int defaultdamageTime = 10;
	int damageTime = defaultdamageTime;
	
	public void fixDamaged(Graphics2D g){
		if(isDamaged()){
			
			if(damageTime == defaultdamageTime){
				g.drawImage(assets.p_damage, (int) getXpos() - 16  ,(int) getYpos() - 26, 64,64,null);
			}
			if(damageTime == defaultdamageTime / 2){
				g.drawImage(assets.p_damage, (int) getXpos() - 16  ,(int) getYpos() - 26, 64,64,null);
			}
			if(damageTime == defaultdamageTime - defaultdamageTime / 4){
				g.drawImage(assets.p_damage, (int) getXpos() - 16  ,(int) getYpos() - 26, 64,64,null);
			}
			
			if(damageTime != 0){
				damageTime-=1;
			}else{
				damageTime = defaultdamageTime;
				assets.playSound("hit.wav");
				setDamaged(false);
			}
		}	
	}

	public void FixMouseStuff(Graphics2D g) {

		g.drawRect((int)GameLoop.game.getMouse_moveX() - 13, (int)GameLoop.game.getMouse_moveY() - 32, 24, 24);
		
		if(!GameLoop.game.isPressedButton1()){
			g.drawImage(assets.getMouseTwo(), (int) GameLoop.game.getMouse_moveX() - 18 ,(int) GameLoop.game.getMouse_moveY() - 38, 32,32,null);
		}else{
			g.drawImage(assets.getMouseThree(),(int) GameLoop.game.getMouse_moveX() - 18,(int) GameLoop.game.getMouse_moveY() - 38, 32,32,null);
		}

	}
	
    //GameLoop.parts.add(new Particle(Player.mx, Player.my, 0, 0, 20, .5, Color.WHITE));
    //GameLoop.parts.add(new Particle(Player.mx, Player.my , 0, 0, 30, .3f, assets.getGrass(), 0));
	
}
