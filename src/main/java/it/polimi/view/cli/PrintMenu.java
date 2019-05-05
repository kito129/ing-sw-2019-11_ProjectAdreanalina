package it.polimi.view.cli;

import it.polimi.model.Player;

import java.io.Serializable;

public class PrintMenu implements Serializable {

    /**
     * Print menu.
     */
    public static void print(){
        System.out.println("WHAT MOVE DO YOU WANT TO MAKE? (PRESS -1 TO ABORT)");
        System.out.println("1) VIEW YOUR ATTRIBUTE"); //name, posizione, score (printPlayer)
        System.out.println("2) VIEW YOUR WEAPONS"); //printWeapons
        System.out.println("3) VIEW YOUR POWERUP"); //printPowerUp
        System.out.println("2) VIEW YOUR AMMO"); //printAmmo
        System.out.println("4) VIEW OTHER PLAYER'S ATTRIBUTE"); //name, position, damages and marks
        System.out.println("5) VIEW OTHER WEAPONS ON THE MAP"); //printWeaponsMap
        System.out.println("6) MAKE A MOVE"); //printSelectMove
    }
}
