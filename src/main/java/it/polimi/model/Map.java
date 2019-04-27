package it.polimi.model;

import java.util.ArrayList;

/**
 * Map of the game, contain an ArrayList of type Square, that represent the gameboard.
 */
public class Map {
    
    private ArrayList<Square> squares;
    
    /**
     * Instantiates a new Map.
     *
     * @param squares list of square to insert in the map
     */
    public Map(ArrayList<Square> squares) {

        ArrayList<Square> temp = new ArrayList<>();
        if (squares != null) {

            for (Square l : squares) {

                if (l != null)
                    temp.add(l);
                System.out.println("aggiungo "+ l.toString());
            }
            this.squares=temp;
        }
        System.out.println("\ncreo la mappa\n");
    }
    
    /**
     * Search square passed row and column, they \represent the grid.
     *
     * @param row    row to search
     * @param colomn column to search
     * @return the square
     */
    public Square getSquare (int row, int colomn) {

        for (Square s : squares) {

            if ((s.getRow() == row) && (s.getColumn() == colomn)) {

                return s;
            }
        }
        return null;
    }
    
    /**
     * Find player in the square, return the square where is located the player.
     *
     * @param player the player
     * @return square where are positioned the player
     */
    public Square findPlayer(Player player) {

        for (Square s : squares) {

            if (s.getPlayers().contains(player)) {

                return s;
            }
        }
        return null;
    }
    
    /**
     * Search and return all player positioned on a square.
     *
     * @param s square where search player
     * @return a list of player in a square
     */
    public ArrayList<Player> playersOnSquare(Square s) {

        for (Square a:squares){

            if(a.equals(s)) {
                return s.getPlayers();
            }
        }
        return null;
    }
    
    /**
     * Calculate the minimum distance from A to B.
     *
     * @param playerA    Current Player
     * @param playerB    PLayer to search distance
 
     * @return minimum distance between A and B
     */
    public int distancePlayer(Player playerA,Player playerB){
        if(playerA.getRow()==playerB.getRow() && playerA.getColumn()==playerB.getColumn()) {
            return 0;
        }
        
        return distance(playerA.getRow(),playerA.getColumn(),playerB.getRow(),playerB.getColumn(),1);
    }
    
    /**
     * Calculate the minimum distance from A to B.
     *
     * @param a    Current Square
     * @param b    Sqaure to search
     * @return minimum distance between A and B
     */
    public int distance(Square a, Square b, int dist){
        if(a.getRow()==b.getRow() && a.getColumn()==b.getColumn()) {
            return 0;
        }else
            return distance(a.getRow(),a.getColumn(),b.getRow(),b.getColumn(),dist);
        }

    /**
     * Calculate the minimum distance from A(rInit,cInit) to B(rDest,cDest).
     *
     * @param rInit row of current square
     * @param cInit column of current square
     * @param rDest row of search sqaure
     * @param cDest column of search square
     * @param dist  the distance at the iteration
     * @return minimum distance from A(rInit,cInit) to B(rDest,cDest)
     */
    public int distance(int rInit, int cInit, int rDest, int cDest, int dist) {
        
        int distance = dist;
        int currR = rInit;
        int curC = cInit;
        int tempPath=0;
        ArrayList<Integer> path =new ArrayList<>();

        Square curSquare = getSquare(currR, curC);
       // System.out.println("curr square : " + curSquare.toString());
        Square destSquare = getSquare(rDest, cDest);
        //System.out.println("search square : " + destSquare.toString());
        ArrayList<Square> link = curSquare.getLink();
        
        for (Square a : link) {
    
            if(a!=null) {
                if (a.getColumn() == cDest && a.getRow() == rDest) {
    
                    path.add(distance);
                    //System.out.println("trovato, dist: " + distance);
                    curSquare.setVisited(false);
                    return calculateMinPath(path);
                }
            }
        }
        for (Square b : link) {
           // System.out.println("chiama: "+curSquare.toString());

            if (b != null && !b.isVisited()) {

                //System.out.println("iter su: " + b.toString());
                b.setVisited(true);
                int tempDist = distance + 1;
                tempPath = distance(b,destSquare, tempDist);
                path.add(tempPath);
                b.setVisited(false);
               // System.out.println("calculated: " + tempPath);
            }
        }
        //curSquare.setVisited(false);
        return calculateMinPath(path);
        
    }
    
