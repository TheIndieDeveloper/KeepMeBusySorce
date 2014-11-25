package com.main.kmb.gamestates;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import com.main.kmb.Assets.assets;
import com.main.kmb.Entity.AI;
import com.main.kmb.Entity.Entity;
import com.main.kmb.Entity.Korax;
import com.main.kmb.Entity.Player;
import com.main.kmb.engine.GameLoop;
import com.main.kmb.game.BonceObject;
import com.main.kmb.gamestate.GameState;
import com.main.kmb.gamestate.GameStateManager;
import com.main.kmb.gamestates.Block.BlockType;
import com.main.kmb.gfx.load;
import com.main.kmb.nature.Flower;
import com.main.kmb.nature.Flower.FlowerType;
import com.main.kmb.nature.NatureObject;
import com.main.kmb.nature.Tree;
import com.main.kmb.nature.Tree.TreeType;

public class PlayingState extends GameState {

	public PlayingState(GameStateManager gsm) {
		super(gsm);
	}

	public static Player player;
	
	public static ArrayList<Block> blocks;
	private static ArrayList<Entity> entitys;
	
	public static CopyOnWriteArrayList<AI> ai_entitys;
	
	public static CopyOnWriteArrayList<NatureObject> nature_o;
	public static CopyOnWriteArrayList<BonceObject> bonce_o;
	
	private boolean init = false;
	public static int[][] map;
	boolean PlayingStateInit = false;
	
	public int BLOCKSIZE = Block.blockSize;
	
	load BufferedImageLoader = new load();
	assets assets = new assets();
	BufferedImage level;
	
	public static int xOffset;
	public static int yOffset;
	public int map_w = 200;
	public int map_h = 200;
	
	public double deltaTime;
	
	public void init() {
		map = new int[map_w][map_h];
		
		blocks = new ArrayList<Block>();
		player = new Player();
		entitys = new ArrayList<Entity>();
		entitys.add(player);
		ai_entitys = new CopyOnWriteArrayList<AI>();
		nature_o = new CopyOnWriteArrayList<NatureObject>();
		bonce_o = new CopyOnWriteArrayList<BonceObject>();
		
		for(int x = 0; x < 500;x++){
			//nature_o.add(new Tree(200*x, 200,TreeType.OAK));
			
//			Random ran = new Random();
//			int rans = ran.nextInt(2);
//			FlowerType type = null;
//			if(rans == 0){
//				type = FlowerType.RED;
//			}
//			if(rans == 1){
//				type = FlowerType.WHITE;
//			}
		
//					 System.out.println("add!");
			nature_o.add(new Flower(200*x, 200, FlowerType.RED));
	
		}
		
		for(int x = 0; x < 10;x++){
			ai_entitys.add(new Korax(200*x, 200));
		}	
		
		loadMap();
		
		for(Entity e : entitys){
			e.init();
		}
		
		for(AI e : ai_entitys){
			e.init();
		}
		
		for(NatureObject n : nature_o){
			n.init();
		}
	}

