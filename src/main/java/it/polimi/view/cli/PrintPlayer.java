package it.polimi.view.cli;

import it.polimi.model.Player;

import java.io.Serializable;

public class PrintPlayer implements Serializable {

    /**
     * Print Player's attributes.
     * @param player   the player to print
     */
    public void print(Player player){

        System.out.println("ID: " +player.getId());
        System.out.println("NAME: " +player.getName());
        System.out.println("ROW: " +player.getRow());
        System.out.println("COLUMN: " +player.getColumn());
        System.out.println("SCORE: " +player.getScore());
        System.out.println("ALIVE: " +player.isAlive());

    }
}
