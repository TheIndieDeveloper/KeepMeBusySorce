package com.main.kmb.gfx;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.main.kmb.gamestates.PlayingState;
import com.main.kmb.main.frame;

public class Light{

	private BufferedImage image;
	private AlphaComposite ac;
	
	public double xpos;
	public double ypos;
	private double cxpos;
	private double cypos;
	private int radius;
	private int transp;
	
	public Light(int x, int y, int size, int transparency) {
		xpos = x;
		ypos = y;
		cxpos = xpos - PlayingState.xOffset;
		cypos = ypos - PlayingState.yOffset;
		radius = size;
		transp = transparency;
		
		image = new BufferedImage(radius * 2, radius * 2, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = (Graphics2D) image.getGraphics();
		
		int step = 8;
		int numSteps = radius / step;
		g2.setColor(new Color(0, 0, 0, transp));
		for(int i = 0; i < numSteps; i++) {
			g2.fillOval(radius - i * step, radius - i * step, i * step * 2, i * step * 2);
		}
		
	}
	
	public void tick() {
		cxpos = xpos - PlayingState.xOffset;
		cypos = ypos - PlayingState.yOffset;
	}
	
	public void render(Graphics2D g) {
//		if(image != null){
//			g.drawImage(image, x - radius - PlayingState.xOffset, y - radius - PlayingState.yOffset, null);
//		}	
		
//		g.fillOval((int)cxpos, (int)cypos , 32, 32);
		//g.fillOval((int)xpos , (int)ypos , 32, 32);
//		g.fillOval((int)cxpos - PlayingState.xOffset, (int)cypos - PlayingState.yOffset , 32, 32);
//		g.fillOval((int)cxpos + PlayingState.xOffset, (int)cypos + PlayingState.xOffset, 32, 32);
		//g.fillOval((int)cxpos, (int)cypos , 32, 32);
		
//		if(cxpos >= 0){
//			if(cypos >= 0){
//				if(cypos <= 720){
//					if(cxpos <= 1280){
						g.drawImage(image, (int)cxpos - radius, (int)cypos - radius, null);
//					}
//				}
//			}
//		}
	}

}
