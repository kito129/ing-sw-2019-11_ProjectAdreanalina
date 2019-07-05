package it.polimi.model;


import it.polimi.model.Exception.MapException;
import it.polimi.model.Exception.NotValidInput;
import it.polimi.model.Exception.NotValidSquareException;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The type Map.
 */

public class Map implements Serializable {
    
    private ArrayList<Square> squares;
    private String name;

    public String getName() {

        return this.name;
    }

    /*

    public void setName(String name) {

        this.name = name;
    }

     */

    /**
     * Instantiates a new Map.
     *
     * @param squares list of square to insert in the map
     */

    public Map(ArrayList<Square> squares,String name) {

        ArrayList<Square> temp = new ArrayList<>();
        if (squares != null) {

            for (Square l : squares) {

                if (l != null)
                    temp.add(l);
                //System.out.println("aggiungo "+ l.toString());
            }
            this.squares= new ArrayList<>(temp);
            this.name=name;
        }
        //System.out.println("\ncreo la mappa\n");
    }

    public Map(){

        //todo metodo da cancellare
    }

    /**
     * Gets the squares of map.
     *
     * @return the squares of map.
     */
    
    public ArrayList<Square> getSquares () {
        
        return squares;
    }
    
    /**
     * Searches square passing row and column.
     *
     * @param row row of square to search
     * @param column column of square to search
     * @return the square with the given coordinate.
     * @throws MapException
     */
    public Square getSquare (int row, int column) throws  MapException {

        for (Square s : squares) {

            if ((s.getRow() == row) && (s.getColumn() == column)) {

                return s;
            }
        }
        throw new MapException();
    }
    
    /**
     * Finds one player in the squares
     *
     * @param player the player to found.
     * @return the square where is located the player.
     * @throws MapException
     */
    public Square findPlayer(Player player) throws MapException {

        for (Square s : squares) {

            if (s.getPlayers().contains(player)) {

                return s;
            }
        }
        throw new MapException();
    }
    
    /**
     * Searches and returns all player positioned on a square.
     *
     * @param s the square where searching player.
     * @return the players positioned on the given square
     */
    public ArrayList<Player> playersOnSquare(Square s){

        
        for (Square a:squares){

            if(a==s) {
                return s.getPlayers();
            }
        }
        return new ArrayList<Player>();
    }
    
    /**
     * Calculate the minimum distance from player A to player B.
     *
     * @param playerA one player.
     * @param playerB one player
     * @return minimum distance between A and B
     */
    public int distance (Player playerA, Player playerB){
        if(playerA.getRow()==playerB.getRow() && playerA.getColumn()==playerB.getColumn()) {
            return 0;
        }
        
        return distance(playerA.getRow(),playerA.getColumn(),playerB.getRow(),playerB.getColumn(),1);
    }
    
    /**
     * Calculate the minimum distance from square A to square B.
     *
     * @param a one square
     * @param b one square
     * @return minimum distance between A and B.
     */
    public int distance(Square a, Square b){
        if(a.getRow()==b.getRow() && a.getColumn()==b.getColumn()) {
            return 0;
        }else
            return distance(a.getRow(),a.getColumn(),b.getRow(),b.getColumn(),1);
        }
    
    /**
     * Only for Private use. Calculate the minimum distance from A to B.
     *
     * @param a    one square.
     * @param b    one square.
     * @param dist recursive dist in this pass of algorithm.
     * @return minimum distance between A and B
     */
    private int distance(Square a, Square b, int dist){
        if(a.getRow()==b.getRow() && a.getColumn()==b.getColumn()) {
            return 0;
        }else
            return distance(a.getRow(),a.getColumn(),b.getRow(),b.getColumn(),dist);
    }

