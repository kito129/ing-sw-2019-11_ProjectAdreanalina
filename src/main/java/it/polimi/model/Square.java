package it.polimi.model;

import java.util.ArrayList;

public class Square{

    protected EnumColorSquare color;
    protected int row;
    protected int column;
    protected Square linkN;
    protected Square linkS;
    protected Square linkW;
    protected Square linkE;
    protected ArrayList<Player> players;

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
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

    public void addPlayer(Player player){

        // TODO
    }
    public void removePlayer(Player player){


        // TODO
    }



}
