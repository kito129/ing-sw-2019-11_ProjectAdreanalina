package it.polimi.model;

public class AmmoCard {

    private int ammoR;
    private int ammoY;
    private int ammoB;
    private PowerUpCard powerUpCard;

    public AmmoCard(int ammoR, int ammoY, int ammoB, PowerUpCard powerUpCard){

        /*  TODO
        guardate factory
         */


    }

    public int getAmmoY() {
        return ammoY;
    }

    public int getAmmoB() {
        return ammoB;
    }

    public int getAmmoR() {
        return ammoR;
    }

    public PowerUpCard getPowerUpCard() {
        return powerUpCard;
    }
}
