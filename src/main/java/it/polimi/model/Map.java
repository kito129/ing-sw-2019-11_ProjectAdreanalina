package it.polimi.model;

import java.util.ArrayList;

/**
 * The type Map.
 */
public class Map {

    private ArrayList<Square> squares;
    
    /**
     * Instantiates a new Map.
     *
     * @param squares the squares
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
     * Search square square.
     *
     * @param row    the row
     * @param colomn the colomn
     * @return the square
     */
    public Square searchSquare (int row, int colomn) {

        for (Square s : squares) {

            if ((s.getRow() == row) && (s.getColumn() == colomn)) {

                return s;
            }
        }
        return null;
    }
    
    /**
     * Find player square.
     *
     * @param player the player
     * @return the square
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
     * Players on square array list.
     *
     * @param s the s
     * @return the array list
     */
    public ArrayList playersOnSquare(Square s) {

        for (Square a:squares){

            a.equals(s);
            return s.getPlayers();
        }
        return null;
    }
    
    /**
     * Distance int.
     *
     * @param a the a
     * @param b the b
     * @return the int
     */
    public int distance(Square a, Square b){
    
    
                return distance(a.getRow(),a.getColumn(),b.getRow(),b.getColumn(),1);
            }
    
    /**
     * Distance int.
     *
     * @param rInit the r init
     * @param cInit the c init
     * @param rDest the r dest
     * @param cDest the c dest
     * @param dist  the dist
     * @return the int
     */
    public int distance(int rInit, int cInit, int rDest, int cDest, int dist) {
        
                int distance = dist;
                int currR = rInit;
                int curC = cInit;
                int tempPath=0;
                ArrayList<Integer> path =new ArrayList<>();
        
                Square curSquare = searchSquare(currR, curC);
                System.out.println("curr square : " + curSquare.toString());
                Square destSquare = searchSquare(rDest, cDest);
                System.out.println("search square : " + destSquare.toString());
                ArrayList<Square> link = curSquare.getLink();
                for (Square a : link) {
            
                    if(a!=null && a.getColumn()== cDest && a.getRow()==rDest){
                path.add(distance);
                System.out.println("trovato, dist: "+distance);
                return calculateMinPath(path);
            }
        }
        curSquare.setVisited(true);
        for (Square b : link) {

            if (b != null && !b.isVisited()) {

                System.out.println("iter su: " + b.toString());
                int tempDist = distance + 1;
                tempPath = distance(b.getRow(), b.getColumn(), rDest, cDest, tempDist);
                path.add(tempPath);
            }
        }
        return calculateMinPath(path);
    }
    
    /**
     * Refresh map.
     */
    public void refreshMap(){

        for (Square a:squares){

            a.setVisited(false);
        }
    }

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
     * Is visible square boolean.
     *
     * @param player      the player
     * @param colorSquare the color square
     * @return the boolean
     */
    public boolean isVisibleSquare(Player player, EnumColorSquare colorSquare) {

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
     * Is visible boolean.
     *
     * @param a the a
     * @param b the b
     * @return the boolean
     */
    public boolean isVisible(Square a, Square b) {

        return isVisible(a.getRow(),a.getColumn(),b.getRow(),b.getColumn());
    }
    
    
    /**
     * Is visible boolean.
     *
     * @param r0 the r 0
     * @param c0 the c 0
     * @param r1 the r 1
     * @param c1 the c 1
     * @return the boolean
     */
    public boolean isVisible(int r0, int c0,int r1, int c1) {

        Square currSquare=searchSquare(r0,c0);
        Square destSquare=searchSquare(r1,c1);
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
     * Move player.
     *
     * @param player the player
     * @param square the square
     */
    public void movePlayer(Player player, Square square) {

        if(this.distance(this.findPlayer(player).getRow(),this.findPlayer(player).getColumn(),square.getRow(),square.getColumn(),1)<=3){

            findPlayer(player).removePlayer(player);
            square.addPlayer(player);
            player.setPosition(square.getRow(),square.getColumn());
        }
    }
    
    /**
     * Add player on square.
     *
     * @param square the square
     * @param player the player
     */
    public void addPlayerOnSquare(Square square, Player player) {

        searchSquare(square.getRow(),square.getColumn()).addPlayer(player);
    }
    
    /**
     * Remove player from square.
     *
     * @param square the square
     * @param player the player
     */
    public void removePlayerFromSquare(Square square, Player player) {

        searchSquare(square.getRow(),square.getColumn()).removePlayer(player);
    }
    
    /**
     * Same direction boolean.
     *
     * @param a the a
     * @param b the b
     * @return the boolean
     */
    public boolean sameDirection(Square a, Square b){

        return a.getRow() == b.getRow() || a.getColumn() == b.getColumn();
    }
    
    /**
     * Is in my square boolean.
     *
     * @param actualPlayer the actual player
     * @param otherPlayer  the other player
     * @return the boolean
     */
    public boolean isInMySquare(Player actualPlayer,Player otherPlayer){

        return this.playersOnSquare(this.searchSquare(actualPlayer.getRow(), actualPlayer.getColumn())).contains(otherPlayer);
    }
    
    /**
     * Player in room array list.
     *
     * @param colorSquare the color square
     * @return the array list
     */
    public ArrayList<Player> playerInRoom(EnumColorSquare colorSquare){

        ArrayList<Player> tempPlayer =new ArrayList<>();
        for (Square a: this.squares){

            if(a.getColor()==colorSquare){

                tempPlayer.addAll(a.playerOnSquare());
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

                tempPlayer.addAll(a.playerOnSquare());
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

                tempPlayer.addAll(a.playerOnSquare());
            }
        } return tempPlayer;
    }

}
