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

    /**
     * Print the killShotTrack
     * @param killShotTrackPoints   the killShotTrackPoint to be displayed with the corresponding colors
     */
    public static void print(ArrayList<KillShotTrackPoint> killShotTrackPoints){

        System.out.println("KILL SHOT TRACK: ");
        int i = 0;
        
        for(KillShotTrackPoint k : killShotTrackPoints) {
            
            i++;
    
            System.out.println();
            System.out.print("KST POINT " + i + ": ");
    
            if (k.isSkull()) {
        
                System.out.println(ANSI_RED + "SKULL" + ANSI_RESET + ANSI_BLACK_BACKGROUND + " ");
            }
    
            if (k.getMark1() != null) {
                
                switch (k.getMark1()) {
            
                    case BLU:
                        System.out.print(ANSI_BLUE_BACKGROUND + "  " + ANSI_BLACK_BACKGROUND + " ");
                
                        if (k.getMark2()!=null) {
                    
                            System.out.print(ANSI_BLUE_BACKGROUND + "  " + ANSI_BLACK_BACKGROUND + " ");
                        }
                        System.out.println();
                        break;
                    case GREEN:
                        System.out.print(ANSI_GREEN_BACKGROUND + "  " + ANSI_BLACK_BACKGROUND + " ");
                
                        if (k.getMark2()!=null) {
                    
                            System.out.print(ANSI_GREEN_BACKGROUND + "  " + ANSI_BLACK_BACKGROUND + " ");
                        }
                        System.out.println();
                        break;
                    case GREY:
                        System.out.print(ANSI_WHITE_BACKGROUND + "  " + ANSI_BLACK_BACKGROUND + " ");
                
                        if (k.getMark2()!=null) {
                    
                            System.out.print(ANSI_WHITE_BACKGROUND + "  " + ANSI_BLACK_BACKGROUND + " ");
                        }
                        System.out.println();
                        break;
                    case PINK:
                        System.out.print(ANSI_PURPLE_BACKGROUND + "  " + ANSI_BLACK_BACKGROUND + " ");
                
                        if (k.getMark2()!=null) {
                    
                            System.out.print(ANSI_PURPLE_BACKGROUND + "  " + ANSI_BLACK_BACKGROUND + " ");
                        }
                        System.out.println();
                        break;
                    case YELLOW:
                        System.out.print(ANSI_YELLOW_BACKGROUND + "  " + ANSI_BLACK_BACKGROUND + " ");
                
                        if (k.getMark2()!=null) {
                    
                            System.out.print(ANSI_YELLOW_BACKGROUND + "  " + ANSI_BLACK_BACKGROUND + " ");
                        }
                        System.out.println();
                        break;
                    default:
                        break;
                }
            }
        }
    }
}