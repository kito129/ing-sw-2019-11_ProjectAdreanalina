package it.polimi.model;

import java.util.ArrayList;

public class Map {

    private ArrayList<Square> squares;

    public Map(ArrayList<Square> squares) {

        ArrayList<Square> temp = new ArrayList<>();
        if (squares != null) {

            for (Square l : squares) {

                if (l != null)

                    temp.add(l);
                System.out.println("aggiungo "+ l.toString());
            }
            this.squares=temp;
        }
        System.out.println("\ncreo la mappa\n");
    }

    public Square searchSquare (int row, int colomn) {

        for (Square s : squares) {

            if ((s.getRow() == row) && (s.getColumn() == colomn)) {

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

    public int distance(Square a, Square b){

        return distance(a.getRow(),a.getColumn(),b.getRow(),b.getColumn(),1);
    }

    public int distance(int rInit, int cInit, int rDest, int cDest, int dist) {

        int distance = dist;
        int currR = rInit;
        int curC = cInit;
        int tempPath=0;
        ArrayList<Integer> path =new ArrayList<>();

        Square curSquare = searchSquare(currR, curC);
        System.out.println("curr square : " + curSquare.toString());
        Square destSquare = searchSquare(rDest, cDest);
        System.out.println("search square : " + destSquare.toString());
        ArrayList<Square> link = curSquare.getLink();
        for (Square a : link) {

            if(a!=null && a.getColumn()== cDest && a.getRow()==rDest){

                path.add(distance);
                System.out.println("trovato, dist: "+distance);
                return calculateMinPath(path);
            }
        }
        curSquare.setVisited(true);
        for (Square b : link) {

            if (b != null && !b.isVisited()) {

                System.out.println("iter su: " + b.toString());
                int tempDist = distance + 1;
                tempPath = distance(b.getRow(), b.getColumn(), rDest, cDest, tempDist);
                path.add(tempPath);
            }
        }
        return calculateMinPath(path);
    }

    public void refreshMap(){

        for (Square a:squares){

            a.visited=false;
        }
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

    

    public boolean isVisible(Square a, Square b) {

        return isVisible(a.getRow(),a.getColumn(),b.getRow(),b.getColumn());
    }


    public boolean isVisible(int r0, int c0,int r1, int c1) {

        Square currSquare=searchSquare(r0,c0);
        Square destSquare=searchSquare(r1,c1);
        if(currSquare.getColor()==destSquare.getColor()){

            return true;
        }else {

            for (Square a:currSquare.getLink()){

                if(a.getColor()==destSquare.getColor()) return true;
            }
        }
        return false;
    }

    public void movePlayer(Player player, Square square) {

        if(this.distance(this.findPlayer(player).getRow(),this.findPlayer(player).getColumn(),square.getRow(),square.getColumn(),1)<=3){

            findPlayer(player).removePlayer(player);
            square.addPlayer(player);
            player.setPosition(square.getRow(),square.getColumn());
        }
    }

    public void addPlayerOnSquare(Square square, Player player) {

        searchSquare(square.getRow(),square.getColumn()).addPlayer(player);
    }

    public void removePlayerFromSquare(Square square, Player player) {

        searchSquare(square.getRow(),square.getColumn()).removePlayer(player);
    }

    public boolean sameDirection(Square a, Square b){

        return a.getRow() == b.getRow() || a.getColumn() == b.getColumn();
    }

    public boolean isInMySquare(Player actualPlayer,Player otherPlayer){
        return this.playersOnSquare(this.searchSquare(actualPlayer.getRow(), actualPlayer.getColumn())).contains(otherPlayer);
    }

    public ArrayList<Player> playerInRoom(EnumColorSquare colorSquare){

        ArrayList<Player> tempPlayer =new ArrayList<>();
        for (Square a: this.squares){

            if(a.getColor()==colorSquare){

                tempPlayer.addAll(a.playerOnSquare());
            }
        } return tempPlayer;
    }

}
