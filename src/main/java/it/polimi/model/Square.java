package it.polimi.model;

import java.util.ArrayList;

public class Square{

    protected EnumColorSquare color;
    protected int posX;
    protected int posY;
    protected Square linkN;
    protected Square linkS;
    protected Square linkW;
    protected Square linkE;
    protected ArrayList<Player> players;

    public int getPosY() {
        return posY;
    }

    public int getPosX() {
        return posX;
    }

    public EnumColorSquare getColor() {
        return color;
    }

    public Square getLinkE() {
        return linkE;
    }

    public Square getLinkN() {
        return linkN;
    }

    public Square getLinkS() {
        return linkS;
    }

    public Square getLinkW() {
        return linkW;
    }
}
