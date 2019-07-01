package it.polimi.view.cli;

import it.polimi.model.Player;

import java.io.Serializable;

public class PrintCoordinate implements Serializable {

    /**
     * Print Player's coordinates.
     * @param player   the player to printList
     */
    public static void print(Player player){

        System.out.println();
        System.out.println("ROW: " +player.getRow());
        System.out.println("COLUMN: " +player.getColumn());
    }
}
