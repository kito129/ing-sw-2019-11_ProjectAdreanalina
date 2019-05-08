package it.polimi.model;

import java.util.ArrayList;

/**
 * The type Ammo card.
 */
public class AmmoCard {

    private ArrayList<EnumColorCardAndAmmo> ammo;
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

        for(int i=0; i<ammoR;i++){

            this.ammo.add(EnumColorCardAndAmmo.RED);
        }
        for(int i=0; i<ammoY;i++){

            this.ammo.add(EnumColorCardAndAmmo.YELLOW);
        }
        for(int i=0; i<ammoB;i++){

            this.ammo.add(EnumColorCardAndAmmo.BLU);
        }
        this.powerUpCard=powerUpCard;
    }

    /**
     * Gets ammo.
     *
     * @return the ammo.
     */

    public ArrayList<EnumColorCardAndAmmo> getAmmo() {

        return ammo;
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