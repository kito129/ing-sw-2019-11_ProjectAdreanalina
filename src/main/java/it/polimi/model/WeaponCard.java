package it.polimi.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The type Weapon card.
 */
public class WeaponCard implements Serializable {

    private String nameWeaponCard;
    private ArrayList<EnumColorCardAndAmmo> rechargeCost;
    private EnumColorCardAndAmmo colorWeaponCard;
    private ArrayList<WeaponsEffect> weaponEffects;
    private boolean isCharge;
    private String description;
    private boolean isOptional;
    
    /**
     * Instantiates a new weapon card.
     *
     * @param nameCard the name of card.
     * @param colorCard the color of card.
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
     * Gets weapon effects.
     *
     * @return the weapon effects.
     */

    public ArrayList<WeaponsEffect> getWeaponEffects() {

        return weaponEffects;
    }

    /**
     * Gets description
     *
     * @return the description of card.
     */

    public String getDescription() {

        return description;
    }

    /**
     * Gets the field is optional.
     *
     * @return true if is a optional weapon.
     */
    
    public boolean isOptional () {
        
        return isOptional;
    }

    /**
     * Sets the field is optional.
     *
     * @param optional true if is a optional weapon
     */
    
    protected void setOptional (boolean optional) {
        
        isOptional = optional;
    }
    
    /**
     * Sets recharge cost.
     *
     * @param rechargeCost the recharge cost we want to set.
     */

    protected void setRechargeCost(ArrayList<EnumColorCardAndAmmo> rechargeCost) {

        this.rechargeCost = rechargeCost;
    }

    /**
     * Sets weapon effects.
     *
     * @param weaponEffects the effects we want to set.
     */

    protected void setWeaponEffects(ArrayList<WeaponsEffect> weaponEffects){

        this.weaponEffects=weaponEffects;
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
     * Sets the field description
     *
     * @param description the description of the card.
     */

    protected void setDescription(String description) {

        this.description = description;
    }

    /**
     * Sets the value of field is charge.
     *
     * @param charge true if we want to set the weapon is charge, false otherwise.
     */
    public void setCharge(boolean charge) {

        isCharge = charge;
    }

    public String toString(){

        return this.nameWeaponCard + this.isCharge;
    }
}
    
