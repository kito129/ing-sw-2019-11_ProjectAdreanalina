package it.polimi.model;

public class AmmoCard {
    private int ammoR;
    private int ammoY;
    private int ammoB;
    private PowerUpCard powerUpCard;


    public int getAmmoY() {
        return ammoY;
    }

    public int getAmmoB() {
        return ammoB;
    }

    public int getAmmoR() {
        return ammoR;
    }
    
    public void setAmmoB(int ammoB) {
        this.ammoB = ammoB;
    }

    public void setAmmoR(int ammoR) {
        this.ammoR = ammoR;
    }

    public void setAmmoY(int ammoY) {
        this.ammoY = ammoY;
    }

    public PowerUpCard getPowerUpCard() {
        return powerUpCard;
    }
}
