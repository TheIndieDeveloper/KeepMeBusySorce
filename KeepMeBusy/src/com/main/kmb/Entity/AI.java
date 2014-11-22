package com.main.kmb.Entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import com.main.kmb.Assets.assets;
import com.main.kmb.engine.GameLoop;
import com.main.kmb.game.b_health;
import com.main.kmb.gamestates.Block;
import com.main.kmb.gamestates.Block.BlockType;
import com.main.kmb.gamestates.PlayingState;
import com.main.kmb.gfx.Particle;
import com.main.kmb.main.frame;

public class AI {

	public double xpos;
	public double ypos;
	public double cxpos;
	public double cypos;
	public int width = 32;
	public int height = 32;
	double speed = 1;
	public int AnimationSpeed = 200;
	
	private static boolean right,left,up,down,running;
	
	public int health = 158;
	public int Maxhealth = 1;
	public float healthScale = health / Maxhealth;
	
	public boolean damaged;
	
	public boolean isAlive;
	public boolean attacking;
	
	public boolean followPlayer;
	
	public Rectangle AIRect;
	public Rectangle walk_AIRect;
	
	public AI() {
	
	}
	
	boolean canMove = true;
	
	public void CreateAIGUI(Graphics2D g) {
		
		if(isAlive){
			g.setColor(Color.RED);
			g.fillRect((int)250 - (int)healthScale,(int) 80, (int) ((int) Maxhealth * healthScale) , 20);
			GameLoop.parts.add(new Particle((int)250 - (int)healthScale,(int) 80, 5, (float) .5, Color.red, true));
			GameLoop.parts.add(new Particle((int)250 - (int)healthScale,(int) 90, 5, (float) .5, Color.red, true));
			GameLoop.parts.add(new Particle((int)250 - (int)healthScale,(int) 100, 5, (float) .5, Color.red, true));
			
			g.setColor(Color.WHITE);
			
			g.drawImage(assets.HealthGUI_AI, 10, 10, 16*15 ,16*10,null);
		}
	}
	
	public double volx = 2;
	public double voly = 2;
	double dx;
	double dy;
	
	int attackT = 30;
	int currAttackT = attackT;
	
	public void tick(double deltaTime,Player player) {
		
		if(isAlive){
			
			cxpos = xpos - PlayingState.xOffset;
			cypos = ypos - PlayingState.yOffset;
			
			AIRect = new Rectangle((int) xpos, (int) ypos, width, height);
			
			walk_AIRect = new Rectangle((int) xpos - walkR * 32 / 2 + width / 2, (int) ypos - walkR * 32 / 2 + height / 2, walkR * 32, walkR * 32);
			
			
			if(AIRect.intersects(player.getStopRect())){
				if(!attacking){
					if(currAttackT != 0){
						currAttackT-=1;
					}
					if(currAttackT == 0){
						currAttackT = attackT;
						attacking = true;
					}
				}
			}
			
			
			if(getBounds().intersects(player.getDetectionDistanceRect())){
				followPlayer = true;
			}else{
				followPlayer = false;
			}
			
			if(followPlayer){
				if(!canMove){
					FollowThePlayer(deltaTime,player);
				}
			}else{
				if(!canMove){
					WalkRandomInGrid(deltaTime,player);
				}
			}
			
			if(healthScale <= 0){
				isAlive = false;
				if(!isAlive){
					//TODO
					if(PlayingState.ai_entitys.contains(this)){
						Random ran = new Random();
						int rans = ran.nextInt(100);
						if(rans > 30 && rans < 32){
							for(int x = 0;x < 2;x++){
								PlayingState.bonce_o.add(new b_health((int)xpos*x*32,(int)ypos));
							}
						}
						if(rans > 70 && rans < 80){
							PlayingState.bonce_o.add(new b_health((int)xpos,(int)ypos));
						}
						if(rans < 10){
							PlayingState.bonce_o.add(new b_health((int)xpos,(int)ypos));
						}	
						PlayingState.ai_entitys.remove(this);
						Player.addKill(1);
					}
					
				}
			}
			
		}
		
		//HANDLE MOVEMNET
	}
	
	boolean choose = false;
	boolean walk_to_choose = false;
	CopyOnWriteArrayList<Block> choosedBlocks = new CopyOnWriteArrayList<Block>();
	
	int aiWaitingTime = 80;
	
	int walkR = 9;
	Random ran = new Random();
	Block choosed_block;
	
