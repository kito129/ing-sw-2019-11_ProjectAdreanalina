package it.polimi.model;

import java.util.ArrayList;

public class Player {
    //TODO
    private int id;
    private String  name;
    private ColorCard color;
    private String tokens;
    private int posX;
    private int posY;
    private ArrayList<Army> playerArmy;
    private ArrayList<Expansion> playerExpansion;
    private PlayerBoard playerBoard;
    private int finalScore;
    private boolean online;

    public Player(){

    }
    public Player(int id, String name, ColorCard color){
        this.id=id;
        this.name=name;
        this.color=color;
        this.posX=0;
        this.posY=0;
        this.finalScore=0;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ColorCard getColor() {
        return color;
    }

    public ArrayList<Army> getPlayerArmy() {
        return playerArmy;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public String getTokens() {
        return tokens;
    }

    public int getFinalScore() {
        return finalScore;
    }

    public PlayerBoard getPlayerBoard() {
        return playerBoard;
    }

    public ArrayList<Expansion> getPlayerExpansion() {
        return playerExpansion;
    }
}
