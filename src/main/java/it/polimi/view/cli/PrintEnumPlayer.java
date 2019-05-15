package it.polimi.view.cli;

import it.polimi.model.EnumColorPlayer;

import java.io.Serializable;
import java.util.ArrayList;

public class PrintEnumPlayer implements Serializable {

    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    /**
     * Print the color corresponding to right damage/mark
     * @param enumColorPlayers      the color of players
     */
    public static void print(ArrayList<EnumColorPlayer> enumColorPlayers){

        for(EnumColorPlayer a : enumColorPlayers){

            switch(a){

                case BLU:
                    System.out.print(ANSI_BLUE_BACKGROUND + "  " + ANSI_BLACK_BACKGROUND + " ");

                case GREEN:
                    System.out.print(ANSI_GREEN_BACKGROUND + "  " + ANSI_BLACK_BACKGROUND + " ");

                case GREY:
                    System.out.print(ANSI_WHITE_BACKGROUND + "  " + ANSI_BLACK_BACKGROUND + " ");

                case PINK:
                    System.out.print(ANSI_PURPLE_BACKGROUND + "  " + ANSI_BLACK_BACKGROUND + " ");

                case YELLOW:
                    System.out.print(ANSI_YELLOW_BACKGROUND + "  " + ANSI_BLACK_BACKGROUND + " ");
            }
        }
    }
}