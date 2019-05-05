package it.polimi.model;

/**
 * The type Power up card.
 */
public class PowerUpCard {

    protected String namePowerUpCard;
    protected EnumColorCardAndAmmo colorPowerUpCard;

    /**
     * Instantiates a new Power up card.
     *
     * @param namePowerUpCard the name of power up card.
     * @param colorCard       the card's color.
     */
    public PowerUpCard(String namePowerUpCard, EnumColorCardAndAmmo colorCard) {

        this.namePowerUpCard = namePowerUpCard;
        this.colorPowerUpCard = colorCard;
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

    @Override
    public String toString(){

        return namePowerUpCard;
    }






}