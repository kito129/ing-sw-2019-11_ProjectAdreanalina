package it.polimi.model;

import java.util.ArrayList;

public class Square{

    private EnumColorSquare color;
    private int row;
    private int column;
    protected ArrayList<Square> link;
    protected ArrayList<Player> players;
    protected boolean visited;


    public Square(Square square){
        this.row=square.getRow();
        this.column=square.getColumn();
        this.color=square.getColor();
        this.setLink(square.getLink());
    }
    /**
     * DESCRIZIONE DEL METODO
     * @param r //descrivo qui che parametro mi serve
     * @param c
     * @param color
     * @param linkSquare
     * @return // metto cosa mi ritorna
     */
    public Square(int r, int c, EnumColorSquare color,ArrayList<Square> linkSquare) {
        this.row=r;
        this.column=c;
        this.color=color;
        this.link=new ArrayList<Square>();
        this.players=new ArrayList<Player>();
        this.setLink(linkSquare);
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
        return obj == this;
    }

    @Override
    public String toString() {
        return ("("+getRow() +"," +getColumn()+")");
    }

}
