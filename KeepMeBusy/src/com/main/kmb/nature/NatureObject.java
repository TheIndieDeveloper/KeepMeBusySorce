package com.main.kmb.nature;

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
import com.main.kmb.gamestates.Block.BlockType;

public class NatureObject{

	protected double xpos;
	protected double ypos;
	
	private double cxpos;
	private double cypos;
	
	private int width = 82;
	private int height = 82;

	private boolean isAlive;
	
	private Rectangle objectRect;
	private Rectangle objectRect_front;
	
	public BufferedImage nature_object_image;

	public NatureObject(int x, int y) {
		this.xpos = x;
		this.ypos = y;
	}
	
	boolean choosedLocation;
	
	public void tick(double deltaTime, Player player){
		
		//if(this instanceof Flower){
			objectRect = new Rectangle((int)xpos,(int)ypos,width,height);
		//}
//		if(this instanceof Tree){
//			objectRect = new Rectangle(	(int) xpos + width / 2 - 16, (int) ypos + height - 32, 32, 32);
//		}
		objectRect_front = new Rectangle((int) xpos, (int) ypos + height - 21, 82, 38);
		if(!choosedLocation){
			for(Block b : PlayingState.blocks){
				if(getObjectRect().intersects(b)){
					if(!b.isChoosed() && b.getBlockType().equals(BlockType.GRASS)){
						if(this.xpos < b.x){
							xpos = b.x;
						}
						if(this.xpos > b.x){
							xpos = b.x;
						}
						if(this.ypos < b.y){
							ypos = b.y;
						}
						if(this.ypos > b.y){
							ypos = b.y;
						}
					}else{
						if(PlayingState.nature_o.contains(this)){
							PlayingState.nature_o.remove(this);
						}
					}
					
					if(xpos == b.x && ypos == b.y){
						//LOOK IF THERE IS 2 IN THE SAME PLACE
						//THEN REMOVE THE FIRST ONE AND MOVE SECOND ONE THERE INSTEAD
						b.setChoosed(true);
						this.choosedLocation = true;
					}
				}
			}
		}
	}
	
	public void init() {

	}
	
	public void render(Graphics2D g){
		if(this instanceof Tree){
			g.drawImage(nature_object_image, (int) xpos - 25 - PlayingState.xOffset, (int) ypos - 48 - PlayingState.yOffset, width, height,null);
		}else{
			g.drawImage(nature_object_image, (int) xpos - PlayingState.xOffset, (int) ypos - PlayingState.yOffset, width, height,null);
		}
		//g.setColor(Color.RED);
		//g.drawRect((int) xpos - PlayingState.xOffset, (int) ypos - PlayingState.yOffset, 32, 32);
		
		if(this instanceof Tree){
			//g.drawRect((int) xpos + width / 2 - 16 - PlayingState.xOffset, (int) ypos + height - 32 - PlayingState.yOffset, 32, 32);
		}
	}
	
	public void setNature_object_image(BufferedImage nature_object_image) {
		this.nature_object_image = nature_object_image;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public Rectangle getObjectRect_front() {
		return objectRect_front;
	}

	protected void teleport(double x, double y) {
		xpos = x;
		ypos = y;
	}
	
	public Rectangle getObjectRect() {
		return objectRect;
	}
	
}
