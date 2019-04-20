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


    public int distance(int r0, int c0, int rd, int cd, int dist) {
        int distance = dist;
        int currR = r0;
        int curC = c0;

        Square curSquare = searchSquare(currR, curC);
        ArrayList<Square> link = curSquare.getLink();
        for (Square v : link) {
            System.out.println("link: " + v.toString());
        }

        for (Square a : link) {
            if(a!=null && a.getColumn()== cd && a.getRow()==rd){
                System.out.println("trovato");
                return distance;
            }
        }
        curSquare.setVisited(true); //è stat visitata
        for (Square b:link){
            if(b!=null && !b.isVisited()){
                System.out.println("iter su: " +b.toString());
                int tempDist=distance+1;
                return distance(b.getRow(),b.getColumn(),rd,cd,tempDist);
            }

        }
        return distance;
    }





    public boolean isVisible(int row, int column) {

        return true;

        //  TODO funzione di visibiltà

    }

    public void movePlayer(Player player, Square square) {
        if(this.distance(this.findPlayer(player).row,this.findPlayer(player).column,square.row,square.column,1)<=3){
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
