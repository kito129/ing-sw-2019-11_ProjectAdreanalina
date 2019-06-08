package it.polimi.model;

import com.sun.org.apache.regexp.internal.RE;

/**
 * The type Power up card.
 */
public class PowerUpCard {
    

    private String namePowerUpCard;
    private EnumColorCardAndAmmo colorPowerUpCard;
    private EnumColorSquare colorRespawn;
    private String description;
    
    /**
     * Instantiates a new Power up card.
     *
     * @param namePowerUpCard the name of power up card.
     * @param colorCard       the card's color.
     */
    public PowerUpCard(String namePowerUpCard, EnumColorCardAndAmmo colorCard) {

        this.namePowerUpCard = namePowerUpCard;
        this.colorPowerUpCard = colorCard;
        switch (colorCard){
            case BLU:
                colorRespawn = EnumColorSquare.BLU;
                break;
            case RED:
                colorRespawn = EnumColorSquare.RED;
                break;
            case YELLOW:
                colorRespawn = EnumColorSquare.YELLOW;
                break;
        }
    }
    
    /**
     * Gets description card.
     *
     * @return the description card.
     */
    public String getDescription() {

        return description;
    }
    
    /**
     * Gets name card.
     *
     * @return the name card
     */
    public String getNameCard() {

        return namePowerUpCard;
    }
    
    /**
     * Gets color card.
     *
     * @return the color card
     */
    public EnumColorCardAndAmmo getColorPowerUpCard() {

        return colorPowerUpCard;
    }
    
    public EnumColorSquare getColorRespawn () {
        
        return colorRespawn;
    }
    
    /**
     * Sets description.
     *
     * @param description the description of the card.
     */

    protected void setDescription(String description) {

        this.description = description;
    }

    public String toString(){

        String color="";

        if (this.colorPowerUpCard==EnumColorCardAndAmmo.YELLOW){

            color="Yellow";
        }
        if (this.colorPowerUpCard==EnumColorCardAndAmmo.RED){

            color="Red";
        }
        if (this.colorPowerUpCard==EnumColorCardAndAmmo.BLU){

            color="Blu";
        }

        return this.namePowerUpCard+" "+color;
    }
}