package it.polimi.model;

import java.util.ArrayList;

public class Map {

    private ArrayList<Square> squares;

    public Square searchSquare (int row, int colomn) {
        for (Square s : squares) {
            if ((s.row == row) && (s.column == colomn)) {
                return s;
            }
        }
        return null;
    }
    public Square findPlayer(Player player) {
        for (Square s : squares) {
            if (s.players.contains(player)) {
                return s;
            }
        }
        return null;

    }
    public ArrayList playersOnSquare(Square s) {
        for (Square a:squares){
            a.equals(s);
            return s.players;
        }
        return null;
    }


    public int distance(int r0, int c0,int r1, int c1) {

        int distance=0;
        Square sourceSquare=this.searchSquare(r0,c0);
        Square targetSquare=this.searchSquare(r1,c1);

        for (Square s:squares)
            for(Square b :sourceSquare.link){
                if (b.equals(targetSquare)){
                    distance++;
                    return distance;
                } else {
                    //vedere che cazzo fare
                }
            }
        return 0;
        }





    public boolean isVisible(int row, int column) {

        return true;

        //  TODO funzione di visibiltà

    }

    public void movePlayer(Player player, Square square) {
        if(this.distance(this.findPlayer(player).row,this.findPlayer(player).column,square.row,square.column)<=3){
            findPlayer(player).removePlayer(player);
            square.addPlayer(player);
            //TODO
            // vedere bene come fa le eccezioni
        }
    }

    public void addPlayerOnSquare(Square square, Player player) {
        searchSquare(square.row,square.column).addPlayer(player);
    }

    public void removePlayerFromSquare(Square square, Player player) {
        searchSquare(square.row,square.column).removePlayer(player);
    }

}
