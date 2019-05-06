package it.polimi.view.cli;

import it.polimi.model.Player;

import java.io.Serializable;

public class PrintMenu implements Serializable {

    /**
     * Print menu.
     */
    public static void print(){
        System.out.println("--------------------------------------------------------");
        System.out.println("WHAT MOVE DO YOU WANT TO MAKE? (PRESS -1 TO ABORT)");
        System.out.println("1) MAKE A MOVE!"); //printSelectMove
        System.out.println("2) VIEW YOUR ATTRIBUTE"); //id, name, position and score (printPlayer)
        System.out.println("3) VIEW YOUR PLAYERBOARD"); //damages, marks and ammo (printPlayerBoard)
        System.out.println("4) VIEW YOUR WEAPONS"); //take weapons from ActualPlayer (printWeapon)
        System.out.println("5) VIEW YOUR POWERUP"); //printPowerUp
        System.out.println("6) VIEW YOUR AMMO"); //only ammo (printAmmo)
        System.out.println("7) VIEW OTHER PLAYER'S ATTRIBUTE"); //id, name, position and score (printPlayer)
        System.out.println("8) VIEW OTHER PLAYER'S PLAYERBOARD"); //damages, marks and ammo (printPlayerBoard)
        System.out.println("9) VIEW OTHER WEAPONS ON THE MAP"); //take weapons from generation square (printWeapon)
        System.out.println("--------------------------------------------------------");
    }
}
