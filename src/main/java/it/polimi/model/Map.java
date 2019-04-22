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


    public int distance(int rInit, int cInit, int rDest, int cDest, int dist) {

        int distance = dist;
        int currR = rInit;
        int curC = cInit;
        int tempPath=0;
        ArrayList<Integer> path =new ArrayList<>();

        Square curSquare = searchSquare(currR, curC);
        Square destSquare = searchSquare(rDest, cDest);
        ArrayList<Square> link = curSquare.getLink();

        for (Square a : link) {
            if(a!=null && a.getColumn()== cDest && a.getRow()==rDest){
                path.add(distance);
                return calculateMinPath(path);
            }
        }
        curSquare.setVisited(true);
        for (Square b : link) {
            if (b != null && !b.isVisited()) {
                int tempDist = distance + 1;
                tempPath = distance(b.getRow(), b.getColumn(), rDest, cDest, tempDist);
                path.add(tempPath);
            }
        }
        return calculateMinPath(path);
    }

    private int calculateMinPath(ArrayList<Integer> path){

        int min=100;
        for (Integer a:path){
            if (a <min){
                min= a;
            }
        }
        return min;
    }

    public boolean isVisible(int r0, int c0,int r1, int c1) {

        Square currSquare=searchSquare(r0,c0);
        Square destSquare=searchSquare(r1,c1);
        if(currSquare.getColor()==destSquare.getColor()){
            return true;
        }else return destSquare.getLink().contains(currSquare);
    }

    public void movePlayer(Player player, Square square) {
        if(this.distance(this.findPlayer(player).row,this.findPlayer(player).column,square.row,square.column,1)<=3){
            findPlayer(player).removePlayer(player);
            square.addPlayer(player);
        }
    }

    public void addPlayerOnSquare(Square square, Player player) {
        searchSquare(square.row,square.column).addPlayer(player);
    }

    public void removePlayerFromSquare(Square square, Player player) {
        searchSquare(square.row,square.column).removePlayer(player);
    }

}
