package com.main.kmb.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;

import com.main.kmb.Entity.Player;
import com.main.kmb.gamestates.Block;
import com.main.kmb.gamestates.PlayingState;

public class BonceObject{

	protected double xpos;
	public double ypos;
	
	public int width = 32;
	public int height = 32;

	public double maxLifeTime = 150;
	public double lifeTime = maxLifeTime;
	
	public boolean isAlive;
	
	private Rectangle objectRect;
	public BufferedImage img;
	
	public double Maxjump = 4;
	public double CurrentJumpSpeed = .1;
	public boolean bonce;
	public boolean falling;
	
	private int CurrentfallSpeed = 2;
	private int MaxfallSpeed = 3;
	
	public BonceObject(int x, int y) {
		xpos = x;
		ypos = y;
	}
	
	public void tick(double deltaTime){
		if(isAlive){
			
			
			if(bonce){
				ypos -= CurrentJumpSpeed;
				
				CurrentJumpSpeed -= .1;
				
				if(CurrentJumpSpeed <= 0) {
					Maxjump -= 1;
					CurrentJumpSpeed = Maxjump;
					bonce = false;
					falling = true;
				}
			}
			
			if(Maxjump != 0){
	            xpos += 0 + Math.random();
	            ypos += 0 + Math.random();
	      	  
	            if(Math.random()>.5){
	                xpos += 1*Math.random();
	            }else{
	                xpos -= 1*Math.random();
	            }
	            
	            if(Math.random()>.5){
	                ypos += 1*Math.random();
	            }else{
	                ypos -= 1*Math.random();
	            }
	            
	            xpos -= Math.sin(Maxjump*Math.random()) * Maxjump;
	            ypos += Math.cos(Maxjump*Math.random()) * Maxjump;
			}
			
			
			if(falling){
				if(CurrentfallSpeed < MaxfallSpeed) {
					CurrentfallSpeed += .1;
				}
				if(Maxjump != 0){
					ypos += CurrentfallSpeed;
					bonce = true;
					falling = false;
				}
			}
			
			
			objectRect = new Rectangle((int)getXpos(),(int)getYpos(), width,height);
			
			if(lifeTime != 0){
				lifeTime-=1;
			}
			if(lifeTime == 0){
				isAlive = false;
			}
		}else{
			
			if(PlayingState.bonce_o.contains(this)){
				PlayingState.bonce_o.remove(this);
			}
		}
		
	}
	
	public void render(Graphics2D g){
//		if(this instanceof b_health){
//			g.drawImage(object_image, (int) xpos - 25 - PlayingState.xOffset, (int) ypos - 48 - PlayingState.yOffset, width, height,null);
//		}
//		if(isAlive){
//			g.setColor(Color.RED);
//			g.fillRect((int) getXpos() , (int) getYpos(), 32, 32);
//		}
		
		if(isAlive && img != null){
			g.drawImage(img, (int) xpos - PlayingState.xOffset, (int) ypos - PlayingState.yOffset, width, height,null);
		}
		
	}
	
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}

	protected void teleport(double x, double y) {
		setXpos(x);
		setYpos(y);
	}
	
	public Rectangle getObjectRect() {
		return objectRect;
	}
	
	public void setMaxLifeTime(int maxLifeTime) {
		this.maxLifeTime = maxLifeTime;
	}

	public double getXpos() {
		return xpos;
	}

	public void setXpos(double xpos) {
		this.xpos = xpos;
	}

	public double getYpos() {
		return ypos;
	}

	public void setYpos(double ypos) {
		this.ypos = ypos;
	}
	
	public void setImg(BufferedImage img) {
		this.img = img;
	}
	
}
