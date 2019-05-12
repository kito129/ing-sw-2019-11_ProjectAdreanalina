package it.polimi.model;

import java.util.ArrayList;

/**
 * The type Weapon card.
 */
public class WeaponCard {

    private String nameWeaponCard;
    private ArrayList<EnumColorCardAndAmmo> rechargeCost;
    private EnumColorCardAndAmmo colorWeaponCard;
    private boolean isCharge;
    
    /**
     * Instantiates a new weapon card.
     * Sets the field is charge true.
     *
     * @param nameCard the name of card.
     * @param colorCard the color of card
     */
    public WeaponCard(String nameCard, EnumColorCardAndAmmo colorCard){

        this.nameWeaponCard =nameCard;
        this.colorWeaponCard =colorCard;
        this.isCharge=true;
    }

    /**
     * Gets name card.
     *
     * @return the name card
     */
    public String getNameWeaponCard() {

        return nameWeaponCard;
    }

    /**
     * Gets recharge cost.
     *
     * @return the recharge cost.
     */
    public ArrayList<EnumColorCardAndAmmo> getRechargeCost() {

        return rechargeCost;
    }

    /**
     * Gets color Card.
     *
     * @return the color card.
     */
    public EnumColorCardAndAmmo getColorWeaponCard() {

        return colorWeaponCard;
    }

    /**
     * Sets recharge cost.
     *
     * @param rechargeCost the recharge cost we want to set.
     */

    public void setRechargeCost(ArrayList<EnumColorCardAndAmmo> rechargeCost) {

        this.rechargeCost = rechargeCost;
    }

    /**
     * Gets the value of field is charge.
     *
     * @return true is the weapon is charge, false otherwise.
     */
    public boolean isCharge() {

        return isCharge;
    }

    /**
     * Sets the value of field is charge.
     *
     * @param charge true if we want to set the weapon is charge, false otherwise.
     */
    public void setCharge(boolean charge) {

        isCharge = charge;
    }
}
    
