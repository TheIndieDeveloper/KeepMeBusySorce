package com.main.kmb.Entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.ArrayList;

import com.main.kmb.Assets.assets;
import com.main.kmb.engine.GameLoop;
import com.main.kmb.game.Economy;
import com.main.kmb.game.PlayerStats;
import com.main.kmb.game.b_health;
import com.main.kmb.gamestate.GameStateManager;
import com.main.kmb.gamestates.Block;
import com.main.kmb.gamestates.Block.BlockType;
import com.main.kmb.gamestates.Collision;
import com.main.kmb.gamestates.DeadState;
import com.main.kmb.gamestates.PlayingState;
import com.main.kmb.gfx.Particle;
import com.main.kmb.main.frame;

public class Entity implements KeyListener {
	
	private static Rectangle EntityRect;
	
	private static Rectangle detectionDistanceRect;
	private int detectionDistanceScuare = 20;
	
	private static Rectangle damageRect;
	private int stopRectDistanceScuare = 3;
	
	private int mx;
	private int my;
	public double xpos;
	public double ypos;
	
	private static boolean detected;
	
	private double runningSpeed = 6;
	private double normalSpeed = 4;
	public double speed;
	
	private int width = 30;
	private int height = 30;
	private int AnimationSpeed = 200;
	
	public static boolean inventory;
	
	private static boolean right,left,up,down,running;
	boolean canMove = true;
	
	//RENDERING
	private int RenderDistanceScuare = 20;
	
	private boolean animate = true;
	private Rectangle render;
	
	private static boolean damaged;
	
	private boolean isAlive = true;

	private double regenScale = .01;
	
	public PlayerStats stats = new PlayerStats();
	
	public Entity(){
		
	}
	
	public void init(){

	}
	
	Rectangle stop_rect;
	
	public void tick(double deltaTime) {

		if(getStats().getCurrHealth() > getStats().getDefaultHealth()){
			getStats().setCurrHealth(getStats().getDefaultHealth());
		}
		
		if(getStats().getCurrStamina() > getStats().getDefaultStamina()){
			getStats().setCurrStamina(getStats().getDefaultStamina());
		}
		
		RegenHP(deltaTime);
		CheckDeath();
		
		EntityRect = new Rectangle((int)xpos + PlayingState.xOffset , (int) ypos + PlayingState.yOffset, getWidth(), getHeight());
		
		EntityMovement(deltaTime);
		
		render = new Rectangle(
				(int)xpos - RenderDistanceScuare*68 / 2 + getWidth() / 2 + PlayingState.xOffset , 
				(int)ypos - RenderDistanceScuare*37 / 2 + getHeight() / 2 - 16 + PlayingState.yOffset, 
				RenderDistanceScuare*68, 
				RenderDistanceScuare*38);
		
		detectionDistanceRect = new Rectangle(
				(int)xpos + PlayingState.xOffset - detectionDistanceScuare*32 / 2 + getWidth() / 2,
				(int)ypos + PlayingState.yOffset - detectionDistanceScuare*32 / 2 + getHeight() / 2,
				detectionDistanceScuare*32, 
				detectionDistanceScuare*32);
		
		//damageRect = new Rectangle((int)xpos - 17 + PlayingState.xOffset, (int)ypos + height - 9 + PlayingState.yOffset, 64,48);
		
		stop_rect = new Rectangle(
				(int)xpos + PlayingState.xOffset - stopRectDistanceScuare*32  / 2 + getWidth() / 2, 
				(int)ypos + PlayingState.yOffset - stopRectDistanceScuare*32 / 2 + getHeight() / 2,
				stopRectDistanceScuare*32, 
				stopRectDistanceScuare*32);
		
		TickDamageTime(deltaTime);
		
		getStats().tickExp(deltaTime);
			
	}

	private void RegenHP(double deltaTime) {
		if(getStats().getCurrHealth() < getStats().getDefaultHealth()){
			getStats().addHealth(getStats().getHealthRegen());
		}
	}

	float blackScreen = 1;
	float healthScale;
	float expScale;
	float staminaScale; 
	
