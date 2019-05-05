package it.polimi.model;


import java.util.ArrayList;

public class Main {

    public static void main(String args[]) {

        PlayerBoard playerBoard= new PlayerBoard();

        ArrayList<EnumColorPlayer> list = new ArrayList<>();
        list.add(EnumColorPlayer.YELLOW);
        list.add(EnumColorPlayer.YELLOW);
        list.add(EnumColorPlayer.BLU);

        playerBoard.increaseMarks(list);

        System.out.println(playerBoard.getMarks());
        playerBoard.removeMarkOfColor1(EnumColorPlayer.YELLOW);
        System.out.println(playerBoard.getMarks());

        PowerUpCard powerUpCard=new PowerUpCard("bella",EnumColorCardAndAmmo.YELLOW);













    }

}