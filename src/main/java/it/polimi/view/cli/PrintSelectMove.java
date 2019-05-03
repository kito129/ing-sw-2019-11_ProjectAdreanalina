package it.polimi.view.cli;

import java.io.Serializable;

public class PrintSelectMove implements Serializable {

    /**
     * prints the client's possible choices
     */
    public static void print(){
        System.out.println("WHAT MOVE DO YOU WANT TO MAKE? (PRESS -1 TO ABORT)");
        System.out.println("1) RUN AROUND");
        System.out.println("2) GRUB STUFF");
        System.out.println("3) SHOOT PEOPLE");
        System.out.println("0) END YOUR TURN (before finishing your turn, if you have ammo, you can reload your weapons");
    }
}
