package main;

public class Health {
int currenthealth;
int maxhealth;
	Health(int maxHealth, int currentHealth){
		maxhealth = maxHealth;
		currenthealth = currentHealth;
	}
	int currentHealth() {
		return(currenthealth);
	}
	int changeCurrentHealth(int change) {
		currenthealth=change+currenthealth;
		if (currenthealth>maxhealth) {
			currenthealth=maxhealth;
		}
		return(currenthealth);
	}
	int maxHealth() {
		return(maxhealth);
	}
	int changeMaxHealth(int change) {
		maxhealth=change+maxhealth;
		return(maxhealth);
	}
	}

