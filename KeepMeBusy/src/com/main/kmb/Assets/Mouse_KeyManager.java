package com.main.kmb.Assets;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.main.kmb.gamestates.PlayingState;

public class Mouse_KeyManager implements MouseListener,MouseMotionListener,KeyListener{

	double mouseX;
	double mouseY;
	
	static double mouse_moveX;
	static double mouse_moveY;
	
	static double mouse_moveX_map;
	static double mouse_moveY_map;
	
	public static boolean isPressedButton1;
	public static boolean isPressedButton2;
	public static boolean isPressedButton3;
	
	public static boolean isDragging;
	public static boolean isMoving;
	public static boolean hasEnteredScreen;
	//GAME
	public static boolean isPaused;
	
	public static Rectangle mouse;
	
	public Mouse_KeyManager() {
		mouse = new Rectangle((int)mouse_moveX - 13 + PlayingState.xOffset, (int)mouse_moveY - 32 + PlayingState.yOffset, 24, 24);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouse_moveX = e.getX();
		mouse_moveY = e.getY();
		
		mouse = new Rectangle((int)mouse_moveX - 13 + PlayingState.xOffset, (int)mouse_moveY - 32 + PlayingState.yOffset, 24, 24);
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		mouse_moveX = e.getX();
		mouse_moveY = e.getY();
	}
	
	public void tick(double deltaTime){
		mouse = new Rectangle((int)mouse_moveX - 13 + PlayingState.xOffset, (int)mouse_moveY - 32 + PlayingState.yOffset, 24, 24);
		
		mouse_moveX_map = mouse_moveX - 13 + PlayingState.xOffset;
		mouse_moveY_map = mouse_moveY - 32 + PlayingState.yOffset;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		if(!hasEnteredScreen){
			hasEnteredScreen = true;
		}
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		if(hasEnteredScreen){
			hasEnteredScreen = false;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1){
			if(!isPressedButton1){
				isPressedButton1 = true;
			}
		}
		if(e.getButton() == MouseEvent.BUTTON2){
			if(!isPressedButton2){
				isPressedButton2 = true;
			}
		}
		if(e.getButton() == MouseEvent.BUTTON3){
			if(!isPressedButton3){
				isPressedButton3 = true;
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1){
			if(isPressedButton1){
				isPressedButton1 = false;
			}
		}
		if(e.getButton() == MouseEvent.BUTTON2){
			if(isPressedButton2){
				isPressedButton2 = false;
			}
		}
		if(e.getButton() == MouseEvent.BUTTON3){
			if(isPressedButton3){
				isPressedButton3 = false;
			}
		}
	}

	public boolean isPressedButton1() {
		return isPressedButton1;
	}
	public boolean isPressedButton2() {
		return isPressedButton2;
	}
	public boolean isPressedButton3() {
		return isPressedButton3;
	}
	public boolean MouseHasEnteredScreen() {
		return hasEnteredScreen;
	}
	public boolean isPaused() {
		return isPaused;
	}
	
	public double getMouse_moveX() {
		return mouse_moveX;
	}
	
	public double getMouse_moveY() {
		return mouse_moveY;
	}

	public Rectangle getMouse() {
		return mouse;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
			if(!isPaused){
				isPaused = true;
				System.out.println("PAUSED!");
			}else{
				isPaused = false;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}
	
}
