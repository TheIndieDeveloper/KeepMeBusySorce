package com.main.kmb.game;

public class PlayerStats {

	Economy eco = new Economy();
	
	private int kills = 0;
	private int skillPoints = 0;
	
	//MANA
	private double maxMana = 200;
	private double manaRegen = 1.2;
	private double defaultMana = maxMana;
	private double currMana = maxMana;
	
	//HEALTH
	private double maxHealth = 150;
	private double healthRegen = 1.2;
	private double defaultHealth = maxHealth;
	private double currHealth = maxHealth;
	
	//STAMINA
	private double maxStamina = 1500;
	private double staminaRegen = 1.2;
	private double defaultStamina = maxStamina;
	private double currStamina = maxStamina;
	
	//ATTACKSPEED
	//ARMOR
	
	
	public PlayerStats() {
	
	}

	public Economy getEconomy() {
		return eco;
	}

	
	
	
	//KILL
	public int getKills() {
		return kills;
	}
	public void addKill(int i) {
		kills+=i;
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
