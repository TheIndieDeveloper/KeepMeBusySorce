package com.main.kmb.gfx;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.main.kmb.Assets.assets;
import com.main.kmb.engine.GameLoop;
import com.main.kmb.gamestates.PlayingState;

public class Particle {

	private int x;
    private int y;
	private int dx;
    private int dy;
    private int size;
    private float life;
    private Color color;
    private BufferedImage img;
    boolean isStill;

    public Particle(int x, int y, int dx, int dy, int size, int life, Color c){
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.size = size;
        this.life = life;
        this.color = c;
    }
    
    public Particle(int x, int y, int dx, int dy, int size, double life, Color c){
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.size = size;
        this.life = (float) life;
        this.color = c;
    }
    
    public Particle(int x, int y, int dx, int dy, int size, float life, Color c, int angle){
    	this.angle = angle;
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.size = size;
        this.life = life;
        this.color = c;
    }

    public Particle(int x, int y, int dx, int dy, int size, float life, Color c, int angle, boolean isStill){
    	this.isStill = isStill;
    	this.angle = angle;
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.size = size;
        this.life = life;
        this.color = c;
    }
    
    public Particle(int x, int y, int size, float life, Color c, boolean isStill){
    	this.isStill = isStill;
        this.x = x;
        this.y = y;
        this.size = size;
        this.life = life;
        this.color = c;
    }
    
    public Particle(int x, int y, int dx, int dy, int size, float life, BufferedImage img, int angle){
    	this.angle = angle;
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.size = size;
        this.life = life;
        this.img = img;
    }
    
	int speed = 3;
    private float angle;
    private float personalAngle = 0;
    private float angleDiff = 0;
    
    public void update(double deltaTime){
    	
    	if(!isStill){
            x += dx + Math.random()/10;
            y += dy + Math.random()/10;
      	  
            if(Math.random()>.5){
                x += 3*Math.random();
            }else{
                x -= 3*Math.random();
            }
            
            if(Math.random()>.5){
                y += 3*Math.random();
            }else{
                y -= 3*Math.random();
            }
    	}else{
            x += dx + Math.random();
            y += dy + Math.random();
      	  
            if(Math.random()>.5){
                x += 3*Math.random();
            }else{
                x -= 3*Math.random();
            }
            
            if(Math.random()>.5){
                y += 3*Math.random();
            }else{
                y -= 3*Math.random();
            }
    	}
    
    	
        if(!isStill){
          
            personalAngle = (float) (angle + Math.random());
          
            personalAngle = angle;
          
            speed = (int) (3 + (Math.random()*3/10));
          
            x -= Math.sin(personalAngle) * speed;
            y += Math.cos(personalAngle) * speed;
        }
        
       
      
        if(life > 0){
    	    life-= lifed;
        }
          
    }
    
    double lifed = .015;

    public void setLifeDown(float life) {
		this.lifed = life;
	}
    
    public void render(Graphics2D g){
   
    	if(life > 0){
    		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, life));
            g.setColor(color);
            if(img == null){
                g.fillRect(x-(size/2), y-(size/2), size, size);
            }else{
            	g.drawImage(img, x-(size/2), y-(size/2), size, size,null);
            }
            
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
    	}else{
    		GameLoop.parts.remove(this);
    	}
    }
    
}
