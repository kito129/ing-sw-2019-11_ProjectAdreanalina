package it.polimi.model;

import it.polimi.model.Exception.AlreadyThreePowerUps;

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
     * Gets the value of the field alive
     *
     * @return true if the player is alive, false otherwise.
     */
    public boolean isAlive () {
    
        return alive;
    }
    
    /**
     * Sets the column
     *
     * @param column the column we want to set to the player
     */
    private void setColumn (int column) {
        
        this.column = column;
    }

    /**
     * Sets the row
     *
     * @param row the row we want to set to the player
     */
    private void setRow (int row) {
        
        this.row = row;
    }
    
    /**
     * Sets position of player assigning a row and a column.
     *
     * @param r the row
     * @param c the column
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
    
    @Override
    public String toString () {
        
        return "Player: " + this.id + " name: " + this.name + " r: " + this.row + " c: " + this.column;
    }

    public void catchAmmoCard(AmmoCard ammoCard) throws AlreadyThreePowerUps {

        this.playerBoard.manageAmmoCard(ammoCard);
    }

    public void recivedDamege(ArrayList<EnumColorPlayer> damages,ArrayList<EnumColorPlayer> marks,EnumColorPlayer colorDamage){

        this.playerBoard.increaseDamages(damages);
        this.playerBoard.shiftMarks(colorDamage);
        this.playerBoard.increaseMarks(marks);

    }
    //todo fare molto funzione recived damage oveloaded per gestire piu situazioni, danno singolo marchio singolo ....
}
