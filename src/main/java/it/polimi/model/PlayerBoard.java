package it.polimi.model;

import java.util.ArrayList;

public class PlayerBoard {

    private EnumColorPlayer color;
    private int ammoY;
    private int ammoR;
    private int ammoB;
    private ArrayList<EnumColorPlayer> damage;
    private ArrayList<EnumColorPlayer> mark;
    private int boardPoint;
    private boolean firstPlayer;

    public PlayerBoard(){
        //TODO
    }



    public EnumColorPlayer getColor() {
        return color;
    }

    public int getAmmoR() {
        return ammoR;
    }

    public int getAmmoB() {
        return ammoB;
    }

    public int getAmmoY() {
        return ammoY;
    }

    public ArrayList<EnumColorPlayer> getDamage() {
        return damage;
    }

    public ArrayList<EnumColorPlayer> getMark() {
        return mark;
    }

    public int getBoardPoint() {
        return boardPoint;
    }

}
