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
        ArrayList<Integer> path =new ArrayList<>();
        int minPath =100;
        int tempPath;

        Square curSquare = searchSquare(currR, curC);
        System.out.println("square curr: " + curSquare.toString());

        Square destSquare = searchSquare(rd, cd);
        System.out.println("square search: " + destSquare.toString());
        ArrayList<Square> link = curSquare.getLink();

        for (Square v : link) {
            System.out.println("link: " + v.toString());
        }

        for (Square a : link) {
            if(a!=null && a.getColumn()== cd && a.getRow()==rd){
                System.out.println("trovato, distanza: " +distance);
                path.add(distance);
                return calculateMinPath(path);
            }
        }
        curSquare.setVisited(true);

        for (Square b : link) {
            if (b != null && !b.isVisited()) {
                System.out.println("iter su: " + b.toString());
                int tempDist = distance + 1;
                tempPath = distance(b.getRow(), b.getColumn(), rd, cd, tempDist);
                path.add(tempPath);
            }
        }
        System.out.println("non trovato, distanza: " +minPath);
        return calculateMinPath(path);
    }

    private int calculateMinPath(ArrayList<Integer> path){
        int min=100;
        for (Integer a:path){
            if (a.intValue()<min){
                min=a.intValue();
            }
        }
        return min;
    }

    public boolean isVisible(int r0, int c0,int r1, int c1) {
        Square currSquare=searchSquare(r0,c0);
        Square destSquare=searchSquare(r1,c1);

        if(currSquare.getColor()==destSquare.getColor()){
            return true;
        }else if(distance(r0,c0,r1,c1,1)==1 && destSquare.getLink().contains(currSquare)){
            return true;
        } else {
            return false;
        }

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
