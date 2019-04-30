package it.polimi.model;

/**
 * The type Ammo card.
 */
public class AmmoCard {

    private int ammoY;
    private int ammoR;
    private int ammoB;
    private PowerUpCard powerUpCard;
    
    /**
     * Instantiates a new Ammo card.
     *
     * @param ammoR       the number of red ammo.
     * @param ammoY       the number of yellow ammo.
     * @param ammoB       the number of blu ammo.
     * @param powerUpCard the power up card contained in ammo card.
     */
    public AmmoCard(int ammoR, int ammoY, int ammoB, PowerUpCard powerUpCard){

        this.ammoY=ammoY;
        this.ammoR=ammoR;
        this.ammoB=ammoB;
        this.powerUpCard=powerUpCard;
    }
    
    /**
     * Gets ammo y.
     *
     * @return the ammo y
     */
    public int getAmmoY() {

        return this.ammoY;
    }
    
    /**
     * Gets ammo r.
     *
     * @return the ammo r
     */
    public int getAmmoR() {

        return this.ammoR;
    }
    
    /**
     * Gets ammo b.
     *
     * @return the ammo b
     */
    public int getAmmoB() {

        return this.ammoB;
    }
    
    /**
     * Gets power up card.
     *
     * @return the power up card
     */
    public PowerUpCard getPowerUpCard(){

        return powerUpCard;
    }




}