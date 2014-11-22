package com.main.kmb.Entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import com.main.kmb.Assets.assets;
import com.main.kmb.engine.GameLoop;
import com.main.kmb.gamestates.PlayingState;
import com.main.kmb.gfx.Animator;
import com.main.kmb.gfx.Particle;
import com.main.kmb.gfx.SpriteSheet;

public class Korax extends AI {

	public Korax(int x, int y) {
		xpos = x;
		ypos = y;
		isAlive = true;
		int max = 3300;
		int maxy = 1500;
		Random ran = new Random();
		int xx = ran.nextInt(max);
		int yy = ran.nextInt(maxy);
		teleport(xx, yy);
	}
	
	public void init() {

		righta.add(SpriteSheet.getTile(0, 64+64+16, 16, 16));
		righta.add(SpriteSheet.getTile(16, 64+64+16, 16, 16));
		righta.add(SpriteSheet.getTile(32, 64+64+16, 16, 16));
		righta.add(SpriteSheet.getTile(48, 64+64+16, 16, 16));
		
		walkright = new Animator(righta);
		walkright.setSpeed(AnimationSpeed);
		walkright.play();
		
		rightdown.add(SpriteSheet.getTile(32, 64+32, 16, 16));
		rightdown.add(SpriteSheet.getTile(48, 64+32, 16, 16));
		
		walkrightdown = new Animator(rightdown);
		walkrightdown.setSpeed(AnimationSpeed);
		walkrightdown.play();
		
		lefta.add(SpriteSheet.getTile(0, 64+64+32, 16, 16));
		lefta.add(SpriteSheet.getTile(16, 64+64+32, 16, 16));
		lefta.add(SpriteSheet.getTile(32, 64+64+32, 16, 16));
		lefta.add(SpriteSheet.getTile(48, 64+64+32, 16, 16));
		
		walkleft = new Animator(lefta);
		walkleft.setSpeed(AnimationSpeed);
		walkleft.play();
		
		leftdown.add(SpriteSheet.getTile(0, 64+32, 16, 16));
		leftdown.add(SpriteSheet.getTile(16, 64+32, 16, 16));
		
		walkleftdown = new Animator(leftdown);
		walkleftdown.setSpeed(AnimationSpeed);
		walkleftdown.play();
		
		upa.add(SpriteSheet.getTile(0, 64+64, 16, 16));
		upa.add(SpriteSheet.getTile(16,64+64, 16, 16));
		
		walkup = new Animator(upa);
		walkup.setSpeed(AnimationSpeed);
		walkup.play();
		
		downa.add(SpriteSheet.getTile(0, 64+48, 16, 16));
		downa.add(SpriteSheet.getTile(16, 64+48, 16, 16));
		
		walkdown = new Animator(downa);
		walkdown.setSpeed(AnimationSpeed);
		walkdown.play();
		
		idle.add(SpriteSheet.getTile(32, 48+48+32, 16, 16));
		idle.add(SpriteSheet.getTile(48, 48+48+32, 16, 16));
		
		walkidle = new Animator(idle);
		walkidle.setSpeed(AnimationSpeed);
		walkidle.play();
	}
	
	boolean walking;
	int wt;
	int walkTime = 200;
	
	@Override
	public void tick(double deltaTime,Player p) {
		super.tick(deltaTime,p);
	}
	
	public static Animator walkidle;
	ArrayList<BufferedImage> idle = new ArrayList<BufferedImage>();
	
	public Animator walkright;
	ArrayList<BufferedImage> righta = new ArrayList<BufferedImage>();
	
	public Animator walkrightdown;
	ArrayList<BufferedImage> rightdown = new ArrayList<BufferedImage>();
	
	public Animator walkleft;
	ArrayList<BufferedImage> lefta = new ArrayList<BufferedImage>();
	
	public Animator walkleftdown;
	ArrayList<BufferedImage> leftdown = new ArrayList<BufferedImage>();
	
	public Animator walkup;
	ArrayList<BufferedImage> upa = new ArrayList<BufferedImage>();
	
	public Animator walkdown;
	ArrayList<BufferedImage> downa = new ArrayList<BufferedImage>();
	
