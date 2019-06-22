package it.polimi.view.cli;

import it.polimi.model.*;
import it.polimi.model.Exception.MapException;

import java.io.Serializable;
import java.util.ArrayList;

public class CLIPrintMap implements Serializable {

    public  final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public  final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public  final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public  final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public  final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public  final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public  final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    public  final String ANSI_BLUE = "\u001B[34m";
    public  final String ANSI_RED = "\u001B[31m";
    public  final String ANSI_YELLOW = "\u001B[33m";
    public  final String ANSI_BLACK = "\u001B[30m";
    public  final String ANSI_RESET = "\u001b[0m";
    public String[][] map;
    public String[][] square;
    public ArrayList<String[][]> squares;
    
    public ArrayList<ArrayList<ArrayList<ArrayList<String>>>> Newmap;
    
    
    GameModel gameModel;
    
    
    public  CLIPrintMap(GameModel gameModel){

        this.gameModel = gameModel;
        //this.Newmap = new String[15][25];
        this.Newmap = new ArrayList<ArrayList<ArrayList<ArrayList<String>>>>();
        this.squares = new ArrayList<String [][]>();
        
        for (int i =0; i<12;i++){
            
            squares.add(new String [5][6]);
        }
    }
    
    public ArrayList<ArrayList<String >>  createSquare(Square s){
    
         ArrayList<ArrayList<String >> singleSquare = new ArrayList<ArrayList<String>>();
         String colorToWrite = createStringColor(s.getColor());
         
         for (int i =0;i<5;i++){
             singleSquare.add(new ArrayList<>());
         }
         
        for (int row =0;row<5;row++){
            for (int column =0;column<5;column++){
               singleSquare.get(row).add(colorToWrite);
            }
        }
        return singleSquare;
    }
    
    public ArrayList<ArrayList<String >>  createBlackSquare(){
        
        ArrayList<ArrayList<String >> blackSquare = new ArrayList<ArrayList<String>>();
        String colorToWrite = ANSI_BLACK_BACKGROUND + " ";
    
        for (int i =0;i<5;i++){
            blackSquare.add(new ArrayList<>());
        }
        
        for (int row =0;row<5;row++){
            for (int column =0;column<5;column++){
                blackSquare.get(row).add(colorToWrite);
            }
        }
        return blackSquare;
    }
    
    
    public void createGrid(){
    
        ArrayList<ArrayList<ArrayList<ArrayList<String>>>> map = this.Newmap;
        String  test;
    
        for (int i =0;i<3;i++){
            map.add(new ArrayList<>());
        }
    
        try {
        for (int row =0;row<3;row++) {
            for (int column = 0; column < 4; column++) {
                if (gameModel.getMap().existInMap(row, column)) {
                    
                    map.get(row).add(createSquare(gameModel.getMap().getSquare(row, column)));
                } else {
                    
                    map.get(row).add(createBlackSquare());
                    
                }
                
            }
        }
        } catch (MapException e) {
            e.printStackTrace();
        }
    }
    