	private void WalkRandomInGrid(double deltaTime, Player player) {
		/*walk random!*/

		/*
		 * if ai has not choosed block
		 * and is not waking towards a block
		 * get blocks in the grid and add them to
		 * the array list of choosed blocks!
		 */
		
		if(!choose && !walk_to_choose){
			for(Block b : PlayingState.getBlocks()){
				if(getWalk_AIRect().intersects(b)){
					if(!choosedBlocks.contains(b)){
						
						/*
						 * adds the blocks in the grid
						 */
						
						choosedBlocks.add(b);
						
						/*
						 * AI has choosed the grid
						 */
						
					}
				}
				//HERE
			}
			choose = true;

		}
			
		if(choose){
			
			/*
			 * Start Walking towards the choosed_block
			 */
			walk_to_choose = true;
			/*
			 * choosed_block is random block out off the arraylist choosedblocks
			 */
			
			choosed_block = choosedBlocks.get(ran.nextInt(choosedBlocks.size()));
			System.out.println("PICKING RANDOM BLOCK!");
			
			
		}
		
		if(walk_to_choose){
			
			//System.out.println("WAKING TO RANDOM BLOCK!");
			choosed_block.setBlockType(BlockType.GROUND_ROAD_STONE);                            

			//SETS CHOOSED BLOCKS TO FALSE SO IT DOES NOT SELECT MORE BLOCKS
			//THEN IN THE GRID
			choose = false;
			
			//WALKS THE AI TO LOCATION OF THE BLOCK!
			if(this.xpos < choosed_block.x){
				this.xpos+= 1.5 * speed * deltaTime;
			}
			if(this.xpos > choosed_block.x){
				this.xpos-= 1.5 * speed * deltaTime;
			}
			if(this.ypos < choosed_block.y){
				this.ypos+= 1.5 * speed * deltaTime;
			}
			if(this.ypos > choosed_block.y){
				this.ypos-= 1.5 * speed * deltaTime;
			}
			
			//CHECK IF THE AI IS AT THE LOCATION OF THE BLOCK
			if(getBounds().intersects(choosed_block)){
				
				for(Block b : choosedBlocks){
					choosedBlocks.remove(b);
				}
				
				//THE DELAY BEFORE NEXT CHOOSE
				if(aiWaitingTime != 0){
					aiWaitingTime-= 1;
				}
				if(aiWaitingTime == 0){
					//STOP WALKING AND GET A NEW BLOCK
					walk_to_choose = false;
					
					//SET TIME BACK TO 80
					aiWaitingTime = 120;
				}
			}
		}
	}

	private void FollowThePlayer(double deltaTime, Player player) {
		double pXpos = player.getXpos();
		double pYpos = player.getYpos();
		
		this.speed = 1.5;
		
		double dx = pXpos - cxpos,dy = pYpos - cypos;
		double distance = Math.sqrt(dx*dx + dy*dy);
		double multi = speed * deltaTime / distance;
		volx = dx * multi;
		voly = dy * multi;
		
		if(cxpos < pXpos){
			this.xpos += this.volx * this.speed * deltaTime;
		}
		if(cxpos > pXpos){
			this.xpos += this.volx * this.speed * deltaTime;
		}
		if(cypos < pYpos ){
			this.ypos += this.voly * this.speed * deltaTime;
		}
		if(cypos > pYpos ){
			this.ypos += this.voly * this.speed * deltaTime;
		}
		
		if(walk_to_choose || choose){
			walk_to_choose = false;
			choose = false;
			for(Block b : choosedBlocks){
				choosedBlocks.remove(b);
			}
		}
	}
	
	//TODO
	
	public void render(Graphics2D g){
		g.drawRect(
				(int)cxpos - walkR * 32 / 2 + width / 2, 
				(int)cypos - walkR * 32 / 2 + height / 2,
				walkR * 32,
				walkR * 32);
		
		
		g.setColor(Color.WHITE);
//		g.drawString("CHOOSE: "+choose, 500, 200);
//		g.drawString("WALKTO: "+walk_to_choose, 500, 250);
		g.drawString("CHOOSE SIZE: "+choosedBlocks.size(), 500, 300);
//		g.drawString("BLOCK: "+random, 500, 350);
//		g.drawString("FP: "+followPlayer, 500, 400);
	}

	public void init(){
		
	}
	
	protected void teleport(double x, double y) {
		xpos = x;
		ypos = y;
	}
	
	public void TakeDamage(double damage, double deltaTime){
		if(healthScale > 0){
			healthScale -= damage * deltaTime;
			
			int knockback = 40;
			
			if(Player.AnimationState == 0){
				ypos = ypos +=knockback+speed*deltaTime;
			}
			if(Player.AnimationState == 1){
				ypos = ypos -=knockback+speed*deltaTime;
			}
			if(Player.AnimationState == 2){
				xpos = xpos +=knockback+speed*deltaTime;
			}
			if(Player.AnimationState == 3){
				xpos = xpos -=knockback+speed*deltaTime;
			}
			damaged = true;
		}
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
	
	public void setRight(boolean right) {
		this.right = right;
	}
	
	public void setLeft(boolean left) {
		this.left = left;
	}
	
	public void setUp(boolean up) {
		this.up = up;
	}
	
	public void setDown(boolean down) {
		this.down = down;
	}
	
	public void setRunning(boolean run) {
		this.running = run;
	}
	
	public boolean isRight() {
		return right;
	}

	public boolean isLeft() {
		return left;
	}
	
	public boolean isUp() {
		return up;
	}

	public boolean isDown() {
		return down;
	}
	
	public boolean isRunning() {
		return running;
	}

	public Rectangle getWalk_AIRect() {
		return walk_AIRect;
	}

	public Rectangle getBounds() {
		return AIRect;
	}
	
	public void stop(boolean canMove) {
		this.canMove = canMove;
	}

	public boolean isAttacking() {
		return attacking;
	}
	
}
