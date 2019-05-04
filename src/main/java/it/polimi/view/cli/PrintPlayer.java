package it.polimi.view.cli;

import it.polimi.model.Player;

import java.io.Serializable;

public class PrintPlayer implements Serializable {

    private int id;
    private String name;
    private int row;
    private int column;
    private int score;
    private boolean alive;

    /**
     * Print Player's attributes.
     */
    public void print(Player player){

        id = player.getId();
        name = player.getName();
        row = player.getRow();
        column = player.getColumn();
        score = player.getScore();
        alive = player.isAlive();

        System.out.println("ID: " +id);
        System.out.println("NAME: " +name);
        System.out.println("ROW: " +row);
        System.out.println("COLUMN: " +column);
        System.out.println("SCORE: " +score);
        System.out.println("ALIVE: " +alive);

    }
}