    /**
     * Refresh map, set all visited attribute to false.
     */
    public void refreshMap(){

        for (Square a:squares){

            a.setVisited(false);
        }
    }
    /**
     * Calculate the min Integer in the Path Array.
     *
     * @param path array contain the distances.
     * @return the min in the array
     */
    private int calculateMinPath(ArrayList<Integer> path){

        int min=100;
        for (Integer a:path){

            if (a <min){
                min= a;
            }
        }
        return min;
    }
    
    /**
     * Return true if Player passed can see the Square passed.
     *
     * @param player    player to search
     * @param colorSquare the color of the Romm to search
     * @return true if player can see room of the color passed
     */
    public boolean isVisibleRoom(Player player, EnumColorSquare colorSquare) {

        if(this.findPlayer(player).getColor()==colorSquare){

            return true;
        } else {

            for (Square a:this.findPlayer(player).getLink()){

                if(a.getColor()==colorSquare){

                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Calculate if A see B.
     *
     * @param a current square
     * @param b search square
     * @return true if A see B
     */
    public boolean isVisible(Square a, Square b) {

        return isVisible(a.getRow(),a.getColumn(),b.getRow(),b.getColumn());
    }
    
    
    /**
     * Calculate if  A(c0,r0) see B(c1,r1).
     *
     * @param r0 row of current square
     * @param c0 column of current square
     * @param r1 row of search square
     * @param c1 column of search square
     * @return true if A(c0,r0) see B(c1,r1)
     */
    public boolean isVisible(int r0, int c0,int r1, int c1) {

        Square currSquare= getSquare(r0,c0);
        Square destSquare= getSquare(r1,c1);
        if(currSquare.getColor()==destSquare.getColor()){

            return true;
        }else {

            for (Square a:currSquare.getLink()){

                if(a.getColor()==destSquare.getColor()) return true;
            }
        }
        return false;
    }
    
    /**
     * Move player to Square.
     *
     * @param player the player to move
     * @param square the square where to move
     */
    public void movePlayer(Player player, Square square) {
  
    this.findPlayer(player).getPlayers().remove(player);
    square.addPlayer(player);
}
    
    /**
     * Add player on square.
     *
     * @param square the square where add
     * @param player the player to add
     */
    public void addPlayerOnSquare(Square square, Player player) {

        getSquare(square.getRow(),square.getColumn()).addPlayer(player);
    }
    
    /**
     * Remove player from square.
     *
     * @param square the square where remove
     * @param player the player to remove
     */
    public void removePlayerFromSquare(Square square, Player player) {

        getSquare(square.getRow(),square.getColumn()).removePlayer(player);
    }
    
    /**
     * Calculate if A is in the same cardinal direction of B.
     *
     * @param a Square A
     * @param b Suare B
     * @return true if A is in the same cardinal direction of B
     */
    public boolean sameDirection(Square a, Square b){

        return a.getRow() == b.getRow() || a.getColumn() == b.getColumn();
    }
    
    /**
     * Calculate if Player A is in the same Square of the Player B.
     *
     * @param actualPlayer the actual player
     * @param otherPlayer  the other player
     * @return tru if if Player A is in the same Square of the Player B
     */
    public boolean isInMySquare(Player actualPlayer,Player otherPlayer){

        return this.playersOnSquare(this.getSquare(actualPlayer.getRow(), actualPlayer.getColumn())).contains(otherPlayer);
    }
    
    /**
     * calculate the player in one room.
     *
     * @param colorSquare the color square of the Room
     * @return list of the player in the Room
     */
    public ArrayList<Player> playerInRoom(EnumColorSquare colorSquare){

        ArrayList<Player> tempPlayer =new ArrayList<>();
        for (Square a: this.squares){

            if(a.getColor()==colorSquare){

                tempPlayer.addAll(a.getPlayers());
            }
        } return tempPlayer;
    }
    
    /**
     * Player on my row array list.
     *
     * @param player the player
     * @return the array list
     */
    public ArrayList<Player> playerOnMyRow(Player player){

        ArrayList<Player> tempPlayer =new ArrayList<>();
        for (Square a: this.squares){

            if(a.getRow()==player.getRow()){

                tempPlayer.addAll(a.getPlayers());
            }
        } return tempPlayer;
    }
    
    /**
     * Player on my colunm array list.
     *
     * @param player the player
     * @return the array list
     */
    public ArrayList<Player> playerOnMyColunm(Player player){

        ArrayList<Player> tempPlayer =new ArrayList<>();
        for (Square a: this.squares){

            if(a.getColumn()==player.getColumn()){

                tempPlayer.addAll(a.getPlayers());
            }
        } return tempPlayer;
    }
    
}
