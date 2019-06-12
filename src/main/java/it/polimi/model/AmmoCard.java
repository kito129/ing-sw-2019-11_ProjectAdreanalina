package it.polimi.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The type Ammo card.
 */
public class AmmoCard implements Serializable {

    private ArrayList<EnumColorCardAndAmmo> ammo;
    private boolean hasPowerUpCard;
    private PowerUpCard powerUpCard;
    
    /**
     * Instantiates a new Ammo card.
     *
     * @param ammoR       the number of red ammo.
     * @param ammoY       the number of yellow ammo.
     * @param ammoB       the number of blu ammo.
     * @param powerUpCard the power up card contained in ammo card.
     */
    public AmmoCard(int ammoR, int ammoY, int ammoB, boolean powerUpCard){

        this.ammo=new ArrayList<EnumColorCardAndAmmo>();

        for(int i=0; i<ammoR;i++){

            this.ammo.add(EnumColorCardAndAmmo.RED);
        }
        for(int i=0; i<ammoY;i++){

            this.ammo.add(EnumColorCardAndAmmo.YELLOW);
        }
        for(int i=0; i<ammoB;i++){

            this.ammo.add(EnumColorCardAndAmmo.BLU);
        }
        this.hasPowerUpCard=powerUpCard;
        this.powerUpCard=null;
    }

    /**
     * Gets ammo.
     *
     * @return the ammo.
     */

    public ArrayList<EnumColorCardAndAmmo> getAmmo() {

        return ammo;
    }

    public PowerUpCard getPowerUpCard() {

        return powerUpCard;
    }

    /**
     * Gets powerUpCard.
     *
     * @return true if ammoCard contains a powerUpCard,false otherwise.
     */

    public boolean hasPowerUpCard() {

        return hasPowerUpCard;
    }

    public void setPowerUpCard(PowerUpCard powerUpCard) {

        this.powerUpCard = powerUpCard;
    }
    
    @Override
    public String toString () {
        String message = new String();
       
        message+=this.ammo.toString();
        if(hasPowerUpCard){
            message+=this.getPowerUpCard();
        }
        return message;
    }
}