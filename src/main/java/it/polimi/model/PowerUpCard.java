package it.polimi.model;

public class PowerUpCard {

    protected String namePowerUpCard;
    protected EnumColorCard colorCard;

    public PowerUpCard(String namePowerUpCard, EnumColorCard colorCard) {

        this.namePowerUpCard = namePowerUpCard;
        this.colorCard = colorCard;
    }

    public String getNameCard() {

        return namePowerUpCard;
    }

    public EnumColorCard getColorCard() {

        return colorCard;
    }


}