package it.polimi.model;

import it.polimi.model.PowerUp.Newton;

import java.util.ArrayList;

public class Main {

    public static void main(String args[]) {

        PlayerBoard playerBoard= new PlayerBoard();
        ArrayList<EnumColorPlayer> markToAdd=new ArrayList<>();



        markToAdd.add(EnumColorPlayer.YELLOW);
        markToAdd.add(EnumColorPlayer.YELLOW);

        System.out.println("marchi del giocatore iniziali "+playerBoard.getMarks());

        System.out.println("marchi da aggiungere "+markToAdd);

        playerBoard.increaseMarks(markToAdd);

        System.out.println("marchi del giocatore finale "+playerBoard.getMarks());











    }

}