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

public class Weather {

	private int x;
    private int y;
	private int dx;
    private int dy;
    private int size;
    private float life = 1;
    private Color color;
    private BufferedImage img;
    boolean isStill;
    public WeatherType type;

    public Weather(int x, int size, int life, WeatherType type){
        this.x = x;
        y = -20*15;
        this.type = type;
        this.size = size;
        this.life = life;
        if(type == WeatherType.SNOW){
        	color = Color.WHITE;
        }
        if(type == WeatherType.RAIN){
        	color = Color.BLUE;
        }
    }
    
	int speed = 60;
    private float angle;
    
    public void update(double deltaTime){
//          
//    	if(this.type == WeatherType.SNOW){
//            x += dx + Math.random()/30;
//            y += dy + Math.random()/30;
//      	  
//            if(Math.random()>.5){
//                x += 3*Math.random();
//            }else{
//                x -= 3*Math.random();
//            }
//            
//            if(Math.random()>.5){
//                y += 3*Math.random();
//            }else{
//                y -= 3*Math.random();
//            }
//    	}
    	
        x += 5 + Math.random()/2;
        y += 5 + Math.random()/2;
  	  
        if(Math.random()>.5){
            x += 10*Math.random();
        }else{
            x -= 5*Math.random();
        }
        
        if(Math.random()>.5){
            y += 20*Math.random();
        }else{
            y -= 0*Math.random();
        }
      
        angle = 14;
        
        speed = (int) (3 + (Math.random()*3/10));
      
        x -= Math.sin(angle) * speed;
        y += Math.cos(angle) * speed;
        
       
      
        if(life > 0){
    	    life-= lifed;
        }
          
    }
    
    double lifed = .0150;

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
    		GameLoop.weather.remove(this);
    	}
    }

    public enum WeatherType{
    	SNOW,RAIN
    }
    
}
