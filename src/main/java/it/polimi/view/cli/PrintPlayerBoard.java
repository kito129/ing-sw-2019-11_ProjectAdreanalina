package it.polimi.view.cli;

import it.polimi.model.*;

import java.io.Serializable;

public class PrintPlayerBoard implements Serializable {

    /**
     * Print attributes of PlayerBoard.
     */
    public static void print(Player player){

        System.out.println();
        System.out.println("BOARD VALUE: " +player.getPlayerBoard().getBoardValue());
        PrintEnumCardsAmmo.print(player.getPlayerBoard().getAmmo());
        System.out.println();
        System.out.print("DAMAGES: ");
        PrintDamagesAndMarks.printDamages(player);
        System.out.println();
        System.out.print("MARKS: ");
        PrintDamagesAndMarks.printMarks(player);
        System.out.println();
        System.out.print("AMMO: ");
        PrintEnumCardsAmmo.print(player.getPlayerBoard().getAmmo());
        }
}
