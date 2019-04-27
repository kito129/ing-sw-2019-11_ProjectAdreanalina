package it.polimi.model;

import java.util.ArrayList;

/**
 * The type Square, contains, color row and column and a list contain where the square is linked
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
     * @param r          the row
     * @param c          the ccolumn
     * @param color      the color
     */
    public Square(int r, int c, EnumColorSquare color) {
        this.row=r;
        this.column=c;
        this.color=color;
        this.link= new ArrayList<>();
        this.players= new ArrayList<>();
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
     * Add player
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
    public String toString() {
        return ("("+getRow() +"," +getColumn()+ ","+ isVisited() +")");
    }

}
