package it.polimi.model;

public class AmmoCard {

    private int ammoY;
    private int ammoR;
    private int ammoB;
    private PowerUpCard powerUpCard;

    public AmmoCard(int ammoR, int ammoY, int ammoB, PowerUpCard powerUpCard){

        this.ammoY=ammoY;
        this.ammoR=ammoR;
        this.ammoB=ammoB;
        this.powerUpCard=powerUpCard;
    }

    public int getAmmoY() {

        return this.ammoY;
    }

    public int getAmmoR() {

        return this.ammoR;
    }

    public int getAmmoB() {

        return this.ammoB;
    }

    public PowerUpCard getPowerUpCard(){

        return powerUpCard;
    }


}