	private void loadMap() {
		System.out.println("Creating Map!");
		level = null;
		try {
			level = BufferedImageLoader.LoadImage("map.png");
			System.out.println("loading: map.png!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		com.main.kmb.Assets.assets.init();
		setMapPos(3703, 1230);
		
		for(int x = 0; x < map_w; x++) {
			for(int y = 0; y < map_h; y++) {
				
				int col = level.getRGB(x, y);
				
				switch(col & 0xFFFFFF)
				{
					case 0x000000:
						map[x][y] = 1;
						blocks.add(new Block(x*BLOCKSIZE, y*BLOCKSIZE, BlockType.WALL,true));
					break;
					
					case 0x808080:
						blocks.add(new Block(x*BLOCKSIZE, y*BLOCKSIZE, BlockType.STONE_GROUND,false));
					break;
					
					case 0x01A622:
						blocks.add(new Block(x*BLOCKSIZE, y*BLOCKSIZE, BlockType.GRASS,false));
					break;
				
					case 0x404040:
						blocks.add(new Block(x*BLOCKSIZE, y*BLOCKSIZE, BlockType.MOSSYSTONE_GROUND,false));
					break;
					
					case 0x7F3300:
						blocks.add(new Block(x*BLOCKSIZE, y*BLOCKSIZE, BlockType.WOOD_FLOOR,false));
					break;
					
					case 0xA0A0A0:
						blocks.add(new Block(x*BLOCKSIZE, y*BLOCKSIZE, BlockType.GROUND_ROAD_STONE,false));
					break;
					
					case 0x999999:
						blocks.add(new Block(x*BLOCKSIZE, y*BLOCKSIZE, BlockType.BRICKS,false));
					break;
				}
			}	
		}
	}


	@Override
	public void tick(double deltaTime) {
		this.deltaTime = deltaTime;
		if(!PlayingStateInit){
			init();
			PlayingStateInit = true;
		}
		
		TickBlocks(deltaTime);
		TickNature(deltaTime);
		TickEntitys(deltaTime);
		TickAI(deltaTime);
		TickOtherStuff(deltaTime);
		
	}

	private void TickBlocks(double deltaTime) {
		for(Block b : blocks){
			b.tick(deltaTime);
		}
	}
	private void TickNature(double deltaTime) {
		for(NatureObject n : nature_o){
			n.tick(deltaTime,player);
		}
	}
	private void TickEntitys(double deltaTime) {
		for(Entity e : entitys){
			e.tick(deltaTime);
		}
	}
	private void TickAI(double deltaTime) {
		for(AI a : ai_entitys){
			a.tick(deltaTime, player);
		}
	}
	private void TickOtherStuff(double deltaTime) {
		for(BonceObject e : bonce_o){
			e.tick(deltaTime);
			
			if(player.getEntityBounds().intersects(e.getObjectRect())){
				if(player.getStats().getCurrHealth() < player.getStats().getDefaultHealth()){
					e.isAlive = false;
					player.getStats().addHealth(5);
				}else{
					e.Maxjump = 2;
					e.ypos--;
				}
			}
		}
	}

	@Override
	public void render(Graphics2D g) {
		
		RenderLayerOne(g);
		RenderLayerTwo(g);
		RenderLayerThree(g);
		RenderLayerFour(g);
		RenderLayerFive(g);
		
		/*Render Ai's if thay are inside Player Render Block*/
		
		//g.drawImage(assets.getTree_oak(), 200, 200, 32*2+16,32*2+16,null);
	}
	
	
	
	private void RenderLayerOne(Graphics2D g) {
		
		for(Block b : blocks){
			if(player.getRenderBox().intersects(b.getBounds())){
				b.render(g);
			}
		}
		
	}

	private void RenderLayerTwo(Graphics2D g) {
		for(NatureObject n : nature_o){
			if(player.getRenderBox().intersects(n.getObjectRect())){
				//if(n instanceof Flower){
					n.render(g);
				//}
			}
		}
		
		for(BonceObject e : bonce_o){
			if(player.getRenderBox().intersects(e.getObjectRect())){
				e.render(g);
			}
		}
		
	}

	private void RenderLayerThree(Graphics2D g) {
		for(AI a : ai_entitys){
			if(player.getRenderBox().intersects(a.getBounds())){
				a.render(g);
			}	
		}
		for(Entity e : entitys){
			e.render(g);
		}
	}

	private void RenderLayerFour(Graphics2D g) {
		
	}

	private void RenderLayerFive(Graphics2D g) {
		FixOtherRenderStuff(g);
	}

	private void FixOtherRenderStuff(Graphics2D g) {
		player.FixMouseStuff(g);
		player.HandlePlayerHealth(gsm, g);
		HandleDamages(g, deltaTime);
	}
	
	private void HandleDamages(Graphics2D g, double deltaTime) {
		for(AI aie :  ai_entitys){
			if(aie.isAlive && player.isAlive()){
			
			//MOUSE | PLAYER AI
				if(Entity.getDamageRect() != null){
					if(Entity.getDamageRect().intersects(aie.getBounds())){
						if(player.isAttacking()){
							aie.CreateAIGUI(g);
							aie.TakeDamage(30, deltaTime);
							player.stats.getEconomy().addMoney(5);
						}	
					}
					
				//AI PLAYER
					if(player.getStopRect().intersects(aie.getBounds())){
						aie.stop(true);
						if(aie.isAttacking()){
							player.TakeDamage(aie, 5, deltaTime);
						}
					}else{
						aie.stop(false);
					}
				}
			}
		}
	}

	public static ArrayList<Block> getBlocks() {
		return blocks;
	}
	
	public static int[][] getMap() {
		return map;
	}
	
	public void setMapPos(int x, int y){
		xOffset = x;
		yOffset = y;
		player.setXpos(625);
		player.setYpos(341);
	}

}
