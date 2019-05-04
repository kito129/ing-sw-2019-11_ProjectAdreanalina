package it.polimi.model;

import java.util.ArrayList;

/**
 * The type Weapon card.
 */
public class WeaponCard {

    protected String nameCard;
    protected ArrayList<EnumColorCardAndAmmo> rechargeCost;
    protected EnumColorCardAndAmmo colorCard;
    protected boolean isCharge;

    /**
     * Instantiates a new weapon card.
     * Sets the field is charge to true.
     *
     * @param nameCard the name of card.
     * @param colorCard the color of card
     */
    public WeaponCard(String nameCard, EnumColorCardAndAmmo colorCard){

        this.nameCard=nameCard;
        this.colorCard=colorCard;
        this.isCharge=true;
    }

    /**
     * Gets name card.
     *
     * @return the name card
     * @param i
     */
    public String getNameCard(int i) {

        return nameCard;
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
    public EnumColorCardAndAmmo getColorCard() {

        return colorCard;
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
    
