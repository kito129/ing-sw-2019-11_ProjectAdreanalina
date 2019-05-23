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
    private static String[][] getsetSquare(Square square) {

        String[][] s = new String[4][6];

        //BLU SQUARE
        if(square.getColor().equals(EnumColorSquare.BLU)){

            s[square.getRow()][square.getColumn()] = ANSI_BLUE_BACKGROUND + " " + ANSI_BLACK_BACKGROUND;
        }

        //GREEN SQUARE
        if(square.getColor().equals(EnumColorSquare.GREEN)){

            s[square.getRow()][square.getColumn()] = ANSI_GREEN_BACKGROUND + " " + ANSI_BLACK_BACKGROUND;
        }

        //PINK SQUARE
        if(square.getColor().equals(EnumColorSquare.PINK)){

            s[square.getRow()][square.getColumn()] = ANSI_PURPLE_BACKGROUND + " " + ANSI_BLACK_BACKGROUND;
        }

        //RED SQUARE
        if(square.getColor().equals(EnumColorSquare.RED)){

            s[square.getRow()][square.getColumn()] = ANSI_RED_BACKGROUND + " " + ANSI_BLACK_BACKGROUND;
        }

        //YELLOW SQUARE
        if(square.getColor().equals(EnumColorSquare.YELLOW)){

            s[square.getRow()][square.getColumn()] = ANSI_YELLOW_BACKGROUND + " " + ANSI_BLACK_BACKGROUND;
        }

        //WHITE SQUARE
        if(square.getColor().equals(EnumColorSquare.WHITE)){

            s[square.getRow()][square.getColumn()] = ANSI_WHITE_BACKGROUND + " " + ANSI_BLACK_BACKGROUND;
        }

        //BLACK SQUARE
        else {

            s[square.getRow()][square.getColumn()] = ANSI_BLACK_BACKGROUND + " ";
        }
        return s;
    }

    /**
     * Set the initial letter corresponding to the player's color in the right position to view the player on the square
     * @param square   the square selected
     * @param s       the square which want to see
     */
    private static String[][] getsetPlayerOnSquare(Square square, String[][] s){

        if(square.getPlayers().size() == 1){

            s[3][3] = PrintMap.colorString(square.getPlayers().get(0));
        }
        else if(square.getPlayers().size() == 2){

            s[3][2] = PrintMap.colorString(square.getPlayers().get(0));
            s[3][4] = PrintMap.colorString(square.getPlayers().get(1));
        }
        else if(square.getPlayers().size() == 3){

            s[2][3] = PrintMap.colorString(square.getPlayers().get(0));
            s[3][2] = PrintMap.colorString(square.getPlayers().get(1));
            s[3][4] = PrintMap.colorString(square.getPlayers().get(2));
        }
        else if(square.getPlayers().size() == 4){

            s[2][3] = PrintMap.colorString(square.getPlayers().get(0));
            s[3][2] = PrintMap.colorString(square.getPlayers().get(1));
            s[3][4] = PrintMap.colorString(square.getPlayers().get(2));
            s[4][3] = PrintMap.colorString(square.getPlayers().get(3));
        }
        else if(square.getPlayers().size() == 5){

            s[2][2] = PrintMap.colorString(square.getPlayers().get(0));
            s[2][4] = PrintMap.colorString(square.getPlayers().get(1));
            s[3][3] = PrintMap.colorString(square.getPlayers().get(2));
            s[4][2] = PrintMap.colorString(square.getPlayers().get(3));
            s[4][4] = PrintMap.colorString(square.getPlayers().get(4));
        }
        return s;
    }

    /**
     * Print a single square of the map to view its attributes
     * @param normalSquare   the square selected
     */
    public static void printNormalSquare(NormalSquare normalSquare) {

        String[][] s = getsetSquare(normalSquare);
        s = getsetPlayerOnSquare(normalSquare, s);

        System.out.println("ROW: " +normalSquare.getRow());
        System.out.println("COLUMN: " +normalSquare.getColumn());
        System.out.println();
        System.out.println("AMMO:");
        PrintEnumCardsAmmo.print(normalSquare.getAmmoCard().getAmmo());

        //print normalSquare
        for(int i=0; i<5; i++){

            for(int j=0; j<7; j++){

                System.out.print(s[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Print a single square of the map to view its attributes
     * @param generationSquare   the square selected
     */
    public static void printGenerationSquare(GenerationSquare generationSquare) {

        String[][] s = getsetSquare(generationSquare);
        s = getsetPlayerOnSquare(generationSquare, s);

        System.out.println("ROW: " +generationSquare.getRow());
        System.out.println("COLUMN: " +generationSquare.getColumn());

        //print generationSquare
        for(int i=0; i<5; i++){

            for(int j=0; j<7; j++){

                System.out.print(s[i][j]);
            }
            System.out.println();
        }

        System.out.println();
        System.out.println("WEAPON LIST:");
        PrintWeapon.print(generationSquare.getWeaponList());
    }
}