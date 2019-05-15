package it.polimi.view.cli;

import it.polimi.model.Player;

import java.io.Serializable;
import java.util.ArrayList;

public class PrintPlayer implements Serializable {

    /**
     * Print Player's attributes.
     * @param player   the player to print
     */
    public static void print(Player player){

        PrintPlayerInfo.print(player);
        PrintCoordinate.print(player);
        PrintScore.print(player);
        System.out.println("ALIVE: " +player.isAlive());
    }
}
