package it.polimi.view.cli;

import it.polimi.model.EnumColorPlayer;
import it.polimi.model.Player;

import java.io.Serializable;

public class PrintPlayerInfo implements Serializable {

    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    /**
     * Print Player's info.
     * @param player   the player to print
     */
    public static void print(Player player){

        System.out.println();
        if(player.getColor().equals(EnumColorPlayer.BLU)){

            System.out.println("COLOR: " + ANSI_BLUE_BACKGROUND + "  " + ANSI_BLACK_BACKGROUND);
        }
        if(player.getColor().equals(EnumColorPlayer.GREEN)){

            System.out.println("COLOR: " + ANSI_GREEN_BACKGROUND + "  " + ANSI_BLACK_BACKGROUND);
        }
        if(player.getColor().equals(EnumColorPlayer.GREY)){

            System.out.println("COLOR: " + ANSI_WHITE_BACKGROUND + "  " + ANSI_BLACK_BACKGROUND);
        }
        if(player.getColor().equals(EnumColorPlayer.PINK)){

            System.out.println("COLOR: " + ANSI_PURPLE_BACKGROUND + "  " + ANSI_BLACK_BACKGROUND);
        }
        if(player.getColor().equals(EnumColorPlayer.YELLOW)){

            System.out.println("COLOR: " + ANSI_YELLOW_BACKGROUND + "  " + ANSI_BLACK_BACKGROUND);
        }

        System.out.println("ID: " +player.getId());
        System.out.println("NAME: " +player.getName());
    }
}
