package com.main.kmb.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.main.kmb.Assets.assets;
import com.main.kmb.engine.GameLoop;
import com.main.kmb.gfx.Particle;

public class Economy {

	
	int money = 100;
	
	public Economy() {
		
	}
	
	public void setMoney(int money){
		this.money = money;
	}
	
	public int getMoney() {
		return money;
	}
	
	public void addMoney(int money){
		this.money = this.money + money;
		GameLoop.parts.add(new Particle(200, 50, 1, money+"+",Color.YELLOW, true));
	}
	
	public void removeMoney(int money){
		this.money = this.money - money;
		GameLoop.parts.add(new Particle(200, 50, 1, money+"-",Color.YELLOW, true));
	}
	
	double xpos;
	double ypos;
	double width = 48;
	double height = 48;

	public void FixEconomy(Graphics2D g) {
		g.setFont(new Font("Calvin",30,40));
		g.setColor(Color.YELLOW);
		g.drawString(getMoney()+"", 360, 42);
		g.setColor(Color.WHITE);
		g.drawImage(assets.getEconomy_icon(), (int) (300 + xpos), (int) (10 + ypos),(int) width, (int) height,null);
		g.setFont(new Font("Terminator Two",32,15));
	}
	
}
