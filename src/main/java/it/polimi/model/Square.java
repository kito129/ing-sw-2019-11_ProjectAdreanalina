package it.polimi.model;

import java.util.ArrayList;

public class Square{

    private EnumColorSquare color;
    private int row;
    private int column;
    protected ArrayList<Square> link;
    protected ArrayList<Player> players;
    protected boolean visited;

    /**
     * construct a Square
     * @param square a square where copy the information
     */
    public Square(Square square){
        this.row=square.getRow();
        this.column=square.getColumn();
        this.color=square.getColor();
        this.setLink(square.getLink());
    }

    /**
     * construct a Square
     * @param r  row of the Square
     * @param c columns of the Square
     * @param color color od the Sqaure
     * @param linkSquare Square linked to this square
     */
    public Square(int r, int c, EnumColorSquare color,ArrayList<Square> linkSquare) {
        this.row=r;
        this.column=c;
        this.color=color;
        this.link= new ArrayList<>();
        this.players= new ArrayList<>();
        this.setLink(linkSquare);
        this.visited=false;
    }

    /**
     * get the column of the Square
     * @return column of the square
     */
    public int getColumn() {
        return column;
    }

    /**
     * get the row of the Square
     * @return row of the square
     */
    public int getRow() {
        return row;
    }

    /**
     * get the color of the Square
     * @return color of the square
     */
    public EnumColorSquare getColor() {
        return color;
    }

    /**
     * get the link of the Square
     * @return link of the square
     */
    public ArrayList<Square> getLink(){

        return link;
    }

    /**
     * get if the square it was visited by distance algorithm
     * @return true if attribute visited is flagged
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     * set the link to new square
     * @param link link to set to the Square
     */
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

    /**
     * set if the square is visited by the distance algorithm
     * @param visited
     * @return true if attribute visited is flagged
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    /**
     * add the player on the square
     * @param player player to add in this square
     */
    public void addPlayer(Player player) {

        players.add(player);
    }

    /**
     * remove the player on the square
     * @param player player to remove in this square
     */
    public void removePlayer (Player player) {

        this.players.remove(player);
    }

    /**
     * return  the player on the square
     * @return the player positioned on this square
     */
    public ArrayList<Player> playerOnSquare() {

        return this.players;
    }

    /**
     * overriding the equals method
     * @return true if obj equals to this
     */
    @Override
    public boolean equals(Object obj) {
        return obj == this;
    }

    /**
     * overriding the equals toString
     * @return a string with info about Square
     */
    @Override
    public String toString() {
        return ("("+getRow() +"," +getColumn()+")");
    }

}
