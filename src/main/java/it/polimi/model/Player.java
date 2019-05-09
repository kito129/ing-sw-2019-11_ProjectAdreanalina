package it.polimi.model;

import java.util.ArrayList;

/**
 * The type Player.
 */
public class Player {
    
    private int id;
    private String name;
    private EnumColorPlayer color;
    private PlayerBoard playerBoard;
    private int row;
    private int column;
    private int score;
    private boolean alive;
    
    /**
     * Instantiates a new Player setting the id, name and color with the given parameters.
     * It creates a new player board for the player and settings score and the field alive to the start value.
     *
     * @param id    the id of the player
     * @param name  the name of the player
     * @param color the color of the player
     */
    public Player (int id, String name, EnumColorPlayer color) {
    
        this.id = id;
        this.name = name;
        this.color = color;
        score = 0;
        alive = true;
        playerBoard = new PlayerBoard();

        // TODO vedere come gestire posizione in fase di inizializzazione.io inizialmente le metterei a  null quando vengono istanziati
    
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

    }

    public void multipleMarks(ArrayList<EnumColorPlayer> marks){

        this.playerBoard.increaseMarks(marks);
    }

    public void singleDamage(EnumColorPlayer damage){

        this.playerBoard.increaseDamages(damage);
        this.playerBoard.shiftMarks(damage);
    }

    public void multipleDamages(ArrayList<EnumColorPlayer> damages){

        this.playerBoard.increaseDamages(damages);
        this.playerBoard.shiftMarks(damages.get(0));
    }

    public void multipleDamagesSingleMark(ArrayList<EnumColorPlayer> damages, EnumColorPlayer mark){

        this.playerBoard.increaseDamages(damages);
        this.playerBoard.shiftMarks(mark);
        this.playerBoard.increaseMarks(mark);
    }

    public void singleDamageMultipleMarks(EnumColorPlayer damage, ArrayList<EnumColorPlayer> marks) {

        this.playerBoard.increaseDamages(damage);
        this.playerBoard.shiftMarks(damage);
        this.playerBoard.increaseMarks(marks);
    }

    @Override
    public String toString () {

        return "Player: " + this.id + " name: " + this.name + " r: " + this.row + " c: " + this.column;
    }


}
