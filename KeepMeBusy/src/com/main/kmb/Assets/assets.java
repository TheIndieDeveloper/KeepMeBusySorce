package com.main.kmb.Assets;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.text.Position.Bias;


import com.main.kmb.gfx.SpriteSheet;
import com.main.kmb.gfx.load;

public class assets {

	/*Other*/
	public static BufferedImage p_damage;
	public static BufferedImage korax_damage;
	public static BufferedImage inventory_slot;
	
	public static BufferedImage p_attack_up;
	public static BufferedImage p_attack_down;
	public static BufferedImage p_attack_right;
	public static BufferedImage p_attack_left;
	
	public static BufferedImage economy_icon;
	
	public static BufferedImage blood;
	public static BufferedImage health_p;
	
	//NATURE
	public static BufferedImage white_flower;
	public static BufferedImage red_flower;
	public static BufferedImage tree_birch;
	public static BufferedImage tree_oak;
	
	public static BufferedImage wall;
	public static BufferedImage stone_gt;
	public static BufferedImage m_stone_gt;
	public static BufferedImage grass;
	public static BufferedImage wood_f;
	public static BufferedImage ground_road_stone;
	public static BufferedImage bricks;
	
	/*GUI IMAGES*/
	public static BufferedImage HealthGUI;
	public static BufferedImage HealthGUI_AI;
	public static BufferedImage out_off_focus_GUI;
	public static BufferedImage minimap;
	public static BufferedImage minimap_img;
	public static BufferedImage rank_GUI;
	public static BufferedImage exp_bar;
	
	/*Mouse Images*/
	public static BufferedImage mouse1;
	public static BufferedImage mouse2;
	public static BufferedImage mouse3;
	
	static load bi = new load();
	
	
	//GRASS
	public static BufferedImage grass_1;
	
	//NEW TEXTURES
	public static BufferedImage wood_1;
	
	public static void init() {
		
		grass_1 = SpriteSheet.getTile(64, 0, 16, 16);
		
		wood_1 = SpriteSheet.getTile(64+64+48, 32, 16, 16);
		
		//NATURE
		tree_oak = SpriteSheet.getTile(64+64+64, 0, 16, 16);
		white_flower = SpriteSheet.getTile(64+64+64+16, 0, 16, 16);
		red_flower = SpriteSheet.getTile(64+64+64+32, 0, 16, 16);
		
		p_damage = SpriteSheet.getTile(48, 16*7, 16, 16);
		korax_damage = SpriteSheet.getTile(32, 16*7, 16, 16);
		
		rank_GUI = SpriteSheet.getTile(64+64, 16*8, 64, 32);
		exp_bar = SpriteSheet.getTile(64+64+64+16, 16, 16*6, 16);
		
		p_attack_up = SpriteSheet.getTile(64+32, 16*8, 16, 16);
		p_attack_down = SpriteSheet.getTile(64+48, 16*8, 16, 16);
		p_attack_left = SpriteSheet.getTile(64+16, 16*8, 16, 16);
		p_attack_right = SpriteSheet.getTile(64, 16*8, 16, 16);
		
		blood =  SpriteSheet.getTile(64+64+32, 32, 16, 16);
		health_p = SpriteSheet.getTile(64+16, 16*7, 16, 16);
		
		economy_icon = SpriteSheet.getTile(64+32, 16*7, 16, 16);
		
		wall = SpriteSheet.getTile(16, 16, 16, 16);
		stone_gt = SpriteSheet.getTile(32, 16, 16, 16);
		m_stone_gt = SpriteSheet.getTile(32, 32, 16, 16);
		grass = SpriteSheet.getTile(48, 0, 16, 16);
		wood_f = SpriteSheet.getTile(48, 16, 16, 16);
		ground_road_stone = SpriteSheet.getTile(64, 48, 16, 16);
		bricks = SpriteSheet.getTile(64, 64+16, 16, 16);
		
		HealthGUI = SpriteSheet.getTile(64+16, 0, 16*3, 16);
		HealthGUI_AI = SpriteSheet.getTile(64+48, 16, 16*3, 32);
		out_off_focus_GUI = SpriteSheet.getTile(64+48, 48, 16*3, 32);
		
		minimap = SpriteSheet.getTile(64+32+64, 48, 16*3, 32);
		
		inventory_slot = SpriteSheet.getTile(64+64+32, 16, 16, 16);
		
		mouse1 = SpriteSheet.getTile(0, 0, 16, 16);
		mouse2 = SpriteSheet.getTile(16, 0, 16, 16);
		mouse3 = SpriteSheet.getTile(32, 0, 16, 16);
		
	}
	
	public static BufferedImage getGrass_1() {
		return grass_1;
	}
	
	public static BufferedImage getExp_bar() {
		return exp_bar;
	}
	
	public static BufferedImage getRank_GUI() {
		return rank_GUI;
	}
	
	public static BufferedImage getWood_1() {
		return wood_1;
	}
	
	public static BufferedImage getEconomy_icon() {
		return economy_icon;
	}
	
	public static BufferedImage getP_attack_up() {
		return p_attack_up;
	}
	
	public static BufferedImage getP_attack_down() {
		return p_attack_down;
	}
	
	public static BufferedImage getP_attack_right() {
		return p_attack_right;
	}
	
	public static BufferedImage getP_attack_left() {
		return p_attack_left;
	}
	
	public static BufferedImage getTree_oak() {
		return tree_oak;
	}
	
	public static BufferedImage getRed_flower() {
		return red_flower;
	}
	
	public static BufferedImage getWhite_flower() {
		return white_flower;
	}
	
	public static BufferedImage getMinimap() {
		return minimap;
	}
	
	public static BufferedImage getMinimap_img() {
		return minimap_img;
	}
	
	public static BufferedImage getWallTexture() {
		return wall;
	}
	public static BufferedImage getStone_GroundTexture() {
		return stone_gt;
	}
	public static BufferedImage getGrass() {
		return grass;
	}
	public static BufferedImage getMossyStone_GroundTexture() {
		return m_stone_gt;
	}
	public static BufferedImage getWoodFloor_Texture() {
		return wood_f;
	}
	public static BufferedImage getGroundRoadStone_Texture() {
		return ground_road_stone;
	}
	public static BufferedImage getBricks_Texture() {
		return bricks;
	}
	
	public static BufferedImage getInventory_slot() {
		return inventory_slot;
	}

	public static Image getMouseOne() {
		init();
		return mouse1;
	}
	
	public static Image getMouseTwo() {
		init();
		return mouse2;
	}
	
	public static Image getMouseThree() {
		init();
		return mouse3;
	}
	
	public static BufferedImage getOut_off_focus_GUI() {
		return out_off_focus_GUI;
	}
	
	public static BufferedImage getHealthGUI() {
		return HealthGUI;
	}
	
	
	public static synchronized void playSound(final String url) {

		  new Thread(new Runnable() {
		    public void run() {
		      try {
		        Clip clip = AudioSystem.getClip();
		        AudioInputStream inputStream = AudioSystem.getAudioInputStream(assets.class.getResource("" + url));
			    clip.open(inputStream);
			    clip.start(); 	
		      } catch (Exception e) {
		        System.err.println(e.getMessage());
		      }
		    }
		  }).start();
	}

	public static BufferedImage getBlood() {
		return blood;
	}


	public static BufferedImage getHealthPickup() {
		// TODO Auto-generated method stub
		return health_p;
	}
	
}
