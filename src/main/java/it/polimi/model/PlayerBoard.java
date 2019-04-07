package it.polimi.model;

import java.util.ArrayList;

public class PlayerBoard {
    private ColorCard color;
    private ArrayList<ColorCard> life;
    private ArrayList<ColorCard> mark;
    private ArrayList<ColorAmmo> playerAmmo;

    public PlayerBoard(ColorCard color){
        this.color=color;
        playerAmmo.add(ColorAmmo.R);
        playerAmmo.add(ColorAmmo.B);
        playerAmmo.add(ColorAmmo.Y);
    }

    public ArrayList<ColorCard> getLife() {
        return life;
    }

    public ArrayList<ColorAmmo> getPlayerAmmo() {
        return playerAmmo;
    }

    public ArrayList<ColorCard> getMark() {
        return mark;
    }

    public ColorCard getColor() {
        return color;
    }
}
