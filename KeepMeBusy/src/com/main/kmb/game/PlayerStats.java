package com.main.kmb.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.text.DecimalFormat;

import com.main.kmb.Assets.assets;
import com.main.kmb.Entity.Entity;
import com.main.kmb.engine.GameLoop;
import com.main.kmb.gamestates.PlayingState;
import com.main.kmb.gfx.Particle;

public class PlayerStats {

	Economy eco = new Economy();
	
	private int kills = 0;
	private int skillPoints = 0;
	
	private int damage = 15;
	
	//RANK
	private int level = 1;
	
	
	private double maxExp = 15;
	private double currExp = 0;
	
	//ATTACk
	private double timeUntillNextAttack = 100;
	private double currAttackT = timeUntillNextAttack;
	private double attackSpeed = 10;
	
	//MANA
	private double maxMana = 200;
	private double manaRegen = 1.2;
	private double defaultMana = maxMana;
	private double currMana = maxMana;
	
	//HEALTH
	private double maxHealth = 150;
	private double healthRegen = 0.05;
	private double defaultHealth = maxHealth;
	private double currHealth = maxHealth;
	
	//STAMINA
	private double maxStamina = 1500;
	private double staminaRegen = 1.2;
	private double defaultStamina = maxStamina;
	private double currStamina = maxStamina;
	
	//ATTACKSPEED
	//ARMOR
	
	//TODO TODAY!!!
	/*
	 * Add Level for Player
	 * Add EXP System From Killings
	 * 
	 */
	
	
	public PlayerStats() {
	
	}
	
	public void fixRank(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Roland",32,65));
		if(level < 10){
			g.drawString(getLevel()+"", 45, 85);
		}else{
			g.drawString(getLevel()+"", 22, 85);
		}
		g.setFont(new Font("Roland",32,35));
		
		g.drawImage(assets.getExp_bar(), -8, 655, 64*19+64+10,32,null);
	}
	
	public void tickExp(double deltaTime) {
		if(currExp >= maxExp){
			promoteToNextLevel();
			currExp = 0;
			maxExp += 200;
			assets.playSound("level_up.wav");
			GameLoop.parts.add(new Particle((int)570, (int)300 ,50, 1, "Level up!",Color.CYAN, true));
		}
	//	currExp+=200*deltaTime;
	}

	
	public void AttackTimer(Entity e) {
		if(currAttackT != 0){
			currAttackT-=attackSpeed;
		}
		if(currAttackT <= 0)
		{
			e.attacking = false;
			currAttackT = timeUntillNextAttack;
		}
	}
	
	public Economy getEconomy() {
		return eco;
	}

	//RANK
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public void promoteToNextLevel(){
		this.level = level + 1;
	}
	
	public double getCurrExp() {
		return currExp;
	}
	public double getMaxExp() {
		return maxExp;
	}
	public void setExp(double exp) {
		this.currExp = exp;
	}
	public void addExp(double amount){
		if(currExp < getMaxExp()){
			currExp += amount;	
		}else{
			System.out.println("");
		}
	}
	//KILL
	public int getKills() {
		return kills;
	}
	public void addKill(int i) {
		kills+=i;
	}
	
	//DAMAGE
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	//ATTACK
	public double getTimeUntillNextAttack() {
		return timeUntillNextAttack;
	}
	public void setTimeUntillNextAttack(int timeUntillNextAttack) {
		this.timeUntillNextAttack = timeUntillNextAttack;
	}
	public double getCurrAttackT() {
		return currAttackT;
	}
	public void setCurrAttackT(int currAttackT) {
		this.currAttackT = currAttackT;
	}
	
	//SKILL P
	public int getSkillPoints() {
		return skillPoints;
	}
	public void setSkillPoints(int skillPoints) {
		this.skillPoints = skillPoints;
	}
	
	//HEALTH STATS
	public double getDefaultHealth() {
		return defaultHealth;
	}
	public double getMaxHealth() {
		return maxHealth;
	}
	public double getCurrHealth() {
		return currHealth;
	}
	public double getHealthRegen() {
		return healthRegen;
	}
	
	public void setCurrHealth(double currHealth) {
		this.currHealth = currHealth;
	}
	public void setMaxHealth(double maxHealth) {
		this.maxHealth = maxHealth;
	}
	
	public void addHealth(double amount){
		if(currHealth < defaultHealth){
			currHealth = currHealth + amount;
		}
	}
	public void removeHealth(double amount){
		if(currHealth > 0){
			currHealth = currHealth - amount;
		}	
	}
	public void setHealthRegen(double healthRegen) {
		this.healthRegen = healthRegen;
	}
	
	//STAMINA STATS
	public double getDefaultStamina() {
		return defaultStamina;
	}
	public double getMaxStamina() {
		return maxStamina;
	}
	public double getCurrStamina() {
		return currStamina;
	}
	public double getStaminaRegen() {
		return staminaRegen;
	}
		
	public void setCurrStamina(double currStamina) {
		this.currStamina = currStamina;
	}
	public void setMaxStamina(double maxStamina) {
		this.maxStamina = maxStamina;
	}
	
	public void addStamina(double amount){
		if(currStamina < defaultStamina){
			currStamina += amount;
		}
	}
	public void removeStamina(double amount){
		if(currStamina > 0){
			currStamina -= amount;
		}
	}
	public void setStaminaRegen(double staminaRegen) {
		this.staminaRegen = staminaRegen;
	}
	
	//MANA STATS
	public double getDefaultMana() {
		return defaultMana;
	}
	public double getMaxMana() {
		return maxMana;
	}
	public double getCurrMana() {
		return currMana;
	}
	public double getManaRegen() {
		return manaRegen;
	}
	
	public void setCurrMana(double currMana) {
		this.currMana = currMana;
	}
	public void setMaxMana(double maxMana) {
		this.maxMana = maxMana;
	}
	
	public void addMana(double amount){
		if(currMana < defaultMana){
			currMana += amount;
		}
	}
	public void removeMana(double amount){
		if(currMana > 0){
			currMana -= amount;
		}	
	}
	public void setManaRegen(double manaRegen) {
		this.manaRegen = manaRegen;
	}




	
}
