package it.polimi.model;

import java.util.ArrayList;

public class Main {

    public static void main(String args[]) {

        PlayerBoard playerBoard= new PlayerBoard();

        System.out.println(playerBoard.getAmmo());
        System.out.println(playerBoard.getAmmoR());
        System.out.println(playerBoard.getAmmoB());
        System.out.println(playerBoard.getAmmoY());
        System.out.println(playerBoard.getBoardValue());
        System.out.println(playerBoard.getNumberOfDeaths());
        System.out.println(playerBoard.getDamages());
        System.out.println(playerBoard.getMarks());
        System.out.println(playerBoard.getPlayerPowerUps());
        System.out.println(playerBoard.getPlayerWeapons());
        System.out.println();


        PowerUpCard powerUpCard= new PowerUpCard("prova",EnumColorCardAndAmmo.BLU);
        System.out.println(powerUpCard.getDescription());
        System.out.println(powerUpCard.getNameCard());
        System.out.println(powerUpCard.getColorPowerUpCard());
        System.out.println();


        AmmoCard ammoCard=new AmmoCard(1,1,1,powerUpCard);
        System.out.println(ammoCard.getAmmo());
        System.out.println((ammoCard.getPowerUpCard()));
        System.out.println();

        playerBoard.manageAmmoCard(ammoCard);


        System.out.println(playerBoard.getAmmo());
        System.out.println(playerBoard.getAmmoR());
        System.out.println(playerBoard.getAmmoB());
        System.out.println(playerBoard.getAmmoY());
        System.out.println(playerBoard.getBoardValue());
        System.out.println(playerBoard.getNumberOfDeaths());
        System.out.println(playerBoard.getDamages());
        System.out.println(playerBoard.getMarks());
        System.out.println(playerBoard.getPlayerPowerUps());
        System.out.println(playerBoard.getPlayerWeapons());
        System.out.println();

        ArrayList<EnumColorCardAndAmmo> ammoToDecrease= new ArrayList<>();
        ammoToDecrease.add(EnumColorCardAndAmmo.YELLOW);
        ammoToDecrease.add(EnumColorCardAndAmmo.BLU);
        ammoToDecrease.add(EnumColorCardAndAmmo.RED);
        playerBoard.decreaseAmmo(ammoToDecrease);

        System.out.println(playerBoard.getAmmo());
        System.out.println(playerBoard.getAmmoR());
        System.out.println(playerBoard.getAmmoB());
        System.out.println(playerBoard.getAmmoY());
        System.out.println(playerBoard.getBoardValue());
        System.out.println(playerBoard.getNumberOfDeaths());
        System.out.println(playerBoard.getDamages());
        System.out.println(playerBoard.getMarks());
        System.out.println(playerBoard.getPlayerPowerUps());
        System.out.println(playerBoard.getPlayerWeapons());
        System.out.println();

        //todo testare da qui.











    }

}