	int col;
	boolean next = false;
	public void HandlePlayerHealth(GameStateManager gsm, Graphics2D g) {
		
		if(getStats().getCurrHealth() > 100){
			g.setColor(Color.GREEN);
		}
		if(getStats().getCurrHealth() < 100){
			g.setColor(Color.ORANGE);
		}
		if(getStats().getCurrHealth() < 50){
			g.setColor(Color.RED);
		}
		
		healthScale = (float) (getStats().getCurrHealth() / getStats().getMaxHealth());
		staminaScale = (float) (getStats().getCurrStamina() / getStats().getMaxStamina());
		expScale = (float) (getStats().getCurrExp() / getStats().getMaxExp());
		
		g.fillRect((int) 304, (int) 4, (int) (184 * healthScale), 42);
		//g.fillRect(14, 638, (int) ((int) getMaxhealth() * getHealthScale()) , 42);
		
		g.setColor(Color.YELLOW);
		//g.fillRect(254, 638, (int) ((int) getMaxStamina() * getStaminaScale()) , 42);
		g.fillRect((int) 504, (int) 4, (int) (184 * staminaScale), 42);

		g.drawImage(assets.getHealthGUI(),300, 1, 32*6, 16*3,null);
		g.drawImage(assets.getHealthGUI(),500, 1, 32*6, 16*3,null);
		
//		g.drawString(GameLoop.parts.size()+"", 200, 200);
//		g.drawString(GameLoop.weather.size()+"", 200, 232);
		
		stats.getEconomy().FixEconomy(g);
		if(!next){
			if(col != 150){
				col+=5;
			}
			if(col == 150){
				next = true;
			}
		}
		if(next){
			if(col != 0){
				col-=5;
			}
			if(col == 0){
				next = false;
			}
		}
		
		g.setColor(new Color(0,255,col));
		g.fillRect((int) 20, (int) 656, (int) (1235 * expScale), 30);
		
		g.drawImage(assets.getRank_GUI(), 0, 0, 64*4,64*2,null);
		
		getStats().fixRank(g);
		
		g.setColor(Color.WHITE);
		
		g.drawRect(
				(int)xpos - stopRectDistanceScuare*32  / 2 + getWidth() / 2, 
				(int)ypos - stopRectDistanceScuare*32 / 2 + getHeight() / 2,
				stopRectDistanceScuare*32 , 
				stopRectDistanceScuare*32);
		
		if(!isAlive())
		{
			if(blackScreen > 0.000000001){
				blackScreen-= 0.02;
			}
			
			if(blackScreen <= 0.000000001){
				blackScreen = 0;
			}
			
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) blackScreen));
			
			if(blackScreen <= 0){
				gsm.states.push(new DeadState(gsm));
			}
			
		}
	}
	
	public void canMove(boolean b) {
		canMove = b;
	}

	private void EntityMovement(double deltaTime) {
		
		if(canMove){
			
			double moveAmount = speed * deltaTime;
			
			if(isLeft()){
				
				if(!Collision.PlayerBlock(
						//LEFT DOWN
						new Point( (int) (xpos + PlayingState.xOffset - moveAmount),
								   (int) (ypos + PlayingState.yOffset + height)),
						//LEFT UP
						new Point( (int) (xpos + PlayingState.xOffset - moveAmount), 
								   (int) (ypos + PlayingState.yOffset)))){
						//MOVE MAP
						PlayingState.xOffset -= moveAmount;
					
				}

			}
			
			if(isRight()) {
				
				if(!Collision.PlayerBlock(
						// RIGHT DOWN
						new Point( (int) (xpos + PlayingState.xOffset + width + moveAmount) ,
								   (int) (ypos + PlayingState.yOffset)),
						// RIGHT UP
						new Point( (int) (xpos + PlayingState.xOffset + width + moveAmount), 
								   (int) (ypos + PlayingState.yOffset + height)))){
					
						PlayingState.xOffset += moveAmount;
					
				}
			}
			
			if(isUp()){
				if(!Collision.PlayerBlock(
						//UP LEFT
						new Point( (int) (xpos + PlayingState.xOffset) ,
								   (int) (ypos + PlayingState.yOffset - moveAmount)),
						//UP RIGHT		
						new Point( (int) (xpos + PlayingState.xOffset + width), 
								   (int) (ypos + PlayingState.yOffset - moveAmount)))){
					
						PlayingState.yOffset -= moveAmount;
					
				}	
			}
			
			if(isDown()) {
				if(!Collision.PlayerBlock(
						//DOWN RIGHT
						new Point( (int) (xpos + PlayingState.xOffset) ,
								   (int) (ypos + PlayingState.yOffset + height + moveAmount)),
						//DOWN LEFT
						new Point( (int) (xpos + PlayingState.xOffset + width), 
								   (int) (ypos + PlayingState.yOffset + height + moveAmount)))){
						PlayingState.yOffset += moveAmount;
			
				}
			}
			
			

			if(isRunning()){
				if(getStats().getCurrStamina() > 2){
					speed = runningSpeed;
					setAnimationSpeed(50);
				}
				if(getStats().getCurrStamina() < 2){
					speed = normalSpeed;
					setAnimationSpeed(200);
				}
				getStats().removeStamina(2 * deltaTime);
			}else{
				getStats().addStamina(1 * deltaTime);
				setAnimationSpeed(200);
				speed = normalSpeed;
			}
		}
	}
	
	//TODO

	
	public void render(Graphics2D g){
		g.drawRect((int) xpos, (int) ypos, getWidth(), getHeight());
		
		g.drawRect(EntityRect.x, EntityRect.y, EntityRect.width, EntityRect.height);
		
		g.setColor(Color.WHITE);
		//g.drawRect((int)xpos - detectionDistanceScuare*32 / 2 + getWidth() / 2,(int)ypos - detectionDistanceScuare*32 / 2 + getHeight() / 2, detectionDistanceScuare*32, detectionDistanceScuare*32);
		g.setFont(new Font("Serif",20,20));
		//g.drawString("Kills: "+getStats().getKills(), 120, 25);
//		g.drawRect(
//				(int)xpos - damageRectDistanceScuare*32 / 2 + getWidth() / 2,
//				(int)ypos - damageRectDistanceScuare*32 / 2 + getHeight() / 2,
//				damageRectDistanceScuare*32, 
//				damageRectDistanceScuare*32);
		//DOWN
//		g.drawRect((int)xpos - 17, (int)ypos + height - 9, 64,48);
//		//UP
//		g.drawRect((int)xpos - 17 , (int)ypos - height - 9, 64,48);
////		//RIGHT
//		g.drawRect( (int)xpos + width - 9, (int)ypos - 17, 48,64);
//		//LEFT
//		g.drawRect((int)xpos - width - 9, (int)ypos - 17, 48,64);
		
		//g.drawImage(assets.getMinimap(), 1017, 530, 32*8,32*5,null);
		
		if(attacking){
			if(Player.AnimationState == 0){
				
				g.drawImage(assets.getP_attack_down(), (int)xpos - 17, (int)ypos + height - 9, 64,64,null);
				getStats().AttackTimer(this);
				
			}
			if(Player.AnimationState == 1){
				
				g.drawImage(assets.getP_attack_up(), (int)xpos - 17 , (int)ypos - height - 24, 64,64,null);
				getStats().AttackTimer(this);
				
			}
			if(Player.AnimationState == 2){
				
				g.drawImage(assets.getP_attack_right(), (int)xpos + width - 9, (int)ypos - 17, 64,64,null);
				getStats().AttackTimer(this);
				
			}
			if(Player.AnimationState == 3){
				
				g.drawImage(assets.getP_attack_left(), (int)xpos - width - 25, (int)ypos - 17, 64,64,null);
				getStats().AttackTimer(this);
				
			}
		}
	}
	
	//TODO
	private void TickDamageTime(double deltaTime) {
	//if(attacking){
		if(Player.AnimationState == 0){
			damageRect = new Rectangle((int)xpos - 17 + PlayingState.xOffset, (int)ypos + height - 9 + PlayingState.yOffset, 64,48);
		}
		if(Player.AnimationState == 1){
			damageRect = new Rectangle((int)xpos - 17 + PlayingState.xOffset, (int)ypos - height - 9 + PlayingState.yOffset, 64,48);
		}
		if(Player.AnimationState == 2){
			damageRect = new Rectangle( (int)xpos + width - 9 + PlayingState.xOffset, (int)ypos - 17 + PlayingState.yOffset, 48,64);
		}
		if(Player.AnimationState == 3){
			damageRect = new Rectangle( (int)xpos - width - 9 + PlayingState.xOffset, (int)ypos - 17 + PlayingState.yOffset, 48,64);
		}
	//}
	}
	
	public Rectangle getStopRect() {
		return stop_rect;
	}
	
	public static boolean attacking;
	
	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}
	
	public static boolean isAttacking() {
		return attacking;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(isAlive()){
			if(e.getKeyCode() == KeyEvent.VK_D){
				this.setRight(true);
			}
			if(e.getKeyCode() == KeyEvent.VK_A){
				this.setLeft(true);
			}
			if(e.getKeyCode() == KeyEvent.VK_W){
				this.setUp(true);
			}
			if(e.getKeyCode() == KeyEvent.VK_S){
				this.setDown(true);
			}
			if(e.getKeyCode() == KeyEvent.VK_SHIFT){
				this.setRunning(true);
			}
			
			//SPELLS

			if(e.getKeyCode() == KeyEvent.VK_SPACE){
	
			}
			if(e.getKeyCode() == KeyEvent.VK_R){

			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(isAlive()){
			if(e.getKeyCode() == KeyEvent.VK_D){
				this.setRight(false);
			}
			if(e.getKeyCode() == KeyEvent.VK_A){
				this.setLeft(false);
			}
			if(e.getKeyCode() == KeyEvent.VK_W){
				this.setUp(false);
			}
			if(e.getKeyCode() == KeyEvent.VK_S){
				this.setDown(false);
			}
			if(e.getKeyCode() == KeyEvent.VK_SHIFT){
				this.setRunning(false);
			}
			if(e.getKeyCode() == KeyEvent.VK_SPACE){
				if(!attacking){
					setAttacking(true);
				}
			}
		}
	}
	@Override
	public void keyTyped(KeyEvent arg0) {}
	
	//ENTITY CHECKS
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
	
	private void CheckDeath() {
		if(getStats().getCurrHealth() <= 0){
			setAlive(false);
			right = false;
			left = false;
			down = false;
			up = false;
			running = false;
		}
		
	}
	
	int default_damageTime = 30;
	int p_damageTime = 30;

	public void TakeDamage(AI attack,double damage, double deltaTime){
		
		if(getStats().getCurrHealth() > 0){
			
			getStats().removeHealth(damage);
			GameLoop.parts.add(new Particle((int)303 + (int) (184 * healthScale),(int) 16, 5, (float) .5, Color.green, true));
			GameLoop.parts.add(new Particle((int)303 + (int) (184 * healthScale),(int) 26, 5, (float) .3, Color.red, true));
			GameLoop.parts.add(new Particle((int)303 + (int) (184 * healthScale),(int) 36, 5, (float) .5, Color.green, true));
				
			setDamaged(true);
			DecimalFormat format = new DecimalFormat("0.#");
			GameLoop.parts.add(new Particle((int)xpos, (int)ypos,25, 1, format.format(damage)+"-",Color.RED, true));
			
			attack.attacking = false;
		}
	}
	
	
	//PLAYERPROPS///////////////////////////////////////////////////////////////////////////////

	//PLAYER GETTERS AND SETTERS////////////////////////////////////////////////////////////////
	////PLAYER RENDER BOX
	public Rectangle getRenderBox() {
		return render;
	}
	public Rectangle getEntityBounds() {
		return EntityRect;
	}
	//DETECTION
	public Rectangle getDetectionDistanceRect() {
		return detectionDistanceRect;
	}
	
	public boolean isDetected() {
		return detected;
	}
	
	public void setDetected(boolean detected) {
		this.detected = detected;
	}
	
	////X
	public void setXpos(double xpos) {
		this.xpos = xpos;
	}
	public double getXpos() {
		return this.xpos;
	}
	
	////Y
	public void setYpos(double ypos) {
		this.ypos = ypos;
	}
	public double getYpos() {
		return this.ypos;
	}
	
	public static Rectangle getDamageRect() {
		return damageRect;
	}
	
	//WIDTH AND HEIGHT
	public void setSize(int w, int h){
		this.setWidth(w);
		this.setHeight(h);
	}
	
	//SET POS
	public void setPos(double xpos, double ypos) {
		PlayingState.xOffset = (int) xpos;
		PlayingState.yOffset = (int) ypos;
		setXpos(625);
		setYpos(341);
	}
	
	//SET WALK
	public void setInventory(boolean inventory) {
		this.inventory = inventory;
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

	public boolean isDamaged() {
		return damaged;
	}

	public void setDamaged(boolean damaged) {
		this.damaged = damaged;
	}

	public int getAnimationSpeed() {
		return AnimationSpeed;
	}

	public void setAnimationSpeed(int animationSpeed) {
		AnimationSpeed = animationSpeed;
	}

	public boolean isAnimate() {
		return animate;
	}

	public void setAnimate(boolean animate) {
		this.animate = animate;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public PlayerStats getStats() {
		return stats;
	}
}

