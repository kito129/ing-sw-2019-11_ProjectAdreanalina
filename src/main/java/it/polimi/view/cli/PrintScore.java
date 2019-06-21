package it.polimi.view.cli;

import it.polimi.model.Player;

import java.io.Serializable;

public class PrintScore implements Serializable {

    /**
     * Print Player's score.
     * @param player   the player to print
     */
    public static void print(Player player){

        System.out.println("SCORE: " + player.getScore());
    }
}
