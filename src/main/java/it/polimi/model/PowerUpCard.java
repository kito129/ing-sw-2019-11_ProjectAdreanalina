package it.polimi.model;

/**
 * The type Power up card.
 */
public  class PowerUpCard {
    
    /**
     * The Name power up card.
     */
    protected String namePowerUpCard;
    /**
     * The Color power up card.
     */
    protected EnumColorCardAndAmmo colorPowerUpCard;
    /**
     * The Description.
     */
    protected String description;
    
    
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
    

    @Override
    public String toString(){

        return namePowerUpCard;
    }






}