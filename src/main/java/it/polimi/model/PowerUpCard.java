package it.polimi.model;

/**
 * The type Power up card.
 */
public class PowerUpCard {

    protected String namePowerUpCard;
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

    @Override
    public String toString(){

        return namePowerUpCard;
    }






}