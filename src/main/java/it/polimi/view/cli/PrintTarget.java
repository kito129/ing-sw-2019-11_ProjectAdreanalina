package it.polimi.view.cli;

import java.io.Serializable;

public class PrintTarget implements Serializable {

    /**
     * Print a string to choose the target
     */
    public static void print(){

        System.out.println();
        System.out.print("Target: ");
    }
}
