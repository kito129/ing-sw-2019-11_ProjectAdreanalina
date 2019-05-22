package it.polimi.view.cli;

import java.io.Serializable;

public class PrintGrabAction implements Serializable {

    /**
     * prints the client's possible choices
     */
    public static void printGrabStuff(){

        System.out.println("CHOOSE YOUR MOVE! (-1 TO ABORT)");
        System.out.println("Put row and column where you want to move for grab stuff.");
        System.out.println("If you want to grab a weapon enter the number corresponding to the weapon you want.");
    }

    /**
     * prints the client's possible choices
     */
    public static void printGrabWeapon(){

        System.out.println("You have selected a Generation Square:");
        System.out.println("Which weapon do you want?");
    }
}
