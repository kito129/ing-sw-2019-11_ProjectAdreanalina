package it.polimi.view.cli;

import it.polimi.model.EnumColorSquare;

import java.io.Serializable;
import java.util.ArrayList;

public class PrintEnumColorSquare implements Serializable {

    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";

    /**
     * Print the color corresponding to right square
     * @param enumColorSquares      the color of square
     */
    public static void print(ArrayList<EnumColorSquare> enumColorSquares){

        System.out.println();
        for(EnumColorSquare c : enumColorSquares){

            for(int i = 0; i < enumColorSquares.size(); i++){

                if(c.equals(EnumColorSquare.BLU)){

                    System.out.print(i + ") " + ANSI_BLUE_BACKGROUND + "  " + ANSI_BLACK_BACKGROUND + " ");
                }

                if(c.equals(EnumColorSquare.GREEN)){

                    System.out.print(i + ") " + ANSI_GREEN_BACKGROUND + "  " + ANSI_BLACK_BACKGROUND + " ");
                }

                if(c.equals(EnumColorSquare.PINK)){

                    System.out.print(i + ") " + ANSI_PURPLE_BACKGROUND + "  " + ANSI_BLACK_BACKGROUND + " ");
                }

                if(c.equals(EnumColorSquare.RED)){

                    System.out.print(i + ") " + ANSI_RED_BACKGROUND + "  " + ANSI_BLACK_BACKGROUND + " ");
                }

                if(c.equals(EnumColorSquare.YELLOW)){

                    System.out.print(i + ") " + ANSI_YELLOW_BACKGROUND + "  " + ANSI_BLACK_BACKGROUND + " ");
                }

                if(c.equals(EnumColorSquare.WHITE)){

                    System.out.print(i + ") " + ANSI_WHITE_BACKGROUND + "  " + ANSI_BLACK_BACKGROUND + " ");
                }
            }
        }
    }
}