	@Override
	public void render(Graphics2D g) {
		super.render(g);

		//TakeDamage(0.1);
		//g.fillRect((int)cxpos,(int)cypos, width, height);
		
		if(isAlive){
			walkright.setSpeed(AnimationSpeed);
			walkrightdown.setSpeed(AnimationSpeed);
			walkdown.setSpeed(AnimationSpeed);
			walkup.setSpeed(AnimationSpeed);
			walkleftdown.setSpeed(AnimationSpeed);
			walkleft.setSpeed(AnimationSpeed);
		
			/*RIGHT DOWN*/
			if(isDown() && isRight()){
				g.drawImage(walkrightdown.sprite, (int) xpos - 16 - PlayingState.xOffset,(int) ypos - 26 - PlayingState.yOffset, 64, 64, null);
				walkrightdown.update(System.currentTimeMillis());
			}
			
			/*LEFT DOWN*/
			if(isDown() && isLeft()){
				g.drawImage(walkleftdown.sprite, (int) xpos - 16 - PlayingState.xOffset,(int) ypos - 26 - PlayingState.yOffset , 64, 64, null);
				walkleftdown.update(System.currentTimeMillis());
			}
			
			if(isRight() && isLeft()){
				g.drawImage(walkidle.sprite, (int) xpos - 16 - PlayingState.xOffset,(int) ypos - 26 - PlayingState.yOffset, 64, 64, null);
				walkidle.update(System.currentTimeMillis());
			}
			
			
			if(isRight() && isLeft() && isUp()){
				g.drawImage(walkidle.sprite, (int) xpos - 16 - PlayingState.xOffset,(int) ypos - 26 - PlayingState.yOffset, 64, 64, null);
				walkidle.update(System.currentTimeMillis());
				setUp(false);
			}
			
			if(isRight() && isLeft() && isDown()){
				g.drawImage(walkidle.sprite, (int) xpos - 16 - PlayingState.xOffset,(int) ypos - 26 - PlayingState.yOffset, 64, 64, null);
				walkidle.update(System.currentTimeMillis());
				setDown(false);
			}
			
			if(isUp() && isDown()){
				g.drawImage(walkidle.sprite, (int) xpos - 16 - PlayingState.xOffset ,(int) ypos - 26 - PlayingState.yOffset, 64, 64, null);
				walkidle.update(System.currentTimeMillis());
			}
			
			if(isRight() && isUp()){
				g.drawImage(walkright.sprite, (int) xpos - 16 - PlayingState.xOffset,(int) ypos - 26 - PlayingState.yOffset, 64, 64, null);
				walkright.update(System.currentTimeMillis());
			}
			
			if(isLeft() && isUp()){
				g.drawImage(walkleft.sprite, (int) xpos - 16  - PlayingState.xOffset,(int) ypos - 26 - PlayingState.yOffset, 64, 64, null);
				walkleft.update(System.currentTimeMillis());
			}
			
			if(isRight() && !isDown() && !isLeft() && !isUp()){
				g.drawImage(walkright.sprite, (int) xpos - 16  - PlayingState.xOffset,(int) ypos - 26 - PlayingState.yOffset, 64, 64, null);
				walkright.update(System.currentTimeMillis());
			}
			
			if(isLeft() && !isDown() && !isRight() && !isUp()){
				g.drawImage(walkleft.sprite, (int) xpos - 16  - PlayingState.xOffset,(int) ypos - 26 - PlayingState.yOffset, 64, 64, null);
				walkleft.update(System.currentTimeMillis());
			}
			
			if(isUp() && !isRight() && !isLeft()){
				g.drawImage(walkup.sprite, (int) xpos - 16  - PlayingState.xOffset,(int) ypos - 26 - PlayingState.yOffset, 64, 64, null);
				walkup.update(System.currentTimeMillis());
			}
			if(isDown() && !isRight() && !isLeft()){
				g.drawImage(walkdown.sprite, (int) xpos - 16 - PlayingState.xOffset ,(int) ypos - 26 - PlayingState.yOffset, 64, 64, null);
				walkdown.update(System.currentTimeMillis());
			}
			
			/*IDLE*/
			if(!isRight() && !isLeft() && !isUp() && !isDown()){
				g.drawImage(walkidle.sprite, (int) xpos - 16  - PlayingState.xOffset,(int) ypos - 26 - PlayingState.yOffset, 64, 64, null);
				walkidle.update(System.currentTimeMillis());
			}else{
				if(isRunning()){
					GameLoop.parts.add(new Particle((int)getXpos() + 8, (int)getYpos() + 32 , 0, 0, 10, .3f, Color.GRAY, 0, true));
					GameLoop.parts.add(new Particle((int)getXpos() + 32 - 8, (int)getYpos() + 32 , 0, 0, 10, .3f, Color.GRAY, 0, true));
					
				}
			}
			fixDamaged(g);
		}
	}
	
	int defaultdamageTime = 10;
	int damageTime = defaultdamageTime;
	
	public void fixDamaged(Graphics2D g){
		if(damaged){
			
			if(damageTime == defaultdamageTime){
				g.drawImage(assets.korax_damage, (int) xpos - 16 - PlayingState.xOffset  ,(int) ypos - 26 - PlayingState.yOffset , 64,64,null);
			}
			if(damageTime == defaultdamageTime / 2){
				g.drawImage(assets.korax_damage, (int) xpos - 16 - PlayingState.xOffset  ,(int) ypos - 26 - PlayingState.yOffset , 64,64,null);
			}
			if(damageTime == defaultdamageTime - defaultdamageTime / 4){
				g.drawImage(assets.korax_damage, (int) xpos - 16 - PlayingState.xOffset ,(int) ypos - 26 - PlayingState.yOffset , 64,64,null);
			}
			
			if(damageTime != 0){
				damageTime-=1;
			}else{
				damageTime = defaultdamageTime;
				damaged = false;
			}
		}	
	}
}
