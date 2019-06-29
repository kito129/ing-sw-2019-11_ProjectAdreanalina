package it.polimi.model;

import java.util.ArrayList;

public class Prova {

    public static void main(String args[]){


        PlayerBoard playerBoard=new PlayerBoard();

        System.out.println(playerBoard.getMarks());

        ArrayList<EnumColorPlayer> ma=new ArrayList<>();
        ma.add(EnumColorPlayer.PINK);
        ma.add(EnumColorPlayer.PINK);
        ma.add(EnumColorPlayer.PINK);
        ma.add(EnumColorPlayer.PINK);
        playerBoard.increaseMarks(ma);
        System.out.println(playerBoard.getMarks());


        ArrayList<EnumColorPlayer> ma1=new ArrayList<>();
        ma1.add(EnumColorPlayer.BLU);
        ma1.add(EnumColorPlayer.BLU);
        playerBoard.increaseMarks(ma1);

        System.out.println(playerBoard.getMarks());


    }
}
