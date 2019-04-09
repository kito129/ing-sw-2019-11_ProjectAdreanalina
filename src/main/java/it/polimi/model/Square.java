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

    public void setColor(EnumColorSquare color) {
        this.color = color;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void setLinkE(Square linkE) {
        this.linkE = linkE;
    }

    public void setLinkN(Square linkN) {
        this.linkN = linkN;
    }

    public void setLinkS(Square linkS) {
        this.linkS = linkS;
    }

    public void setLinkW(Square linkW) {
        this.linkW = linkW;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }
}
