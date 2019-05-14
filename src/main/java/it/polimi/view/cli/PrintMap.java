package it.polimi.view.cli;

import it.polimi.model.EnumColorSquare;
import it.polimi.model.Square;

import java.io.Serializable;
import java.util.ArrayList;

public class PrintMap implements Serializable {

    //⇅ doppia freccia verticale
    //⇄ doppia freccia orizzontale

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

    public static String[][] map = new String[3][4];

    /**
     * Get and set map: control if the row and column exists and set them with the corresponding color; if doesn't exists,
     * set that square with black color
     * @param map   the map choosen for the game
     */
    public static void getMap(ArrayList<Square> squares) {

        for (Square s : squares) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 4; j++) {
                    if (s.getRow() == i && s.getColumn() == j) {

                        //BLU SQUARE
                        if (s.getColor().equals(EnumColorSquare.BLU)) {
                            map[s.getRow()][s.getColumn()] = " " + ANSI_BLUE_BACKGROUND + "   " + ANSI_BLACK_BACKGROUND + " ";
                        }

                        //GREEN SQUARE
                        if (s.getColor().equals(EnumColorSquare.GREEN)) {
                            map[s.getRow()][s.getColumn()] = " " + ANSI_GREEN_BACKGROUND + "   " + ANSI_BLACK_BACKGROUND + " ";
                        }

                        //PINK SQUARE
                        if (s.getColor().equals(EnumColorSquare.PINK)) {
                            map[s.getRow()][s.getColumn()] = " " + ANSI_PURPLE_BACKGROUND + "   " + ANSI_BLACK_BACKGROUND + " ";
                        }

                        //RED SQUARE
                        if (s.getColor().equals(EnumColorSquare.RED) && i == 1 && j == 0) {
                            map[s.getRow()][s.getColumn()] = ANSI_RED + "w" + ANSI_RESET + ANSI_RED_BACKGROUND + "   " + ANSI_BLACK_BACKGROUND + " ";
                        }
                        else if (s.getColor().equals(EnumColorSquare.RED)) {
                            map[s.getRow()][s.getColumn()] = " " + ANSI_RED_BACKGROUND + "   " + ANSI_BLACK_BACKGROUND + " ";
                        }

                        //YELLOW SQUARE
                        if (s.getColor().equals(EnumColorSquare.YELLOW) && i == 2 && j == 3) {
                            map[s.getRow()][s.getColumn()] = " " + ANSI_YELLOW_BACKGROUND + "   " + ANSI_BLACK_BACKGROUND + ANSI_YELLOW + "w" + ANSI_RESET;
                        }
                        else if (s.getColor().equals(EnumColorSquare.YELLOW)) {
                            map[s.getRow()][s.getColumn()] = " " + ANSI_YELLOW_BACKGROUND + "   " + ANSI_BLACK_BACKGROUND + " ";
                        }

                        //WHITE SQUARE
                        if (s.getColor().equals(EnumColorSquare.WHITE)) {
                            map[s.getRow()][s.getColumn()] = " " + ANSI_WHITE_BACKGROUND + "   " + ANSI_BLACK_BACKGROUND + " ";
                        }

                        //BLACK SQUARE
                        } else {
                            map[s.getRow()][s.getColumn()] = ANSI_BLACK_BACKGROUND + "  " + ANSI_BLACK_BACKGROUND + " ";
                        }
                    }
                }

            }
        }

    }

    /**
     * Print map.
     * @param map   the map choosen for the game
     */
    public static void printMap(String[][] map) {
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 4; j++){
                System.out.print(map[i][j]);
            }
            System.out.println();
            System.out.println();
        }
        //TODO fare in modo che stampa un segno in corrispondenza dei punti di generazione (che sono sempre gli stessi)
        //TODO per le porte bisogna verificare se fra due indici di riga/colonna seguenti è presente un link
    }
}