    public void printGrid() {
    
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 4; column++) {
                for (int rowSquare = 0; rowSquare < 5; rowSquare++) {
                    for (int columnSquare = 0; columnSquare < 5; columnSquare++) {
    
                        System.out.print(Newmap.get(row).get(column).get(rowSquare).get(columnSquare));
    
                    }
                }
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public void createListSquare(){
        
        for (int row=0;row<3;row++){
            
            for (int column=0;column<4;column++){
                
                if(gameModel.getMap().existInMap(row,column)){
                
                
            }
        }
        }
    }
    
    
    public void viewMapNew() {
        
        createGrid();
        printGrid();
        
        
    }
    
    
    
    
    
    
    public String createStringColor(EnumColorSquare colorSquare){
    
        String string = new String();
        //BLU SQUARE
        switch (colorSquare) {
            case BLU:
                string = ANSI_BLUE_BACKGROUND + " " + ANSI_BLACK_BACKGROUND;
                break;
            case GREEN:
                string = ANSI_GREEN_BACKGROUND + " " + ANSI_BLACK_BACKGROUND;
                break;
            case PINK:
                string = ANSI_PURPLE_BACKGROUND + " " + ANSI_BLACK_BACKGROUND;
                break;
            case RED:
                string= ANSI_RED_BACKGROUND + " " + ANSI_BLACK_BACKGROUND;
                break;
            case YELLOW:
                string= ANSI_YELLOW_BACKGROUND + " " + ANSI_BLACK_BACKGROUND;
                break;
            case WHITE:
                string= ANSI_WHITE_BACKGROUND + " " + ANSI_BLACK_BACKGROUND;
                break;
            default:
                string = ANSI_BLACK_BACKGROUND + " ";
                break;
        }
        return string;
    }

    
    /**
    public void createMap(){
        
        int count;
    
    
        for (int row =0; row<3;row ++){
            for (int column=0;column<4;column++) {
                
                Newmap[row][column] = squares.get(row+column);
            }
        
    
    
    }

    
     * Get and set Newmap: control if the row and column exists and set them with the corresponding color; if doesn't exists,
     * set that square with black color
     * @param squares   the Newmap choosen for the game
     
    private void getSetMap (ArrayList<Square> squares) {

        String[][] Newmap = this.Newmap;

        
        for (int row = 0; row < 15; row++) {

            for (int column = 0; column < 25; column++) {
        
                //secondo me qui deve essere in or o comuqnue non tutte in and perchè andrai quasi sempre nell'if
                if (s.getRow() == (row - 1) / 5 && row != 5 && row != 10 && row != 0 && s.getColumn() == (column - 1) / 6 && column !=  0 && column != 6 && column != 12 && column != 18) {

                    //BLU SQUARE
                    switch (s.getColor()) {
                        case BLU:
                            Newmap[row][column] = ANSI_BLUE_BACKGROUND + " " + ANSI_BLACK_BACKGROUND;
                            break;
                        case GREEN:
                            Newmap[row][column] = ANSI_GREEN_BACKGROUND + " " + ANSI_BLACK_BACKGROUND;
                            break;
                        case PINK:
                            Newmap[row][column] = ANSI_PURPLE_BACKGROUND + " " + ANSI_BLACK_BACKGROUND;
                            break;
                        case RED:
                            Newmap[row][column] = ANSI_RED_BACKGROUND + " " + ANSI_BLACK_BACKGROUND;
                            break;
                        case YELLOW:
                            Newmap[row][column] = ANSI_YELLOW_BACKGROUND + " " + ANSI_BLACK_BACKGROUND;
                            break;
                        case WHITE:
                            Newmap[row][column] = ANSI_WHITE_BACKGROUND + " " + ANSI_BLACK_BACKGROUND;
                            break;
                        default:
                            Newmap[row][column] = ANSI_BLACK_BACKGROUND + " ";
                            break;
                        }
                } else {

                    Newmap[row][column] = ANSI_BLACK_BACKGROUND + " ";
                }
            }
        }
        
    }

    public void putGenerationSquare(Map m) {

        String[][] Newmap = this.Newmap;

        for (Square s : m.getSquares()) {

            for (int row = 0; row < 15; row++) {

                for (int col = 0; col < 25; col++) {

                    if (s.getRow() == 0) {

                        //per colorare il generation square blu sopra
                        if (col >= 13 && col <= 17) {

                            Newmap[0][col] = ANSI_BLUE + "-" + ANSI_RESET;
                        }
                    }

                    if (s.getRow() == 1) {

                        //per colorare il generation square rosso a lato
                        if (row >= 6 && row <= 9) {

                            Newmap[row][0] = ANSI_RED + "|" + ANSI_RESET;
                        }
                    }

                    if (s.getRow() == 2) {

                        //per colorare il generation square giallo a lato
                        if (row >= 11 && row <= 14) {

                            Newmap[row][24] = ANSI_YELLOW + "|" + ANSI_RESET;
                        }
                    }
                }
            }
        }
    }
    */

    /**
     * Set the right string to view for CLI to the corresponding color
     * @param player      the selected player
     */
    public String colorString(Player player, ArrayList<Square> squares){

        String s = "";
        String colorPlayer = player.getColor().toString();

        if(colorPlayer == "BLU"){

            colorPlayer = "B";
        }

        if(colorPlayer == "GREEN"){

            colorPlayer = "G";
        }

        if(colorPlayer == "GREY"){

            colorPlayer = "W";
        }

        if(colorPlayer == "PINK"){

            colorPlayer = "P";
        }

        if(colorPlayer == "YELLOW"){

            colorPlayer = "Y";
        }

        for(Square sq : squares){

            if(sq.getColor().equals(EnumColorSquare.BLU)) {

                s = ANSI_BLUE_BACKGROUND + colorPlayer + ANSI_BLACK_BACKGROUND;
            }

            if(sq.getColor().equals(EnumColorSquare.GREEN)) {

                s = ANSI_GREEN_BACKGROUND + colorPlayer + ANSI_BLACK_BACKGROUND;
            }

            if(sq.getColor().equals(EnumColorSquare.PINK)) {

                s = ANSI_PURPLE_BACKGROUND + colorPlayer + ANSI_BLACK_BACKGROUND;
            }

            if(sq.getColor().equals(EnumColorSquare.RED)) {

                s = ANSI_RED_BACKGROUND + colorPlayer + ANSI_BLACK_BACKGROUND;
            }

            if(sq.getColor().equals(EnumColorSquare.WHITE)) {

                s = ANSI_WHITE_BACKGROUND + ANSI_BLACK + colorPlayer + ANSI_RESET + ANSI_BLACK_BACKGROUND;
            }

            if(sq.getColor().equals(EnumColorSquare.YELLOW)) {

                s = ANSI_YELLOW_BACKGROUND + ANSI_BLACK + colorPlayer + ANSI_RESET + ANSI_BLACK_BACKGROUND;
            }
        }
        return s;
    }

    /**
     * Set the initial letter corresponding to the player's color in the right position to view the player on the Newmap
     * Set the string "amm" in the right position (NormalSquare)
     * @param squares   the squares of the Newmap
     
    private void getSetPlayersOnMap (ArrayList<Square> squares){

        String[][] map = this.Newmap;

        for (Square s : squares){

            if(s.getPlayers().size() == 1){

                map[5*s.getRow()+3][6*s.getColumn()+3] = colorString(s.getPlayers().get(0), squares);
            }
            else if(s.getPlayers().size() == 2){

                map[5*s.getRow()+3][6*s.getColumn()+2] = colorString(s.getPlayers().get(0), squares);
                map[5*s.getRow()+3][6*s.getColumn()+4] = colorString(s.getPlayers().get(1), squares);
            }
            else if(s.getPlayers().size() == 3){

                map[5*s.getRow()+2][6*s.getColumn()+3] = colorString(s.getPlayers().get(0), squares);
                map[5*s.getRow()+3][6*s.getColumn()+2] = colorString(s.getPlayers().get(1), squares);
                map[5*s.getRow()+3][6*s.getColumn()+4] = colorString(s.getPlayers().get(2), squares);
            }
            else if(s.getPlayers().size() == 4){

                map[5*s.getRow()+2][6*s.getColumn()+3] = colorString(s.getPlayers().get(0), squares);
                map[5*s.getRow()+3][6*s.getColumn()+2] = colorString(s.getPlayers().get(1), squares);
                map[5*s.getRow()+3][6*s.getColumn()+4] = colorString(s.getPlayers().get(2), squares);
                map[5*s.getRow()+4][6*s.getColumn()+3] = colorString(s.getPlayers().get(3), squares);
            }
            else if(s.getPlayers().size() == 5){

                map[5*s.getRow()+2][6*s.getColumn()+2] = colorString(s.getPlayers().get(0), squares);
                map[5*s.getRow()+2][6*s.getColumn()+4] = colorString(s.getPlayers().get(1), squares);
                map[5*s.getRow()+3][6*s.getColumn()+3] = colorString(s.getPlayers().get(2), squares);
                map[5*s.getRow()+4][6*s.getColumn()+2] = colorString(s.getPlayers().get(3), squares);
                map[5*s.getRow()+4][6*s.getColumn()+4] = colorString(s.getPlayers().get(4), squares);
            }

            //per scrivere "ammo" sopra ai normal square
            if(!(s.getRow() == 0 && s.getColumn() == 2  || s.getRow() == 1 && s.getColumn() == 0 || s.getRow() == 2 && s.getColumn() == 3)){

                map[5*s.getRow()+1][6*s.getColumn()+1] = "a";
                map[5*s.getRow()+1][6*s.getColumn()+2] = "m";
                map[5*s.getRow()+1][6*s.getColumn()+3] = "m";
                map[5*s.getRow()+1][6*s.getColumn()+4] = "o";
            }
        }
    }

    /**
     * Set the doors on the Newmap.
     * @param m   the Newmap choosen for the game
     
    private void putDoors (Map m){

        String[][] map = this.Newmap;

        //characters for doors ??
        char degrees = '\u00B0'; //non va ma fa vedere un cuore anziché °
        char Hdoor = '\u21D4'; //non va (si vede ?)
        char Vdoor = '\u21D5'; //non va (si vede ?)

        for (Square s : m.getSquares()){

            if(s.getColumn()<3 && m.isPort(s.getRow(),s.getColumn(),s.getRow(),s.getColumn()+1)){

                for(int i = 1; i <= 4; i++){

                    map[5*s.getRow()+i][6*(s.getColumn()+1)] = "-";
                }
            }

            if(s.getRow()<2 && m.isPort(s.getRow(),s.getColumn(),s.getRow()+1,s.getColumn())){

                for (int i = 1; i <= 5; i++){

                    map[5*(s.getRow()+1)][6*s.getColumn()+i] = "|";
                }
            }
        }
    }
    
    
    
    /**
     * Print Newmap.
     * @param m   the choosen for the game
    
    public void viewMap(Map m) {

        String[][] map = this.Newmap;
        //getSetMap(m.getSquares());
        //putGenerationSquare(m);
        putDoors(m);
        getSetPlayersOnMap(m.getSquares());

        System.out.println("      0     1     2     3");

        for(int i = 0; i < 15; i++){

            if(i == 3 || i == 8 || i == 13){

                System.out.print(" " + (i/5) + " ");
            }
            else {

                System.out.print("   ");
            }
            for(int j = 0; j < 25; j++){

                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }
    */
}