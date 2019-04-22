package it.polimi.model;

public class AmmoCard extends Ammo {

    private Ammo ammo;
    private PowerUpCard powerUpCard;


    public AmmoCard(Ammo ammo, PowerUpCard powerUpCard){
        super(ammo);
        this.powerUpCard=powerUpCard;
    }

    public Ammo getAmmo() {

        return this.ammo;
    }


    public PowerUpCard getPowerUpCard() {
        return powerUpCard;
    }
}
