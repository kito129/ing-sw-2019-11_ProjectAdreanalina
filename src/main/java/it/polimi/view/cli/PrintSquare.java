package it.polimi.view.cli;

import it.polimi.model.*;

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
     * Get and set a single square: control if the row and column exists and set them with the corresponding color; if doesn't exists,
     * set that square with black color
     * @param square   the square selected
     */
    public static void getsetSquare(Square square) {

        String[][] s = new String[4][6];

        System.out.println("ROW: " +square.getRow());
        System.out.println("COLUMN: " +square.getColumn());

        if(square.getColor().equals(EnumColorSquare.BLU)){

            s[square.getRow()][square.getColumn()] = ANSI_BLUE_BACKGROUND + " " + ANSI_BLACK_BACKGROUND;
        }

        if(square.getColor().equals(EnumColorSquare.GREEN)){

            s[square.getRow()][square.getColumn()] = ANSI_GREEN_BACKGROUND + " " + ANSI_BLACK_BACKGROUND;
        }

        if(square.getColor().equals(EnumColorSquare.PINK)){

            s[square.getRow()][square.getColumn()] = ANSI_PURPLE_BACKGROUND + " " + ANSI_BLACK_BACKGROUND;
        }

        if(square.getColor().equals(EnumColorSquare.RED)){

            s[square.getRow()][square.getColumn()] = ANSI_RED_BACKGROUND + " " + ANSI_BLACK_BACKGROUND;
        }

        if(square.getColor().equals(EnumColorSquare.YELLOW)){

            s[square.getRow()][square.getColumn()] = ANSI_YELLOW_BACKGROUND + " " + ANSI_BLACK_BACKGROUND;
        }

        if(square.getColor().equals(EnumColorSquare.WHITE)){

            s[square.getRow()][square.getColumn()] = ANSI_WHITE_BACKGROUND + " " + ANSI_BLACK_BACKGROUND;
        }

        else {

            s[square.getRow()][square.getColumn()] = ANSI_BLACK_BACKGROUND + " ";
        }
    }

    /**
     * Print a single square of the map to view its attributes
     * @param normalSquare   the square selected
     */
    public static void printNormalSquare(NormalSquare normalSquare) {

        System.out.println("ROW: " +normalSquare.getRow());
        System.out.println("COLUMN: " +normalSquare.getColumn());
        PrintEnumCardsAmmo.print(normalSquare.getAmmoCard().getAmmo());

        //todo stampare a colori
    }

    /**
     * Print a single square of the map to view its attributes
     * @param generationSquare   the square selected
     */
    public static void printGenerationSquare(GenerationSquare generationSquare) {

        System.out.println("ROW: " +generationSquare.getRow());
        System.out.println("COLUMN: " +generationSquare.getColumn());
        System.out.println();
        System.out.println("WEAPON LIST: ");
        PrintWeapon.print(generationSquare.getWeaponList());

        //todo stampare a colori
    }
}