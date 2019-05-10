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

        System.out.println("ID: " +player.getId());
        System.out.println("NAME: " +player.getName());
        System.out.println("ROW: " +player.getRow());
        System.out.println("COLUMN: " +player.getColumn());
        System.out.println("SCORE: " +player.getScore());
        System.out.println("ALIVE: " +player.isAlive());
    }

    /**
     * Print Player's attributes.
     * @param player   the player to print
     */
    public static void print(ArrayList<Player> players){

        for(Player p : players){
            System.out.println("ID: " +p.getId());
            System.out.println("NAME: " +p.getName());
            System.out.println("ROW: " +p.getRow());
            System.out.println("COLUMN: " +p.getColumn());
            System.out.println("SCORE: " +p.getScore());
            System.out.println("ALIVE: " +p.isAlive());
        }

    }
}
