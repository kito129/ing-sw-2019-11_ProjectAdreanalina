package it.polimi.view.cli;

import it.polimi.model.EnumColorSquare;
import it.polimi.model.GenerationSquare;
import it.polimi.model.NormalSquare;

import java.io.Serializable;

public class PrintSquare implements Serializable {

    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RESET = "\u001b[0m";

    /**
     * Print a single square of the map to view its attributes
     * @param normalSquare   the square selected
     */
    public static void printNormalSquare(NormalSquare normalSquare) {

        System.out.println("ROW: " +normalSquare.getRow());
        System.out.println("COLUMN: " +normalSquare.getColumn());

        if(normalSquare.getColor().equals(EnumColorSquare.BLU)){
            //todo
        }

    }

    /**
     * Print a single square of the map to view its attributes
     * @param generationSquare   the square selected
     */
    public static void printGenerationSquare(GenerationSquare generationSquare) {

        System.out.println("ROW: " +generationSquare.getRow());
        System.out.println("COLUMN: " +generationSquare.getColumn());

        if(generationSquare.getColor().equals(EnumColorSquare.BLU)){
            //todo
        }

    }
}
