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
    public static int maxR=0;
    public static int maxC=0;

    public static void print(ArrayList<Square> squares) {

        for(Square s : squares) {
            if (s.getColor().equals(EnumColorSquare.BLU)) {
                map[s.getRow()][s.getColumn()] = ANSI_BLUE_BACKGROUND + " ";
            }
            if (s.getColor().equals(EnumColorSquare.GREEN)) {
                map[s.getRow()][s.getColumn()] = ANSI_GREEN_BACKGROUND + " ";
            }
            if (s.getColor().equals(EnumColorSquare.PINK)) {
                map[s.getRow()][s.getColumn()] = ANSI_PURPLE_BACKGROUND + " ";
            }
            if (s.getColor().equals(EnumColorSquare.RED)) {
                map[s.getRow()][s.getColumn()] = ANSI_RED_BACKGROUND + " ";
            }
            if (s.getColor().equals(EnumColorSquare.YELLOW)) {
                map[s.getRow()][s.getColumn()] = ANSI_YELLOW_BACKGROUND + " ";
            }
            if (s.getColor().equals(EnumColorSquare.WHITE)) {
                map[s.getRow()][s.getColumn()] = ANSI_WHITE_BACKGROUND + " ";
            }
            if(s.getRow()>maxR){
                maxR=s.getRow();
            }
            if(s.getColumn()>maxC){
                maxC=s.getColumn();
            }

        }

        /* print map */
        for(int i=0; i<maxR; i++) {
            for (int j = 0; j < maxC; j++) {
                System.out.print(map[i][j] + ANSI_BLACK_BACKGROUND + " ");
            }
            System.out.println();
        }
    }
}

