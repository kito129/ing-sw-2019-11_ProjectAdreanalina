package it.polimi.model;

import java.util.ArrayList;

public class WeaponCard {

    protected EnumColorYBR colorCard;
    protected String nameCard;
    protected int costYellow;
    protected int costBlu;
    protected int costRed;
    protected boolean charge;


    public String getNameCard() {
        return nameCard;
    }

    public int getCostBlu() {
        return costBlu;
    }

    public int getCostRed() {
        return costRed;
    }

    public int getCostYellow() {
        return costYellow;
    }

    public EnumColorYBR getColorCard() {
        return colorCard;
    }

    public boolean isCharge() {
        return charge;
    }
}
