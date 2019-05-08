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

    public static void print(ArrayList<Square> squares) {

        for(Square s : squares) {
            if (s.getColor().equals(EnumColorSquare.BLU)) {
                map[s.getRow()][s.getColumn()] = " ";
            }
            if (s.getColor().equals(EnumColorSquare.GREEN)) {
                map[s.getRow()][s.getColumn()] = " ";
            }
            if (s.getColor().equals(EnumColorSquare.PINK)) {
                map[s.getRow()][s.getColumn()] = " ";
            }
            if (s.getColor().equals(EnumColorSquare.RED)) {
                map[s.getRow()][s.getColumn()] = " ";
            }
            if (s.getColor().equals(EnumColorSquare.YELLOW)) {
                map[s.getRow()][s.getColumn()] = " ";
            }
            if (s.getColor().equals(EnumColorSquare.WHITE)) {
                map[s.getRow()][s.getColumn()] = " ";
            }
        }
    }
}

