package it.polimi.view.cli;

import java.io.Serializable;

public class PrintGrabAction implements Serializable {

    /**
     * prints the client's possible choices
     */
    public static void printGrabStuff(){

        System.out.println();
        System.out.println("Put row and column where you want to move for grab stuff. (-1 TO ABORT)");
        System.out.println("If you want to grab a weapon, then enter the number corresponding to the weapon you want.");
        System.out.println();
    }

    /**
     * prints the client's possible choices
     */
    public static void printGrabWeapon(){

        System.out.println();
        System.out.println("You have selected a Generation Square:");
        System.out.println("Which weapon do you want?");
    }
}
