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
    public  final String ANSI_BLACK = "\u001B[30m";
    public  final String ANSI_RESET = "\u001b[0m";
    public ArrayList<ArrayList<ArrayList<ArrayList<String>>>> mappa = new ArrayList<ArrayList<ArrayList<ArrayList<String>>>>();
    GameModel gameModel;
    
    
    public  CLIPrintMap(GameModel gameModel){

        this.gameModel = gameModel;
    }

    /**
     * Create the map for the view like an array of array of array of array: set in right position
     * the color background, the players (initial letter), the doors, a sign corresponing to the generation
     * square and the string ammo
     * @param s     a square of the map
     */
    public ArrayList<ArrayList<String >> createSquareBG (Square s){
        
        ArrayList<ArrayList<String >> squares = new ArrayList<ArrayList<String>>();
        String colorToWrite = createStringColor(s.getColor());
        String black = ANSI_BLACK_BACKGROUND + " ";
        String hDoors= "-";
        String vDoors= "|";
        //for ammo and gen
        boolean checkWeapon=false;
        boolean checkAmmo=false;
        if (s.getClass().equals(GenerationSquare.class)) {
            
            GenerationSquare gen = (GenerationSquare) s;
             checkWeapon = gen.getWeaponList().size()>0;
        } else if (s.getClass().equals(NormalSquare.class)) {
            
            NormalSquare nor = (NormalSquare) s;
             checkAmmo = nor.containAmmoCard();
        }
       
        int row;
        int column;
        boolean check1;
        boolean check2;
        // H door ⇄
        // V door ⇅

        //create row
        for (row = 0; row < 11; row++){

            squares.add(new ArrayList<>());
        }
        
        //fill row with array list size 11 (column)
        for (row = 0; row < 11; row++){

            for (column = 0; column < 11; column++){
                
                if(((column < 3) || (column > 7 )) || ((row < 3) || (row > 7))){

                    check1 = false;
                    //put vertical door (top)
                    if(row == 1 && (column > 3 && column < 7)){

                        for(Square sq : s.getLink()){

                            if (sq.getRow() == s.getRow()-1){

                                squares.get(row).add(vDoors);
                                check1 = true;
                            }
                        }
                    }

                    //put vertical door (bottom)
                    if(row == 9 && (column > 3 && column < 7)){

                        for(Square sq : s.getLink()){

                            if (sq.getRow() == s.getRow()+1){

                                squares.get(row).add(vDoors);
                                check1 = true;
                            }
                        }
                    }

                    //put horizontal door (left)
                    if((row > 3 && row < 7) && column == 1){

                        for(Square sq : s.getLink()){

                            if (sq.getColumn() == s.getColumn()-1){

                                squares.get(row).add(hDoors);
                                check1 = true;
                            }
                        }
                    }

                    //put horizontal door (right)
                    if((row > 3 && row < 7) && column == 9){

                        for(Square sq : s.getLink()){

                            if (sq.getColumn() == s.getColumn()+1){

                                squares.get(row).add(hDoors);
                                check1 = true;
                            }
                        }
                    }

                    if (!check1){

                        //black border limit
                        squares.get(row).add(black);
                    }

                } else {

                    check2 = false;
                    //put player
                    if(s.getPlayers().size() == 1){

                        if(row == 5 && column == 5){

                            squares.get(row).add(colorString(s.getPlayers().get(0), s.getColor()));
                            check2 = true;
                        }
                    }

                    if(s.getPlayers().size() == 2){

                        if(row == 5 && column == 4){

                            squares.get(row).add(colorString(s.getPlayers().get(0), s.getColor()));
                            check2 = true;
                        }
                        if(row == 5 && column == 6){

                            squares.get(row).add(colorString(s.getPlayers().get(1), s.getColor()));
                            check2 = true;
                        }
                    }

                    if(s.getPlayers().size() == 3){

                        if(row == 4 && column == 5){

                            squares.get(row).add(colorString(s.getPlayers().get(0), s.getColor()));
                            check2 = true;
                        }
                        if(row == 6 && column == 4){

                            squares.get(row).add(colorString(s.getPlayers().get(1), s.getColor()));
                            check2 = true;
                        }
                        if(row == 6 && column == 6){

                            squares.get(row).add(colorString(s.getPlayers().get(2), s.getColor()));
                            check2 = true;
                        }
                    }

                    if(s.getPlayers().size() == 4){

                        if(row == 4 && column == 5){

                            squares.get(row).add(colorString(s.getPlayers().get(0), s.getColor()));
                            check2 = true;
                        }
                        if(row == 5 && column == 4){

                            squares.get(row).add(colorString(s.getPlayers().get(1), s.getColor()));
                            check2 = true;
                        }
                        if(row == 5 && column == 6){

                            squares.get(row).add(colorString(s.getPlayers().get(2), s.getColor()));
                            check2 = true;
                        }
                        if(row == 6 && column == 5){

                            squares.get(row).add(colorString(s.getPlayers().get(3), s.getColor()));
                            check2 = true;
                        }
                    }

                    if(s.getPlayers().size() == 5){

                        if(row == 4 && column == 5){

                            squares.get(row).add(colorString(s.getPlayers().get(0), s.getColor()));
                            check2 = true;
                        }
                        if(row == 5 && column == 4){

                            squares.get(row).add(colorString(s.getPlayers().get(1), s.getColor()));
                            check2 = true;
                        }
                        if(row == 5 && column == 6){

                            squares.get(row).add(colorString(s.getPlayers().get(2), s.getColor()));
                            check2 = true;
                        }
                        if(row == 6 && column == 5){

                            squares.get(row).add(colorString(s.getPlayers().get(3), s.getColor()));
                            check2 = true;
                        }
                        if(row == 5 && column == 5){

                            squares.get(row).add(colorString(s.getPlayers().get(4), s.getColor()));
                            check2 = true;
                        }
                    }

                    //put a string in right position for generation square
                    if (s.getClass().equals(GenerationSquare.class)){
                       
                        switch (s.getColor()){

                            case BLU:
                                if(row == 3){
                                    
                                    if (checkWeapon) {
                                        
                                        squares.get(row).add(colorStringAmmo(s.getColor(), "-"));
                                        check2 = true;
                                    }
                                }
                                break;
                            case RED:
                                if(column == 3){
                                    
                                    if (checkWeapon) {
                                        
                                        squares.get(row).add(colorStringAmmo(s.getColor(), "|"));
                                        check2 = true;
                                    }
                                }
                                break;
                            case YELLOW:
                                if(column == 7){
                                    
                                    if (checkWeapon) {
                                        
                                        squares.get(row).add(colorStringAmmo(s.getColor(), "|"));
                                        check2 = true;
                                    }
                                }
                                break;
                        }

                    }else {
                       
                        
                        //put string ammo in right position
                        if (row == 3 && column == 3){
    
                            if (checkAmmo) {
                                
                                squares.get(row).add(colorStringAmmo(s.getColor(), "a"));
                                check2 = true;
                            }
                        }
                        if (row == 3 && column == 4) {
    
                            if (checkAmmo) {
                                
                                squares.get(row).add(colorStringAmmo(s.getColor(), "m"));
                                check2 = true;
                            }
                        }
                        if (row == 3 && column == 5){
    
                            if (checkAmmo) {
    
    
                                squares.get(row).add(colorStringAmmo(s.getColor(), "m"));
                                check2 = true;
                            }
                        }
                        if (row == 3 && column == 6){
                            
                        
                            if (checkAmmo) {
    
                                squares.get(row).add(colorStringAmmo(s.getColor(), "o"));
                                check2 = true;
                            }
                        }
                    }
                    if(!check2){

                        //color
                        squares.get(row).add(colorToWrite);
                    }
                }
            }
        }
        return squares;
    }

    /**
     * Create a black square
     */
    public ArrayList<ArrayList<String >> createBlackSquare (){
        
        ArrayList<ArrayList<String >> blackSquares = new ArrayList<ArrayList<String>>();
        String black = ANSI_BLACK_BACKGROUND + " ";
        int row;
        int column;
    
        //create row
        for (row = 0; row < 11; row++){

            blackSquares.add(new ArrayList<>());
        }
        
        //fill row with array list size 11 (column)
        
        for (row = 0; row < 11; row++) {

            for (column = 0; column < 11; column++) {
                //all black square
                blackSquares.get(row).add(new String(black));
            }
        }
        return blackSquares;
    }

    /**
     * create the grid for the map
     */
    public void createGrid(){
    
        ArrayList<ArrayList<ArrayList<ArrayList<String>>>> mappa = this.mappa;
    
        for (int i = 0; i < 3; i++){

            mappa.add(new ArrayList<>());
        }
    
        try {

        for (int row = 0; row < 3; row++) {

            for (int column = 0; column < 4; column++) {

                if (gameModel.getMap().existInMap(row, column)) {
                    
                    mappa.get(row).add(createSquareBG(gameModel.getMap().getSquare(row, column)));
                } else {
        
                    mappa.get(row).add(createBlackSquare());
        
                }
    
            }
        }
        } catch (MapException e) {

            e.printStackTrace();
        }
    }

    /**
     * print the map
     */
    public void printGrid() {

        printLegendMap(gameModel.getPlayers(true));

        for (int sqRow = 0; sqRow < 3; sqRow++){

            for (int intRow = 0; intRow < 11; intRow++) {

                for (int sqCol = 0; sqCol < 4; sqCol++) {

                    for (int intCol = 0; intCol < 11; intCol++) {

                        System.out.print(mappa.get(sqRow).get(sqCol).get(intRow).get(intCol));
                    }
                }
                System.out.println();
            }
        }
    }

    /**
     * print legend of the map
     */
    public void printLegendMap(ArrayList<Player> players){

        System.out.println();
        System.out.println("LEGEND");
        System.out.println("--------------------------------------------------------");
        System.out.println();
        System.out.println("ON A NORMAL SQUARE YOU CAN GRAB AN AMMO CARD:");
        System.out.println("ON A GENERATIONS SQUARE YOU CAN GRAB A WEAPON CARD");
        System.out.println("PLAYERS:");
        printLegendPlayers(players);
        System.out.println();
        System.out.println("'ammo' MEANS THAT THERE IS AN AMMO CARD ON THAT SQUARE");
        System.out.println("- BETWEEN TWO SQUARES MEANS THAT THERE IS A DOOR");
        System.out.println("| BETWEEN TWO SQUARES MEANS THAT THERE IS A DOOR");
        System.out.println("| ON A SQUARE MEANS THAT THAT IS A GENERATIONS SQUARE");
        System.out.println();
        System.out.println("--------------------------------------------------------");
        System.out.println();
    }

    /**
     * print legend of players
     */
    public void printLegendPlayers(ArrayList<Player> players){

        for (Player p : players){

            switch (p.getColor()){
                case BLU:
                    System.out.println("\tB = " + p.getName());
                    break;
                case GREEN:
                    System.out.println("\tG = " + p.getName());
                    break;
                case GREY:
                    System.out.println("\tW = " + p.getName());
                    break;
                case PINK:
                    System.out.println("\tP = " + p.getName());
                    break;
                case YELLOW:
                    System.out.println("\tY = " + p.getName());
                    break;
                default:
                     break;
            }
        }
    }

    /**
     * create and print the map
     */
    public void viewMapNew() {
        
        createGrid();
        printGrid();
    }

    /**
     * Set the right string color for background
     * @param colorSquare           the color of square
     */
    public String createStringColor(EnumColorSquare colorSquare){
    
        String string;

        switch (colorSquare) {

            case BLU:
                string = ANSI_BLUE_BACKGROUND + " " ;
                break;
            case GREEN:
                string = ANSI_GREEN_BACKGROUND + " ";
                break;
            case PINK:
                string = ANSI_PURPLE_BACKGROUND + " ";
                break;
            case RED:
                string= ANSI_RED_BACKGROUND + " ";
                break;
            case YELLOW:
                string= ANSI_YELLOW_BACKGROUND + " ";
                break;
            case WHITE:
                string= ANSI_WHITE_BACKGROUND + " ";
                break;
            default:
                string = ANSI_BLACK_BACKGROUND + " ";
                break;
        }
        return string;
    }

    /**
     * Set the right string to view for CLI to the corresponding color
     * @param c           the color of square
     * @param letter      a letter of string 'ammo'
     */
    public String colorStringAmmo(EnumColorSquare c, String letter){

        String s = "";

        switch (c){

            case BLU:
                s = ANSI_BLUE_BACKGROUND + letter + ANSI_BLACK_BACKGROUND;
                break;
            case GREEN:
                s = ANSI_GREEN_BACKGROUND + letter + ANSI_BLACK_BACKGROUND;
                break;
            case PINK:
                s = ANSI_PURPLE_BACKGROUND + letter + ANSI_BLACK_BACKGROUND;
                break;
            case RED:
                s = ANSI_RED_BACKGROUND + letter + ANSI_BLACK_BACKGROUND;
                break;
            case WHITE:
                s = ANSI_WHITE_BACKGROUND + ANSI_BLACK + letter + ANSI_RESET + ANSI_BLACK_BACKGROUND;
                break;
            case YELLOW:
                s = ANSI_YELLOW_BACKGROUND + ANSI_BLACK + letter + ANSI_RESET + ANSI_BLACK_BACKGROUND;
                break;
            default:
                break;
        }
        return s;
    }
   
    /**
     * Set the right string to view for CLI to the corresponding color
     * @param player      the selected player
     * @param c           the color of square
     */
    public String colorString(Player player, EnumColorSquare c){

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

      return colorStringAmmo(c, colorPlayer);
    }
}

