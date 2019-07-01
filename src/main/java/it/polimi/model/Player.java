package it.polimi.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The type Player.
 */
public class Player implements Serializable {
    
    private int id;
    private String name;
    private EnumColorPlayer color;
    private PlayerBoard playerBoard;
    private int row;
    private int column;
    private int score;
    private boolean alive;
    private boolean damaged;
    private boolean online;
    private ArrayList<PowerUpCard> powerUpCardsSpawn = new ArrayList<>(2);
    private ArrayList<Player> damagedGameModel;
    private ArrayList<Player> markedGameModel;
   
    
    /**
     * Instantiates a new Player setting the id, name and color with the given parameters.
     * It creates a new player board for the player and settings score and the field alive to the start value.
     *
     * @param id    the id of the player
     * @param name  the name of the player
     * @param color the color of the player
     */
    public Player (int id, String name, EnumColorPlayer color,GameModel gameModel) {

        this.id = id;
        this.name = name;
        this.color = color;
        score = 0;
        alive = true;
        damaged = false;
        playerBoard = new PlayerBoard();
        this.row = -1;
        this.column = -1;
        setOnlineModel(true);
        this.damagedGameModel = gameModel.getPlayerDamaged();
        this.markedGameModel = gameModel.getPlayerMarked();


    }

    public Player(){

        //todo metodo da cancellare
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId () {
    
        return id;
    }
    
    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName () {
    
        return name;
    }
    
    /**
     * Gets color.
     *
     * @return the color
     */
    public EnumColorPlayer getColor () {
    
        return color;
    }
    
    /**
     * Gets player board.
     *
     * @return the player board
     */
    public PlayerBoard getPlayerBoard () {
    
        return playerBoard;
    }
    
    /**
     * Gets score.
     *
     * @return the score
     */
    public int getScore () {
    
        return score;
    }
    
    /**
     * Gets column.
     *
     * @return the column
     */
    public int getColumn () {
    
        return column;
    }
    
    /**
     * Gets row.
     *
     * @return the row
     */
    public int getRow () {
    
        return row;
    }
    
    /**
     * Gets the value of the field alive.
     *
     * @return true if the player is alive, false otherwise.
     */
    public boolean isAlive () {
    
        return alive;
    }

    public boolean getOnline(){
        
        return online;
    }
    
    public ArrayList<PowerUpCard> getPowerUpCardsSpawn () {
        
        return powerUpCardsSpawn;
    }
    
    public void setPowerUpCardsSpawn (ArrayList<PowerUpCard> powerUpCardsSpawn) {
        
        this.powerUpCardsSpawn.add(powerUpCardsSpawn.get(0));
        this.powerUpCardsSpawn.add(powerUpCardsSpawn.get(1));
    }
    
    public void setOnlineModel(Boolean online){

        this.online = online;
        if(online) {

            System.out.println(name + " IS ONLINE");
        }
        else{

            System.out.println(name+" IS OFFLINE");
        }
    }
    
    /**
     * Sets the column
     *
     * @param column the column we want to set to the player.
     */
    private void setColumn (int column) {
        
        this.column = column;
    }

    /**
     * Sets the row
     *
     * @param row the row we want to set to the player.
     */
    private void setRow (int row) {
        
        this.row = row;
    }
    
    /**
     * Sets position of player assigning a row and a column.
     *
     * @param r the row.
     * @param c the column.
     */
    public void setPosition (int r, int c) {
    
        this.setRow(r);
        this.setColumn(c);
    }
    
    /**
     * Sets the field alive.
     *
     * @param alive player status
     */
    public void setAlive (boolean alive) {
    
        this.alive = alive;
    }
    
    
    /**
     * Increase score of the player.
     *
     * @param scoreToAdd points to add at player score.
     */
    public void increaseScore (int scoreToAdd) {

        this.score += scoreToAdd;
    }


    public void catchAmmoCard(AmmoCard ammoCard){

        this.playerBoard.manageAmmoCard(ammoCard);
    }

    public void singleMark(EnumColorPlayer mark){

        this.playerBoard.increaseMarks(mark);
        this.markedGameModel.add(this);

    }

    public void multipleMarks(ArrayList<EnumColorPlayer> marks){

        this.playerBoard.increaseMarks(marks);
        this.markedGameModel.add(this);
    }

    public void singleDamage(EnumColorPlayer damage){

        this.playerBoard.increaseDamages(damage);
        this.playerBoard.shiftMarks(damage);
        this.damagedGameModel.add(this);
    }

    public void multipleDamages(ArrayList<EnumColorPlayer> damages){

        this.playerBoard.increaseDamages(damages);
        this.playerBoard.shiftMarks(damages.get(0));
        this.damagedGameModel.add(this);
    }

    public void multipleDamagesSingleMark(ArrayList<EnumColorPlayer> damages, EnumColorPlayer mark){

        this.playerBoard.increaseDamages(damages);
        this.playerBoard.shiftMarks(mark);
        this.playerBoard.increaseMarks(mark);
        this.damagedGameModel.add(this);
        this.markedGameModel.add(this);
    }

    public void singleDamageMultipleMarks(EnumColorPlayer damage, ArrayList<EnumColorPlayer> marks) {

        this.playerBoard.increaseDamages(damage);
        this.playerBoard.shiftMarks(damage);
        this.playerBoard.increaseMarks(marks);
        this.damagedGameModel.add(this);
        this.markedGameModel.add(this);
    }

    @Override
    public String toString () {

        return "\nPlayer:\t" + this.id + "\nname:\t" + this.name + "\nr:\t" + this.row + "\nc:\t" + this.column + "\n" + this.getPowerUpCardsSpawn().toString();
    }

    public void stampa(){

        System.out.print("id: "+this.id+"              ");
        System.out.print("name: "+this.name+"              ");
        System.out.print("color: "+this.color+"             ");
        //System.out.println("playboard: "+this.playerBoard);
        System.out.print("row :"+this.row+"            ");
        System.out.print("column: "+this.column+"            ");
        System.out.print("score: "+this.score+"           ");
        System.out.print("alive: "+this.alive+"            ");
        System.out.print("damaged: "+this.damaged+"           ");
        System.out.print("ammo: "+playerBoard.getAmmo()+"           ");
        //System.out.println("ammoY: "+playerBoard.getAmmoY());
        //System.out.println("ammoR: "+playerBoard.getAmmoR());
        //System.out.println("ammoB: "+playerBoard.getAmmoB());
        System.out.print("boardvalue: "+playerBoard.getBoardValue()+"            ");
        System.out.print("damages: "+playerBoard.getDamages()+"            ");
        System.out.print("marks: "+playerBoard.getMarks()+"             ");
        System.out.print("weapons: "+playerBoard.getPlayerWeapons()+"              ");
        System.out.print("powerup :"+playerBoard.getPlayerPowerUps()+"            ");
        System.out.println();
        System.out.println();
    }
    
   
}
