package it.polimi.view.cli;

import it.polimi.model.EnumColorPlayer;
import it.polimi.model.KillShotTrackPoint;

import java.io.Serializable;
import java.util.ArrayList;

public class PrintKillShotTrack implements Serializable {

    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001b[0m";
    public static final String SKULL = "\u2620";

    /**
     * Print the killShotTrack
     * @param killShotTrackPoints   the killShotTrackPoint to be displayed with the corresponding colors
     */
    public static void print(ArrayList<KillShotTrackPoint> killShotTrackPoints){

        for(KillShotTrackPoint k : killShotTrackPoints){

            if(k.isSkull()){

                System.out.print(ANSI_RED + "SKULL" + ANSI_RESET + ANSI_BLACK_BACKGROUND + " ");
            }

            if(k.getMark1().equals(EnumColorPlayer.BLU)){

                System.out.print(ANSI_BLUE_BACKGROUND + "  " + ANSI_BLACK_BACKGROUND + " ");
            }

            if(k.getMark1().equals(EnumColorPlayer.GREEN)){

                System.out.print(ANSI_GREEN_BACKGROUND + "  " + ANSI_BLACK_BACKGROUND + " ");
            }

            if(k.getMark1().equals(EnumColorPlayer.GREY)){

                System.out.print(ANSI_WHITE_BACKGROUND + "  " + ANSI_BLACK_BACKGROUND + " ");
            }

            if(k.getMark1().equals(EnumColorPlayer.PINK)){

                System.out.print(ANSI_PURPLE_BACKGROUND + "  " + ANSI_BLACK_BACKGROUND + " ");
            }

            if(k.getMark1().equals(EnumColorPlayer.YELLOW)){

                System.out.print(ANSI_YELLOW_BACKGROUND + "  " + ANSI_BLACK_BACKGROUND + " ");
            }
        }
    }
}
