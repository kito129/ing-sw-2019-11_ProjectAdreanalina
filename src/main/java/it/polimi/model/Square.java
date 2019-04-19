package it.polimi.model;

import java.util.ArrayList;

public class Square{

    protected EnumColorSquare color;
    protected int row;
    protected int column;
    protected ArrayList<Square> link;
    protected ArrayList<Player> players;
    protected boolean visited;

    public Square(EnumColorSquare color, int r, int c, Square n, Square s, Square e, Square w) {
        this.color=color;
        this.row=r;
        this.column=c;
        link=new ArrayList<>();
        link.add(n);
        link.add(e);
        link.add(s);
        link.add(w);
        this.players=new ArrayList<Player>();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==this){
            return true;
        }
        return false;
    }

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
        return link.get(1);
    }

    public Square getLinkN() {
        return link.get(0);
    }

    public Square getLinkS() {
        return link.get(2);
    }

    public Square getLinkW() {
        return link.get(3);
    }

    public void addPlayer(Player player) {

        players.add(player);
    }
    public void removePlayer (Player player) {

        this.players.remove(player);
    }
    public ArrayList playerOnSquare() {

        return this.players;
    }

}
