package it.polimi.model;

/**
 * The type Power up card.
 */
public class PowerUpCard {

    protected String namePowerUpCard;
    protected EnumColorCard colorPowerUpCard;

    /**
     * Instantiates a new Power up card.
     *
     * @param namePowerUpCard the name power up card
     * @param colorCard       the color card
     */
    public PowerUpCard(String namePowerUpCard, EnumColorCard colorCard) {

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
    public EnumColorCard getColorPowerUpCard() {

        return colorPowerUpCard;
    }

    @Override
    public String toString(){

        return namePowerUpCard;
    }






}