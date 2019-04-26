package it.polimi.model;

/**
 * The type Power up card.
 */
public class PowerUpCard {
    
    /**
     * The Name power up card.
     */
    protected String namePowerUpCard;
    /**
     * The Color card.
     */
    protected EnumColorCard colorCard;
    
    /**
     * Instantiates a new Power up card.
     *
     * @param namePowerUpCard the name power up card
     * @param colorCard       the color card
     */
    public PowerUpCard(String namePowerUpCard, EnumColorCard colorCard) {

        this.namePowerUpCard = namePowerUpCard;
        this.colorCard = colorCard;
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
    public EnumColorCard getColorCard() {

        return colorCard;
    }


}