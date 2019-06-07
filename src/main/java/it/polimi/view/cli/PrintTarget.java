package it.polimi.view.cli;

import it.polimi.model.EnumColorSquare;
import it.polimi.model.GameModel;

import java.io.Serializable;

public class PrintTarget implements Serializable {

    /**
     * Print a string to choose the target
     */
    public static void print(){

        System.out.println();
        System.out.print("Target: ");
    }

    /**
     * Print a string to choose the target
     */
    public static void printNumbTarget(){

        System.out.println();
        System.out.print("Number of targets: ");
    }

    /**
     * Print a string to choose the target
     */
    public static void printSquare(){

        System.out.println();
        System.out.print("Square target: ");
    }

    /**
     * Print a string to choose the target
     */
    public static void printRoom(){

        System.out.println();
        System.out.println("Room target (insert ID corresponding to the color you want):");
    }

    /**
     * Print a string to choose the target
     */
    public static void printYesNo(){

        System.out.println();
        System.out.println("What's your choice?");
        System.out.println("0 -> YES");
        System.out.println("1 -> NO");
    }

    /**
     * Print a string to choose the target
     */
    public static void printCardinalDirection(){

        System.out.println();
        System.out.println("Choose a cardinal direction:");
        System.out.println("0 -> NORTH");
        System.out.println("1 -> SOUTH");
        System.out.println("2 -> EAST");
        System.out.println("3 -> WEST");
    }

    /**
     * Print a string to choose the target
     */
    public static void printColor(GameModel gameModel){
        int id=0;
        
        for (EnumColorSquare a: gameModel.getMap().getRoomColor()){
            
            System.out.println(id + a.toString());
        }
    }
}
