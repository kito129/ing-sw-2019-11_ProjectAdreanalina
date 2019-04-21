package it.polimi.model;

import java.util.ArrayList;

public class Square{

    protected EnumColorSquare color;
    protected int row;
    protected int column;
    protected ArrayList<Square> link;
    protected ArrayList<Player> players;
    protected boolean visited;

    public Square(int r, int c, EnumColorSquare color,ArrayList<Square> link) {
        this.row=r;
        this.column=c;
        this.color=color;
        link=new ArrayList<>();
        setLink(link);
        this.visited=false;
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

    public ArrayList<Square> getLink(){

        return link;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setLink(ArrayList<Square>link) {
        ArrayList<Square> temp = new ArrayList<>();
        if (link != null) {
            for (Square l : link) {
                if (l != null)
                    temp.add(l);
            }
            this.link=temp;
        }
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
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

    @Override
    public boolean equals(Object obj) {
        if(obj==this){
            //TODO vedere eqauls
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return ("("+getRow() +"," +getColumn()+")");
    }

}