    /**
     * Private recursive method, called by public method. Calculate the minimum distance from A(rInit,cInit) to B(rDest,cDest).
     *
     * @param rInit row of current square
     * @param cInit column of current square
     * @param rDest row of search square
     * @param cDest column of search square
     * @param dist recursive dist in this pass of algorithm
     * @return minimum distance from A(rInit,cInit) to B(rDest,cDest)
     */
    private int distance(int rInit, int cInit, int rDest, int cDest, int dist) {
        
        int distance = dist;
        int currR = rInit;
        int curC = cInit;
        int tempPath=0;
        ArrayList<Integer> path =new ArrayList<>();
    
        Square curSquare = null;
        try {
            curSquare = getSquare(currR, curC);
        } catch (MapException e) {
            return -1;
        }
        // System.out.println("curr square : " + curSquare.toString());
        Square destSquare = null;
        try {
            destSquare = getSquare(rDest, cDest);
        } catch (MapException e) {
            return -1;
        }
    
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
     * Refreshes map, sets all visited attribute to false.
     */
    public void refreshMap(){

        for (Square a:squares){

            a.setVisited(false);
        }
    }

    /**
     * Calculates the smallest number in the ArrayList.
     *
     * @param path array contains the distances.
     * @return the smallest value in the array
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
     * Computes if the Player passed can see the Square passed.
     *
     * @param player  one player
     * @param colorSquare the color of the Room to search
     * @return true if player passed can see the room passed
     */
    public boolean isVisibleRoom(Player player, EnumColorSquare colorSquare) {
    
        try {
            if(this.findPlayer(player).getColor()==colorSquare){
    
                return true;
            } else {
    
                for (Square a:this.findPlayer(player).getLink()){
    
                    if(a.getColor()==colorSquare){
    
                        return true;
                    }
                }
            }
        } catch (MapException e) {
            return false;
        }
        return false;
    }

    /**
     * Computes if player a don't see player b.
     * @param a one player.
     * @param b one player.
     *
     * @return true if player a don't see player b.
     */
    
    
    public boolean isNotVisible(Player a, Player b) {
        
        try {
            return !isVisible(findPlayer(a),findPlayer(b));
        } catch (MapException e) {
            
            return false;
            
        }
    }
    
    /**
     * Calculates if PlayerA see PlayerB.
     *
     * @param a one player.
     * @param b one player.
     * @return true if PlayerA see PlayerB
     */
    public boolean isVisible(Player a, Player b) {
        
        try {
            return isVisible(findPlayer(a),findPlayer(b));
        } catch (MapException e) {
            
            return false;
        }
    }
    
    
    /**
     * Calculate if SquareA see SquareB.
     *
     * @param a one square.
     * @param b pne square.
     * @return true if A see B
     */

    public boolean isVisible(Square a, Square b) {

        return isVisible(a.getRow(),a.getColumn(),b.getRow(),b.getColumn());
    }
    
    
    /**
     * Calculate if the coordinate(c0,r0) see coordinate(c1,r1).
     *
     * @param r0 row1
     * @param c0 column1
     * @param r1 row2
     * @param c1 column2
     * @return true if A(c0,r0) see B(c1,r1)
     */
    
    public boolean isVisible(int r0, int c0,int r1, int c1) {
        
        try {
            Square currSquare = getSquare(r0,c0);
            Square destSquare = getSquare(r1,c1);
    
            if (currSquare==destSquare || currSquare.getColor()==destSquare.getColor()){
                
                return true;
            }else {
                
                for (Square a:currSquare.getLink()){
            
                    if(a.getColor()==destSquare.getColor()) {
                       
                        return true;
                    }
                }
            }
            return false;
            
        } catch (MapException e) {
            
            return false;
        }
    }
    
    /**
     * Moves player in map.
     *
     * @param player the player to move
     * @param square the square where moving the player
     *
     */
    public void movePlayer(Player player, Square square) throws MapException {
  
        removePlayerFromSquare(player);
        addPlayerOnSquare(square,player);
    }
    
    /**
     * Adds the passed player on the given square.
     *
     * @param square the square where adding the player
     * @param player the player to add on map
     */
    public void addPlayerOnSquare(Square square, Player player) throws  MapException {

        getSquare(square.getRow(),square.getColumn()).addPlayer(player);
    }
    
    /**
     * Removes player from the given square.
     *
     * @param player the player to remove.
     */
    public void removePlayerFromSquare(Player player) throws  MapException {

        getSquare(findPlayer(player).getRow(),findPlayer(player).getColumn()).removePlayer(player);
    }
    
    
    /**
     * Calculate if PlayerA is in the same cardinal direction of PlayerB, and PlayerC.
     *
     * @param a PlayerA
     * @param b PlayerB
     * @param c Player c
     * @return true if PlayerA is in the same cardinal direction of PlayerB, and PlayerC
     */
    public boolean sameDirection(Player a, Player b,Player c){
    
        try {
            return sameDirection(findPlayer(a),findPlayer(b),findPlayer(c));
        } catch (MapException e) {
            return false;
        }
    }
    
    /**
     * Calculate if SquareA is in the same cardinal direction of SquareB, and PlayerC.
     *
     * @param a Square A
     * @param b Square B
     * @param c Square C
     * @return true if A is in the same cardinal direction of B, and PlayerC.
     */
    public boolean sameDirection(Square a, Square b, Square c){
        
        return ((((a.getRow() == b.getRow()) && (b.getRow() == c.getRow())) || ((a.getColumn() == b.getColumn()) && (b.getColumn() == c.getColumn()))) &&
                b.getLink().contains(a) && b.getLink().contains(c) && c.getLink().contains(b) && !c.getLink().contains(a));
        
    }
    
    /**
     * Calculate if SquareA is in the same cardinal direction of SquareB.
     *
     * @param a Square A
     * @param b Square B
     * @return true if A is in the same cardinal direction of B
     */
    public boolean sameDirection(Square a, Square b){

        return ((a.getRow() == b.getRow()) || ((a.getColumn() == b.getColumn())));
    }
    
    /**
     * Gets the players in one room.
     *
     * @param colorSquare the color of the Room.
     * @return list of the player in the Room.
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
     * Gets Player on my North Cardinal direction .
     *
     * @param player one player.
     * @return an ArrayList that contains Player in my North Cardinal Direction.
     */
    public ArrayList<Player> playerOnMyNorth(Player player){

        ArrayList<Player> tempPlayer =new ArrayList<>();
        for (Square a: this.squares){

            if(a.getColumn()==player.getColumn() && a.getRow()<=player.getRow()){

                tempPlayer.addAll(a.getPlayers());
            }
        }
        return tempPlayer;
    }
    
    /**
     * Gets Player on my Est Cardinal direction.
     *
     * @param player one player.
     * @return an ArrayList that contain Player in my Est Cardinal Direction
     */
    public ArrayList<Player> playerOnMyEst(Player player){
        
        ArrayList<Player> tempPlayer =new ArrayList<>();
        for (Square a: this.squares){
    
            if(a.getRow()==player.getRow() && a.getColumn()>=player.getColumn()){
                
                tempPlayer.addAll(a.getPlayers());
            }
        }
        return tempPlayer;
    }

    /**
     * Gets Player on my West Cardinal direction.
     *
     * @param player one player.
     * @return an ArrayList that contain Player in my West Cardinal Direction
     */
    public ArrayList<Player> playerOnMyWest(Player player){
        
        ArrayList<Player> tempPlayer =new ArrayList<>();
        for (Square a: this.squares){
    
            if(a.getRow()==player.getRow() && a.getColumn()<=player.getColumn()){
                
                tempPlayer.addAll(a.getPlayers());
            }
        }
        return tempPlayer;
    }

    /**
     * Gets Player on my South Cardinal direction.
     *
     * @param player one player.
     * @return an ArrayList that contain Player in my South Cardinal Direction
     */
    public ArrayList<Player> playerOnMySouth(Player player){
        
        ArrayList<Player> tempPlayer =new ArrayList<>();
        for (Square a: this.squares){
    
            if(a.getColumn()==player.getColumn() && a.getRow()>=player.getRow()){
                
                tempPlayer.addAll(a.getPlayers());
            }
        }
        return tempPlayer;
    }
    
    /**
     * Controls if the square passed exist in map.
     *
     * @param square the square we want to verify.
     * @return true if the square passed exists in map.
     */
    public boolean existInMap(Square square){
        
        boolean found=false;
        
        for (Square a:squares) {
            if (a.getRow() == square.getRow() && a.getColumn() == square.getColumn()) {
                
                return true;
            }
        }
       return false;
    }
    /**
     * Controls if the coordinate passed exist in map.
     *
     * @param row one row .
     * @param column one column.
     * @return true if the coordinate passed exists in map.
     */
    public boolean existInMap(int row, int column){
        
        
        for (Square a:squares) {
            if (row == a.getRow() && column == a.getColumn()) {
                
                return true;
            }
        }
        return false;
    }
    
    
    /**
     * Controls if the square passed is a generation square.
     *
     * @param square the square to search.
     * @return true if this square is a Generation Square.
     */
    public boolean isGenerationSquare(Square square){
        if(square!=null) {
            return square.getClass().equals(GenerationSquare.class);
        } return false;
    }

    
    /**
     * Gets generation square of the given color.
     *
     * @param color the color to search.
     * @return the generation square of this color.
     */
    public Square getGenerationSquare(EnumColorSquare color) throws MapException {
    
        for (Square a : squares) {
            if (this.isGenerationSquare(a) && (a.getColor() == color)) {
                return a;
            }
        }
        throw new MapException();
    }
    
    /**
    * Calculate if there is a port from SquareA to SquareB.
     *
    * @param squareA Square A
    * @param squareB Square B
    * @return true if there is a port, false otherwise
    * */
    public boolean isPort(Square squareA, Square squareB){
        
        if(squareA.getLink().contains(squareB) && squareB.getLink().contains(squareA) && squareA.getColor()!=squareB.getColor()){
            return true;
        }
        return false;
    }
    
    /**
     * Calculate if there is a port from the given coordinates.
     * @param row1 row1
     * @param col1 column1
     * @param row2 row2
     * @param col2 column2
     * @return true if there is a port, false otherwise
     * */
    public boolean isPort(int row1,int col1, int row2,int col2){
    
        try {
            if(isPort(getSquare(row1,col1),getSquare(row2,col2))){
                return true;
            }else return false;
        } catch (MapException e) {
            return false;
        }
    }
    
   /* public void print(){
        
        for (Square a: squares){
            System.out.println(a.toString());
        }
    }

    */

    /**
     * Gets the color of square in map.
     *
     * @return the color in map.
     */

    public ArrayList<EnumColorSquare> getRoomColor (){
        
        ArrayList<EnumColorSquare> temp = new ArrayList<>();
        
        for (Square a:squares){
            
            if(!temp.contains(a.getColor())){
                
                temp.add(a.getColor());
            }
        }
    return temp;
    }

    public ArrayList<EnumCardinalDirection> getCardinalDirection(){

        ArrayList<EnumCardinalDirection> temp = new ArrayList<>();

        //todo
        return temp;
    }
}
