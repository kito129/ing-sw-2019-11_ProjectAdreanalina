package it.polimi.view.cli;

import it.polimi.model.*;

import java.io.Serializable;
import java.util.ArrayList;

public class PrintPlayerBoard implements Serializable {

    /**
     * Print attributes of PlayerBoard.
     */
    public static void print(Player player){

        System.out.println("BOARD VALUE: " +player.getPlayerBoard().getBoardValue());
        System.out.println("N. DEATHS: " +player.getPlayerBoard().getNumberOfDeaths());
        System.out.println("DAMAGES: " +player.getPlayerBoard().getDamages().toString());
        System.out.println("MARKS: " +player.getPlayerBoard().getMarks().toString());
        PrintAmmo.print(player);
    }

}
