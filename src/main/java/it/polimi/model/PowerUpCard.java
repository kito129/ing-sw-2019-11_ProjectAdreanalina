package it.polimi.model;

public abstract class PowerUpCard {

    protected EnumColorYBR colorCard;
    protected String nameCard;

    public PowerUpCard(EnumColorYBR colorCard, String nameCard){
        this.colorCard=colorCard;
        this.nameCard=nameCard;
    }

    public EnumColorYBR getColorCard() {
        return colorCard;
    }

    public String getNameCard() {
        return nameCard;
    }

    public void effect(){


    }
}
