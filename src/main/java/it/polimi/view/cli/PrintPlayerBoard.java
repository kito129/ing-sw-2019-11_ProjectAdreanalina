package it.polimi.view.cli;

import it.polimi.model.*;

import java.io.Serializable;
import java.util.ArrayList;

public class PrintPlayerBoard implements Serializable {

    /**
     * Print attributes of PlayerBoard.
     */
    public static void print(Player player){

        System.out.println("AMMO BLUE: " +player.getPlayerBoard().getAmmoB());
        System.out.println("AMMO RED: " +player.getPlayerBoard().getAmmoR());
        System.out.println("AMMO YELLOW: " +player.getPlayerBoard().getAmmoY());
        System.out.println("BOARD VALUE: " +player.getPlayerBoard().getBoardValue());
        System.out.println("N. DEATHS: " +player.getPlayerBoard().getNumberOfDeaths());
        System.out.println("DAMAGES: " +player.getPlayerBoard().getDamages().toString());
        System.out.println("MARKS: " +player.getPlayerBoard().getMarks().toString());
    }

    /**
     * Print attributes of PlayerBoard.
     */
    public static void print(ArrayList<Player> players){

        for(Player p : players){
            System.out.println("AMMO BLUE: " +p.getPlayerBoard().getAmmoB());
            System.out.println("AMMO RED: " +p.getPlayerBoard().getAmmoR());
            System.out.println("AMMO YELLOW: " +p.getPlayerBoard().getAmmoY());
            System.out.println("BOARD VALUE: " +p.getPlayerBoard().getBoardValue());
            System.out.println("N. DEATHS: " +p.getPlayerBoard().getNumberOfDeaths());
            System.out.println("DAMAGES: " +p.getPlayerBoard().getDamages().toString());
            System.out.println("MARKS: " +p.getPlayerBoard().getMarks().toString());
        }
    }
}
