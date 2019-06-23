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
    public ArrayList<ArrayList<ArrayList<ArrayList<String>>>> map;
    String [][] toStamp;
    
    
    GameModel gameModel;
    
    
    public  CLIPrintMap(GameModel gameModel){

        this.gameModel = gameModel;
        this.map = new ArrayList<ArrayList<ArrayList<ArrayList<String>>>>();
        toStamp = new String[44][33];

    }
    
    public ArrayList<ArrayList<String >> createSquareBG (Square s){
        
        ArrayList<ArrayList<String >> squares = new ArrayList<ArrayList<String>>();
        String colorToWrite = createStringColor(s.getColor());
        String black = ANSI_BLACK_BACKGROUND + " ";
        int row;
        int column;
    
        //create row
        for (row =0;row<11;row++){
            squares.add(new ArrayList<>());
        }
        
        //fill row with array list size 11 (column)
        
        for (row =0;row<11;row++){
            for (column =0;column<11;column++){
                
                if(((column>-1 && column<3) || (column>7 )) || (((row>-1 && row<3) || (row>7)))){
                    //black border limit
                    squares.get(row).add(new String(black));
                } else {
                    //color
                    squares.get(row).add(new String(colorToWrite));
                }
            }
        }
        return squares;
    }
    
    public ArrayList<ArrayList<String >> createBlackSquare (){
        
        ArrayList<ArrayList<String >> blackSquares = new ArrayList<ArrayList<String>>();
        String black = ANSI_BLACK_BACKGROUND + " ";
        int row;
        int column;
    
        //create row
        for (row =0;row<11;row++){
            blackSquares.add(new ArrayList<>());
        }
        
        //fill row with array list size 11 (column)
        
        for (row =0;row<11;row++) {
            for (column = 0; column < 11; column++) {
                //all black square
                blackSquares.get(row).add(new String(black));
            }
        }
        return blackSquares;
    }
    
    
    public void createGrid(){
    
        ArrayList<ArrayList<ArrayList<ArrayList<String>>>> map = this.map;
    
        for (int i =0;i<3;i++){
            map.add(new ArrayList<>());
        }
    
        try {
        for (int row =0;row<3;row++) {
            for (int column = 0; column < 4; column++) {
                if (gameModel.getMap().existInMap(row, column)) {
                    
                    map.get(row).add(createSquareBG(gameModel.getMap().getSquare(row, column)));
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
    
        System.out.println(map.size());
        System.out.println(map.get(0).size());
        System.out.println(map.get(0).get(0).size());
        System.out.println(map.get(0).get(0).get(0).size());
        int row;
        int column;
        int extRow;
        int extColumn;
        int max;
        ArrayList<ArrayList<ArrayList<String>>> temp1 = new ArrayList<>();
        ArrayList<ArrayList<String>> temp2 = new ArrayList<>();
        ArrayList<String> temp3 = new ArrayList<>();
    
        for (ArrayList<ArrayList<ArrayList<String>>> lists : map) {
            temp1.addAll(lists);
        }
    
        for (ArrayList<ArrayList<String>> arrayLists : temp1) {
        
            temp2.addAll(arrayLists);
        }
        max=44;
        for (int j=0;j<11;j++) {
            for (int c=j;c<44+(j*2);c=c+10){
                
                temp3.addAll(temp2.get(c));
                
                
            }
        }
        
        System.out.println();
        
        for (int fin =0;fin<temp3.size();fin++){
            if (fin%176==0){
                System.out.println();
            } else {
                System.out.print(temp3.get(fin));
            }
        }
        System.out.println();
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
}

