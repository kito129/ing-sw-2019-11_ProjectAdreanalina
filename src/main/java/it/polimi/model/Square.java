package it.polimi.model;

import java.util.ArrayList;

/**
 * The type Square.
 */
public class Square{

    private EnumColorSquare color;
    private int row;
    private int column;
    private ArrayList<Square> link;
    private ArrayList<Player> players;
    private boolean visited;
    
    /**
     * Instantiates a new Square.
     *
     * @param square the square
     */
    public Square(Square square){
        this.row=square.getRow();
        this.column=square.getColumn();
        this.color=square.getColor();
        this.setLink(square.getLink());
    }
    
    /**
     * Instantiates a new Square.
     *
     * @param r          the r
     * @param c          the c
     * @param color      the color
     * @param linkSquare the link square
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
     * Gets column.
     *
     * @return the column
     */
    public int getColumn() {
        return column;
    }
    
    /**
     * Gets row.
     *
     * @return the row
     */
    public int getRow() {
        return row;
    }
    
    /**
     * Gets color.
     *
     * @return the color
     */
    public EnumColorSquare getColor() {
        return color;
    }
    
    /**
     * Get link array list.
     *
     * @return the array list
     */
    public ArrayList<Square> getLink(){

        return link;
    }
    
    /**
     * Gets players.
     *
     * @return the players
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }
    
    /**
     * Is visited boolean.
     *
     * @return the boolean
     */
    public boolean isVisited() {
        return visited;
    }
    
    /**
     * Sets link.
     *
     * @param link the link
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
     * Sets visited.
     *
     * @param visited the visited
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }
    
    /**
     * Add player.
     *
     * @param player the player
     */
    public void addPlayer(Player player) {

        players.add(player);
    }
    
    /**
     * Remove player.
     *
     * @param player the player
     */
    public void removePlayer (Player player) {

        this.players.remove(player);
    }
    
    /**
     * Player on square array list.
     *
     * @return the array list
     */
    public ArrayList<Player> playerOnSquare() {

        return this.players;
    }

    @Override
    public boolean equals(Object obj) {
        
        return obj == this;
    }

    @Override
    public String toString() {
        return ("("+getRow() +"," +getColumn()+ ","+ isVisited() +")");
    }

}
