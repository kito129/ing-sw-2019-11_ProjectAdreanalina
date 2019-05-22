package it.polimi.view.cli;

import it.polimi.model.EnumColorPlayer;
import it.polimi.model.EnumColorSquare;
import it.polimi.model.Player;
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

    /**
     * Get and set map: control if the row and column exists and set them with the corresponding color; if doesn't exists,
     * set that square with black color
     * @param squares   the map choosen for the game
     */
    private static String[][] getMap(ArrayList<Square> squares) {

        String[][] map = new String[14][15];

        for (Square s : squares) {

            for (int i = 0; i < 15; i++) {

                //per colorare il generation square rosso a lato
                if(i >= 6 && i <= 9){

                    map[i][0] = ANSI_RED + "|" + ANSI_RESET;
                }

                for (int j = 0; j < 16; j++) {

                    if (s.getRow() == (i-1)/5 && i!=5 && i!=10 && s.getColumn() == (j-1)/4 && j!=4 && i!=8 && i!=12) {

                        //per colorare il generation square blu sopra
                        if(i == 0 && j >= 9 && j <= 11){

                            map[0][j] = ANSI_BLUE + "-" + ANSI_RESET;
                        }

                        //BLU SQUARE
                        if (s.getColor().equals(EnumColorSquare.BLU)) {

                            map[i][j] = ANSI_BLUE_BACKGROUND + " " + ANSI_BLACK_BACKGROUND;
                        }

                        //GREEN SQUARE
                        if (s.getColor().equals(EnumColorSquare.GREEN)) {

                            map[i][j] = ANSI_GREEN_BACKGROUND + " " + ANSI_BLACK_BACKGROUND;
                        }

                        //PINK SQUARE
                        if (s.getColor().equals(EnumColorSquare.PINK)) {

                            map[i][j] = ANSI_PURPLE_BACKGROUND + " " + ANSI_BLACK_BACKGROUND;
                        }

                        //RED SQUARE
                        if (s.getColor().equals(EnumColorSquare.RED)) {

                            map[i][j] = ANSI_RED_BACKGROUND + " " + ANSI_BLACK_BACKGROUND;
                        }

                        //YELLOW SQUARE
                        if (s.getColor().equals(EnumColorSquare.YELLOW)) {

                            if(j < 15){

                                map[i][j] = ANSI_YELLOW_BACKGROUND + " " + ANSI_BLACK_BACKGROUND;
                            }
                            else{

                                map[i][j] = ANSI_YELLOW_BACKGROUND + " " + ANSI_BLACK_BACKGROUND + ANSI_YELLOW + "|" + ANSI_RESET;
                            }
                        }

                        //WHITE SQUARE
                        if (s.getColor().equals(EnumColorSquare.WHITE)) {

                            map[i][j] = ANSI_WHITE_BACKGROUND + " " + ANSI_BLACK_BACKGROUND;
                        }

                        //BLACK SQUARE
                    } else {

                        map[i][j] = ANSI_BLACK_BACKGROUND + " ";
                    }
                }
            }
        }
        return map;
    }

    /**
     * Set the right string to view for CLI to the corresponding color
     * @param player      the selected player
     */
    private static String colorString(Player player){

        String s = "";

        if(player.getColor().equals(EnumColorPlayer.BLU)){

            s = ANSI_BLUE_BACKGROUND + "B" + ANSI_BLACK_BACKGROUND;
        }
        else if(player.getColor().equals(EnumColorPlayer.GREEN)){

            s = ANSI_GREEN_BACKGROUND + "G" + ANSI_BLACK_BACKGROUND;
        }
        else if(player.getColor().equals(EnumColorPlayer.GREY)){

            s = ANSI_WHITE_BACKGROUND + "G" + ANSI_BLACK_BACKGROUND;
        }
        else if(player.getColor().equals(EnumColorPlayer.PINK)){

            s = ANSI_PURPLE_BACKGROUND + "G" + ANSI_BLACK_BACKGROUND;
        }
        else if(player.getColor().equals(EnumColorPlayer.YELLOW)){

            s = ANSI_YELLOW_BACKGROUND + "G" + ANSI_BLACK_BACKGROUND;
        }

        return s;
    }

    /**
     * Set the initial letter corresponding to the player's color in the right position to view the player on the map
     * Set the string "amm" in the right position (NormalSquare)
     * @param squares   the squares of the map
     * @param map       the map choosen for the game
     */
    public static String[][] getPlayersOnMap(ArrayList<Square> squares, String[][] map){

        for (Square s : squares){

            if(s.getPlayers().size() == 1){

                map[5*s.getRow()+3][4*s.getColumn()+2] = colorString(s.getPlayers().get(0));
            }
            else if(s.getPlayers().size() == 2){

                map[5*s.getRow()+3][4*s.getColumn()+1] = colorString(s.getPlayers().get(0));
                map[5*s.getRow()+3][4*s.getColumn()+3] = colorString(s.getPlayers().get(1));
            }
            else if(s.getPlayers().size() == 3){

                map[5*s.getRow()+3][4*s.getColumn()+1] = colorString(s.getPlayers().get(0));
                map[5*s.getRow()+3][4*s.getColumn()+2] = colorString(s.getPlayers().get(1));
                map[5*s.getRow()+3][4*s.getColumn()+3] = colorString(s.getPlayers().get(2));
            }
            else if(s.getPlayers().size() == 4){

                map[5*s.getRow()+2][4*s.getColumn()+1] = colorString(s.getPlayers().get(0));
                map[5*s.getRow()+2][4*s.getColumn()+3] = colorString(s.getPlayers().get(1));
                map[5*s.getRow()+4][4*s.getColumn()+1] = colorString(s.getPlayers().get(2));
                map[5*s.getRow()+4][4*s.getColumn()+3] = colorString(s.getPlayers().get(3));
            }
            else if(s.getPlayers().size() == 5){

                map[5*s.getRow()+2][4*s.getColumn()+1] = colorString(s.getPlayers().get(0));
                map[5*s.getRow()+2][4*s.getColumn()+3] = colorString(s.getPlayers().get(1));
                map[5*s.getRow()+3][4*s.getColumn()+2] = colorString(s.getPlayers().get(2));
                map[5*s.getRow()+4][4*s.getColumn()+1] = colorString(s.getPlayers().get(3));
                map[5*s.getRow()+4][4*s.getColumn()+3] = colorString(s.getPlayers().get(4));
            }

            //per scrivere "amm" sopra ai normal square
            if(!(s.getRow() == 0 && s.getColumn() == 2  || s.getRow() == 1 && s.getColumn() == 0 || s.getRow() == 2 && s.getColumn() == 3)){

                map[5*s.getRow()+1][4*s.getColumn()+1] = "a";
                map[5*s.getRow()+1][4*s.getColumn()+2] = "m";
                map[5*s.getRow()+1][4*s.getColumn()+3] = "m";
            }
        }
        return map;
    }

    /**
     * Print map.
     * @param squares   the squares of the map choosen for the game
     */
    public static void printMap(ArrayList<Square> squares) {

        String[][] map = getMap(squares);
        map = getPlayersOnMap(squares, map);

        for(int i = 0; i < 15; i++){

            if(i == 2 || i == 7 || i == 12){

                System.out.print(" " + ((i/5)+1) + " ");
            }
            else {

                System.out.print("   ");
            }
            for(int j = 0; j < 16; j++){

                System.out.print(map[i][j]);
            }
            System.out.println();
        }
        //TODO per le porte bisogna verificare se fra due indici di riga/colonna seguenti è presente un link
    }
}