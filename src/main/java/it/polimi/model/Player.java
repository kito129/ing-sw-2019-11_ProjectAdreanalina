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
    private boolean overKill;
    private boolean markToDead;
    private boolean online;
    private ArrayList<PowerUpCard> powerUpCardsSpawn = new ArrayList<>();
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
        playerBoard = new PlayerBoard();
        this.row = -1;
        this.column = -1;
        setOnlineModel(true);
        this.damagedGameModel = gameModel.getPlayerDamaged();
        this.markedGameModel = gameModel.getPlayerMarked();
        
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

    /**
     * Gets the field online.
     *
     * @return true if the client associated with this player is online.
     */

    public boolean getOnline(){
        
        return online;
    }

    /**
     *
     * @return
     */
    
    public boolean isMarkToDead () {
        
        return markToDead;
    }
    
    public void setMarkToDead (boolean markToDead) {
        
        this.markToDead = markToDead;
    }
    
    public boolean isOverKill () {
        
        return overKill;
    }
    
    public void setOverKill (boolean overKill) {
        
        this.overKill = overKill;
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

    /**
     * Manages the grab of a ammoCard.
     *
     * @param ammoCard the ammo card to manage.
     */
    public void catchAmmoCard(AmmoCard ammoCard){

        this.playerBoard.manageAmmoCard(ammoCard);
    }

    /**
     * Adds a single mark.
     *
     * @param mark the color of mark.
     */

    public void singleMark(EnumColorPlayer mark){

        this.playerBoard.increaseMarks(mark);
        this.markedGameModel.add(this);

    }

    /**
     * Adds a multiple marks.
     *
     * @param marks the color of marks.
     */

    public void multipleMarks(ArrayList<EnumColorPlayer> marks){

        this.playerBoard.increaseMarks(marks);
        this.markedGameModel.add(this);
    }

    /**
     * Adds a single damage.
     *
     * @param damage the color of damage.
     */

    public void singleDamage(EnumColorPlayer damage){

        this.playerBoard.increaseDamages(damage);
        this.playerBoard.shiftMarks(damage);
        checkAlive();
        this.damagedGameModel.add(this);
    }

    /**
     * Adds a multiple damages.
     *
     * @param damages the color of damages.
     */

    public void multipleDamages(ArrayList<EnumColorPlayer> damages){

        this.playerBoard.increaseDamages(damages);
        this.playerBoard.shiftMarks(damages.get(0));
        checkAlive();
        this.damagedGameModel.add(this);
    }

    /**
     * Adds multiple damages and single mark.
     *
     * @param damages the color of damages.
     * @param mark the color of mark.
     */

    public void multipleDamagesSingleMark(ArrayList<EnumColorPlayer> damages, EnumColorPlayer mark){

        this.playerBoard.increaseDamages(damages);
        this.playerBoard.shiftMarks(mark);
        this.playerBoard.increaseMarks(mark);
        checkAlive();
        this.damagedGameModel.add(this);
        this.markedGameModel.add(this);
    }

    /**
     * Adds multiple marks and single damage.
     *
     * @param damage the color of damage.
     * @param marks the color of marks.
     */

    public void singleDamageMultipleMarks(EnumColorPlayer damage, ArrayList<EnumColorPlayer> marks) {

        this.playerBoard.increaseDamages(damage);
        this.playerBoard.shiftMarks(damage);
        this.playerBoard.increaseMarks(marks);
        checkAlive();
        this.damagedGameModel.add(this);
        this.markedGameModel.add(this);
    }

    @Override
    public String toString () {

        return "\nPlayer:\t" + this.id + "\nname:\t" + this.name + "\nr:\t" + this.row + "\nc:\t" + this.column + "\n" + this.getPowerUpCardsSpawn().toString();
    }
    
    public void checkAlive(){
        
        if (this.getPlayerBoard().getDamages().size()==11){
            
            setMarkToDead(true);
        } else if (this.getPlayerBoard().getDamages().size()==12){
            
            setOverKill(true);
            setAlive(false);
        }
    }
}
