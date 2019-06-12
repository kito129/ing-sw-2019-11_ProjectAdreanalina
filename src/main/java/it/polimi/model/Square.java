package it.polimi.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The type Square, contains, color row and column and a list contain where the square is linked
 */
public class Square implements Serializable {

    private EnumColorSquare color;
    private int row;
    private int column;
    private ArrayList<Square> link;
    private ArrayList<Player> players;
    private boolean visited;
    
    /**
     * Instantiates a new Square.
     *
     * @param r     the row of the square
     * @param c     the column of the square
     * @param color the color of the square
     */
    public Square(int r, int c, EnumColorSquare color) {
        this.row=r;
        this.column=c;
        this.color=color;
        this.link= new ArrayList<>();
        this.players= new ArrayList<>();
        this.visited=false;
    }

    public Square(){

        //todo da eliminare serve per compilare
    }
    
    /**
     * Gets column of the square.
     *
     * @return the column of the square
     */
    public int getColumn() {
        
        return column;
    }
    
    /**
     * Gets row of the square.
     *
     * @return the row of the square
     */
    public int getRow() {
        
        return row;
    }
    
    /**
     * Gets color of the square.
     *
     * @return the color of th square
     */
    public EnumColorSquare getColor() {
        
        return color;
    }
    
    /**
     * Get link of the square in array list.
     *
     * @return the array list contain linked square
     */
    public ArrayList<Square> getLink(){

        return link;
    }
    
    /**
     * Gets players on this sqaure.
     *
     * @return the players
     */
    public ArrayList<Player> getPlayers() {
        
        return players;
    }
    
    /**
     * Is visited for distance mehtod .
     *
     * @return return true if visited
     */
    public boolean isVisited() {
        
        return visited;
    }
    
    /**
     * Sets link of the square.
     *
     * @param link link of th square in ArrayList
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
     * Sets visited for distance method.
     *
     * @param visited  visited param
     */
    public void setVisited(boolean visited) {
        
        this.visited = visited;
    }
    
    /**
     * Add player on the square
     *
     * @param player the player to add
     */
    public void addPlayer(Player player) {
        
        player.setPosition(this.row,this.column);
        players.add(player);
    }
    
    /**
     * Remove player from the square.
     *
     * @param player the player to remove
     */
    public void removePlayer (Player player) {
        
        player.setPosition(-1,-1);
        this.players.remove(player);
    }
    

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Square)) {
            return false;
        }
        Square sq = (Square) o;
        return sq.row == this.row && sq.column==this.column;
    }
    
    @Override
    public int hashCode () {
        
        return super.hashCode();
    }
    
    @Override
    public String toString() {
        return ("("+getRow() +"," +getColumn()+ ","+ getColor().toString() + getPlayers().toString() +")");
    }

}
