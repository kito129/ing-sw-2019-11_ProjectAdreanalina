package it.polimi.view.cli;

import it.polimi.model.Player;

import java.io.Serializable;

public class PrintPlayerInfo implements Serializable {

    /**
     * Print Player's info.
     * @param player   the player to print
     */
    public static void print(Player player){

        System.out.println("ID: " +player.getId());
        System.out.println("NAME: " +player.getName());
    }
}
